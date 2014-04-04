package polytope.tests

import org.junit.runner.RunWith

import org.scalatest._
import org.scalatest.junit.JUnitRunner

import polytope._

import scala.collection.immutable.Set
import scala.collection.mutable.ArrayBuffer


@RunWith(classOf[JUnitRunner])
class inequalityFactorySuite extends UnitSpec {
  "InequalityFactory" should "correctly calculate trivial coefficients." in {
    val c1 = InequalityFactory.c(Array(1, 2), Array(1, 2), Array(1, 2, 3, 4), 
                                 RectTableau(2, 2, ArrayBuffer(1, 1, 2, 2)))

    c1 should be (0)
  }
  
  it should "correctly calculate non-trivial coefficients." is (pending)
  
  it should "convert inequalities to Latex." in {
    val ieq1 = new Inequality(Array(1, 2), Array(2, 1), Array(3, 2, 4, 1), 
                              new ABEdge(Array(1, 0, -1, 2), 2))
    val ieq2 = new Inequality(Array(), Array(2, 1), Array(),
                              new ABEdge(Array(0, -1), 0))
    
    ieq1.toLatex.replaceAll(" ", "") should be (
           """$\lambda^A_1+2\lambda^B_1-\lambda^B_2\leq"""
         + """3\lambda^{AB}_1-\lambda^{AB}_3+2\lambda^{AB}_4$""")
    ieq2.toLatex.replaceAll(" ", "") should be ("""$-\lambda^B_1\leq0$""")
  }
  
  it should "calculate 2x2x4 inequalities." in {
    val (dimA, dimB) = (2, 2)
    val ineqs = InequalityFactory.inequalities(2, 2)
    val poly = PolyhedralCone(Array[Array[Int]](),
                              ineqs.map(_.toArray()).toArray).
               intersection(PolyhedralCone.positiveWeylChamber(8, 0, 2)).
               intersection(PolyhedralCone.positiveWeylChamber(8, 2, 2)).
               intersection(PolyhedralCone.positiveWeylChamber(8, 4, 4))
    // Differs from Michael's by negatives in the last four coords
    // He intersects with the Weyl Chambers for increasing spectra
    val bravyiPoly = PolyhedralCone(Array[Array[Int]](), Array(
                                    Array(-1, 1, 0, 0, -1, -1, 1, 1),
                                    Array(0, 0, -1, 1, -1, -1, 1, 1),
                                    Array(-1, 0, -1, 0, -1, 0, 0, 1),
                                    Array(-1, 0, 1, 0, 0, -1, 0, 1),
                                    Array(1, 0, -1, 0, 0, -1, 0, 1),
                                    Array(-1, 0, 1, 0, -1, 0, 1, 0),
                                    Array(1, 0, -1, 0, -1, 0, 1, 0))).
                     intersection(PolyhedralCone.positiveWeylChamber(8, 0, 2)).
                     intersection(PolyhedralCone.positiveWeylChamber(8, 2, 2)).
                     intersection(PolyhedralCone.positiveWeylChamber(8, 4, 4))
                     
    poly.edges().map(_.edge.to[ArrayBuffer]).toSet should be (
        bravyiPoly.edges().map(_.edge.to[ArrayBuffer]).toSet
    )
    
    println("===============================")
    println("2x2x4 Inequalities: ")
    ineqs.foreach(i => println(i.toLatex() + """\\"""))
    println("===============================")
  }
  
  it should "calculate 3x3x9 inequalities." in {
    /*
     * Currently infeasible on a single core
     *
    val ineqs = InequalityFactory.inequalities(3, 3)
    println("===============================")
    println("3x3x9 Inequalities: ")
    ineqs.foreach(i => println(i.toLatex() + """\\"""))
     */
  }
}
