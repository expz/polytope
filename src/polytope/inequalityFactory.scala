package polytope

import scala.collection.mutable.HashMap
import scala.collection.mutable.HashSet
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.ArrayBuffer

/** Factory for data related to marginal inequalities including cubicles, 
  * extremal edges, product flag coefficients, polytope inequalities and
  * polytope vertices.
  *  
  * Naming convention for methods
  * Type of particles:
  *   D = distinguishable particles
  *   F = fermions
  * Type of states:
  *   P = pure states
  *   M = mixed states
  */
object InequalityFactory {

  /** Returns ListBuffer of RectTableau corresponding to marginal cubicles for
    * mixed states of distinguishable particles
    * 
    * @param dims two positive dimensions of single-particle state spaces
    * @return a ListBuffer of all RectTableau which correspond to cubicles 
    */
  def cubiclesDM(dims: List[Int]) = 
    if (dims.length < 2) ListBuffer[RectTableau]()
    else RectTableau.standardTableaux(dims(0), dims(1)).filter(_.isAdmissible)

  /** Returns map from an extremal edge to an ArrayBuffer of cubicles containing 
    * the extremal edge
    * 
    * @param cubicles cubicles of which to compute the extremal edges
    * @return map from an extremal edge to cubicles containing it
    */ 
  def edgesDM(cubicles: ListBuffer[RectTableau]): 
    HashMap[ABEdge, ArrayBuffer[RectTableau]] = {
    val es = HashMap[ABEdge, ArrayBuffer[RectTableau]]()
    if (!cubicles.isEmpty) {
      val dims = List(cubicles(0).rows, cubicles(0).cols)
      for (T <- cubicles) {
        for (e <- T.toCone.edges(dims(0))._2) {
          if (es.contains(e)) es(e) += T
          else es(e) = ArrayBuffer(T)
        }
      }
    }
    return es
  }
  
  def potentialInequalitiesDP(edges: Iterable[ABEdge], dims: List[Int]): Int = {
    val ineqs = HashSet[List[Int]]()
    if (!dims.isEmpty) {
      val dropLength = dims.dropRight(1).product - dims.last
      for (e <- edges) {
        for (u <- PermutationFactory.shuffles(e.multA)) {
          for (v <- PermutationFactory.shuffles(e.multB)) {
            for (w <- PermutationFactory.shufflesOfGivenLength(
              e.multAB, reducedWord(u).length + reducedWord(v).length)) {
              val coeffs = (act(inverse(u), e.A).map(-_) ++
                            act(inverse(v), e.B).map(-_) ++ 
                            act(inverse(w), e.AB)).
                            dropRight(dropLength)
              ineqs += coeffs.toList
            }
          }
        }
      }
    }
    return ineqs.size
  }
  /** Returns ArrayBuffer of all nonzero product flag coefficients relevant for
    * distinguishable particles mixed polytope corresponding to the given edges
    * 
    * @param edges a map from extremal edges to cubicles which contain them
    * @return nonzero product flag coefficients
    */ 
  def coeffsDM(edges: HashMap[ABEdge, ArrayBuffer[RectTableau]]):
    ArrayBuffer[PFCoefficient] = {
    val cs = ArrayBuffer[PFCoefficient]()
    for (e <- edges.keys) {
      for (u <- PermutationFactory.shuffles(e.multA)) {
        for (v <- PermutationFactory.shuffles(e.multB)) {
          for (w <- PermutationFactory.shufflesOfGivenLength(
            e.multAB, reducedWord(u).length + reducedWord(v).length)) {
            for (T <- edges(e)) {
              // In this case, c(u,v,w,T) will be a constant
              val cc = c(u, v, w, T).getOrElse(0L, 0)
              if (cc > 0) cs += new PFCoefficient((cc, e, T, List(u, v, w)))
            }
          }
        }
      }
    }
    return cs
  }  
  
  /** Returns ArrayBuffer of all nonzero product flag coefficients for the
    * distinguishable particles mixed polytope corresponding to the given
    * cubicles
    * 
    * @param cubicles cubicles
    * @return nonzero product flag coefficients corresponding to edges of given
    * cubicles
    */ 
  def coeffsDM(cubicles: ListBuffer[RectTableau]):
    ArrayBuffer[PFCoefficient] = {
    val cs = ArrayBuffer[PFCoefficient]()
    if (!cubicles.isEmpty) {
      val dims = List(cubicles(0).rows, cubicles(0).cols)
      for (T <- cubicles) {
        for (e <- T.toCone.edges(dims(0))._2) {
          for (u <- PermutationFactory.shuffles(e.multA)) {
            for (v <- PermutationFactory.shuffles(e.multB)) {
              for (w <- PermutationFactory.shufflesOfGivenLength(
                  e.multAB, reducedWord(u).length + reducedWord(v).length)) {
                // In this case, c(u,v,w,T) will be a constant
                val cc = c(u, v, w, T).getOrElse(0L, 0)
                if (cc > 0) cs += new PFCoefficient(cc, e, T, List(u, v, w))
              }
            }
          }
        }
      }
    }
    return cs
  }  
    
