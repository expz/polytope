package polytope

/**
  * An object providing basic arithmetic functions lacking in the Scala
  * standard library.
  */
object Arithmetic {
  
  /** Returns the greatest common divisor of a and b */
  def gcd(a: Int, b: Int): Int = if (b == 0) a.abs else gcd(b, a % b)
  
  /** Returns the greatest common divisor of a list of integers */
  def gcd(as: List[Int]): Int = if (as.length > 0) 
                                  as.fold(as.head)((k, g) => gcd(k, g))
                                else
                                  1
  /** Returns the least common multiple of a and b */
  def lcm(a: Int, b: Int): Int = (a * b).abs / gcd(a, b)
  
  /** Returns the least common multiple of a list of integers */
  def lcm(as: List[Int]): Int = if (as.length > 0)
                                  as.fold(as.head)((k, l) => lcm(k, l))
                                else
                                  1
  
  /** Return the binomial coefficient n Choose k */
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
}
