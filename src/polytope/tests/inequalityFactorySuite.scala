package polytope.tests

import org.junit.runner.RunWith

import org.scalatest._
import org.scalatest.junit.JUnitRunner

import polytope._

import scala.collection.immutable.Set
import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.HashSet

@RunWith(classOf[JUnitRunner])
class inequalityFactorySuite extends UnitSpec {
  "InequalityFactory" should "correctly calculate trivial coefficients." in {
    val c1 = InequalityFactory.c(Array(1, 2), Array(1, 2), Array(1, 2, 3, 4), 
                                 RectTableau(2, 2, ArrayBuffer(1, 1, 2, 2)))

    isInteger(c1) should be (true)
    c1.getOrElse(0L, 0) should be (1)
  }
  
  it should "correctly calculate non-trivial coefficients." in {
    val c1 = InequalityFactory.c(Array(2, 1), Array(2, 1), Array(1, 3, 4, 2),
                                RectTableau(Array(Array(1, 2), Array(3, 4))))
    val c2 = InequalityFactory.c(Array(2, 1), Array(2, 1), Array(3, 1, 2, 4),
                                RectTableau(Array(Array(1, 2), Array(3, 4))))
    
    isInteger(c1) should be (true)
    c1.getOrElse(0L, 0) should be > 0
    isInteger(c2) should be (true)
    c2.getOrElse(0L, 0) should be > 0
  }
  
  it should "convert inequalities to Latex." in {
    val ieq1 = new InequalityDM(Array(1, 2), Array(2, 1), Array(3, 2, 4, 1), 
                              new ABEdge(Array(1, 0, -1, 2), 2))
    val ieq2 = new InequalityDM(Array(), Array(2, 1), Array(),
                              new ABEdge(Array(0, -1), 0))
    
    ieq1.toLatex.replaceAll(" ", "") should be (
           """$\lambda^A_1-\lambda^B_1+2\lambda^B_2\leq"""
         + """-\lambda^{AB}_1+2\lambda^{AB}_2+3\lambda^{AB}_3$""")
    ieq2.toLatex.replaceAll(" ", "") should be ("""$-\lambda^B_1\leq0$""")
  }
  
  it should "calculate 2x2x4 inequalities." in {
    val ieqs = InequalityFactory.ineqsDM(List(2, 2))
    val poly = PolyhedralCone.positiveWeylChamberDM(List(2,2,4)).
                 intersection(PolyhedralCone(ieqs))
    
    val bravyiPoly = PolyhedralCone(Array[Array[Int]](), Array(
                                    Array(-1, 1, -1, 1, 2, 0, 0, -2, 0),
                                    Array(-1, 1, 0, 0, 1, 1, -1, -1, 0),
                                    Array(0, 0, -1, 1, 1, 1, -1, -1, 0),
                                    Array(1, -1, -1, 1, 0, 2, 0, -2, 0),
                                    Array(-1, 1, 1, -1, 0, 2, 0, -2, 0),
                                    Array(1, -1, -1, 1, 2, 0, -2, 0, 0),
                                    Array(-1, 1, 1, -1, 2, 0, -2, 0, 0))).
      intersection(PolyhedralCone.positiveWeylChamberDM(List(2, 2, 4)))

    println("2x2x4 Inequalities:\n")
    ieqs.foreach(i => println(i.toLatex() + """\\"""))
    println("\n\n2x2x4 Rays of polytope:\n")
    val (rs, ps) = poly.edges()
    rs.foreach(e => println("(" + e.edge.mkString(", ") + ")"))
    println("\nVertices of polytope:\n")
    ps.foreach(e => println("(" + e.edge.mkString(", ") + ")"))
    
    val (brs, bps) = bravyiPoly.edges()
    rs.map(_.edge.toVector).toSet should be (brs.map(_.edge.toVector).toSet)
    ps.map(_.edge.toVector).toSet should be (bps.map(_.edge.toVector).toSet)
    
  }
  
  it should "calculate 3x3x9 inequalities." is (pending)
    /*
     * Currently infeasible on a single core
     *
    val ineqs = InequalityFactory.inequalities(3, 3)
    println("===============================")
    println("3x3x9 Inequalities: ")
    ineqs.foreach(i => println(i.toLatex() + """\\"""))
     */
}
