package polytope

/*
 * The RectTableau class requires that the environment variable LD_LIBRARY_PATH
 * be set to the directory containing liblpsolve55j.so due to its dependence
 * on lpsolve.
 * 
 * It requires different lpsolve libraries for 32-bit and 64-bit 
 * implementations.
 * 
 * Perhaps fix library with: execstack -c <libfile>
 */

import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.ListBuffer
import scala.collection.JavaConversions._

//import lpsolve._


/*
import com.sun.jna.{Library, Native, Platform, Structure}
import java.math._

trait CLibrary extends Library {
  def puts(s: String)
}
object CLibrary {
  def Instance = Native.loadLibrary("c", classOf[CLibrary]).asInstanceOf[CLibrary]
}
*/

import com.sun.jna.Structure
import _root_.net.java.dev.sna.SNA
import scala.collection.mutable.ListBuffer
class lprec extends Structure {
  def getFieldOrder(): java.util.List[String] = {
    val fields = new ListBuffer[String]()
    fields.append("add_column")
    fields.append("add_columnex")
    fields.append("add_constraint")
    fields.append("add_constraintex")
    fields.append("add_lag_con")
    fields.append("add_SOS")
    fields.append("column_in_lp")
    fields.append("copy_lp")
    fields.append("default_basis")
    fields.append("del_column")
    fields.append("del_constraint")
    fields.append("delete_lp")
    fields.append("dualize_lp")
    fields.append("free_lp")
    fields.append("get_anti_degen")
    fields.append("get_basis")
    fields.append("get_basiscrash")
    fields.append("get_bb_depthlimit")
    fields.append("get_bb_floorfirst")
    fields.append("get_bb_rule")
    fields.append("get_bounds_tighter")
    fields.append("get_break_at_value")
    fields.append("get_col_name")
    fields.append("get_columnex")
    fields.append("get_constr_type")
    fields.append("get_constr_value")
    fields.append("get_constraints")
    fields.append("get_dual_solution")
    fields.append("get_epsb")
    fields.append("get_epsd")
    fields.append("get_epsel")
    fields.append("get_epsint")
    fields.append("get_epsperturb")
    fields.append("get_epspivot")
    fields.append("get_improve")
    fields.append("get_infinite")
    fields.append("get_lambda")
    fields.append("get_lowbo")
    fields.append("get_lp_index")
    fields.append("get_lp_name")
    fields.append("get_Lrows")
    fields.append("get_mat")
    fields.append("get_mat_byindex")
    fields.append("get_max_level")
    fields.append("get_maxpivot")
    fields.append("get_mip_gap")
    fields.append("get_multiprice")
    fields.append("get_nameindex")
    fields.append("get_Ncolumns")
    fields.append("get_negrange")
    fields.append("get_nonzeros")
    fields.append("get_Norig_columns")
    fields.append("get_Norig_rows")
    fields.append("get_Nrows")
    fields.append("get_obj_bound")
    fields.append("get_objective")
    fields.append("get_orig_index")
    fields.append("get_origcol_name")
    fields.append("get_origrow_name")
    fields.append("get_partialprice")
    fields.append("get_pivoting")
    fields.append("get_presolve")
    fields.append("get_presolveloops")
    fields.append("get_primal_solution")
    fields.append("get_print_sol")
    fields.append("get_pseudocosts")
    fields.append("get_ptr_constraints")
    fields.append("get_ptr_dual_solution")
    fields.append("get_ptr_lambda")
    fields.append("get_ptr_primal_solution")
    fields.append("get_ptr_sensitivity_obj")
    fields.append("get_ptr_sensitivity_objex")
    fields.append("get_ptr_sensitivity_rhs")
    fields.append("get_ptr_variables")
    fields.append("get_rh")
    fields.append("get_rh_range")
    fields.append("get_row")
    fields.append("get_rowex")
    fields.append("get_row_name")
    fields.append("get_scalelimit")
    fields.append("get_scaling")
    fields.append("get_sensitivity_obj")
    fields.append("get_sensitivity_objex")
    fields.append("get_sensitivity_rhs")
    fields.append("get_simplextype")
    fields.append("get_solutioncount")
    fields.append("get_solutionlimit")
    fields.append("get_status")
    fields.append("get_statustext")
    fields.append("get_timeout")
    fields.append("get_total_iter")
    fields.append("get_total_nodes")
    fields.append("get_upbo")
    fields.append("get_var_branch")
    fields.append("get_var_dualresult")
    fields.append("get_var_primalresult")
    fields.append("get_var_priority")
    fields.append("get_variables")
    fields.append("get_verbose")
    fields.append("get_working_objective")
    fields.append("has_BFP")
    fields.append("has_XLI")
    fields.append("is_add_rowmode")
    fields.append("is_anti_degen")
    fields.append("is_binary")
    fields.append("is_break_at_first")
    fields.append("is_constr_type")
    fields.append("is_debug")
    fields.append("is_feasible")
    fields.append("is_infinite")
    fields.append("is_int")
    fields.append("is_integerscaling")
    fields.append("is_lag_trace")
    fields.append("is_maxim")
    fields.append("is_nativeBFP")
    fields.append("is_nativeXLI")
    fields.append("is_negative")
    fields.append("is_obj_in_basis")
    fields.append("is_piv_mode")
    fields.append("is_piv_rule")
    fields.append("is_presolve")
    fields.append("is_scalemode")
    fields.append("is_scaletype")
    fields.append("is_semicont")
    fields.append("is_SOS_var")
    fields.append("is_trace")
    fields.append("is_unbounded")
    fields.append("is_use_names")
    fields.append("lp_solve_version")
    fields.append("make_lp")
    fields.append("print_constraints")
    fields.append("print_debugdump")
    fields.append("print_duals")
    fields.append("print_lp")
    fields.append("print_objective")
    fields.append("print_scales")
    fields.append("print_solution")
    fields.append("print_str")
    fields.append("print_tableau")
    fields.append("put_abortfunc")
    fields.append("put_bb_nodefunc")
    fields.append("put_bb_branchfunc")
    fields.append("put_logfunc")
    fields.append("put_msgfunc")
    fields.append("read_LP")
    fields.append("read_MPS")
    fields.append("read_XLI")
    fields.append("read_params")
    fields.append("read_basis")
    fields.append("reset_basis")
    fields.append("reset_params")
    fields.append("resize_lp")
    fields.append("set_add_rowmode")
    fields.append("set_anti_degen")
    fields.append("set_basisvar")
    fields.append("set_basis")
    fields.append("set_basiscrash")
    fields.append("set_bb_depthlimit")
    fields.append("set_bb_floorfirst")
    fields.append("set_bb_rule")
    fields.append("set_BFP")
    fields.append("set_binary")
    fields.append("set_bounds")
    fields.append("set_bounds_tighter")
    fields.append("set_break_at_first")
    fields.append("set_break_at_value")
    fields.append("set_column")
    fields.append("set_columnex")
    fields.append("set_col_name")
    fields.append("set_constr_type")
    fields.append("set_debug")
    fields.append("set_epsb")
    fields.append("set_epsd")
    fields.append("set_epsel")
    fields.append("set_epsint")
    fields.append("set_epslevel")
    fields.append("set_epsperturb")
    fields.append("set_epspivot")
    fields.append("set_unbounded")
    fields.append("set_improve")
    fields.append("set_infinite")
    fields.append("set_int")
    fields.append("set_lag_trace")
    fields.append("set_lowbo")
    fields.append("set_lp_name")
    fields.append("set_mat")
    fields.append("set_maxim")
    fields.append("set_maxpivot")
    fields.append("set_minim")
    fields.append("set_mip_gap")
    fields.append("set_multiprice")
    fields.append("set_negrange")
    fields.append("set_obj_bound")
    fields.append("set_obj_fn")
    fields.append("set_obj_fnex")
    fields.append("set_obj")
    fields.append("set_obj_in_basis")
    fields.append("set_outputfile")
    fields.append("set_outputstream")
    fields.append("set_partialprice")
    fields.append("set_pivoting")
    fields.append("set_preferdual")
    fields.append("set_presolve")
    fields.append("set_print_sol")
    fields.append("set_pseudocosts")
    fields.append("set_rh")
    fields.append("set_rh_range")
    fields.append("set_rh_vec")
    fields.append("set_row")
    fields.append("set_rowex")
    fields.append("set_row_name")
    fields.append("set_scalelimit")
    fields.append("set_scaling")
    fields.append("set_semicont")
    fields.append("set_sense")
    fields.append("set_simplextype")
    fields.append("set_solutionlimit")
    fields.append("set_timeout")
    fields.append("set_trace")
    fields.append("set_upbo")
    fields.append("set_use_names")
    fields.append("set_var_branch")
    fields.append("set_var_weights")
    fields.append("set_verbose")
    fields.append("set_XLI")
    fields.append("solve")
    fields.append("str_add_column")
    fields.append("str_add_constraint")
    fields.append("str_add_lag_con")
    fields.append("str_set_obj_fn")
    fields.append("str_set_rh_vec")
    fields.append("time_elapsed")
    fields.append("unscale")
    fields.append("write_lp")
    fields.append("write_LP")
    fields.append("write_mps")
    fields.append("write_MPS")
    fields.append("write_freemps")
    fields.append("write_freeMPS")
    fields.append("write_XLI")
    fields.append("write_basis")
    fields.append("write_params")
    fields.append("alignmentspacer")
    fields.append("lp_name")
    fields.append("sum")
    fields.append("rows")
    fields.append("columns")
    fields.append("equalities")
    fields.append("boundedvars")
    fields.append("INTfuture1")
    fields.append("sum_alloc")
    fields.append("rows_alloc")
    fields.append("columns_alloc")
    fields.append("source_is_file")
    fields.append("model_is_pure")
    fields.append("model_is_valid")
    fields.append("tighten_on_set")
    fields.append("names_used")
    fields.append("use_row_names")
    fields.append("use_col_names")
    fields.append("lag_trace")
    fields.append("spx_trace")
    fields.append("bb_trace")
    fields.append("streamowned")
    fields.append("obj_in_basis")
    fields.append("spx_status")
    fields.append("lag_status")
    fields.append("solutioncount")
    fields.append("solutionlimit")
    fields.append("real_solution")
    fields.append("solution")
    fields.append("best_solution")
    fields.append("full_solution")
    fields.append("edgeVector")
    fields.append("drow")
    fields.append("nzdrow")
    fields.append("duals")
    fields.append("full_duals")
    fields.append("dualsfrom")
    fields.append("dualstill")
    fields.append("objfrom")
    fields.append("objtill")
    fields.append("objfromvalue")
    fields.append("orig_obj")
    fields.append("obj")
    fields.append("current_iter")
    fields.append("total_iter")
    fields.append("current_bswap")
    fields.append("total_bswap")
    fields.append("solvecount")
    fields.append("max_pivots")
    fields.append("simplex_strategy")
    fields.append("simplex_mode")
    fields.append("verbose")
    fields.append("print_sol")
    fields.append("outstream")
    fields.append("bb_varbranch")
    fields.append("piv_strategy")
    fields.append("_piv_rule_")
    fields.append("bb_rule")
    fields.append("bb_floorfirst")
    fields.append("bb_breakfirst")
    fields.append("_piv_left_")
    fields.append("BOOLfuture1")
    fields.append("scalelimit")
    fields.append("scalemode")
    fields.append("improve")
    fields.append("anti_degen")
    fields.append("do_presolve")
    fields.append("presolveloops")
    fields.append("perturb_count")
    fields.append("row_name")
    fields.append("col_name")
    fields.append("rowname_hashtab")
    fields.append("colname_hashtab")
    fields.append("rowblocks")
    fields.append("colblocks")
    fields.append("var_type")
    fields.append("multivars")
    fields.append("multiblockdiv")
    fields.append("fixedvars")
    fields.append("int_vars")
    fields.append("sc_vars")
    fields.append("sc_lobound")
    fields.append("var_is_free")
    fields.append("var_priority")
    fields.append("GUB")
    fields.append("sos_vars")
    fields.append("sos_ints")
    fields.append("SOS")
    fields.append("sos_priority")
    fields.append("bsolveVal")
    fields.append("bsolveIdx")
    fields.append("orig_rhs")
    fields.append("rhs")
    fields.append("row_type")
    fields.append("longsteps")
    fields.append("orig_upbo")
    fields.append("upbo")
    fields.append("orig_lowbo")
    fields.append("lowbo")
    fields.append("matA")
    fields.append("invB")
    fields.append("bb_bounds")
    fields.append("rootbounds")
    fields.append("bb_basis")
    fields.append("rootbasis")
    fields.append("monitor")
    fields.append("scalars")
    fields.append("scaling_used")
    fields.append("columns_scaled")
    fields.append("varmap_locked")
    fields.append("basis_valid")
    fields.append("crashmode")
    fields.append("var_basic")
    fields.append("val_nonbasic")
    fields.append("is_basic")
    fields.append("is_lower")
    fields.append("rejectpivot")
    fields.append("bb_PseudoCost")
    fields.append("bb_PseudoUpdates")
    fields.append("bb_strongbranches")
    fields.append("is_strongbranch")
    fields.append("bb_improvements")
    fields.append("rhsmax")
    fields.append("suminfeas")
    fields.append("bigM")
    fields.append("P1extraVal")
    fields.append("P1extraDim")
    fields.append("spx_action")
    fields.append("spx_perturbed")
    fields.append("bb_break")
    fields.append("wasPreprocessed")
    fields.append("wasPresolved")
    fields.append("INTfuture2")
    fields.append("matL")
    fields.append("lag_rhs")
    fields.append("lag_con_type")
    fields.append("lambda")
    fields.append("lag_bound")
    fields.append("lag_accept")
    fields.append("infinite")
    fields.append("negrange")
    fields.append("epsmachine")
    fields.append("epsvalue")
    fields.append("epsprimal")
    fields.append("epsdual")
    fields.append("epspivot")
    fields.append("epsperturb")
    fields.append("epssolution")
    fields.append("bb_status")
    fields.append("bb_level")
    fields.append("bb_maxlevel")
    fields.append("bb_limitlevel")
    fields.append("bb_totalnodes")
    fields.append("bb_solutionlevel")
    fields.append("bb_cutpoolsize")
    fields.append("bb_cutpoolused")
    fields.append("bb_constraintOF")
    fields.append("bb_cuttype")
    fields.append("bb_varactive")
    fields.append("bb_upperchange")
    fields.append("bb_lowerchange")
    fields.append("bb_deltaOF")
    fields.append("bb_breakOF")
    fields.append("bb_limitOF")
    fields.append("bb_heuristicOF")
    fields.append("bb_parentOF")
    fields.append("bb_workOF")
    fields.append("presolve_undo")
    fields.append("workarrays")
    fields.append("epsint")
    fields.append("mip_absgap")
    fields.append("mip_relgap")
    fields.append("timecreate")
    fields.append("timestart")
    fields.append("timeheuristic")
    fields.append("timepresolved")
    fields.append("timeend")
    fields.append("sectimeout")
    fields.append("ex_status")
    fields.append("hBFP")
    fields.append("bfp_name")
    fields.append("bfp_compatible")
    fields.append("bfp_init")
    fields.append("bfp_free")
    fields.append("bfp_resize")
    fields.append("bfp_memallocated")
    fields.append("bfp_restart")
    fields.append("bfp_mustrefactorize")
    fields.append("bfp_preparefactorization")
    fields.append("bfp_factorize")
    fields.append("bfp_finishfactorization")
    fields.append("bfp_updaterefactstats")
    fields.append("bfp_prepareupdate")
    fields.append("bfp_pivotRHS")
    fields.append("bfp_finishupdate")
    fields.append("bfp_ftran_prepare")
    fields.append("bfp_ftran_normal")
    fields.append("bfp_btran_normal")
    fields.append("bfp_btran_double")
    fields.append("bfp_status")
    fields.append("bfp_nonzeros")
    fields.append("bfp_implicitslack")
    fields.append("bfp_indexbase")
    fields.append("bfp_rowoffset")
    fields.append("bfp_pivotmax")
    fields.append("bfp_pivotalloc")
    fields.append("bfp_colcount")
    fields.append("bfp_canresetbasis")
    fields.append("bfp_efficiency")
    fields.append("bfp_pivotvector")
    fields.append("bfp_pivotcount")
    fields.append("bfp_refactcount")
    fields.append("bfp_isSetI")
    fields.append("bfp_findredundant")
    fields.append("hXLI")
    fields.append("xli_name")
    fields.append("xli_compatible")
    fields.append("xli_readmodel")
    fields.append("xli_writemodel")
    fields.append("userabort")
    fields.append("report")
    fields.append("explain")
    fields.append("get_lpcolumn")
    fields.append("get_basiscolumn")
    fields.append("get_OF_active")
    fields.append("getMDO")
    fields.append("invert")
    fields.append("set_action")
    fields.append("is_action")
    fields.append("clear_action")
    fields.append("ctrlc")
    fields.append("ctrlchandle")
    fields.append("writelog")
    fields.append("loghandle")
    fields.append("debuginfo")
    fields.append("usermessage")
    fields.append("msgmask")
    fields.append("msghandle")
    fields.append("bb_usenode")
    fields.append("bb_nodehandle")
    fields.append("bb_usebranch")
    fields.append("bb_branchhandle")
    fields.append("rowcol_name")
    return fields
  }
}

