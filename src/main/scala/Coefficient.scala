package polytope

/** Represents a coefficient. */
trait Coefficient extends Any

/**
  * Represents a coefficient `\(c^w_{uv}(T)\)` appearing in the decomposition of
  * the pull-back of Schubert cocyles,
  * `$$
  * \phi_T(\sigma_w) = 
  *   \sum_{\ell(u) + \ell(v) = \ell(w)} c^w_{uv}(T) \sigma_u \otimes \sigma_v,
  * $$`
  * by the morphism,
  * `$$
  * \phi^{*}_T : H^{*}(Fl(H_A \otimes H_B)) \to H^{*}(Fl(H_A) \times Fl(H_B)),
  * $$` 
  * from the cohomology of the complete flag variety of a bipartite tensor
  * product to the cohomology of the product of the complete flag varieties 
  * of the two tensor product components.
  *
  * This is a value class: it's instatiation is factored out of the runtime
  * code, so it incurs no overhead. This feature requires Scala >= 2.10.
  *
  * @constructor Create a new coefficient \(c^w_{uv}(T)\) with its value, 
  * associated cubicle, T, and associated permutations, u, v, and w.
  * @param self._1 The value of the coefficient. It will always be a positive
  *   integer. A non-zero value means its associated inequalities hold for
  *   mixed, bipartite states.
  * @param self._2 An edge of the cubicle defined by the tableau T.
  * @param self._3 A tableau defining the cubicle, T.
  * @param self._4 A list of three permutations, u, v, and w. If u and v are
  *   permutations on m and n elements respectively, then w is a permutation 
  *   on m*n elements.
  * @see Klyachko's paper at [[http://arxiv.org/pdf/quant-ph/0409113v1.pdf]]
  *
  */
class PFCoefficient(val self: (Int, ABEdge, RectTableau, List[Permutation])) 
  extends AnyVal with Coefficient {
  /** The value of the coefficient. */
  def value() = self._1
  /** */
  def edge() = self._2
  /** A tableau defining the cubicle, T, of the coefficient. */
  def tableau() = self._3
  /** Returns u for i=1, v for i=2, and w for i=3. */
  def perms(i: Int) = self._4(i)
  /** The permutations, u, v, and w of the coefficient. */
  def perms() = self._4
  /** The dimensions of the tableau, T. */
  def dims() = List[Int](self._2.dimA, self._2.dimB)
  
  /** Returns headers for printing the coefficient as a line of CSV data. */
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
  
  /** Returns the inequality associated to the coefficient as a line of CSV 
    * data.
    */
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
  
  /** Returns a string listing of the inequality coefficients, edge, tableau
    *  and permutations associated with this coefficient.
    */
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
  
  // Functions to check equality and compute hashes are automatic for value 
  // classes.
}
