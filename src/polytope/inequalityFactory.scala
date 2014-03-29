package polytope

import scala.collection.mutable.ArrayBuffer

object InequalityFactory {
    
  def inequalities(dimA: Int, dimB: Int): ArrayBuffer[Inequality] = {
    val ieqs = ArrayBuffer[Inequality]()
    for (T <- RectTableau.standardTableaux(dimA, dimB).filter(_.isAdmissible)) {
      val dimAB = dimA*dimB
      for (edge <- T.toCone.edges(dimA)) {
        for (u <- PermutationFactory.shuffles(edge.multA)) {
          for (v <- PermutationFactory.shuffles(edge.multB)) {
            for (w <- PermutationFactory.shuffles(edge.multAB)) {
              if (c(u, v, w, T) > 0) {
                // This can create duplicates in the ArrayBuffer
                //   The duplicates are removed at the end
                //   Using a HashSet in the loop is too costly
                ieqs.append(new Inequality(u, v, w, edge))
              }
            }
          }
        }
      }
    }
    // TODO Get rid of duplicates
    return ieqs
  }
  
  def c(u: Permutation, v: Permutation, w: Permutation, T: RectTableau): Int = 
  {
    // requires knowledge of tableau for substituting vars
    println("In c()")
    println("schubert poly: " + hashMapToString(collectTerms(SchubertFactory.schubertPolynomial(w))))
    println("tableau: " + T.toMatrix.map(_.to[ArrayBuffer]).to[ArrayBuffer])
    println("substituted schubert poly: " + hashMapToString(subst(collectTerms(SchubertFactory.schubertPolynomial(w)), T)))
    val f = delta(u,
                  subst(collectTerms(SchubertFactory.schubertPolynomial(w)), T),
                  0)
    println("f = " + hashMapToString(f))
    for (term <- f.keys) {
      // If the term contains y variables, then discard it
      if (term > (1L << T.rows*4 - 1))
        f.remove(term)
    }
    val f2 = delta(v, f, T.rows)
    println("f2 = " + hashMapToString(f))
    assert(isInteger(f2))
    return f2.getOrElse(0L, 0)
  }
}

class Inequality(u: Permutation, v: Permutation, w: Permutation, e: ABEdge) {
  assert(e.dimA == u.length && e.dimB == v.length)
  assert(w.length == u.length*v.length)
  
  def toLatex(): String = { 
    def format(coeff: Int, term: String): String = {
      if (coeff == 0) ""
      else if (coeff == 1) term
      else if (coeff == -1) "-" + term
      else coeff.toString() + term
    }
    val b = new StringBuilder("$")
      
    val uA = act(inverse(u), e.A())
    val vB = act(inverse(v), e.B())
    val wAB = act(inverse(w), e.ABTriples())
    
    var i = 0
    while (i < uA.length) {
      if (b.length == 1) {
        b ++= format(uA(i), "\\lambda^A_" + (i+1))
      } else if (uA(i) < 0) {
        b ++= " - " + format(-uA(i), "\\lambda^A_" + (i+1))
      } else if (uA(i) > 0) {
        b ++= " + " + format(uA(i), "\\lambda^A_" + (i+1)) 
      }
      i += 1
    }
    i = 0
    while (i < vB.length) {
      if (b.length == 1) {
        b ++= format(vB(i), "\\lambda^B_" + (i+1))
      } else if (vB(i) < 0) {
        b ++= " - " + format(-vB(i), "\\lambda^B_" + (i+1))
      } else if (vB(i) > 0) {
        b ++= " + " + format(vB(i), "\\lambda^B_" + (i+1))
      }
      i += 1
    }
    if (b.length == 1) b += '0'
    b ++= " \\leq "
    var zero = true
    var coeff = 0
    i = 0
    while (i < wAB.length) {
      coeff = e.A(wAB(i)._1) + e.B(wAB(i)._2)
      if (zero && coeff != 0) {
        zero = false
        b ++= format(coeff, "\\lambda^{AB}_" + (i+1))
      } else if (coeff > 0) {
        b ++= " + " + format(coeff, "\\lambda^{AB}_" + (i+1))
      } else if (coeff < 0) {
        b ++= " - " + format(-coeff, "\\lambda^{AB}_" + (i+1))
      }
      i += 1
    }
    if (zero) b += '0'
    b += '$'
    return b.result
  }
}