object LpSolve extends SNA {
  //def Instance = Native.loadLibrary("lpsolve55", classOf[LpSolve]).asInstanceOf[LpSolve]
  snaLibrary = "lpsolve55"
  val make_lp = SNA[Int, Int, lprec]
  
  def makeLp(rows: Int, cols: Int) = {
    var r = rows
    var c = cols
    val lp = make_lp(r, c)
    println(lp)
  }
}

  

class RectTableau(val rows: Int, val cols: Int, tableau: ArrayBuffer[Int]) {
  def this(rows: Int, cols: Int) = this(rows, cols, 
      ArrayBuffer.tabulate[Int](rows*cols)(k => k/cols + 1))
  
  val matrixForm = new ArrayBuffer[ArrayBuffer[Int]]()
  
  /*
   * getRow() -- Return the row containing the cell labeled by n.
   */
  def getRowOf(n: Int) = tableau(n-1)
  
  /*
   * @param k Label of cell of tableau
   * 
   * @result  Row and column in which the cell labeled by k lies
   */
  def apply(k: Int) = {
    val row = tableau(k)
    var col = 1
    var i = 0
    while (i < k) {
      if (tableau(i) == row) col += 1
      i += 1
    }
    (row, col) 
  }

  def updateRow(label: Int, newRow: Int) = { tableau(label-1) = newRow }