  /** Returns HashSet of inequalities which are minimal for defining the
    * distinguishable particles mixed polytope and correspond to given cubicles
    * 
    * @param cubicles the tableaux corresponding to the cubicles
    * @return inequalities of the polytope corresponding to the given cubicles
    */
  def ineqsDM(cubicles: ListBuffer[RectTableau]): HashSet[InequalityDM] = {
    val ieqs = HashSet[InequalityDM]()
    if (!cubicles.isEmpty) {
      val dims = List(cubicles(0).rows, cubicles(0).cols)
      for (T <- cubicles) {
        for (edge <- T.toCone.edges(dims(0))._2) {
          for (u <- PermutationFactory.shuffles(edge.multA)) {
            for (v <- PermutationFactory.shuffles(edge.multB)) {
              for (w <- PermutationFactory.shufflesOfGivenLength(
                  edge.multAB, reducedWord(u).length + reducedWord(v).length)) {
                // In this case, c(u,v,w,T) will be a constant
                if (c(u, v, w, T).getOrElse(0L, 0) == 1) {
                  ieqs += new InequalityDM(u, v, w, edge)
                }
              }
            }
          }
        }
      }
    }
    return ieqs
  }

  /** Returns HashSet of inequalities which are minimal for defining the
    * distinguishable particles mixed polytope
    * 
    * @param dims two positive dimensions of single-particle state spaces
    * @return inequalities of the polytope
    */
  def ineqsDM(dims: List[Int]): HashSet[InequalityDM] =
    ineqsDM(cubiclesDM(dims))
          
  /** Returns HashSet of inequalities which are minimal for defining the
    * distinguishable particles mixed polytope and correspond to the given edges
    * 
    * @param edges extremal edges
    * @return (minimal) inequalities of the polytope whose coefficients are 
    * permutations of the extremal edges
    */  
  def ineqsDM(edges: HashMap[ABEdge, ArrayBuffer[RectTableau]]): 
    HashSet[InequalityDM] = {
    val ieqs = HashSet[InequalityDM]()
    
    for (edge <- edges.keys) {
      for (u <- PermutationFactory.shuffles(edge.multA)) {
        for (v <- PermutationFactory.shuffles(edge.multB)) {
          for (w <- PermutationFactory.shufflesOfGivenLength(
               edge.multAB, reducedWord(u).length + reducedWord(v).length)) {
            for (T <- edges(edge)) {            
              if (c(u, v, w, T).getOrElse(0L, 0) == 1) {
                ieqs += new InequalityDM(u, v, w, edge)
              }
            }
          }
        }
      }
    }
    return ieqs
  }

  /** Returns HashSet of inequalities corresponding to the given coefficients
    * which are equal to 1
    * 
    * @param coeffs product flag coefficients
    * @return (minimal) inequalities of the polytope corresponding to coeffs
    */  
  def ineqsDM(coeffs: ArrayBuffer[PFCoefficient]): HashSet[InequalityDM] = {
    val ieqs = HashSet[InequalityDM]()
    if (!coeffs.isEmpty) {
      val dims = List(coeffs(0).edge.dimA, coeffs(0).edge.dimB)
      val filteredCoeffs = HashSet[List[Int]]()
      for (c <- coeffs.filter(_.value == 1)) {
        val v = act(inverse(c.perms(0)), c.edge.A).map(-_) ++
                act(inverse(c.perms(1)), c.edge.B).map(-_) ++ 
                act(inverse(c.perms(2)), c.edge.AB)
        val vl = v.toList
        if(!filteredCoeffs.contains(vl)) {
          filteredCoeffs += vl
          ieqs += new InequalityDM(v, dims)
        }
      }
    }
    return ieqs
  }

  def ineqsDP(dims: List[Int]): HashSet[InequalityDP] = {
    // Calculate constant arising from changing trace from 0 to dimension
    /*
    def renorm(coeffs: Array[Int], dims: List[Int]): Array[Int] = {
      def gcd(a: Int, b: Int): Int = if (b == 0) a.abs else gcd(b, a%b)
      def lcm(a: Int, b: Int) = (a*b).abs/gcd(a,b)      
      val tr = dims.fold(1)((m, n) => lcm(m,n))
      for (d <- dims) {
        var i = 0
        while (i < d) {
          const += (coeffs(sumOfDims+i) % tr)
          i += 1
        }
      }
    }
    * 
    */
    if (dims.length == 0) return HashSet[InequalityDP]()
    val productDim = dims.dropRight(1).product
    assert(dims.last <= productDim)
    
    val mixedIneqs = ineqsDM(dims.dropRight(1))
    return mixedIneqs.map(
      ineq => new InequalityDP(
        ineq.coeffs.dropRight(productDim - dims.last),
        dims,
        ineq.const))
    
        
  }
  
