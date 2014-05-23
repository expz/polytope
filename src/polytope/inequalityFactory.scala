package polytope

import scala.collection.mutable.HashMap
import scala.collection.mutable.HashSet
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.ArrayBuffer

/** Factory for data related to marginal inequalities including cubicles, 
  *  extremal edges, product flag coefficients, polytope inequalities and
  *  polytope vertices.
  */
object InequalityFactory {
  
  /** Returns ListBuffer of RectTableau corresponding to marginal cubicles for
    * distinguishable particles mixed polytope
    * 
    * @param dims two positive dimensions of single-particle state spaces
    * @return a ListBuffer of all RectTableau which correspond to cubicles 
    */
  def cubicles(dims: List[Int]) = 
    if (dims.length < 2) ListBuffer[RectTableau]()
    else RectTableau.standardTableaux(dims(0), dims(1)).filter(_.isAdmissible)
    
  /** Returns HashSet of inequalities which are minimal for defining the
    * distinguishable particles mixed polytope and correspond to given cubicles
    * 
    * @param cubicles the tableaux corresponding to the cubicles
    * @return inequalities of the polytope corresponding to the given cubicles
    */
  def ineqs(cubicles: ListBuffer[RectTableau]): HashSet[Inequality] = {
    val ieqs = HashSet[Inequality]()
    if (!cubicles.isEmpty) {
      val dims = List(cubicles(0).rows, cubicles(0).cols)
      for (T <- cubicles) {
        for (edge <- T.toCone.edges(dims(0))) {
          for (u <- PermutationFactory.shuffles(edge.multA)) {
            for (v <- PermutationFactory.shuffles(edge.multB)) {
              for (w <- PermutationFactory.shufflesOfGivenLength(
                  edge.multAB, reducedWord(u).length + reducedWord(v).length)) {
                // In this case, c(u,v,w,T) will be a constant
                if (c(u, v, w, T).getOrElse(0L, 0) == 1) {
                  ieqs += new Inequality(u, v, w, edge)
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
  def ineqs(dims: List[Int]): HashSet[Inequality] =
    ineqs(RectTableau.standardTableaux(dims(0), dims(1)).filter(_.isAdmissible))
          
  /** Returns HashSet of inequalities which are minimal for defining the
    * distinguishable particles mixed polytope and correspond to the given edges
    * 
    * @param edges extremal edges
    * @return (minimal) inequalities of the polytope whose coefficients are 
    * permutations of the extremal edges
    */  
  def ineqs(edges: HashMap[ABEdge, ArrayBuffer[RectTableau]]): 
    HashSet[Inequality] = {
    val ieqs = HashSet[Inequality]()
    
    for (edge <- edges.keys) {
      for (u <- PermutationFactory.shuffles(edge.multA)) {
        for (v <- PermutationFactory.shuffles(edge.multB)) {
          for (w <- PermutationFactory.shufflesOfGivenLength(
               edge.multAB, reducedWord(u).length + reducedWord(v).length)) {
            for (T <- edges(edge)) {            
              if (c(u, v, w, T).getOrElse(0L, 0) == 1) {
                ieqs += new Inequality(u, v, w, edge)
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
  def ineqs(coeffs: ArrayBuffer[PFCoefficient]): HashSet[Inequality] = {
    val ieqs = HashSet[Inequality]()
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
          ieqs += new Inequality(v, dims)
        }
      }
    }
    return ieqs
  }

  /** Returns map from an extremal edge to an ArrayBuffer of cubicles containing 
    * the extremal edge
    * 
    * @param cubicles cubicles of which to compute the extremal edges
    * @return map from an extremal edge to cubicles containing it
    */ 
  def edges(cubicles: ListBuffer[RectTableau]): 
    HashMap[ABEdge, ArrayBuffer[RectTableau]] = {
    val es = HashMap[ABEdge, ArrayBuffer[RectTableau]]()
    if (!cubicles.isEmpty) {
      val dims = List(cubicles(0).rows, cubicles(0).cols)
      for (T <- cubicles) {
        for (e <- T.toCone.edges(dims(0))) {
          if (es.contains(e)) es(e) += T
          else es(e) = ArrayBuffer(T)
        }
      }
    }
    return es
  }
  
  /** Returns ArrayBuffer of all nonzero product flag coefficients relevant for
    * distinguishable particles mixed polytope corresponding to the given edges
    * 
    * @param edges a map from extremal edges to cubicles which contain them
    * @return nonzero product flag coefficients
    */ 
  def coeffs(edges: HashMap[ABEdge, ArrayBuffer[RectTableau]]):
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
  def coeffs(cubicles: ListBuffer[RectTableau]):
    ArrayBuffer[PFCoefficient] = {
    val cs = ArrayBuffer[PFCoefficient]()
    if (!cubicles.isEmpty) {
      val dims = List(cubicles(0).rows, cubicles(0).cols)
      for (T <- cubicles) {
        for (e <- T.toCone.edges(dims(0))) {
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

// coeffs*vars >= 0
class Inequality(val coeffs: Array[Int], val dims: List[Int]) {
  def this(u: Permutation, v: Permutation, w: Permutation, e: ABEdge) = 
    this(act(inverse(u), e.A).map(-_) ++
         act(inverse(v), e.B).map(-_) ++ 
         act(inverse(w), e.AB), 
         List(e.dimA, e.dimB))
         
  //assert(e.dimA == u.length && e.dimB == v.length)
  //assert(w.length == u.length*v.length)
  
  def dimA = dims(0)
  def dimB = dims(1)
  def dimAB = dims(0)*dims(1)
               
  override def hashCode() = coeffs.deep.hashCode()
  
  override def equals(a: Any) = a match {
    case ieq: Inequality => ieq.hashCode() == this.hashCode()
    case _ => false
  }
  
  def stdFormCoeffs(): Array[Int] = 
    coeffs.dropRight(dimB + dimAB).map(-_) ++ 
    coeffs.drop(dimA).dropRight(dimAB).map(-_) ++
    coeffs.drop(dimA + dimB)
    
  def csvHeaders(): String = 
    (1 to dimA).map("a" + _).mkString(",") + "," +
    (1 to dimB).map("b" + _).mkString(",") + "," +
    (1 to dimAB).map("c" + _).mkString(",")
    
  def toCSV(): String = stdFormCoeffs.mkString(",")
  
  override def toString(): String = 
    "Inequality(dimA = " + dimA + 
    ", dimB = " + dimB + 
    ", coeffs = (" + stdFormCoeffs.mkString(", ") + "))"
  
  // Returns the coefficients of the inequality  
  // a_1 l_au(1) + .. + b(1) l_bv(1) + .. - ab(1) l_abw(1) >= 0 
  def toArray(): Array[Int] = coeffs
  
  def toLatex(): String = { 
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
        b ++= format(-coeffs(i), "\\lambda^A_" + (i+1))
      } else if (-coeffs(i) < 0) {
        b ++= " - " + format(coeffs(i), "\\lambda^A_" + (i+1))
      } else if (-coeffs(i) > 0) {
        b ++= " + " + format(-coeffs(i), "\\lambda^A_" + (i+1)) 
      }
      i += 1
    }
    
    // We move the coefficients of \lambda^B to the LHS of the <=
    i = dimA
    while (i < dimA + dimB) {
      if (b.length == 1) {
        b ++= format(-coeffs(i), "\\lambda^B_" + (i+1-dimA))
      } else if (-coeffs(i) < 0) {
        b ++= " - " + format(coeffs(i), "\\lambda^B_" + (i+1-dimA))
      } else if (-coeffs(i) > 0) {
        b ++= " + " + format(-coeffs(i), "\\lambda^B_" + (i+1-dimA))
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
        b ++= format(coeffs(i), "\\lambda^{AB}_" + (i+1-dimA-dimB))
      } else if (coeffs(i) > 0) {
        b ++= " + " + format(coeffs(i), "\\lambda^{AB}_" + (i+1-dimA-dimB))
      } else if (coeffs(i) < 0) {
        b ++= " - " + format(-coeffs(i), "\\lambda^{AB}_" + (i+1-dimA-dimB))
      }
      i += 1
    }
    // If the coefficients were all zero, then put a zero on the right side
    if (zero) b += '0'

    // Close off the LaTeX and return
    b += '$'
    return b.result
  }
}
