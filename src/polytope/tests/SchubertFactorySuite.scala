package polytope.tests

import org.junit.runner.RunWith

import org.scalatest._
import org.scalatest.junit.JUnitRunner

import polytope._

import scala.collection.immutable.Set
import scala.collection.mutable.ArrayBuffer


@RunWith(classOf[JUnitRunner])
class SchubertFactorySuite extends UnitSpec {

  "SchubertFactory" should "calculate a schubert polynomial." in {
    println(hashMapToString(collectTerms(SchubertFactory.schubertPolynomial(Array(2, 4, 5, 1, 3, 6, 7)))))
  }
}