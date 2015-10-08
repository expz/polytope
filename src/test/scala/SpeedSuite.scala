package polytope.tests

import java.io._
import org.jfree.data.xy.DefaultXYDataset
import org.jfree.data.xy._
import org.jfree.chart.ChartFactory
import org.jfree.chart.plot.PlotOrientation
import org.jfree.data.general.DatasetUtilities
import org.jfree.chart.JFreeChart
import org.jfree.chart.ChartUtilities 

import org.junit.runner.RunWith

import org.scalatest._
import org.scalatest.junit.JUnitRunner

import polytope._

import scala.collection.immutable.Set
import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.HashSet
import scala.collection.mutable.HashMap
import scala.collection.mutable.ListBuffer

@RunWith(classOf[JUnitRunner])
class SpeedSuite extends UnitSpec {
  /*
   * Timing functions
   */
  def printMicroTime[A](a: => A) = {
    val start = System.nanoTime
    val result = a
    val micros = (System.nanoTime - start) / 1000
    println("%d microseconds".format(micros))
    result
  }
  
  def printNanoTime[A](a: => A) = {
    val start = System.nanoTime
    val result = a
    val nanos = System.nanoTime - start
    println("%d nanoseconds".format(nanos))
    result
  }
  
  def milliTime(a: => Unit) = nanoTime(a) / 1000000
  
  def microTime(a: => Unit) = nanoTime(a) / 1000
    
  def nanoTime(a: => Unit): Long = {
    val start = System.nanoTime
    val result = a
    val nanos = System.nanoTime - start
    nanos
  }
  
  def avgMilliTime(f: => Unit, runs: Int = 1000) = 
      avgNanoTime(f, runs) / 1000000
  
  def avgMicroTime(f: => Unit, runs: Int = 1000) = avgNanoTime(f, runs) / 1000
    
  def avgNanoTime(f: => Unit, maxRuns: Int = 1000): Long = { 
    val warmupRuns = maxRuns/10
    var tot = 0L
    var count = 0
    for (k <- (1 to maxRuns)) { 
      val t = nanoTime(f)
      if (k > warmupRuns) {
        // Reset count if the time decreased by at least 50%
        if (count != 0 && t < (tot/count) / 1.5) {
          count = 1
          tot = t
        // Count the test if its the first one or if the time was not too high
        //   This avoids counting run times skewed by garbage collection
        } else if (count == 0 || t < 1.5*(tot/count)) { 
          count += 1
          tot += t
        }
      } 
    }
    return tot/count    
  }
  
  ignore should "time range to array and tabulate functions." in {
    val n = 10
    def rangeToArray(): Unit = {
      (1 to n).toArray
    }
    def tabulate(): Unit = {
      Array.tabulate[Int](n)(i => i)
    }    
    println("f1: " + avgNanoTime(rangeToArray, 20000))
    println("f1: " + avgNanoTime(rangeToArray, 20000))
    println("f2: " + avgNanoTime(tabulate, 20000))
    println("f2: " + avgNanoTime(tabulate, 20000))
  }
  
  // Use while loops to avoid compile error
  "Test" should "count cubicles and extremal edges." in {
    def countObjects(dimA: Int, dimB: Int) = {
      val edges = ListBuffer[ABEdge]()
      println("dimA: " + dimA + "  dimB: " + dimB)
      val cubicles = RectTableau.standardTableaux(dimA, dimB).filter(_.isAdmissible) 
      println("No. cubicles: " + cubicles.length)
      for (T <- cubicles) {
        edges.prependAll(T.toCone.edges(dimA)._2)
      }
      println("Total edges: " + edges.length)
      println("No. distinct edges: " + edges.to[HashSet].size)
    }
    
    // Warm-up function so JIT can finish optimizing
    for (i <- 1 to 5)
      countObjects(2, 3)
    
    countObjects(1, 1)
    countObjects(1, 2)
    countObjects(1, 3)
    countObjects(2, 2)
    countObjects(2, 3)
    /*
    countObjects(2, 4)
    countObjects(3, 3)
    countObjects(2, 5)
    countObjects(2, 6)
    countObjects(3, 4)
    countObjects(2, 7)
    countObjects(3, 5)
    countObjects(4, 4)
    countObjects(3, 6)
    countObjects(4, 5)
    * 
    */    
  }
  
