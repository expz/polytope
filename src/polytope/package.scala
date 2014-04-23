package object polytope {

//////////////////////////////////////////
// Version
val bigVersion = 0
val littleVersion = 95
val versionString = "polytope %d.%02d".format(bigVersion, littleVersion)
//
//////////////////////////////////////////

import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.HashMap
import scala.collection.mutable.ListBuffer
import scala.reflect.ClassTag

// Scallop library for command line parsing
import org.rogach.scallop._
import org.rogach.scallop.exceptions._

import scala.io._

type Term = Long
type Polynomial = ArrayBuffer[Long]

/*
 * Permutation type
 *   !!!Permutations are not checked for correctness
 *   Includes a function for acting on an array
 */
type Permutation = Array[Int]



def main(args: Array[String]) {
  
  val versionOutput = versionString + "\n" +
    "Copyright (C) 2014 ETH Zürich\n" +
    "License Simplified BSD Style <http://intra.csb.ethz.ch/tools/LICENSE.txt>\n" +
    "This is free software: you are free to change and redistribute it.\n" +
    "There is NO WARRANTY or GUARANTEE OF FITNESS FOR A PARTICULAR PURPOSE,\n" +
    "to the extent permitted by law.\n" +
    "\n" +
    "Written by Jonathan Skowera."
  try {
    object Conf extends ScallopConf(args.toList) {
      banner("""Usage: polytope [OPTIONS] DIM_A DIM_B
               |Calculate and print the vertices of the entanglement polytope for mixed states
               |of two distinguishable particles with DIM_A and DIM_B degrees of freedom
               |""".stripMargin)
  
      val mixed = new Subcommand("mixed") {
        val dims = trailArg[List[Int]](name="dims", required=true, hidden=true, 
                        validate=(L => L.map(_ > 0).reduce(_ && _)))
        val f = opt[String]("cubiclefile",
                             short='f',
                             descr="Only calculate inequalities associated to a particular cubicle (Useful for splitting up calculation into parts)")
        val all = tally("all", 
                        descr="Output all calculated polytope features (TODO)")
        val plaintext = tally("plaintext", descr = 
          "Print equations in plain text (Default is LaTeX) (TODO)")
      }
      val pure = new Subcommand("pure") {
        val dims = trailArg[List[Int]](name="dims", required=true, hidden=true,
                        validate=(L => L.map(_ > 0).reduce(_ && _)))
        /*
        val dimA = trailArg[Int](name="dimA", required=true, hidden=true)
        val dimB = trailArg[Int](name="dimB", required=true, hidden=true)
        val dimC = trailArg[Int](name="dimC", required=false, hidden=true)
        val dimD = trailArg[Int](name="dimD", required=false, hidden=true)
        * 
        */
        val all = tally("all", 
                        descr="Output all calculated polytope features (TODO)")
        val plaintext = tally("plaintext", descr = 
          "Print equations in plain text (Default is LaTeX) (TODO)")
      }
      val cubicles = new Subcommand("cubicles") {
        val dimA = trailArg[Int](name="dimA", required=true, hidden=true,
                                 validate=(_>=0))
        val dimB = trailArg[Int](name="dimB", required=true, hidden=true,
                                 validate=(_>=0))
        // Includes permutations and edges corresponding to inequalities
        // Includes vertices of polytope
        // Includes inequalities of polytope
      }
      val help = tally("help", noshort=true, descr="Display this help and exit")
      val computer = tally("computer", descr =
          "Output in computer readable format " +
          "(overrides other options)")
      val verbose = tally("verbose", 
                          descr="Print notifications of progress (TODO)")
      val version = tally("version", 
                          noshort=true, 
                          descr="Output version information and exit")
      footer("\n" +
             "Report bugs to jskowera@gmail.com\n" +
             "Source code available at " +
             "<https://github.com/expz/entanglement-polytopes/>")
      
    }
    
    if (Conf.help() > 0) {
      Conf.builder.printHelp
      return
    } else if (Conf.version() > 0) {
      println(versionOutput)
      return
    }
    val printProgress = Conf.verbose.get match 
                          { case None => false; case _ => true }
    
    Conf.subcommand match {
      /**************************************
       * Calculate a mixed polytope
       */
      case Some(Conf.mixed) => {
        val dims = if (Conf.mixed.dims().length == 1) 
                     List(Conf.mixed.dims()(0), 0) 
                   else 
                     Conf.mixed.dims()

        if (dims.length > 2) {
          println("polytope: feature not implemented; mixed accepts at most two dimensions")
          return
        } 
        
        val cubicles = ListBuffer[RectTableau]()
        if (Conf.mixed.f.isSupplied) {
          val fileLines = Source.fromFile(Conf.mixed.f()).getLines.toList
          fileLines.foldLeft(cubicles)((LB, str) => if (str.trim().isEmpty()) LB else LB += RectTableau(str))
          if (cubicles.length > 0 && (cubicles(0).rows != dims(0) || cubicles(0).cols != dims(1))) {
            println("polytope: mismatched cubicle shape")
            println("the input file had cubicles of shape " + cubicles(0).rows + "x" + cubicles(0).cols + " but the command line dimensions were " + dims(0) + "x" + dims(1))
            return
          }
        }
        
        /*
        if (!Conf.computer.isSupplied && dims(0)*dims(1) >= 9) {
          println("polytope: the polytope will have dimensions " + dims(0) + "x" + dims(1) + "x" + dims(0)*dims(1))
          println("WARNING: THIS COMPUTATION COULD TAKE OVER 30 MINS ON A SINGLE CORE")
          println("Are you sure you want to continue [Y/n]?")
          var ans = readChar()
          while (ans != 'Y' && ans != 'n') {
            println("Please enter 'Y' for yes or 'n' for no")
            ans = readChar()
          }
          if (ans == 'n') {
            println("polytope: computation aborted at user request")
            return
          }
        } 
        *        
        */       
        
        val ieqs = InequalityFactory.inequalities(dims(0), dims(1), cubicles)
        
        if (Conf.computer() > 0) {
          ieqs.foreach(ieq => println("[" + (ieq.toArray() mkString ",") + "]"))
        } else {
          if (Conf.mixed.plaintext() > 0) {
            // TODO: Print inequalities in plaintext instead of LaTeX
            println(dims(0) + "x" + dims(1) + "x" + dims(0)*dims(1) + " Inequalities\n")
            if (ieqs.size == 0) println("There are no inequalities.")
            else ieqs.foreach(i => println(i.toLatex() + """\\"""))
          } else {
            println(dims(0) + "x" + dims(1) + "x" + dims(0)*dims(1) + " Inequalities\n")
            if (ieqs.size == 0) println("There are no inequalities.")
            else ieqs.foreach(i => println(i.toLatex() + """\\"""))          
          }
        
          if (Conf.mixed.all() > 0) {
            val poly = PolyhedralCone.momentPolyhedron(ieqs)
            
            // Print vertices
            println("\n\nVertices of polytope (normalized to trace zero)\n")
            if (ieqs.size == 0) 
              println("The cone is the whole positive Weyl chamber.")
            else     
              poly.edges().foreach(e => println(e))
              
            // TODO: Print permutations and extremal edges which lead to
            // inequalities
          }
        }
      } 
      
      /**********************************************
       * Calculate a pure polytope
       */
      case Some(Conf.pure) => {
        val dims = if (Conf.mixed.dims().length == 1) 
             List(Conf.mixed.dims()(0), 0)
           else 
             Conf.mixed.dims()

        if (dims.length > 3) {
          println("polytope: feature not implemented; pure accepts at most three dimensions")
          return
        } 
        
        val cubicles = ListBuffer[RectTableau]()
        if (Conf.mixed.f.isSupplied) {
          val fileLines = Source.fromFile(Conf.mixed.f()).getLines.toList
          fileLines.foldLeft(cubicles)((LB, str) => if (str.trim().isEmpty()) LB else LB += RectTableau(str))
          if (cubicles.length > 0 && (cubicles(0).rows != dims(0) || cubicles(0).cols != dims(1))) {
            println("polytope: mismatched cubicle shape")
            println("the input file had cubicles of shape " + cubicles(0).rows + "x" + cubicles(0).cols + " but the command line dimensions were " + dims(0) + "x" + dims(1))
            return
          }
        }
        
        println("polytope: calculation of pure polytope is not implemented")
      }
      
      /**********************************************
       * Calculate all dimA by dimB cubicles
       */
      case Some(Conf.cubicles) => {
        val (dimA, dimB) = (Conf.cubicles.dimA(), Conf.cubicles.dimB())
        
        // Confirm that user wants to do such a large calculation
        if (!Conf.computer.isSupplied && dimA*dimB >= 18) {
          println("polytope: the cubicles will have dimensions " + dimA + "x" + dimB)
          println("WARNING: THIS COMPUTATION COULD TAKE OVER 10 MINS ON A SINGLE CORE")
          println("Are you sure you want to continue [Y/n]?")
          var ans = readChar()
          while (ans != 'Y' && ans != 'n') {
            println("Please enter 'Y' for yes or 'n' for no")
            ans = readChar()
          }
          if (ans == 'n') {
            println("polytope: computation aborted at user request")
            return
          }
        }        
        
        val cs = RectTableau.standardTableaux(dimA, dimB).filter(_.isAdmissible)
        if (Conf.computer.isSupplied) {
          for (c <- cs)
            println("[" + (c.toMatrix map(_ mkString ",") mkString ";") + "]")
        } else {
          if (dimA*dimB == 0) {
            println("The only cubicle corresponds to the empty tableaux.")
          } else {
            println("The cubicles correspond to the following tableaux:\n")
            for (c <- cs) {
              println("-"*((2+dimA*dimB/10)*dimB-1))
              println(c)
            }
            println("-"*((2+dimA*dimB/10)*dimB-1))
          }
        }
      }
      case None => Conf.builder.printHelp
    }

  } catch {
    case e: java.lang.NumberFormatException => {
      println("polytope: the dimensions must be numbers")
    }
  }
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