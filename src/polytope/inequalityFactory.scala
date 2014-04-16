package polytope

import scala.collection.mutable.HashMap
import scala.collection.mutable.HashSet

object InequalityFactory {
    
  def inequalities(dimA: Int, dimB: Int): HashSet[Inequality] = {
    val ieqs = HashSet[Inequality]()
    for (T <- RectTableau.standardTableaux(dimA, dimB).filter(_.isAdmissible)) {
      val dimAB = dimA*dimB
      for (edge <- T.toCone.edges(dimA)) {
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
    // TODO Get rid of duplicates
    return ieqs
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

class Inequality(val u: Permutation, 
                 val v: Permutation, 
                 val w: Permutation, 
                 e: ABEdge) {
  assert(e.dimA == u.length && e.dimB == v.length)
  assert(w.length == u.length*v.length)
  
  def dimA = u.length
  def dimB = v.length
  def dimAB = w.length
  
  // coeffs*vars >= 0
  val coeffs = act(inverse(u), e.A).map(-_) ++
               act(inverse(v), e.B).map(-_) ++ 
               act(inverse(w), e.AB)
               
  override def hashCode() = coeffs.deep.hashCode()
  
  override def equals(a: Any) = a match {
    case ieq: Inequality => ieq.hashCode() == this.hashCode()
    case _ => false
  }
  
  override def toString: String = "Inequality(dimA = " + dimA + 
                                  ", dimB = " + dimB + 
                                  ", coeffs = (" + coeffs.mkString(", ") + "))"
  
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
