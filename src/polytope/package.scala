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
import scala.collection.mutable.HashSet
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
  assert(true == false)
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
      banner(
"""Usage: polytope [OPTIONS] (pure|mixed) [CMD-OPTIONS] DIM_A DIM_B
  |Calculate and print data for entanglement polytopes for two distinguishable
  |particles with DIM_A and DIM_B degrees of freedom
  |""".stripMargin)
  
      val mixed = new Subcommand("mixed") {
        val dims = trailArg[List[Int]](name="dims", required=true, hidden=true, 
                        validate=(L => L.map(_ > 0).reduce(_ && _)))
        val f = opt[String]("cubiclefile", 
            short='f',
            descr="Only calculate inequalities associated to a particular " +
                  "cubicle (Useful for splitting up calculation into parts)")
        val cubicles = 
          tally("cubicles", short='u', descr="Print cubicles")
        val edges = 
          tally("edges", short='e', descr="Print integral extremal edges")
        val coeffs = 
          tally("coeffs", short='c', descr="Print nonzero coefficients")
        val ineqs = tally("ineqs", short='i', descr="Print inequalities")
        val vertices = tally("vertices", short='v', descr="Print vertices")
 
        val all = tally("all", 
                        descr="Output all calculated polytope features")
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
        val f = opt[String]("cubiclefile",
                             short='f',
                             descr="Only calculate inequalities associated to a particular cubicle (Useful for splitting up calculation into parts)")
        val cubicles = tally("cubicles", short='u', descr="Print cubicles")
        val potentialIneqs = tally("potentialIneqs", short='p', descr="Count potential inequalities")
        val coeffs = tally("coeffs", short='c', descr="Print nonzero coefficients (TODO)")
        val ineqs = tally("ineqs", short='i', descr="Print inequalities")
        val vertices = tally("vertices", short='v', descr="Print vertices")

        val all = tally("all", 
                        descr="Output all calculated polytope features")
        val plaintext = tally("plaintext", descr = 
          "Print equations in plain text (Default is LaTeX) (TODO)")
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
        println("polytope: feature not implemented; " +
                "mixed accepts at most two dimensions")
        return
      } 
      
      // Prepare list of cubicles
      val cubicles = ListBuffer[RectTableau]()
      // If the cubicles are supplied in a file, then read the file
      if (Conf.mixed.f.isSupplied) {
        val fileLines = Source.fromFile(Conf.mixed.f()).getLines.toList
        // Read data into cubicles ListBuffer
        fileLines.foldLeft(cubicles)((LB, str) => 
          if (str.trim().isEmpty()) LB 
          else LB += RectTableau(str)
        )
        // Check that the cubicle dimensions are correct
        if (cubicles.length > 0 && 
            (cubicles(0).rows != dims(0) || cubicles(0).cols != dims(1))) {
          println("polytope: mismatched cubicle shape")
          println("the input file had cubicles of shape " + 
                  cubicles(0).rows + "x" + cubicles(0).cols + 
                  " but the command line dimensions were " + 
                  dims(0) + "x" + dims(1))
          return
        }
      // Otherwise calculate the cubicles anew
      } else {
        cubicles ++= InequalityFactory.cubiclesDM(dims)
      }
      
      // If we should print the cubicles
      if (Conf.mixed.cubicles() > 0 || Conf.mixed.all() > 0) {
        // If it should be in computer readable form
        if (Conf.computer.isSupplied) {
          println("cubicles:")
          if (!cubicles.isEmpty) {
            println(cubicles(0).csvHeaders)
            cubicles.foreach(c => println(c.toCSV))
          }
        // Otherwise in verbose form
        } else {
          if (dims(0)*dims(1) == 0) {
            println("The only cubicle corresponds to the empty tableaux.")
          } else {
            println("The cubicles correspond to the following tableaux:\n")
            for (c <- cubicles) {
              println("-"*((2+dims(0)*dims(1)/10)*dims(1)-1))
              println(c)
            }
            println("-"*((2+dims(0)*dims(1)/10)*dims(1)-1))
          }
          println()
        }
      }
      
      val calculateEdgesWithAdjacentCubicles = 
        (Conf.mixed.coeffs() > 0 || Conf.mixed.ineqs() > 0 ||
         Conf.mixed.vertices() > 0 || Conf.mixed.all() > 0)
         
      val edgesWithAdjacentCubicles =
        if (calculateEdgesWithAdjacentCubicles) {
          // Need edges with adjacent cubicles
          InequalityFactory.edgesWithAdjacentCubiclesDM(cubicles)
        } else {
          HashMap[ABEdge, ArrayBuffer[RectTableau]]()
        }
      
      val edges =
        if (calculateEdgesWithAdjacentCubicles) {
          HashSet[ABEdge]()
        } else {
          InequalityFactory.edgesDM(cubicles)
        }
      
      // If we should print extremal edges
      // Only prints edges (not cubicles which they correspond to)
      if (Conf.mixed.edges() > 0 || Conf.mixed.all() > 0) {
        if (calculateEdgesWithAdjacentCubicles) {
          if (Conf.computer.isSupplied) {
            println("edges:")
            println(edgesWithAdjacentCubicles.keys.head.csvHeaders)
            for (e <- edgesWithAdjacentCubicles) println(e._1.toCSV)
          } else {
            println("The above cubicles correspond to the following edges:\n")
            for (e <- edgesWithAdjacentCubicles) println(e._1.toString)
            println()
          }
        } else {
          if (Conf.computer.isSupplied) {
            println("edges:")
            println(edges.head.csvHeaders)
            for (e <- edges) println(e.toCSV)
          } else {
            println("The above cubicles correspond to the following edges:\n")
            for (e <- edges) println(e.toString)
            println()
          }
        }
      }
      
      // Calculate all nonzero coefficients. Much more than needed for ineqs.
      val coeffs =
        if (Conf.mixed.coeffs() > 0 || Conf.mixed.all() > 0) {
          if (Conf.mixed.edges() > 0 || Conf.mixed.all() > 0) {
            InequalityFactory.coeffsDM(edgesWithAdjacentCubicles)
          } else {
            InequalityFactory.coeffsDM(cubicles)
          }
        } else {
          ArrayBuffer[PFCoefficient]()
        }
      
      // If we calculated coefficients, then print them
      if (Conf.mixed.coeffs() > 0 || Conf.mixed.all() > 0) {
        if (Conf.computer() > 0) {
          println("nonzero-coefficients:")
          if (!coeffs.isEmpty) {
            println(coeffs(0).csvHeaders)
            coeffs.foreach(c => println(c.toCSV))
          }
        } else {
          println("Nonzero product flag coefficients:\n")
          coeffs.foreach(c => println(c.toString))
          println()
        }
      }
      
      // Calculate the inequalities if necessary
      val ineqs =
        if (Conf.mixed.ineqs() > 0 || 
            Conf.mixed.vertices() > 0 || 
            Conf.mixed.all() > 0) {
          // Calculate the inequalities given what we have already computed
          if (Conf.mixed.coeffs() > 0 || Conf.mixed.all() > 0) 
            InequalityFactory.ineqsDM(coeffs)
          else if (Conf.mixed.edges() > 0)
            InequalityFactory.ineqsDM(edgesWithAdjacentCubicles)
          else
            InequalityFactory.ineqsDM(cubicles)
        } else {
          HashSet[InequalityDM]()
        }

      // If we should print the inequalities
      if (Conf.mixed.ineqs() > 0 || Conf.mixed.all() > 0) {   
        // If we should print in computer format
        if (Conf.computer() > 0) {
          println("inequalities:")
          if (!ineqs.isEmpty) {
            println(ineqs.head.csvHeaders)
            ineqs.foreach(
                    ineq => println(ineq.toCSV))
          }
        // Else print in readable format
        } else {
          if (Conf.mixed.plaintext() > 0) {
            // TODO: Print inequalities in plaintext instead of LaTeX
            println(dims(0) + "x" + dims(1) + "x" + dims(0)*dims(1) + 
                    " Inequalities\n")
            if (ineqs.size == 0) println("There are no inequalities.")
            else ineqs.foreach(i => println(i.toLatex() + """\\"""))
          } else {
            println(dims(0) + "x" + dims(1) + "x" + dims(0)*dims(1) + 
                    " Inequalities\n")
            if (ineqs.size == 0) println("There are no inequalities.")
            else ineqs.foreach(i => println(i.toLatex() + """\\"""))          
          }
        }
      }
      
      // If we should calculate vertices
      if (Conf.mixed.vertices() > 0 || Conf.mixed.all() > 0) {
        val poly = PolyhedralCone.
                     positiveWeylChamberDM(dims ++ List(dims.product)).
                     intersection(PolyhedralCone(ineqs))
        val (rs, ps) = poly.edges()
        
        // Print vertices
        if (Conf.computer() > 0) {
          println("vertices:")
          if (!ineqs.isEmpty) {
            // CSV Header for vertices (cut off trailing ",const")
            println(ineqs.head.csvHeaders.dropRight(6) + ",type")
            /*
              (1 to dims(0)).map("lambdaA" + _).mkString(",") + "," +
              (1 to dims(1)).map("lambdaB" + _).mkString(",") + "," +
              (1 to dims(0)*dims(1)).map("lambdaAB" + _).mkString(",")
            )*/
            ps.foreach(e => println(e.toCSV + ",vertex"))
          }
          if (!ineqs.isEmpty && !rs.isEmpty)
            rs.foreach(e => println(e.toCSV + ",ray"))
        } else {
          println("\n\nVertices of polytope (normalized to trace " + 
                  -poly.eqs.head.last + ")\n")
          if (ineqs.isEmpty)
            println("The polytope is the whole positive Weyl chamber.")
          else     
            ps.foreach(e => println(e))
          if (!ineqs.isEmpty && !rs.isEmpty) {
            println("\n\nRays of polytope (normalized to trace " + 
                    -poly.eqs.head.last + ")\n")
            rs.foreach(e => println(e))
          }            
        }
      }
    }
    
    /**********************************************
     * Calculate a pure polytope
     */
    case Some(Conf.pure) => {
      val dims = if (Conf.pure.dims().length == 1) 
           List(Conf.pure.dims()(0), 0)
         else 
           Conf.pure.dims()

      if (dims.length > 3) {
        println("polytope: feature not implemented; pure accepts at most three dimensions")
        return
      } 
      
      // FIXME
      val cubicles = ListBuffer[RectTableau]()
      if (Conf.pure.f.isSupplied) {
        val fileLines = Source.fromFile(Conf.pure.f()).getLines.toList
        fileLines.foldLeft(cubicles)(
          (LB, str) => if (str.trim().isEmpty()) LB else LB += RectTableau(str)
        )
        if (cubicles.length > 0 && 
             (cubicles(0).rows != dims(0) || cubicles(0).cols != dims(1))) {
          println("polytope: mismatched cubicle shape")
          println("the input file had cubicles of shape " + 
                  cubicles(0).rows + "x" + cubicles(0).cols + 
                  " but the command line dimensions were " + 
                  dims(0) + "x" + dims(1))
          return
        }
      // Otherwise calculate the cubicles anew
      } else {
        cubicles ++= InequalityFactory.cubiclesDM(dims.dropRight(1))
      }
       
      if (Conf.pure.potentialIneqs() > 0 || Conf.pure.all() > 0) {
        val edges = InequalityFactory.edgesDM(cubicles)
        val potentialIneqs = 
          InequalityFactory.potentialInequalitiesDP(edges, dims)
        /*
        println("potential-inequalities:")
        if (!potentialIneqs.isEmpty) {
          println(potentialIneqs.head.csvHeaders)
          potentialIneqs.foreach(ineq => println(ineq.toCSV))
        }
        */
        println("total-potential-inequalities:")
        println(potentialIneqs)
      }
      
      // Calculate the inequalities if necessary
      val ineqs =
        if (Conf.pure.ineqs() > 0 || 
            Conf.pure.vertices() > 0 || 
            Conf.pure.all() > 0) {
          // Calculate the inequalities given what we have already computed
          InequalityFactory.ineqsDP(cubicles, dims)
        } else {
          HashSet[InequalityDP]()
        }
      
      // If we should print the inequalities
      if (Conf.pure.ineqs() > 0 || Conf.pure.all() > 0) {   
        // If we should print in computer format
        if (Conf.computer() > 0) {
          println("inequalities:")
          if (!ineqs.isEmpty) {
            println(ineqs.head.csvHeaders)
            ineqs.foreach(ineq => println(ineq.toCSV))
          }
        // Else print in readable format
        } else {
          if (Conf.pure.plaintext() > 0) {
            // TODO: Print inequalities in plaintext instead of LaTeX
            println(dims(0) + "x" + dims(1) + "x" + dims(0)*dims(1) + 
                    " Inequalities\n")
            if (ineqs.size == 0) println("There are no inequalities.")
            else ineqs.foreach(i => println(i.toLatex() + """\\"""))
          } else {
            println(dims(0) + "x" + dims(1) + "x" + dims(0)*dims(1) + 
                    " Inequalities\n")
            if (ineqs.size == 0) println("There are no inequalities.")
            else ineqs.foreach(i => println(i.toLatex() + """\\"""))          
          }
        }
      }

      if (Conf.pure.vertices() > 0 || Conf.pure.all() > 0) {
        val polytope = PolyhedralCone.positiveWeylChamberDP(dims).
                         intersection(PolyhedralCone(ineqs))
        
        val (rs, ps) = polytope.edges()
        
        // Print vertices
        if (Conf.computer() > 0) {
          println("vertices:")
          if (!ineqs.isEmpty) {
            // CSV Header for vertices (cut off trailing ",const")
            println(ineqs.head.csvHeaders.dropRight(6) + ",type")
            ps.foreach(e => println(e.toCSV + ",vertex"))
          }
          if (!ineqs.isEmpty && !rs.isEmpty) {
            rs.foreach(e => println(e.toCSV + ",ray"))
          }
        } else {
          println("\n\nVertices of polytope (normalized to trace " + 
                  -polytope.eqs.head.last + ")\n")
          if (!ineqs.isEmpty)
            ps.foreach(e => println(e))
          else
            println("The polytope is the whole positive Weyl chamber.")
          if (!ineqs.isEmpty && !rs.isEmpty) {
            println("\n\nRays of polytope (normalized to trace " + 
                    -polytope.eqs.head.last + ")\n")
            rs.foreach(e => println(e))
          }
        }
      }
    }
    
    /**********************************
     * Catch all
     */
    case _ => Conf.builder.printHelp
    
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
    p += addToExp(addToExp(term, firstvar, k), secondvar, exp-k) -> 
           Arithmetic.binomial(exp, k)
    k += 1
  }
  return p
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