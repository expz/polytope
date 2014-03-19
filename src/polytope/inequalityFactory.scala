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
    val f = delta(u,
                  subst(collectTerms(SchubertFactory.schubertPolynomial(w)), T),
                  0)
    for (term <- f.keys) {
      // If the term contains y variables, then discard it
      if (term > (1L << T.rows*4 - 1))
        f.remove(term)
    }
    val f2 = delta(v, f, T.rows)
    assert(isInteger(f2))
    return f2(0L)
  }
}

class Inequality(u: Permutation, v: Permutation, w: Permutation, e: Edge) {
  def prettyPrint(): String = { 
    def format(coeff: Int, term: String): String = {
      if (coeff == 0) ""
      else if (coeff == 1) term
      else coeff.toString() + term
    }
    val b = new StringBuilder('$')
      
      val uA = act(u, e.A())
      val vB = act(v, e.B())
      val wAB = act(w, e.AB())
      
      var i = 0
      while (i < uA.length) {
          if (b.length == 1) {
              b ++= format(uA(i), "\\lambda^A_" + (i+1))
          } else if (uA(i) < 0) {
              b ++= format(-uA(i), "\\lambda^A_" + (i+1))
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
      i = 0
      while (i < wAB.length) {
          if (zero && wAB(i) != 0) {
              zero = false
              b ++= format(wAB(i)._3, "\\lambda^{AB}_" + (i+1))
          } else if (wAB(i)._3 > 0) {
              b ++= " + " + format(wAB(i)._3, "\\lambda^{AB}_" + (i+1))
          } else if (wAB(i)._3 < 0) {
              b ++= " - " + format(-wAB(i)._3, "\\lambda^{AB}_" + (i+1))
          }
          i += 1
      }
      if (zero) b += '0'
      b += '$'
      return b.result
  }
}
