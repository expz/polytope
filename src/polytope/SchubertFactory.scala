package polytope

import scala.collection.mutable.HashMap
import scala.collection.mutable.HashSet
import scala.collection.mutable.ListBuffer
import scala.collection.immutable.Vector

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
	type Polynomial = HashMap[Long, Int]
	type Term = Long
	type Permutation = Vector[Byte]
	
	def readPermutation(): Permutation = {
	    println("Schubert Polynomial Calculator")
	    println("Please enter the number of symbols: ")
	    val n = readInt()
	    return Vector.tabulate(n){i => { 
	        printf("%d => ", i+1)
	        readByte()
	    }} 
	}
	
	def addInPlace(p1: Polynomial, p2: Polynomial) = {
		p2.foreach(kv => p1(kv._1) = p1.getOrElse(kv._1, 0) + kv._2)
	}
	def addInPlace(p1: Polynomial, t: Term) = {
		p1(t) = p1.getOrElse(t, 0) + 1
	}
	def isZero(p: Polynomial): Boolean = {
		for (t <- p.keys) {
			if (t != 0L && p(t) != 0) return false
		}
		return true
	}
	def polyToString(p: Polynomial): String = {	
		if (isZero(p)) "0"
		p.foldLeft("")((s,kv) => {
			if (s != "") {
				if (kv._2 != 1) s + " + " + kv._2.toString + "*" + termToString(kv._1)
				else s + " + " + termToString(kv._1)
			} 
			else {
				if(kv._2 != 1) kv._2.toString + "*" + termToString(kv._1)
				else termToString(kv._1)
			}
		})
	}
	def isIdentity(perm: Permutation): Boolean = {
	    var i: Int = 0
	    while (i < perm.length) {
	        i += 1
	        if (perm(i) != i) return false
	    }
	    return true
	}
	 
	// A term with at most 16 variables whose exponents are at most 15
	// Each block of 4 bits represents a single exponent

	// Increment the i^th exponent by one (0 <= i <= 15)
	
	@inline
	def incExp(t: Term, i: Int): Term = t + (1L << i*4)
	
	@inline
	def changeExp(t: Term, i: Int, exp: Int): Term = (~(15L << i*4) & t) + (exp << i*4)
	
	@inline
	def getExp(t: Term, i: Int): Int = ((t >> i*4) & 15L).toInt
	
	@inline
	def isZero(t: Term): Boolean = t == 0L
	
	@inline
	def termToString(t: Term): String = {
		var i = 0
		var s = ""
		while (i < 16) {
			val exp = (t >> i*4) & 15L 
			if (exp != 0L) {
				if (s != "") s += "*"
				s += "x" + i
				if (exp > 1)
					s += "^" + exp
			}
			i += 1
		}
		return s
	}
	
	// Makes no attempt to check that the Permutation is actually a Permutation
	// i.e., that it contains every number 1..n exactly once
	def schubertPolynomial(perm: Permutation): Polynomial = { 
	    if (isIdentity(perm)) return HashMap[Term, Int]()
	    
	    val leadFactor: Term = 0L
	    return schubertAlgorithm(leadFactor, 0, perm.length-1, perm)
	}
	def schubertAlgorithm(leadFactor: Term, index: Int, exponent: Int, perm: Permutation): Polynomial = {
		val result = HashMap[Term, Int]()
		
		// Set limits of the optimized code
		/*
		require(index < 16)
		require(exponent < 16)
		require(perm.length < 16)
		*/
		if (perm.length == 2) {
			if (perm(0) == 2) {
				addInPlace(result, incExp(leadFactor, index))
			} else {
				addInPlace(result, leadFactor)
			}
		} else if (perm(0) == perm.length) {
		    val newPerm: Permutation = perm.drop(1)
		    val newLeadFactor: Term = changeExp(leadFactor, index, exponent)
		    addInPlace(result, schubertAlgorithm(newLeadFactor, index+1, newPerm.length - 1, newPerm))
		} else {
		    var max: Int = perm.length + 1
		    var i: Int = 1
		    while (i < perm.length) {
		        if (perm(i) < max && perm(i) > perm(0)) {
		        	max = perm(i)
		        	val newPerm: Permutation = perm.updated(0, perm(i)).updated(i, perm(0))
		            addInPlace(result, schubertAlgorithm(leadFactor, index, exponent-1, newPerm))
		        }
		        i += 1
		    }
		}
		return result
	}
	
	///////////////////////////////////
	// Tests
	///////////////////////////////////
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
	    //time { SchubertFactory.schubertPolynomial(Vector(10, 5, 2, 4, 15, 1, 3, 16, 12, 13, 9, 6, 7, 14, 8, 11))}
	    //time { SchubertFactory.schubertPolynomial(Vector(2, 7, 13, 10, 5, 9, 1, 12, 16, 13, 8, 4, 3, 6, 11, 15))}
	    //time { SchubertFactory.schubertPolynomial(Vector(1, 5, 4, 10, 3, 2, 12, 13, 16, 8, 7, 11, 9, 15, 9, 6))}
	    
	    Vector.range(0, 20).foreach(
	            _ =>
	    		time { SchubertFactory.schubertPolynomial(Vector(3, 1, 4, 5, 2)) }
	    		)
	    Thread sleep 200
	    Vector.range(0, 20).foreach(
	            _ =>
	    		time { SchubertFactory.schubertPolynomial(Vector(9, 3, 5, 1, 10, 2, 4, 8, 6, 7)) }
	    		)
	    println(polyToString(SchubertFactory.schubertPolynomial(Vector(1,4,3,2))))
	    println(polyToString(SchubertFactory.schubertPolynomial(Vector(2,4,1,3))))
	    println(polyToString(SchubertFactory.schubertPolynomial(Vector(1,3,4,2))))
	    println(polyToString(SchubertFactory.schubertPolynomial(Vector(1,4,2,3))))
	    println(polyToString(SchubertFactory.schubertPolynomial(Vector(1,2,3,4))))
	    println(polyToString(SchubertFactory.schubertPolynomial(Vector(2,1,3,4))))
	    println(polyToString(SchubertFactory.schubertPolynomial(Vector(4,3,2,1))))
	}
}
