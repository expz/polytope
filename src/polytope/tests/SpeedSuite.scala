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
  
  def microTime[A](a: => A) = nanoTime(a) / 1000
  
  def microTimeArg1[B, A](f: B => A, arg1: B): Long = 
        nanoTimeArg1(f, arg1) / 1000
  
  def nanoTime[A](a: => A): Long = {
    val start = System.nanoTime
    val result = a
    val nanos = System.nanoTime - start
    nanos
  }

  def nanoTimeArg1[B, A](f: B => A, arg1: B): Long = {
    val start = System.nanoTime
    val result = f(arg1)
    val nanos = System.nanoTime - start
    nanos
  }

  def avgMicroTime(f: => Unit, runs: Int = 1000) = avgNanoTime(f, runs) / 1000
  
  def avgMicroTimeArg1[B, A](f: B => A, arg1: B, maxRuns: Int = 1000) =
        avgNanoTimeArg1[B, A](f, arg1, maxRuns) / 1000
  
  def avgNanoTime(f: => Unit, maxRuns: Int = 1000): Long = 
        avgNanoTimeArg1[Unit, Unit](_ => f, Unit, maxRuns)
  
  def avgNanoTimeArg1[B, A](f: B => A, arg1: B, maxRuns: Int = 1000): Long = {
    val warmupRuns = maxRuns/10
    var tot = 0L
    var count = 0
    for (k <- (1 to maxRuns)) { 
      val t = nanoTimeArg1[B, A](f, arg1)
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
  /*
   * Functions to test
   */
  val n = 10
  def f1(): Unit = {
    (1 to n).toArray
  }
  def f2(): Unit = {
    Array.tabulate[Int](n)(i => i)
  }
  
  "The timer" should "time while and for loops." in {
    println("f1: " + avgNanoTime(f1, 20000))
    println("f1: " + avgNanoTime(f1, 20000))
    //println("f1: " + avgNanoTime(f1, 10000))
    //println("f1: " + avgNanoTime(f1, 10000))
    println("f2: " + avgNanoTime(f2, 20000))
    println("f2: " + avgNanoTime(f2, 20000))
  }
  
  // Use while loops to avoid compile error
  "The counter" should "count loops." in {
    val counts = ArrayBuffer.fill[ArrayBuffer[Int]](3)(ArrayBuffer(0,0,0))
    var dimA = 1
    var dimB = 1
    while (dimA <= 3) {
      dimB = dimA
      while (dimB <= 3) {
	    for (T <- RectTableau.standardTableaux(dimA, dimB).filter(_.isAdmissible)) {
	      val dimAB = dimA*dimB
	      for (edge <- T.toCone.edges(dimA)) {
	        for (u <- PermutationFactory.shuffles(edge.multA)) {
	          for (v <- PermutationFactory.shuffles(edge.multB)) {
	            //for (w <- PermutationFactory.shufflesOfGivenLength(
	            //    edge.multAB, reducedWord(u).length + reducedWord(v).length)) {
	                counts(dimA-1)(dimB-1) += 1
	            //}
	          }
	        }
	      }
	    }
	    println("(" + dimA + ", " + dimB + "): " + counts(dimA-1)(dimB-1))
	    dimB += 1
      }
      dimA += 1
    }
	
    println("Number of loops to calculate inequalities")
    println(counts mkString "\n")
  }

  /*
   * Measure max/avg/total runtimes for calculating Schubert
   * Polynomials of permutations of a given size 
   */
  /*
  it should "time SchubertFactory.schubertPolynomial" in {
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
          runtimes(perm) = avgMicroTimeArg1[Permutation, Polynomial](
              SchubertFactory.schubertPolynomial, perm, 100)
        else
          runtimes(perm) = microTimeArg1[Permutation, Polynomial](
              SchubertFactory.schubertPolynomial, perm)
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
*/  
  /*
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
  * 
  */
}
