package object polytope {

import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.HashMap
import scala.reflect.ClassTag

type Term = Long
type Polynomial = ArrayBuffer[Long]

/*
 * Permutation type
 *   !!!Permutations are not checked for correctness
 *   Includes a function for acting on an array
 */
type Permutation = Array[Int]

def main(args: Array[String]) {
  println(PermutationFactory.shuffles(Array(1)).length)
  //val perm = SchubertFactory.readPermutation()
  //val perm = Vector(3, 1, 4, 2) 
  //SchubertFactory.test()
}

def inverse(p: Permutation): Permutation 
            = Array.tabulate[Int](p.length)(n => p.indexWhere(_ == n+1) + 1)

def act[A](p: Permutation, a: Array[A])(implicit tag: ClassTag[A]): Array[A] = {
  assert(a.length >= p.length)
  val pa = ArrayBuffer[A]()
  var i = 0
  while (i < p.length) {
    pa += a(p(i)-1)
    i += 1
  }
  return pa.toArray[A]
}

/*
 * toLehmerCode() -- Returns the Lehmer code of the permutation.
 *
 *                    The Lehmer code of the permutation p is
 *                      (c(1), c(2), ..., c(n))
 *                    where c(i) = |{ j > i | p(j) < p(i) }|
 */
def toLehmerCode(p: Permutation): ArrayBuffer[Int] = {
  // A routine to sum an ArrayBuffer faster than a foreach( sum += _ )
  var i = 0
  var k = 0
  var N = 0
  def sum(ab: ArrayBuffer[Int]): Int = {
    i = 0
    N = 0
    while (i < ab.length) { N += ab(i); i += 1 }
    return N
  }
  
  val code = ArrayBuffer[Int]()
  val checked = ArrayBuffer.fill[Int](p.length)(1)
  var j = 0
  while (j < p.length) {
    k = p(j)
    checked(k-1) = 0
    code += sum(checked.take(k))
    j += 1
  }
  return code
}

/*
 * reducedWord() -- Decompose a permutation into a minimal length product of
 *                  transpositions.
 */
def reducedWord(p: Permutation): Array[Int] = {
  val code = toLehmerCode(p)
  for (i <- Array.range(0, code.length); j <- Array.range(0, code(i))) yield i+code(i)-j
}

def isInteger(f: Polynomial): Boolean = {
  if (f.isEmpty) return true
  var i = 0
  while (i < f.length) {
    if (f(i) != 0L) return false
    i += 1
  }
  return true
}

def isInteger(f: HashMap[Long, Int]): Boolean = {
  if (f.isEmpty) return true
  var i = 0
  f.foreach( keyVal => if (keyVal._1 != 0L && keyVal._2 != 0) return false )
  return true
}

def isZero(f: HashMap[Long, Int]): Boolean = {
  if (f.isEmpty) return true
  f.foreach( keyVal => if (keyVal._2 != 0) return false)
  return true
}

/*
 * subst() -- Substitute x_i + y_j for z_T(i,j) where T is a rectangular tableau
 * 
 *            NOTE: Changes the polynomial in place
 */
def subst(f: HashMap[Long, Int], T: RectTableau): HashMap[Long, Int] = {
  assert(T.rows*T.cols > 0 && T.rows*T.cols <= 16)
  val substF = HashMap[Long, Int]()
  var substTerm: HashMap[Long, Int] = null

  // Perform substitution for every term of f
  for (term <- f.keys) {
    // Initialize substituted term to 1
    substTerm = HashMap[Long, Int](0L -> f(term))
    
    // For every variable in term
    var n = 0
    while (n < T.rows*T.cols) {
      // Multiply substTerm by (x_i + y_j)^exp where T(i, j) = n
      substTerm = multiply(substTerm, binomialExpansion(T(n)._1-1, T(n)._2-1 + T.rows, getExp(term, n)))
      n += 1
    }
    addInPlace(substF, substTerm)
  }
  return substF
}

/*
 * addInPlace() -- Adds g to f and places the result in f
 */
def addInPlace(f: HashMap[Long, Int], g: HashMap[Long, Int]) = {
  for (term <- g.keys) {
    f(term) = f.getOrElse(term, 0) + g(term)
  }
}

// WARNING: No check that exponents are between 0 and 15
def multiply(p1: HashMap[Long, Int], p2: HashMap[Long, Int]): HashMap[Long, Int] = {
  val prod = HashMap[Long, Int]()
  for (term1 <- p1.keys) {
    for (term2 <- p2.keys) {
      addInPlace(prod, multiplyTerms(term1, term2), p1(term1)*p2(term2))
    }
  }
  return prod
}

// WARNING: No check that exponents are between 0 and 15
def multiplyTerms(t1: Term, t2: Term): Term = t1 + t2

def binomialExpansion(firstvar: Int, secondvar: Int, exp: Int): HashMap[Long, Int] = {
  assert(0 <= firstvar && firstvar < 16)
  assert(0 <= secondvar && secondvar < 16)
  assert(0 <= exp && exp < 16)
  if (exp == 0) return HashMap[Long, Int](0L->1)
  
  val p = HashMap[Long, Int]()
  var term = 0L
  var k = 0
  while (k <= exp) {
    term = 0L
    p += addToExp(addToExp(term, firstvar, k), secondvar, exp-k) -> binomial(exp, k)
    k += 1
  }
  return p
}

/*
 * Calculate the binomial coefficient n Choose k
 * 
 */
def binomial(n: Int, k: Int): Int = {
  var r = 1
  var d = n - k
  var nn = n; var kk = k
  
  /* choose the smaller of k and n - k */
  if (d > kk) { kk = d; d = nn - kk } 
  while (nn > kk) {
    r *= nn
    nn -= 1
    /* divide (n - k)! as soon as we can to delay overflows */
    while (d > 1 && (r % d == 0)) {
      r /= d
      d -= 1
    }
  }
  return r
}

/*
 * delta() -- The multiple divided difference del_u(f) acting on the sequence 
 *            of variables starting with variable number offset.
 *            
 *            NOTE: Changes the polynomial in place.
 */
def delta(u: Permutation, hm: HashMap[Long, Int], offset: Int)
    : HashMap[Long, Int] = {
  assert(offset >= 0)
  assert(u.length + offset <= 16)
  
  // The successive finite differences are stored in f and deltaF
  var f = hm
  var deltaF: HashMap[Long, Int] = null
  
  // Convert the permutation to a sequence of transpositions of the form (i,i+1)
  // The permutations are interpreted as applying to the variables beginning
  // with offset
  val trans = reducedWord(u).map(_ + offset)
  
  // loop variables
  var (i, k, d, coeff) = (0, 0, 0, 0)
  var newTerm = 0L
  
  // Loop through each transposition
  while (i < trans.length) {
    // k is indexed from 1
    k = trans(i)
    
    // Assign a new polynomial to deltaF
    deltaF = HashMap[Long, Int]()
    
    // If f is now an integer, then we're done
    if (isInteger(f)) return deltaF
    
    // Calculate the finite difference for each monomial separately
    for (monom <- f.keys) {
      coeff = f(monom)
      // Calculate the difference between the exponents of x[k] and x[k+1]
      d = getExp(monom, k-1) - getExp(monom, k)
      if (d > 0) {
        // One monomial becomes d monomials
        for (j <- 0 until d) {
          newTerm = addToExp(addToExp(monom, k-1, -d+j), k, d-1-j)
          deltaF(newTerm) = deltaF.getOrElse(newTerm, 0) + coeff
        }
      } else if (d < 0) {
        d = -d
        // One monomial becomes d monomials
        for (j <- 0 until d) {
          newTerm = addToExp(addToExp(monom, k, -1-j), k-1, j) 
          deltaF(newTerm) = deltaF.getOrElse(newTerm, 0) - coeff
        }
      }
    }
    f = deltaF
    i += 1
  }
  return f
}

@inline
def addInPlace(p1: Polynomial, p2: Polynomial) = p1.appendAll(p2)

@inline
def addInPlace(p1: Polynomial, t: Term) = p1.append(t)

@inline
def addInPlace(p1: HashMap[Long, Int], t: Term, coeff: Int) = { 
  p1(t) = p1.getOrElse(t, 0) + coeff  
}

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

def polyToLatex(p: Polynomial): StringBuilder = {
  if (isZero(p)) "0"
  hashMapToLatex(collectTerms(p))
}

def hashMapToString(hm: HashMap[Term, Int], xvars: Int, yvars: Int): StringBuilder = {
  return hashMapToString(hm, Array.tabulate[String](xvars)(n => "x" + (n+1)) ++ Array.tabulate[String](yvars)(n => "y" + (n+1)))
}

def hashMapToString(hm: HashMap[Term, Int], varnames: Array[String]): StringBuilder = {
  if (hm.isEmpty) return new StringBuilder("0")
  val s = hm.foldLeft(new StringBuilder)((s,kv) => {
    if (s.size != 0) {
      if (kv._2 != 0) { 
        if (kv._2 != 1) {
          s.appendAll(" + " + kv._2.toString)
          if (kv._1 != 0L) s.appendAll("*" + termToString(kv._1, varnames))
        }
        else {
          s.appendAll(" + ")
          if (kv._1 != 0L) s.appendAll(termToString(kv._1, varnames))
          else s.append('1')
        }
        
      }
    } 
    else {
      if (kv._2 != 0) {
        if(kv._2 != 1) {
          s.appendAll(kv._2.toString)
          if (kv._1 != 0L) s.appendAll("*" + termToString(kv._1, varnames))
        }
        else {
          if (kv._1 != 0L) s.appendAll(termToString(kv._1, varnames))
          else s.append('1')
        }
      }
    }
    s
  })
  if (s.length == 0) return new StringBuilder("0")
  else return s  
}

def hashMapToString(hm: HashMap[Term, Int]): StringBuilder = {
  return hashMapToString(hm, Array.tabulate[String](16)(n => "z" + (n+1)))
}

def hashMapToLatex(hm: HashMap[Term, Int]): StringBuilder = {
  hm.foldLeft(new StringBuilder)((s, keyval) => {
    if (s.size != 0) {
      if (keyval._2 != 1) s.appendAll(" + " + keyval._2.toString + termToLatex(keyval._1))
      else s.appendAll(" + " + termToLatex(keyval._1))
    }
    else {
      if (keyval._2 != 1) s.appendAll(keyval._2.toString + termToLatex(keyval._1))
      else s.appendAll(termToLatex(keyval._1))
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

// assert(getExp(t, varnum) < 15)
@inline
def incExp(t: Term, varnum: Int): Term = t + (1L << varnum*4)

// assert(getExp(t, varnum) > 0)
@inline
def decExp(t: Term, varnum: Int): Term = t - (1L << varnum*4)

// assert(n + getExp(t, varnum) >= 0)
// assert(n + getExp(t, varnum) < 16)
@inline
def addToExp(t: Term, varnum: Int, n: Int): Term = t + (n.toLong << varnum*4)

@inline
def changeExp(t: Term, i: Int, exp: Int): Term = (~(15L << i*4) & t) + (exp << i*4)

// i = 0 gives the exponent of the first variable!
@inline
def getExp(t: Term, i: Int): Int = ((t >> i*4) & 15L).toInt

@inline
def isZero(t: Term): Boolean = t == 0L

@inline
def termToString(t: Term): String = {
  var i = 0
  var s = ""
  while (i < 16) {
    val exp = getExp(t, i) 
    if (exp != 0) {
      if (s != "") s += "*"
      s += "x" + i
      if (exp > 1)
        s += "^" + exp
    }
    i += 1
  }
  return s
}

// WARNING: No check that varnames has enough names to describe the term!!
@inline
def termToString(t: Term, varnames: Array[String]): String = {
  var i = 0
  var s = ""
  while (i < 16) {
    val exp = getExp(t, i) 
    if (exp != 0) {
      if (s != "") s += "*"
      s += varnames(i)
      if (exp > 1)
        s += "^" + exp
    }
    i += 1
  }
  return s  
}

@inline
def termToLatex(t: Term): String = {
  var i = 0
  var s = ""
  while (i < 16) {
    val exp = getExp(t, i)
    if (exp != 0L) {
      s += "x_{" + i + "}"
      if (exp > 1)
        s += "^{" + exp + "}"
    }
    i += 1
  }
  return s
}


}