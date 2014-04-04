package polytope

import scala.collection.mutable.ArrayBuffer

object InequalityFactory {
    
  def inequalities(dimA: Int, dimB: Int): ArrayBuffer[Inequality] = {
    val ieqs = ArrayBuffer[Inequality]()
    for (T <- RectTableau.standardTableaux(dimA, dimB).filter(_.isAdmissible)) {
      val dimAB = dimA*dimB
      println("===========")
      println(T.toMatrix)
      println(T.toCone.edges(dimA).toList)
      for (edge <- T.toCone.edges(dimA)) {
        println("----------")
        println("edge: " + edge.toString)
        println("A: " + edge.A().toVector)
        //println("shuffles u: " + PermutationFactory.shuffles(edge.multA).map(_.to[ArrayBuffer]))
        println("B: " + edge.B().toVector)
        println("AB: " + edge.AB().toVector)
        println("multA: " + edge.multA.to[ArrayBuffer])
        println("multB: " + edge.multB.to[ArrayBuffer])
        println("multAB: " + edge.multAB.to[ArrayBuffer])
        //println("shuffles v: " + PermutationFactory.shuffles(edge.multB).map(_.to[ArrayBuffer]))
        //println("==========")
        for (u <- PermutationFactory.shuffles(edge.multA)) {
          for (v <- PermutationFactory.shuffles(edge.multB)) {

            //println("shuffles w: " + PermutationFactory.shuffles(edge.multAB).map(_.to[ArrayBuffer]))
            for (w <- PermutationFactory.shuffles(edge.multAB)) {
                //println("u: " + u.to[ArrayBuffer] + "  v: " + v.to[ArrayBuffer] + "  w: " + w.to[ArrayBuffer])
                //println("l(u): " + reducedWord(u).length + "  l(v): " + reducedWord(v).length + "  l(w): " + reducedWord(w).length)
                c(u, v, w, T)
              if (reducedWord(u).length + reducedWord(v).length == 
                                                        reducedWord(w).length) {
                
                if (c(u, v, w, T) != 0) {
                  // This can create duplicates in the ArrayBuffer
                  //   The duplicates are removed at the end
                  //   Using a HashSet in the loop is too costly
                  ieqs.append(new Inequality(u, v, w, edge))
                  println("++++ Found ++++  ieq Array: " + ieqs.last.toArray.toVector + "  c: " + c(u,v,w,T))
                }
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
    //println("In c()")
    //println("w: " + w.to[ArrayBuffer])
    //println(SchubertFactory.schubertPolynomial(w))
    //println("schubert poly of w: " + hashMapToString(collectTerms(SchubertFactory.schubertPolynomial(w))))
    //println("tableau: " + T.toMatrix.map(_.to[ArrayBuffer]).to[ArrayBuffer])
    //println("substituted schubert poly: " + hashMapToString(subst(collectTerms(SchubertFactory.schubertPolynomial(w)), T), u.length, v.length))
    //println("u: " + u.to[ArrayBuffer])
    val f = delta(u,
                  subst(collectTerms(SchubertFactory.schubertPolynomial(w)), T),
                  0)
    //println("delta_u = " + hashMapToString(f, u.length, v.length))
    /*
    // Remove terms which only have y variables, because delta will zero them
    // anyway. Saves time?
    for(term <- f.keys) {
      if ((term & ~((1L << T.rows*4) - 1L)) == 0L) f.remove(term)
    }
    */
    val f2 = delta(v, f, T.rows)
    //println("v: " + v.to[ArrayBuffer])
    //println("delta_v delta_u = " + hashMapToString(f2, u.length, v.length))
    //assert(isInteger(f2))
    return f2.getOrElse(0L, 0)
  }
}

class Inequality(u: Permutation, v: Permutation, w: Permutation, e: ABEdge) {
  assert(e.dimA == u.length && e.dimB == v.length)
  assert(w.length == u.length*v.length)
  
  // Returns the coefficients of the inequality  
  // a_1 l_au(1) + .. + b(1) l_bv(1) + .. - ab(1) l_abw(1) >= 0 
  def toArray(): Array[Int] = {
    val uA = act(inverse(u), e.A())
    val vB = act(inverse(v), e.B())
    val wAB = act(inverse(w), e.AB()).map(-_)
    
    return uA ++ vB ++ wAB
  }
  
  def toLatex(): String = { 
    def format(coeff: Int, term: String): String = {
      if (coeff == 0) ""
      else if (coeff == 1) term
      else if (coeff == -1) "-" + term
      else coeff.toString() + term
    }
    // WARNING: The argument must be a string (double quotes) for this to work
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