  def copy(): RectTableau = new RectTableau(rows, cols, tableau.clone())
  
  def toMatrix = {
    if (matrixForm.length == 0) {
      var i = 0
      while (i < rows) { matrixForm += ArrayBuffer[Int](); i += 1 }
      i = 0
      while (i < rows*cols) { matrixForm(tableau(i)-1).append(i+1); i += 1 }
    }
    matrixForm
  }

  def toCone = {
    assert(rows > 0)
    assert(cols > 0)
    val P = PolyhedralCone.positiveWeylChamber(rows + cols, 0, rows).
            intersection(
              PolyhedralCone.positiveWeylChamber(rows + cols, rows, cols)
            )
            
    val ieqs = Array.newBuilder[Array[Int]]
    val ieq = ArrayBuffer.fill[Int](rows + cols)(0)
    for (i <- 0 to rows-1) {
      for (j <- 0 to cols-1) {
        for (k <- 0 to rows-1) {
          for (l <- 0 to cols-1) {
            ieq.update(i, 1)
            ieq.update(rows + j, 1)
            ieq(k) -= 1
            ieq(cols + l) -= 1
            // Add inequality to the array of inequalities
            ieqs += ieq.toArray[Int]
            // Reset ArrayBuffer to zero for next loop
            ieq(i)=0; ieq(rows+j)=0; ieq(k)=0; ieq(rows+l)=0
          }
        }
      }
    }
    P.intersection(new PolyhedralCone(Array[Array[Int]](), ieqs.result))
  }
  
