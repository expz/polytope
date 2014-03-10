package polytope

import scala.collection.mutable.HashMap
import scala.collection.mutable.ListBuffer
import scala.collection.immutable.Vector

/*
import com.sun.jna.{Library, Native, Platform}
import com.perisic.ring._
import java.math._

trait CLibrary extends Library {
  def puts(s: String)
}

object CLibrary {
  def Instance = Native.loadLibrary(
    if (Platform.isWindows) "msvcrt" else "c",
    classOf[CLibrary]).asInstanceOf[CLibrary]
}

trait Symmetrica extends Library {
	def m_perm_schubert_monom_summe(perm: Vector[Int], res: Any): Int
}

object Symmetrica {
	def Instance = Native.loadLibrary("symmetrica", classOf[Symmetrica]).asInstanceOf[Symmetrica]
}

object Main {
  def main(args: Array[String]) {
    CLibrary.Instance.puts("Hello, World")
    for ((arg, i) <- args.zipWithIndex) {
      CLibrary.Instance.puts(
        "Argument %d: %s".format(i, arg))
    }
  }

}

*/

object Main {
	def main(args: Array[String]) {
	    val perm = Vector(3, 1, 4, 2) //SchubertFactory.readPermutation()
	    SchubertFactory.test()
	}	
}
/*
 *  TODO run from command line with enough memory
 *  TODO change polynomial to the fastest collection for updates: HashSet? Vector?
 *  TODO estimate of number of recursions: plot run time, memory usage, no. of recursions for Vector(1, 2, 3, 4, ..., n)
 *  	Memory usage: construct collection, call System.gc, open Eclipse MAT 
 *  TODO is it possible to encode the situation as BitSet?
 *  TODO is it possible to save the intermediate calculations?
 *  TODO should I save all the vectors in a single hashmap to prevent garbage collection?
 *  TODO How much memory does each node have?
 *  TODO If I run 8 copies of the algorithm, one per core, will the node run out of memory? If so, should I parallelize the algorithm?
 */
object SchubertFactory {
	type Monomial = Pair[Int, Vector[Int]]
	type Polynomial = ListBuffer[Monomial]
	type Permutation = Vector[Int]
	
	def time[R](block: => R): R = {
	    val t0 = System.nanoTime()
	    val result = block
	    val t1 = System.nanoTime()
	    println("Elapsed time: " + Math.round((t1 - t0)/1000.0) + "μs")
	    result
	}
	def test() = {	    
	    Vector.range(0, 20).foreach(
	            _ =>
	    		time { SchubertFactory.schubertPolynomial(Vector(3, 1, 4, 5, 2)) }
	    		)
	    Thread sleep 200
	    println("===")
	    Vector.range(0, 10).foreach( _ => {
		    time { SchubertFactory.schubertPolynomial(Vector(9, 3, 5, 1, 10, 2, 4, 8, 6, 7)) }
		    time { SchubertFactory.schubertPolynomial(Vector(8, 2, 4, 9, 1, 10, 3, 6, 7, 5)) }
		    time { SchubertFactory.schubertPolynomial(Vector(9, 1, 3, 4, 2, 10, 7, 8, 6, 5)) }
		    time { SchubertFactory.schubertPolynomial(Vector(6, 2, 3, 7, 1, 4, 8, 10, 5, 9)) }
	    })
	    println("====")
	    Thread sleep 200
	    Vector.range(0, 10).foreach( _ => {
	        time { SchubertFactory.schubertPolynomial(Vector(10, 5, 2, 4, 15, 1, 3, 16, 12, 13, 9, 6, 7, 14, 8, 11))}
	        time { SchubertFactory.schubertPolynomial(Vector(2, 7, 13, 10, 5, 9, 1, 12, 16, 13, 8, 4, 3, 6, 11, 15))}
	        time { SchubertFactory.schubertPolynomial(Vector(1, 5, 4, 10, 3, 2, 12, 13, 16, 8, 7, 11, 9, 15, 9, 6))}
	    })
	    Vector.range(0, 20).foreach(
	            _ =>
	    		time { SchubertFactory.schubertPolynomial(Vector(3, 1, 4, 5, 2)) }
	    		)
	    Thread sleep 200
	    Vector.range(0, 20).foreach(
	            _ =>
	    		time { SchubertFactory.schubertPolynomial(Vector(9, 3, 5, 1, 10, 2, 4, 8, 6, 7)) }
	    		)
	    println(SchubertFactory.schubertPolynomial(Vector(1,4,3,2)))
	    println(SchubertFactory.schubertPolynomial(Vector(2,4,1,3)))
	    println(SchubertFactory.schubertPolynomial(Vector(1,3,4,2)))
	    println(SchubertFactory.schubertPolynomial(Vector(1,4,2,3)))
	    println(SchubertFactory.schubertPolynomial(Vector(1,2,3,4)))
	    println(SchubertFactory.schubertPolynomial(Vector(2,1,3,4)))
	    println(SchubertFactory.schubertPolynomial(Vector(4,3,2,1)))
	}
	def readPermutation(): Permutation = {
	    println("Schubert Polynomial Calculator")
	    println("Please enter the number of symbols: ")
	    val n = readInt()
	    return Vector.tabulate(n){i => { 
	        printf("%d => ", i+1)
	        readInt()
	    }} 
	}
	def isIdentity(perm: Permutation): Boolean = {
	    var i: Int = 0
	    while (i < perm.length) {
	        i += 1
	        if (perm(i) != i) return false
	    }
	    return true
	}
	
	// Makes no attempt to check that the Permutation is actually a Permutation
	// i.e., that it contains every number 1..n exactly once
	def schubertPolynomial(perm: Permutation): Polynomial = { 
	    if (isIdentity(perm)) return ListBuffer((1, Vector(0)))
	    
	    val leadFactor: Vector[Int] = Vector.fill(perm.length){0}
	    return schubertAlgorithm(leadFactor, 0, perm.length - 1, perm)
	}
	def schubertAlgorithm(leadFactor: Vector[Int], index: Int, exponent: Int, perm: Permutation): Polynomial = {
		val result: Polynomial = ListBuffer()
		if (leadFactor.length == 0) {
			println("Error in algorithm(): leadTerm was 0.")
			return result
		}
		if (perm.length == 2) {
			if (perm(0) == 2) {
				result.append((1, leadFactor.updated(index,leadFactor(index)+1)))
			} else {
			    result.append((1, leadFactor))
			}
		} else if (perm(0) == perm.length) {
		    val newPerm: Permutation = perm.drop(1)
		    val newLeadFactor: Vector[Int] = leadFactor.updated(index, exponent)
		    result.appendAll(schubertAlgorithm(newLeadFactor, index+1, newPerm.length - 1, newPerm))
		} else {
		    var max: Int = perm.length + 1
		    var i: Int = 1
		    while (i < perm.length) {
		        if (perm(i) < max && perm(i) > perm(0)) {
		        	max = perm(i)
		        	val newPerm: Permutation = perm.updated(0, perm(i)).updated(i, perm(0))
		            result.appendAll(schubertAlgorithm(leadFactor, index, exponent-1, newPerm))
		        }
		        i += 1
		    }
		}
		return result
	}
}