  // Only works with exactly three dimensions
  def ineqsDP(cubicles: ListBuffer[RectTableau], dims: List[Int]): 
    HashSet[InequalityDP] = {
    if (cubicles.length == 0) return HashSet[InequalityDP]()
    val productDim = dims.dropRight(1).product
    assert(dims.last <= productDim)
    
    val mixedIneqs = ineqsDM(cubicles)
    
    return mixedIneqs.map(
      ineq => new InequalityDP(
        ineq.coeffs.dropRight(productDim - dims.last),
        dims, 
        ineq.const))    
  }
  
  def c(u: Permutation, v: Permutation, 
        w: Permutation, T: RectTableau): HashMap[Long, Int] = 
  {
    // requires knowledge of tableau for substituting vars
    val f = delta(u,
                  subst(collectTerms(SchubertFactory.schubertPolynomial(w)), T),
                  0)
    /*
    // Remove terms which only have y variables, because delta will zero them
    // anyway. Saves time?
    for(term <- f.keys) {
      if ((term & ~((1L << T.rows*4) - 1L)) == 0L) f.remove(term)
    }
    */
    val f2 = delta(v, f, T.rows)
    return f2
  }
}

// coeffs*vars >= c
class Inequality(val coeffs: Array[Int], val const: Int = 0) {
  override def hashCode() = (coeffs.deep, const).hashCode()
  
  override def equals(a: Any) = a match {
    case ieq: Inequality => ieq.hashCode() == this.hashCode()
    case _ => false
  }
  
  def toArray(): Array[Int] = coeffs
}

// coeffs*vars >= c
class MarginalInequality(coeffs: Array[Int], val dims: List[Int], const: Int)
  extends Inequality(coeffs, const) {
  
  def varnames = 
    Array.tabulate[String](dims.length)(i => ('a' + i).toChar.toString)

  def latexVarnames = varnames
  
  def csvHeaders(): String = {
    val str = new StringBuilder("")
    var varnum = 0
    for (dim <- dims) {
      str ++= (1 to dim).map(i => varnames(varnum) + i).mkString(",")
      if (varnum < dims.length - 1) str += ','
      varnum += 1
    }
    str ++= ",const"
    return str.result
  }
  
  def toCSV(): String = coeffs.mkString(",") + "," + const
    
  def toLatex(): String = { 
    def format(coeff: Int, term: String): String = {
      if (coeff == 0) ""
      else if (coeff == 1) term
      else if (coeff == -1) "-" + term
      else coeff.toString() + term
    }
    // WARNING: The argument must be a string (double quotes) for this to work
    val b = new StringBuilder("$")
    
    var sumOfDims = 0
    var d = 0
    while (d < dims.length) {
      var i = 0
      while (i < dims(d)) {
        if (b.length == 1) {
          b ++= format(coeffs(sumOfDims+i), 
                       latexVarnames(d) + "_{" + (i+1) + "}")
        } else if (coeffs(sumOfDims+i) < 0) {
          b ++= " - " + format(-coeffs(sumOfDims+i), 
                               latexVarnames(d) + "_{" + (i+1) + "}")
        } else if (coeffs(sumOfDims+i) > 0) {
          b ++= " + " + format(coeffs(sumOfDims+i), 
                               latexVarnames(d) + "_{" + (i+1) + "}") 
        }
        i += 1
      }
      sumOfDims += dims(d)
      d += 1      
    }    
    // If the coefficients were all zero, then put a zero on the left side
    if (b.length == 1) b += '0'
    
    // Close off the LaTeX and return
    b ++= " \\geq " + const + "$"
    return b.result
  }  
}

class InequalityDP(coeffs: Array[Int], dims: List[Int], const: Int = 0)
  extends MarginalInequality(coeffs, dims, const) {
  
  override def varnames = Array.tabulate[String](dims.length)(
    i => "lambda" + ('A' + i).toChar)
    
  override def latexVarnames = Array.tabulate[String](dims.length)(
    i => "\\lambda^" + ('A' + i).toChar)
}