  /*
   * isTrivial -- True if the tableau is labeled row-by-row sequentially 
   */
  def isTrivialTableau: Boolean = {
    if (rows*cols == 0) return true
    var i = 0
    while (i < rows*cols) { 
      if (tableau(i) != i/cols + 1) {
        return false
      }
      i += 1
    }
    return true
  }

  def isStandardTableau: Boolean = {
    val rowLength = ArrayBuffer.fill[Int](rows)(0)
    var i = 0
    while (i < rows*cols) {
      if (tableau(i) > 0 && tableau(i) <= rows) {
        rowLength(tableau(i)-1) += 1
        // If a row extends beyond the specified number of columns, its not standard
        if (rowLength(tableau(i)-1) > cols) return false
        // If a cell was filled in before the cell above it was filled in
        // then the column will have a decrease
        if (tableau(i) > 1 && rowLength(tableau(i)-1) > rowLength(tableau(i)-2)) return false
      } else {
        return false
      }
      i += 1
    }
    return true
  }
  
  def isAdmissible: Boolean = {
    val solver = LpSolve.makeLp(rows, cols)
    /*
    // Define MIP
    //val solver = LpSolve.makeLp(0, rows + cols)
    
    // Makes building a model row by row faster
    // WARNING: This caused a memory fault
    // solver.setAddRowmode(true)
    
    // Consider quantities less than 0.1 to be zero
    solver.setEpsb(0.1)
    // solver.setEpsel(0.1)
    
    // Break at first solution
    solver.setBreakAtFirst(true)
    
    val eps = 0.1
        
    // Add constraint that A variables minus B variables is zero
    val sum = ArrayBuffer.fill[Double](rows)(1.0)
    val varlist = (1 to rows + cols).toArray
    sum.appendAll(ArrayBuffer.fill[Double](cols)(-1.0))
    solver.addConstraint(sum.toArray, LpSolve.EQ, 0)
    // add order condition
    
    solver.addConstraintex(rows + cols, 
                           Array[Double](1.0, -1.0, 1.0, -1.0), 
                           Array(1, 2, 3, 4), 
                           LpSolve.GE, 
                           eps)
    
    for (i <- 1 to rows-1)
      solver.addConstraintex(rows + cols, 
                             Array(1.0, -1.0, 0.0), 
                             Array(i, i+1, rows+1), 
                             LpSolve.GE, 
                             eps)
    for (i <- 1 to cols-1)
      solver.addConstraintex(rows + cols, 
                             Array(1.0, -1.0, 0.0), 
                             Array(rows+i, rows+i+1, 1), 
                             LpSolve.GE, 
                             eps)
    // add order condition  
    for (i <- 1 to rows) {
      for (j <- 1 to cols) {
        for (k <- 1 to rows) {
          for (l <- 1 to cols) {
            if (this.toMatrix(i)(j) < this.toMatrix(k)(l))
              solver.addConstraintex(rows + cols, 
                                     Array[Double](1.0, -1.0, 1.0, -1.0), 
                                     Array(i, k, rows+j, rows+l), 
                                     LpSolve.GE, 
                                     eps)
          }
        }
      }
    }
    println(a)
    //solver.setPresolve(LpSolve.PRESOLVE_ROWS + LpSolve.PRESOLVE_COLS, 0)
        
    // Solve the problem
    solver.solve()
    
    // Test whether the solution was empty
    val result = (solver.getSolutioncount() > 0)

    // delete the problem and free memory
    solver.deleteLp();
    */
    return true //result
  }
}

