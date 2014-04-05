package polytope.tests

import org.junit.runner.RunWith

import org.scalatest._
import org.scalatest.junit.JUnitRunner

import polytope._

import scala.collection.immutable.Set
import scala.collection.mutable.ArrayBuffer

/*
 * Some of these tests fail because of Polco's apparent lack of support for 
 * degenerate cases.
 */
@RunWith(classOf[JUnitRunner])
class polyhedralConeSuite extends UnitSpec {
  "PolyhedralCone" should "calculate edges of a trivial cone." in {
    val pc1 = PolyhedralCone(Array(), Array())    
    val pc2 = PolyhedralCone(Array(Array[Int]()), Array(Array[Int]()))
    
    pc1.edges().map(_.edge).deep should be (Array[Array[Int]]().deep)
    pc2.edges().map(_.edge).deep should be (Array[Array[Int]]().deep)  
  }

  it should "calculate edges of a degenerate cone." in {
    val pc1 = PolyhedralCone(Array(), Array(Array(1, -1)))
    val pc2 = PolyhedralCone(Array(Array(0, 0), Array(0, 0)), Array(Array(1, -1)))
    
    pc1.edges().map(_.edge.toVector).toSet.contains(Vector(1, -1)) should 
        be (true)
    pc1.edges().map(_.edge.toVector).toSet.contains(Vector(-1, 1)) should 
        be (true)
    pc2.edges().map(_.edge).deep should be (Array(Array(1, -1)).deep)
  }
  
  it should "calculate edges of a non-trivial cone." in {
    val pc1 = PolyhedralCone(Array(), Array(Array(1, 0), Array(0, 1), Array(-1, 1)))
    val pc2 = PolyhedralCone(Array(Array(1, 0, -1)), Array(Array(1, 0, 0), Array(0, 1, -1)))
    
    pc1.edges().map(_.edge.toVector).toSet should be (Set(Vector(0, 1), Vector(1, 1)))
    pc2.edges().map(_.edge.toVector).toSet should be (Set(Vector(0, 1, 0), Vector(1, 1, 1)))
  }
  
  it should "calculate AB-edges of a non-trivial cone." in {
    val pc1 = PolyhedralCone(Array(Array(1, -1, 0, 0), Array(0, 0, 1, -1)), Array(Array(1, 0, 0, 0), Array(0, 0, 1, 0)))
    pc1.edges(2).map(_.edge.to[ArrayBuffer]).toSet should be (Set(ArrayBuffer(1, 1, 0, 0), ArrayBuffer(0, 0, 1, 1)))
  }
    
  it should "correctly make a trivial Weyl chamber." in {
    val wc1 = PolyhedralCone.positiveWeylChamber(1, 0, 1)
    wc1.edges().map(_.edge).deep should be (Array(Array(1)).deep) 
  }
  
  it should "correctly make a non-trivial Weyl chamber." in {
    val wc1 = PolyhedralCone.positiveWeylChamber(2, 0, 2)
    val wc2 = PolyhedralCone.positiveWeylChamber(6, 0, 3)
    val wc3 = PolyhedralCone.positiveWeylChamber(6, 3, 3)

    wc1.eqs.deep should be (Array(Array(1, 1)).deep)
    wc1.ieqs.deep should be (Array(Array(1, -1)).deep)
    wc2.eqs.deep should be (Array(Array(1, 1, 1, 0, 0, 0)).deep)
    wc2.ieqs.deep should be (Array(Array(1, -1, 0, 0, 0, 0), 
                                   Array(0, 1, -1, 0, 0, 0)).deep)
    wc3.eqs.deep should be (Array(Array(0, 0, 0, 1, 1, 1)).deep)
    wc3.ieqs.deep should be (Array(Array(0, 0, 0, 1, -1, 0), 
                                   Array(0, 0, 0, 0, 1, -1)).deep)    
  }
  
  "ABEdge" should "calculate the A and B edges." in {
    val e1 = new ABEdge(Array(-1, 1, 1, -1), 2)
    
    e1.A.deep should be (Array(1, -1)) // Ordered decreasing!
    e1.B.deep should be (Array(1, -1))
  }
  "ABEdge" should "calculate the mixed AB edge." in {
    val e1 = new ABEdge(Array(0, 0, 0, 0), 2)
    val e2 = new ABEdge(Array(-1, 1, 1, -1), 2)
    
    e1.AB.deep should be (Array(0, 0, 0, 0).deep)
    e2.AB.deep should be (Array(2, 0, 0, -2).deep)
  }
  
  it should "return multiplicities of A and B." in {
    val e1 = new ABEdge(Array(), 0)
    val e2 = new ABEdge(Array(1, 1), 0)
    val e3 = new ABEdge(Array(1, 1, -1, -1), 3)
    val e4 = new ABEdge(Array(-1, -1, 0, 0, 1, 0), 3)
    
    e1.multA().deep should be (Array[Int]().deep)
    e2.multA().deep should be (Array[Int]().deep)
    e3.multA().deep should be (Array(2, 1).deep)
    e4.multB().deep should be (Array(1, 2).deep)
  }
  
  "multAB" should "return multiplicities of AB pairs." in {
    val e1 = new ABEdge(Array(1, 2, 1, 2), 2)
    
    e1.multAB().deep should be (Array(1, 2, 1).deep)
  }
}