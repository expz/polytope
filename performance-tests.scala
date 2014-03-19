import scala.Array
import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.ListBuffer

def time[A](a: => A) = {
val start = System.nanoTime
val result = a
val micros = (System.nanoTime - start) / 1000
println("%d microseconds".format(micros))
result
}

def nanoTime[A](a: => A) = {
val start = System.nanoTime
val result = a
val nanos = System.nanoTime - start
println("%d nanoseconds".format(nanos))
result
}

def returnTime[A](a: => A): Long = {
val start = System.nanoTime
val result = a
val micros = System.nanoTime - start
micros
}

def test(N: Int, f: => Unit): Long = { 
var tot = 0L
var count = 0
for (k <- (1 to N)) { 
val t = returnTime { f } 
if (k > 4 && (count == 0 || t < 1.5*(tot/count))) { 
count += 1
tot += t
} 
} 
return tot/count 
}

def abtest() = {
val ab = ArrayBuffer[Array[Int]]()
for (k <- (1 to 25)) {
ab += (1 to 24).toArray
}
val result = ab.toArray
}

def atest() = {
var a = Array[Array[Int]]()
for (k <- (1 to 25)) {
a = a :+ (1 to 24).toArray
}
}

def lbtest() = {
val lb = ListBuffer[Array[Int]]()
for (k <- (1 to 25)) {
lb += (1 to 24).toArray
}
val result = lb.toArray
}

// 107 microseconds
def abtest2() = {
val dimA = 3
val dimB = 3
val ieq = ArrayBuffer.fill[Int](dimA+dimB)(0)
for (i <- 0 to 1) {
for (j <- 0 to 1) {
for (k <- 0 to 1) {
for (l <- 0 to 1) {
ieq.update(i, 1)
ieq.update(dimA + j, 1)
ieq(k) -= 1
ieq(dimA + l) -= 1
ieq.toArray
}}}}
}

// 203 microseconds
def atest2() = {
val dimA = 3
val dimB = 3
for (i <- 0 to 1) {
for (j <- 0 to 1) {
for (k <- 0 to 1) {
for (l <- 0 to 1) {
val ieq = Array.tabulate[Int](dimA+dimB)(n => {
if (n == i) {
if(k == i) 0
else 1
} else if (n == dimA + j) {
if(l == j) 0
else 1
} else if (n == k) {
-1
} else if (n == dimA + l) {
-1
} else {
0
}
})
}}}}
}

// 200 microseconds
def abuildtest() = {
val dimA = 3
val dimB = 3
for (i <- 0 to 1) {
for (j <- 0 to 1) {
for (k <- 0 to 1) {
for (l <- 0 to 1) {
val ieq = Array.newBuilder[Int]
var n = 0
while (n < dimA + dimB) {
if (n == i) {
if(k == i) ieq += 0
else ieq += 1
} else if (n == dimA + j) {
if(l == j) ieq += 0
else ieq += 1
} else if (n == k) {
ieq += -1
} else if (n == dimA + l) {
ieq += -1
} else {
ieq += 0
}
n += 1
}
ieq.result
}}}}
}

========================

scala> def looptest(n: Int) = { val ab = ArrayBuffer[Int](); for (i <- 1 to n) { ab.append(i) }; }
looptest: (n: Int)Unit

scala> def looptest2(n: Int) = { val ab = ArrayBuffer[Int](); var i = 1; while (i <= n) { ab.append(i); i += 1 }; }
looptest2: (n: Int)Unit

scala> test(1000, looptest(10000))
res14: Long = 574969

scala> test(1000, looptest2(10000))
res15: Long = 514954