// a_1 l^A_u(1) + .. + b(1) l^B_v(1) + .. + ab(1) l^{ab}_w(1) >= 0 
class InequalityDM(coeffs: Array[Int], dims: List[Int], const: Int = 0) 
  extends MarginalInequality(coeffs, dims, const) {
  def this(u: Permutation, v: Permutation, w: Permutation, e: ABEdge) = 
    this(act(inverse(u), e.A).map(-_) ++
         act(inverse(v), e.B).map(-_) ++ 
         act(inverse(w), e.AB), 
         List(e.dimA, e.dimB), 0)
         
  //assert(e.dimA == u.length && e.dimB == v.length)
  //assert(w.length == u.length*v.length)

  def totalVar = (0 until dims.length).foldLeft("")(
                     (str, varnum) => str + ('A' + varnum).toChar
                   )
  
  override def varnames = 
    Array.tabulate[String](dims.length)(i => "lambda" + ('A' + i).toChar) ++
    Array("lambda" + totalVar)
  
  override def latexVarnames = 
    Array.tabulate[String](dims.length)(i => "\\lambda^" + ('A' + i).toChar) ++
    Array("\\lambda^{" + totalVar + "}")
  
  def totalDim = dims.foldRight(1)((d, total) => d*total)
  
  def dimA = dims(0)
  def dimB = dims(1)
  def dimAB = dims(0)*dims(1)
               
  def stdFormCoeffs(): Array[Int] = 
    coeffs.dropRight(dimB + dimAB).map(-_) ++ 
    coeffs.drop(dimA).dropRight(dimAB).map(-_) ++
    coeffs.drop(dimA + dimB)
    
  override def csvHeaders(): String = {
    val str = new StringBuilder("")
    var varnum = 0
    while (varnum < varnames.length - 1) {
      str ++= (1 to dims(varnum)).map(i => varnames(varnum) + i).mkString(",")
      str += ','
      varnum += 1
    }
    str ++= (1 to totalDim).map(i => varnames.last + i).mkString(",")
    str ++= ",const"
    return str.result
  }    
    
  override def toCSV(): String = stdFormCoeffs.mkString(",") + "," + const
  
  override def toString(): String = 
    "Inequality(dimA = " + dimA + 
    ", dimB = " + dimB + 
    ", coeffs = (" + stdFormCoeffs.mkString(", ") + "))"
  
  override def toLatex(): String = { 
    def format(coeff: Int, term: String): String = {
      if (coeff == 0) ""
      else if (coeff == 1) term
      else if (coeff == -1) "-" + term
      else coeff.toString() + term
    }
    // WARNING: The argument must be a string (double quotes) for this to work
    val b = new StringBuilder("$")
    
    // We move the coefficients of \lambda^A to the LHS of the <=    
    var i = 0
    while (i < dimA) {
      if (b.length == 1) {
        b ++= format(-coeffs(i), "\\lambda^A_{" + (i+1) + "}")
      } else if (-coeffs(i) < 0) {
        b ++= " - " + format(coeffs(i), "\\lambda^A_{" + (i+1) + "}")
      } else if (-coeffs(i) > 0) {
        b ++= " + " + format(-coeffs(i), "\\lambda^A_{" + (i+1) + "}") 
      }
      i += 1
    }
    
    // We move the coefficients of \lambda^B to the LHS of the <=
    i = dimA
    while (i < dimA + dimB) {
      if (b.length == 1) {
        b ++= format(-coeffs(i), "\\lambda^B_{" + (i+1-dimA) + "}")
      } else if (-coeffs(i) < 0) {
        b ++= " - " + format(coeffs(i), "\\lambda^B_{" + (i+1-dimA) + "}")
      } else if (-coeffs(i) > 0) {
        b ++= " + " + format(-coeffs(i), "\\lambda^B_{" + (i+1-dimA) + "}")
      }
      i += 1
    }
    if (b.length == 1) b += '0'
    b ++= " \\leq "
      
    var zero = true
    i = dimA + dimB
    while (i < dimA + dimB + dimAB) {
      if (zero && coeffs(i) != 0) {
        zero = false
        b ++= format(coeffs(i), "\\lambda^{AB}_{" + (i+1-dimA-dimB) + "}")
      } else if (coeffs(i) > 0) {
        b ++= " + " + 
              format(coeffs(i), "\\lambda^{AB}_{" + (i+1-dimA-dimB) + "}")
      } else if (coeffs(i) < 0) {
        b ++= " - " + 
              format(-coeffs(i), "\\lambda^{AB}_{" + (i+1-dimA-dimB) + "}")
      }
      i += 1
    }
    // If the coefficients were all zero, then put just the constant on the RHS
    if (zero) b ++= (-const).toString
    // Else write a +/- and the constant
    else if (const > 0) b ++= " - " + const
    else b ++= " + " + (-const)

    // Close off the LaTeX and return
    b += '$'
    return b.result
  }
}
