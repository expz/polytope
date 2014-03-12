package polytope

import scala.collection.mutable.ArrayBuffer
import scala.collection.immutable.Vector
import scala.collection.mutable.HashMap

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
	type Polynomial = ArrayBuffer[Long]
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
	
	@inline
	def addInPlace(p1: Polynomial, p2: Polynomial) = p1.appendAll(p2)
	
	@inline
	def addInPlace(p1: Polynomial, t: Term) = p1.append(t)
	
	@inline
	def isZero(p: Polynomial): Boolean = {
		for (t <- p) {
			if (t != 0L) return false
		}
		return true
	}
	
	@inline
	def collectTerms(p: Polynomial): HashMap[Long, Int] = {
		val hm = HashMap[Long,Int]()
		p.foreach(t => hm(t) = hm.getOrElse(t, 0) + 1)
		return hm
	}
	
	def polyToString(p: Polynomial): StringBuilder = {
		if (isZero(p)) "0"
		hashMapToString(collectTerms(p))
	}
	
	def hashMapToString(hm: HashMap[Term, Int]): StringBuilder = {
		hm.foldLeft(new StringBuilder)((s,kv) => {
			if (s.size != 0) {
				if (kv._2 != 1) s.appendAll(" + " + kv._2.toString + "*" + termToString(kv._1))
				else s.appendAll(" + " + termToString(kv._1))
			} 
			else {
				if(kv._2 != 1) s.appendAll(kv._2.toString + "*" + termToString(kv._1))
				else s.appendAll(termToString(kv._1))
			}
			s
		})
	}
	
	def isIdentity(perm: Permutation): Boolean = {
	    var i: Int = 0
	    while (i < perm.length) {
	        if (perm(i) != i+1) return false
	        i += 1
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
	    if (isIdentity(perm)) return ArrayBuffer[Term]()
	    
	    val leadFactor: Term = 0L
	    return schubertAlgorithm(leadFactor, 0, perm.length-1, perm)
	}
	def schubertAlgorithm(leadFactor: Term, index: Int, exponent: Int, perm: Permutation): Polynomial = {		
		// The code actually runs slower when you only define result when its needed
		val result = ArrayBuffer[Term]()
		
		// Set limits of the optimized code
		/*
		require(index < 16)
		require(exponent < 16)
		require(perm.length < 16)
		*/
		if (perm.length == 2) {
			if (perm(0) == 2) {
				result.append(incExp(leadFactor, index))
			} else {
				result.append(leadFactor)
			}
		} else if (perm(0) == perm.length) {
		    val newPerm: Permutation = perm.drop(1)
		    val newLeadFactor: Term = changeExp(leadFactor, index, exponent)
		    return schubertAlgorithm(newLeadFactor, index+1, newPerm.length - 1, newPerm)
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
		/*
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
	    * 
	    */
		Vector.range(0, 10).foreach( _ => {
		    time { SchubertFactory.schubertPolynomial(Vector(9, 3, 5, 1, 10, 2, 4, 8, 6, 7)) }
		    time { SchubertFactory.schubertPolynomial(Vector(8, 2, 4, 9, 1, 10, 3, 6, 7, 5)) }
		    time { SchubertFactory.schubertPolynomial(Vector(9, 1, 3, 4, 2, 10, 7, 8, 6, 5)) }
		    time { SchubertFactory.schubertPolynomial(Vector(6, 2, 3, 7, 1, 4, 8, 10, 5, 9)) }
	    })
	    
	    val shortmed = Vector(
	    		Vector[Byte](2, 1),
	    		Vector[Byte](3, 2, 1),
	    		Vector[Byte](4, 3, 2, 1),
	    		Vector[Byte](5, 4, 3, 1, 2),
	    		Vector[Byte](6, 5, 4, 1, 2, 3),
	    		Vector[Byte](7, 6, 5, 1, 2, 3, 4),
	    		Vector[Byte](8, 7, 6, 1, 2, 3, 5, 4),
	    		Vector[Byte](9, 8, 7, 1, 2, 3, 6, 5, 4),
	    		Vector[Byte](10, 9, 8, 1, 2, 3, 7, 6, 5, 4),
	    		Vector[Byte](11, 10, 9, 1, 2, 3, 8, 7, 6, 4, 5),
	    		Vector[Byte](12, 11, 10, 1, 2, 3, 9, 8, 7, 4, 5, 6),
	    		Vector[Byte](13, 12, 11, 1, 2, 3, 10, 9, 8, 4, 5, 6, 7),
	    		Vector[Byte](14, 13, 12, 1, 2, 3, 11, 10, 9, 4, 5, 6, 8, 7),
	    		Vector[Byte](15, 14, 13, 1, 2, 3, 12, 11, 10, 4, 5, 6, 9, 8, 7),
	    		Vector[Byte](16, 15, 14, 1, 2, 3, 13, 12, 11, 4, 5, 6, 10, 9, 8, 7)
	    		)
	    val med = Vector(
	    		Vector[Byte](2, 1),
	    		Vector[Byte](3, 2, 1),
	    		Vector[Byte](4, 3, 1, 2),
	    		Vector[Byte](5, 4, 1, 2, 3),
	    		Vector[Byte](6, 5, 1, 2, 4, 3),
	    		Vector[Byte](7, 6, 1, 2, 5, 4, 3),
	    		Vector[Byte](8, 7, 1, 2, 6, 5, 3, 4),
	    		Vector[Byte](9, 8, 1, 2, 7, 6, 3, 4, 5),
	    		Vector[Byte](10, 9, 1, 2, 8, 7, 3, 4, 6, 5),
	    		Vector[Byte](11, 10, 1, 2, 9, 8, 3, 4, 7, 6, 5),
	    		Vector[Byte](12, 11, 1, 2, 10, 9, 3, 4, 8, 7, 5, 6),
	    		Vector[Byte](13, 12, 1, 2, 11, 10, 3, 4, 9, 8, 5, 6, 7),
	    		Vector[Byte](14, 13, 1, 2, 12, 11, 3, 4, 10, 9, 5, 6, 8, 7),
	    		Vector[Byte](15, 14, 1, 2, 13, 12, 3, 4, 11, 10, 5, 6, 9, 8, 7),
	    		Vector[Byte](16, 15, 1, 2, 14, 13, 3, 4, 12, 11, 5, 6, 10, 9, 7, 8)
	    		)
	    val long = Vector(
	    		Vector[Byte](2, 1),
	    		Vector[Byte](3, 1, 2),
	    		Vector[Byte](4, 1, 3, 2),
	    		Vector[Byte](5, 1, 4, 2, 3),
	    		Vector[Byte](6, 1, 5, 2, 4, 3),
	    		Vector[Byte](7, 1, 6, 2, 5, 3, 4),
	    		Vector[Byte](8, 1, 7, 2, 6, 3, 5, 4),
	    		Vector[Byte](9, 1, 8, 2, 7, 3, 6, 4, 5),
	    		Vector[Byte](10, 1, 9, 2, 8, 3, 7, 4, 6, 5),
	    		Vector[Byte](11, 1, 10, 2, 9, 3, 8, 4, 7, 5, 6),
	    		Vector[Byte](12, 1, 11, 2, 10, 3, 9, 4, 8, 5, 7, 6),
	    		Vector[Byte](13, 1, 12, 2, 11, 3, 10, 4, 9, 5, 8, 6, 7),
	    		Vector[Byte](14, 1, 13, 2, 12, 3, 11, 4, 10, 5, 9, 6, 8, 7),
	    		Vector[Byte](15, 1, 14, 2, 13, 3, 12, 4, 11, 5, 10, 6, 9, 7, 8),
	    		Vector[Byte](16, 1, 15, 2, 14, 3, 13, 4, 12, 5, 11, 6, 10, 7, 9, 8)
	    		)
	    
	    def testPerm(p: Permutation) = {
			val result = time { SchubertFactory.schubertPolynomial(p) }
			println("ArrayBuffer Length: " + result.length)
			println("Number of Terms: " + collectTerms(result).size)
		}
		
		def testPerms(ps: Vector[Permutation]) = {
			for (k <- (0 to 11)) {
				testPerm(ps(k))
			}
		}
		
		testPerms(shortmed)
		testPerms(med)
		testPerms(long)
	}
}
