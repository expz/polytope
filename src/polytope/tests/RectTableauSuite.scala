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
    val t1 = RectTableau(3, 3)
    val t2 = RectTableau(3, 3, ArrayBuffer[Int](1, 1, 1, 2, 2, 2, 3, 3, 3))
    val t3 = RectTableau(3, 3, ArrayBuffer[Int](1, 2, 1, 2, 3, 3, 1, 2, 3))
    
    t1.isTrivial should be (true)
    t2.isTrivial should be (true)
    t3.isTrivial should be (false)
  }
  
  it should "recognize admissible tableaux." in {
    val t1 = RectTableau(3, 3, ArrayBuffer[Int](1, 2, 1, 2, 3, 3, 1, 2, 3))
    val t2 = RectTableau(3, 3, ArrayBuffer[Int](1, 1, 2, 2, 3, 1, 3, 2, 3))
    val t3 = RectTableau(3, 3, ArrayBuffer[Int](1, 1, 2, 2, 3, 1, 3, 2, 3))
    
    t1.isAdmissible should be (true)
    t2.isAdmissible should be (true)
    t3.isAdmissible should be (false)
  }
  
  it should "correctly convert a tableau to a matrix." in {
    val t1 = RectTableau(2, 2, ArrayBuffer[Int](1, 2, 1, 2))
    val t2 = RectTableau(3, 3, ArrayBuffer[Int](1, 1, 2, 2, 3, 1, 3, 2, 3))
    
    t1.toMatrix should be (ArrayBuffer(ArrayBuffer(1, 3), 
                                       ArrayBuffer(2, 4)))
    t2.toMatrix should be (ArrayBuffer(ArrayBuffer(1, 2, 6), 
                                       ArrayBuffer(3, 4, 8), 
                                       ArrayBuffer(5, 7, 9)))
  }
  /*
  "Standard Tableaux" should "enumerate the 2 standard 2x2 tableaux." in {
    val tblx = Set[ArrayBuffer[ArrayBuffer[Int]]](
        ArrayBuffer(ArrayBuffer(1, 2), ArrayBuffer(3, 4)),
        ArrayBuffer(ArrayBuffer(1, 3), ArrayBuffer(2, 4))
        )
    RectTableau.standardTableaux(2, 2).map(_.toMatrix).toSet should be (tblx)
  }
  
  it should "produce 42 standard 3x3 tableaux." in {
    RectTableau.standardTableaux(3, 3).length should be (42)
  }
  */

}