  ignore should "count loops." in {
    val dimAMax = 2
    val dimBMax = 4
    def doLoop() = {
    val counts = ArrayBuffer.fill[ArrayBuffer[Int]](dimAMax)(
                     ArrayBuffer.fill[Int](dimBMax)(0))
    val totalTime = ArrayBuffer.fill[ArrayBuffer[Long]](dimAMax)(
                     ArrayBuffer.fill[Long](dimBMax)(0))
    val saveEdges = ListBuffer[ABEdge]()
    var dimA = 1
    var dimB = 1      
    while (dimA <= dimAMax) {
      dimB = dimA
      while (dimB <= dimBMax) {
  	    for (T <- RectTableau.standardTableaux(dimA, dimB).filter(_.isAdmissible)) {
  	      val dimAB = dimA*dimB
  	      val Tedges = T.toCone.edges(dimA)
  	      var e = 0
  	      while (e < Tedges._2.length) {
  	        val edge = Tedges._2(e)
  	        saveEdges.prepend(edge)
  	        for (u <- PermutationFactory.shuffles(edge.multA)) {
  	          val ul = reducedWord(u).length
  	          for (v <- PermutationFactory.shuffles(edge.multB)) {
  	            val vl = reducedWord(v).length
  	            for (w <- PermutationFactory.shufflesOfGivenLength(
  	                edge.multAB, ul + vl)) {
  	                counts(dimA-1)(dimB-1) += 1
  	                totalTime(dimA-1)(dimB-1) += 
  	                    microTime({ InequalityFactory.c(u, v, w, T) })
  	                //if (counts(dimA-1)(dimB-1) % 250 == 0)
  	                  //println(totalTime(dimA-1)(dimB-1)/counts(dimA-1)(dimB-1))
  	            }
  	          }
  	        }
  	        e += 1
  	      }
  	    }
  	    println("(" + dimA + ", " + dimB + "): " + counts(dimA-1)(dimB-1) +
  	        ", " + totalTime(dimA-1)(dimB-1).toDouble / counts(dimA-1)(dimB-1))
  	    println("distinct edges: " + saveEdges.distinct.length)
  	    saveEdges.clear()
  	    
  	    dimB += 1
      }
      dimA += 1
    }
    }
    
    var i = 0
    while ( i < 20) {
      doLoop()
      i += 1
    }
    //println("Number of loops to calculate inequalities")
    //println(counts mkString "\n")
  }

  /*
   * Measure max/avg/total runtimes for calculating Schubert
   * Polynomials of permutations of a given size 
   */

