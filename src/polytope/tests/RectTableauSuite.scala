package polytope.tests


import org.junit.runner.RunWith

import org.scalatest._
import org.scalatest.junit.JUnitRunner

import polytope._

import scala.collection.immutable.Set
import scala.collection.mutable.ArrayBuffer

abstract class UnitSpec extends FlatSpec with ShouldMatchers with
  OptionValues with Inside with Inspectors

@RunWith(classOf[JUnitRunner])
class RectTableauSuite extends UnitSpec {
  
  "RectTableau" should "recognize trivial tableaux." in {
    val t0 = RectTableau(2, 2, ArrayBuffer(1, 2, 1, 2))
    val t1 = RectTableau(3, 3)
    val t2 = RectTableau(3, 3, ArrayBuffer[Int](1, 1, 1, 2, 2, 2, 3, 3, 3))
    val t3 = RectTableau(3, 3, ArrayBuffer[Int](1, 2, 1, 2, 3, 3, 1, 2, 3))
    
    t0.isTrivialTableau should be (false)
    t1.isTrivialTableau should be (true)
    t2.isTrivialTableau should be (true)
    t3.isTrivialTableau should be (false)
  }

  it should "get the row of a label." in {
    val t1 = RectTableau(2, 2, ArrayBuffer[Int](1, 2, 1, 2))
    t1.getRowOf(2) should be (2)
    t1.getRowOf(3) should be (1)
    
    val t2 = RectTableau(3, 3, ArrayBuffer[Int](1, 1, 2, 2, 3, 1, 3, 2, 3))
    t2.getRowOf(6) should be (1)
    t2.getRowOf(7) should be (3)
  }
  
  it should "update the row of a label." in {
    val t1 = RectTableau(2, 2, ArrayBuffer[Int](1, 1, 2, 2))
    t1.updateRow(2, 2)
    t1.updateRow(3, 1)
    t1.toMatrix should be (ArrayBuffer(ArrayBuffer(1, 3), 
                                       ArrayBuffer(2, 4)))
  }
  
  it should "recognize standard tableaux." in {
    val t1 = RectTableau(0, 0, ArrayBuffer[Int]())
    val t2 = RectTableau(1, 1, ArrayBuffer[Int](1))
    val t3 = RectTableau(1, 1, ArrayBuffer[Int](-1))
    val t4 = RectTableau(2, 2, ArrayBuffer(1, 1, 1, 2))
    val t5 = RectTableau(2, 2, ArrayBuffer(2, 2, 3, 3)) 
    val t6 = RectTableau(2, 2, ArrayBuffer(2, 2, 1, 1))
    val t7 = RectTableau(3, 3, ArrayBuffer(1, 2, 1, 2, 3, 3, 1, 2, 3))
    
    t1.isStandardTableau should be (true)
    t2.isStandardTableau should be (true)
    t3.isStandardTableau should be (false)
    t4.isStandardTableau should be (false)
    t5.isStandardTableau should be (false)
    t6.isStandardTableau should be (false)
    t7.isStandardTableau should be (true)
  }
  
  it should "recognize admissible tableaux." in {
    val t1 = RectTableau(3, 3, ArrayBuffer[Int](1, 2, 1, 2, 3, 3, 1, 2, 3))
    val t2 = RectTableau(3, 3, ArrayBuffer[Int](1, 1, 2, 2, 3, 1, 3, 2, 3))
    val t3 = RectTableau(3, 3, ArrayBuffer[Int](1, 2, 1, 1, 3, 2, 3, 2, 3))
    
    t1.isAdmissible should be (true)
    t2.isAdmissible should be (true)
    t3.isAdmissible should be (false)
  }
  
  it should "correctly convert a tableau to a matrix." in {
    val t1 = RectTableau(2, 2, ArrayBuffer[Int](1, 2, 1, 2))
    t1.toMatrix should be (ArrayBuffer(ArrayBuffer(1, 3), 
                                       ArrayBuffer(2, 4)))
    
    val t2 = RectTableau(3, 3, ArrayBuffer[Int](1, 1, 2, 2, 3, 1, 3, 2, 3))
    t2.toMatrix should be (ArrayBuffer(ArrayBuffer(1, 2, 6), 
                                       ArrayBuffer(3, 4, 8), 
                                       ArrayBuffer(5, 7, 9)))

    val t3 = RectTableau(3, 3, ArrayBuffer[Int](1, 2, 1, 1, 3, 2, 3, 2, 3))
    t3.toMatrix should be (ArrayBuffer(ArrayBuffer(1, 3, 4),
                                       ArrayBuffer(2, 6, 8),
                                       ArrayBuffer(5, 7, 9)))
  }
  
  it should "convert a tableau to a cone." is (pending)
  
  "Standard Tableaux" should "enumerate the 2 standard 2x2 tableaux." in {
    val tblx = Set[ArrayBuffer[ArrayBuffer[Int]]](
        ArrayBuffer(ArrayBuffer(1, 2), 
                    ArrayBuffer(3, 4)),
        ArrayBuffer(ArrayBuffer(1, 3), 
                    ArrayBuffer(2, 4)))
                    
    RectTableau.standardTableaux(2, 2).map(_.toMatrix).toSet should be (tblx)
  }
  
  it should "produce 42 standard 3x3 tableaux." in {
    RectTableau.standardTableaux(3, 3).length should be (42)
  }

}