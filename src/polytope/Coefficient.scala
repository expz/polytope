package polytope

trait Coefficient extends Any

class PFCoefficient(val self: (Int, ABEdge, RectTableau, List[Permutation])) 
  extends AnyVal with Coefficient {
  def value() = self._1
  def edge() = self._2
  def tableau() = self._3
  def perms(i: Int) = self._4(i)
  def perms() = self._4
  def dims() = List[Int](self._2.dimA, self._2.dimB)
  
  def csvHeaders(): String =
    "value" + "," + 
    (1 to edge.dimA).map("lambdaA" + _).mkString(",") + "," +
    (1 to edge.dimB).map("lambdaB" + _).mkString(",") + "," +
    (1 to edge.dimAB).map("lambdaAB" + _).mkString(",") + "," +
    edge.csvHeaders + "," +
    tableau.csvHeaders + "," +
    (1 to perms(0).length).map("u(" + _ + ")").mkString(",") + "," +
    (1 to perms(1).length).map("v(" + _ + ")").mkString(",") + "," +
    (1 to perms(2).length).map("w(" + _ + ")").mkString(",")
    
  def toCSV(): String = {
    val ineqCoeffs = act(inverse(perms(0)), edge.A).map(-_) ++
                     act(inverse(perms(1)), edge.B).map(-_) ++ 
                     act(inverse(perms(2)), edge.AB)          

    return "" + value + "," + 
      ineqCoeffs.mkString(",") + "," +
      edge.toCSV + "," + 
      tableau.toCSV + "," +
      perms.map(_.mkString(",")).mkString(",")
  }
  
  override def toString(): String = {
    val ineqCoeffs = act(inverse(perms(0)), edge.A).map(-_) ++
                     act(inverse(perms(1)), edge.B).map(-_) ++ 
                     act(inverse(perms(2)), edge.AB)          
    
    return "" + value + "," + 
      ineqCoeffs.mkString(",") +
      edge.toCSV + "," + 
      tableau.toCSV + "," +
      perms.map(_.mkString(",")).mkString(",")
  }
  // Equality check and hash code are automatic for a value class
}