object RectTableau {
  def apply(rows: Int, cols: Int) = new RectTableau(rows, cols)
  def apply(rows: Int, cols: Int, tableau: ArrayBuffer[Int]) 
    = new RectTableau(rows, cols, tableau)
  
  def standardTableaux(rows: Int, cols: Int): ListBuffer[RectTableau] = {
    /*
     * [[1, 3],
     * [2, 4]]
     * [1, 2, 1, 2]
     * j = 2
    Locate the smallest integer j such that j is not
    in the lowest corner of the subtableau T_j formed by
    1,...,j.  This happens to be first j such that
    tableau_vector[j]<tableau_vector[j-1].
    l will correspond to the shape of T_j
    */
    // Initialize to the vertically sequential tableau
    var T = new RectTableau(rows, 
                            cols,
                            ArrayBuffer.tabulate[Int](rows*cols)(
                                n => (n % rows) + 1))
    
    var listTableaux = ListBuffer[RectTableau](T.copy())
    if (rows <= 1 || cols <= 1) return listTableaux
    val size = rows*cols
    var subTabHeight = 0
    var nextRow = 0
    
    var i, m, r = 0
    var subTabSize = 0
    
    // Initialize subTabShape to the correct length
    val subTabShape = ArrayBuffer.fill[Int](size)(0)
    
    // Separate part of the algorithm into a function so that the second while
    // loop can be broken by a return statement.
    def calcSubTabShape(): Unit = {
      // Initialize subTabShape to [1, 0, ...]
      subTabShape(0) = 1
      var j = 1
      while (j < size) {
        subTabShape(j) = 0
        j += 1
      }
      // Calculate subTabShape
      subTabSize = 1
      j = 2
      while (j <= size) {
        subTabShape(T.getRowOf(j)-1) += 1
        if (T.getRowOf(j) < T.getRowOf(j-1)) {
          subTabSize = j
          return
        }
        j += 1
      }
    }

    do {
      calcSubTabShape()
      
      // Find the last nonzero row of l and store it in k
      i = size - 1
      while (subTabShape(i) == 0) i -= 1
      subTabHeight = i+1
      
      // Find a new row for the largest element of the subtableau 
      // (next lowest corner)
      nextRow = subTabShape(T.getRowOf(subTabSize))
      //i = subTabHeight
      while (subTabShape(i) != nextRow) i -= 1
      
      // Move label subTabSize to row i+1
      T.updateRow(subTabSize, i+1)
      subTabShape(i) -= 1
      
      // WARNING: T must be allowed to temporarily be a non-tableau for the updates to work
      // Fill in the columns of T_j using 1,...,j-1 in increasing order
      m = 1
      while (m < subTabSize) {
        r = 1
        while (subTabShape(r-1) != 0) {
          T.updateRow(m, r)
          subTabShape(r-1) -= 1
          m += 1
          r += 1
        }
      }
      listTableaux.prepend(T.copy())
    } while (!T.isTrivialTableau)
    return listTableaux
  }
}