  ignore should "time SchubertFactory.schubertPolynomial" in {
    val (permSizeMin, permSizeMax) = (0, 8)
    
    val runtimes = HashMap[Permutation, Long]()
    val maxRuntime = ArrayBuffer.fill[Long](permSizeMax+1)(0L)
    val totalRuntime = ArrayBuffer.fill[Long](permSizeMax+1)(0L)
    val avgRuntime = ArrayBuffer.fill[Long](permSizeMax+1)(0L)
    // Measure runtimes of schubertPolynomial for all permutations
    for (permSize <- permSizeMin to permSizeMax) {
      val trivialPerm = Array.tabulate[Int](permSize)(_+1)
      for (perm <- trivialPerm.permutations) {
        if (permSize <= 3)
          runtimes(perm) = avgMicroTime(
              { SchubertFactory.schubertPolynomial(perm) }, 100)
        else
          runtimes(perm) = microTime(
              { SchubertFactory.schubertPolynomial(perm) })
        maxRuntime(permSize) = Math.max(maxRuntime(permSize), runtimes(perm))
        totalRuntime(permSize) += runtimes(perm)
        println(permSize.toString + " " + runtimes(perm))
      }
      avgRuntime(permSize) = 
          totalRuntime(permSize) / (1 to permSize).fold(1)(_*_)
    }
    println("size max")
    printAsMatrix(maxRuntime)
    println("size total")
    printAsMatrix(totalRuntime)
    println("size average")
    printAsMatrix(avgRuntime)
    
    def printAsMatrix(a: ArrayBuffer[Long]) = {
      for (i <- a.indices) println(i.toString + " " + a(i))
    }
    /* 
     linePlot(maxRuntime, 
           "Max runtime of schubertPolynomial for permutations of a given size", 
           "Permutation size", 
           "Max runtime (microsec)", 
           "maxRuntimes")
    
    // For each size of permutation, plot distribution of run times in 
    // buckets
    for (permSize <- permSizeMin to permSizeMax) {
      
      val data = runtimes.filterKeys(perm => perm.length == permSize)
      val runtimesArray = data.iterator.map(_._2).toArray[Long]
      
      bucketPlot(runtimesArray, 
                 maxRuntime(permSize), 
                 20, 
                 "Permutations of size " + permSize + " grouped by runtime", 
                 "Runtime (microsec)",
                 "Permutations")
    }
    * 
    */
  }
  
  //////////////////////////////////////////////////////////
  // Plotting helper functions
  
  def linePlot(y: Array[Double], title: String, xlabel: String, ylabel: String, filename: String) = 
      linePlotXY(Array.tabulate[Array[Double]](y.length)(i => Array(i.toDouble, y(i))), title, xlabel, ylabel, filename)

  def linePlotXY(xy: Array[Array[Double]], title: String, xlabel: String, ylabel: String, filename: String) = {
    // Plot maximum run times for each size of Permutation
    // Prepare the dataset
    val dataset = new DefaultXYDataset()
          
    //((0, 1, 2, ...), (max0, max1, max2, ...))
    dataset.addSeries("data", xy.transpose) 
    
    // Create the line chart
    val lineChartObject = ChartFactory.createXYLineChart(
          title,
          xlabel,
          ylabel,
          dataset,
          PlotOrientation.VERTICAL,
          false, true, false)                

    // Write chart to a file               
    val width = 640 /* Width of the image */
    val height = 480 /* Height of the image */                
    val lineChart = new File(filename + ".png")              
    ChartUtilities.saveChartAsPNG(lineChart, lineChartObject, width, height) 
  }     
    
  def bucketPlot(y: Array[Double], ymax: Double, numBuckets: Int, title: String, xlabel: String, ylabel: String, filename: String) = {      
    // Collect the runtimes into buckets
    val bucketSize: Double = Math.max(ymax, numBuckets) / numBuckets
    
    val bucketData = y.
         groupBy(d => Math.floor(d / bucketSize)).  // ((0, bucket0), (1, bucket1), ...)
         map(keyVal => Array[Double]((keyVal._1.toDouble + 0.5)*bucketSize, 
                                     keyVal._2.length.toDouble)).
         toArray.
         transpose                 // ((bucketMidPoint0, # perms), ...)
         
       // Prepare the dataset for permutations of size permSize
    val dataset = new DefaultXYDataset()
    dataset.addSeries("data", bucketData)
         
    val barDataset = new XYBarDataset(dataset, bucketSize)
    
    // Create the bar chart
    val barChartObject = ChartFactory.createXYBarChart(
          title, 
          xlabel,
          false,
          ylabel, 
          barDataset,
          PlotOrientation.VERTICAL, 
          false, true, false)   
     
    // Write the bar chart to a file
    val width = 640 /* Width of the image */
    val height = 480 /* Height of the image */
    val barChart = new File(filename + ".png")              
    ChartUtilities.saveChartAsPNG(barChart, barChartObject, width, height)
  }
}
