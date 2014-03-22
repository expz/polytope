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
    val c1 = InequalityFactory.c(
                                 Array(1, 2), Array(1, 2), Array(1, 2, 3, 4), 
                                 RectTableau(2, 2, ArrayBuffer(1, 1, 2, 2)))

    c1 should be (0)
  }
  
  it should "correctly calculate non-trivial coefficients." is (pending)
}
