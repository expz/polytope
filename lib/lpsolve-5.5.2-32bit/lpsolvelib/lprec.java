package lpsolvelib;
import com.sun.jna.NativeLong;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.ptr.DoubleByReference;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;
import java.util.Arrays;
import java.util.List;
import lpsolvelib.LpSolveLibLibrary.BFP_lp;
import lpsolvelib.LpSolveLibLibrary.BFP_lprealint;
import lpsolvelib.LpSolveLibLibrary.BFP_lprealintrealint;
import lpsolvelib.LpSolveLibLibrary.BFPbool_lp;
import lpsolvelib.LpSolveLibLibrary.BFPbool_lpbool;
import lpsolvelib.LpSolveLibLibrary.BFPbool_lpint;
import lpsolvelib.LpSolveLibLibrary.BFPbool_lpintintchar;
import lpsolvelib.LpSolveLibLibrary.BFPbool_lpintintint;
import lpsolvelib.LpSolveLibLibrary.BFPchar;
import lpsolvelib.LpSolveLibLibrary.BFPint_lp;
import lpsolvelib.LpSolveLibLibrary.BFPint_lpbool;
import lpsolvelib.LpSolveLibLibrary.BFPint_lpint;
import lpsolvelib.LpSolveLibLibrary.BFPint_lpintintboolbool;
import lpsolvelib.LpSolveLibLibrary.BFPint_lpintrealcbintint;
import lpsolvelib.LpSolveLibLibrary.BFPlreal_lpintintreal;
import lpsolvelib.LpSolveLibLibrary.BFPreal_lp;
import lpsolvelib.LpSolveLibLibrary.BFPreal_lplrealreal;
import lpsolvelib.LpSolveLibLibrary.BFPrealp_lp;
import lpsolvelib.LpSolveLibLibrary.XLIbool_lpcharcharbool;
import lpsolvelib.LpSolveLibLibrary.XLIbool_lpcharcharcharint;
import lpsolvelib.LpSolveLibLibrary.XLIbool_lpintintint;
import lpsolvelib.LpSolveLibLibrary.XLIchar;
import lpsolvelib.LpSolveLibLibrary.add_SOS_func;
import lpsolvelib.LpSolveLibLibrary.add_column_func;
import lpsolvelib.LpSolveLibLibrary.add_columnex_func;
import lpsolvelib.LpSolveLibLibrary.add_constraint_func;
import lpsolvelib.LpSolveLibLibrary.add_constraintex_func;
import lpsolvelib.LpSolveLibLibrary.add_lag_con_func;
import lpsolvelib.LpSolveLibLibrary.clear_actionfunc;
import lpsolvelib.LpSolveLibLibrary.column_in_lp_func;
import lpsolvelib.LpSolveLibLibrary.copy_lp_func;
import lpsolvelib.LpSolveLibLibrary.default_basis_func;
import lpsolvelib.LpSolveLibLibrary.del_column_func;
import lpsolvelib.LpSolveLibLibrary.del_constraint_func;
import lpsolvelib.LpSolveLibLibrary.delete_lp_func;
import lpsolvelib.LpSolveLibLibrary.dualize_lp_func;
import lpsolvelib.LpSolveLibLibrary.explainfunc;
import lpsolvelib.LpSolveLibLibrary.free_lp_func;
import lpsolvelib.LpSolveLibLibrary.getMDOfunc;
import lpsolvelib.LpSolveLibLibrary.get_Lrows_func;
import lpsolvelib.LpSolveLibLibrary.get_Ncolumns_func;
import lpsolvelib.LpSolveLibLibrary.get_Norig_columns_func;
import lpsolvelib.LpSolveLibLibrary.get_Norig_rows_func;
import lpsolvelib.LpSolveLibLibrary.get_Nrows_func;
import lpsolvelib.LpSolveLibLibrary.get_OF_activefunc;
import lpsolvelib.LpSolveLibLibrary.get_anti_degen_func;
import lpsolvelib.LpSolveLibLibrary.get_basis_func;
import lpsolvelib.LpSolveLibLibrary.get_basiscrash_func;
import lpsolvelib.LpSolveLibLibrary.get_bb_depthlimit_func;
import lpsolvelib.LpSolveLibLibrary.get_bb_floorfirst_func;
import lpsolvelib.LpSolveLibLibrary.get_bb_rule_func;
import lpsolvelib.LpSolveLibLibrary.get_bounds_tighter_func;
import lpsolvelib.LpSolveLibLibrary.get_break_at_value_func;
import lpsolvelib.LpSolveLibLibrary.get_col_name_func;
import lpsolvelib.LpSolveLibLibrary.get_columnex_func;
import lpsolvelib.LpSolveLibLibrary.get_constr_type_func;
import lpsolvelib.LpSolveLibLibrary.get_constr_value_func;
import lpsolvelib.LpSolveLibLibrary.get_constraints_func;
import lpsolvelib.LpSolveLibLibrary.get_dual_solution_func;
import lpsolvelib.LpSolveLibLibrary.get_epsb_func;
import lpsolvelib.LpSolveLibLibrary.get_epsd_func;
import lpsolvelib.LpSolveLibLibrary.get_epsel_func;
import lpsolvelib.LpSolveLibLibrary.get_epsint_func;
import lpsolvelib.LpSolveLibLibrary.get_epsperturb_func;
import lpsolvelib.LpSolveLibLibrary.get_epspivot_func;
import lpsolvelib.LpSolveLibLibrary.get_improve_func;
import lpsolvelib.LpSolveLibLibrary.get_infinite_func;
import lpsolvelib.LpSolveLibLibrary.get_lambda_func;
import lpsolvelib.LpSolveLibLibrary.get_lowbo_func;
import lpsolvelib.LpSolveLibLibrary.get_lp_index_func;
import lpsolvelib.LpSolveLibLibrary.get_lp_name_func;
import lpsolvelib.LpSolveLibLibrary.get_mat_byindex_func;
import lpsolvelib.LpSolveLibLibrary.get_mat_func;
import lpsolvelib.LpSolveLibLibrary.get_max_level_func;
import lpsolvelib.LpSolveLibLibrary.get_maxpivot_func;
import lpsolvelib.LpSolveLibLibrary.get_mip_gap_func;
import lpsolvelib.LpSolveLibLibrary.get_multiprice_func;
import lpsolvelib.LpSolveLibLibrary.get_nameindex_func;
import lpsolvelib.LpSolveLibLibrary.get_negrange_func;
import lpsolvelib.LpSolveLibLibrary.get_nz_func;
import lpsolvelib.LpSolveLibLibrary.get_obj_bound_func;
import lpsolvelib.LpSolveLibLibrary.get_objective_func;
import lpsolvelib.LpSolveLibLibrary.get_orig_index_func;
import lpsolvelib.LpSolveLibLibrary.get_origcol_name_func;
import lpsolvelib.LpSolveLibLibrary.get_origrow_name_func;
import lpsolvelib.LpSolveLibLibrary.get_partialprice_func;
import lpsolvelib.LpSolveLibLibrary.get_pivoting_func;
import lpsolvelib.LpSolveLibLibrary.get_presolve_func;
import lpsolvelib.LpSolveLibLibrary.get_presolveloops_func;
import lpsolvelib.LpSolveLibLibrary.get_primal_solution_func;
import lpsolvelib.LpSolveLibLibrary.get_print_sol_func;
import lpsolvelib.LpSolveLibLibrary.get_pseudocosts_func;
import lpsolvelib.LpSolveLibLibrary.get_ptr_constraints_func;
import lpsolvelib.LpSolveLibLibrary.get_ptr_dual_solution_func;
import lpsolvelib.LpSolveLibLibrary.get_ptr_lambda_func;
import lpsolvelib.LpSolveLibLibrary.get_ptr_primal_solution_func;
import lpsolvelib.LpSolveLibLibrary.get_ptr_sensitivity_obj_func;
import lpsolvelib.LpSolveLibLibrary.get_ptr_sensitivity_objex_func;
import lpsolvelib.LpSolveLibLibrary.get_ptr_sensitivity_rhs_func;
import lpsolvelib.LpSolveLibLibrary.get_ptr_variables_func;
import lpsolvelib.LpSolveLibLibrary.get_rh_func;
import lpsolvelib.LpSolveLibLibrary.get_rh_range_func;
import lpsolvelib.LpSolveLibLibrary.get_row_func;
import lpsolvelib.LpSolveLibLibrary.get_row_name_func;
import lpsolvelib.LpSolveLibLibrary.get_rowex_func;
import lpsolvelib.LpSolveLibLibrary.get_scalelimit_func;
import lpsolvelib.LpSolveLibLibrary.get_scaling_func;
import lpsolvelib.LpSolveLibLibrary.get_sensitivity_obj_func;
import lpsolvelib.LpSolveLibLibrary.get_sensitivity_objex_func;
import lpsolvelib.LpSolveLibLibrary.get_sensitivity_rhs_func;
import lpsolvelib.LpSolveLibLibrary.get_simplextype_func;
import lpsolvelib.LpSolveLibLibrary.get_solutioncount_func;
import lpsolvelib.LpSolveLibLibrary.get_solutionlimit_func;
import lpsolvelib.LpSolveLibLibrary.get_status_func;
import lpsolvelib.LpSolveLibLibrary.get_statustext_func;
import lpsolvelib.LpSolveLibLibrary.get_timeout_func;
import lpsolvelib.LpSolveLibLibrary.get_total_iter_func;
import lpsolvelib.LpSolveLibLibrary.get_total_nodes_func;
import lpsolvelib.LpSolveLibLibrary.get_upbo_func;
import lpsolvelib.LpSolveLibLibrary.get_var_branch_func;
import lpsolvelib.LpSolveLibLibrary.get_var_dualresult_func;
import lpsolvelib.LpSolveLibLibrary.get_var_primalresult_func;
import lpsolvelib.LpSolveLibLibrary.get_var_priority_func;
import lpsolvelib.LpSolveLibLibrary.get_variables_func;
import lpsolvelib.LpSolveLibLibrary.get_verbose_func;
import lpsolvelib.LpSolveLibLibrary.get_working_objective_func;
import lpsolvelib.LpSolveLibLibrary.getpackedfunc;
import lpsolvelib.LpSolveLibLibrary.getvectorfunc;
import lpsolvelib.LpSolveLibLibrary.has_BFP_func;
import lpsolvelib.LpSolveLibLibrary.has_XLI_func;
import lpsolvelib.LpSolveLibLibrary.invertfunc;
import lpsolvelib.LpSolveLibLibrary.is_SOS_var_func;
import lpsolvelib.LpSolveLibLibrary.is_actionfunc;
import lpsolvelib.LpSolveLibLibrary.is_add_rowmode_func;
import lpsolvelib.LpSolveLibLibrary.is_anti_degen_func;
import lpsolvelib.LpSolveLibLibrary.is_binary_func;
import lpsolvelib.LpSolveLibLibrary.is_break_at_first_func;
import lpsolvelib.LpSolveLibLibrary.is_constr_type_func;
import lpsolvelib.LpSolveLibLibrary.is_debug_func;
import lpsolvelib.LpSolveLibLibrary.is_feasible_func;
import lpsolvelib.LpSolveLibLibrary.is_infinite_func;
import lpsolvelib.LpSolveLibLibrary.is_int_func;
import lpsolvelib.LpSolveLibLibrary.is_integerscaling_func;
import lpsolvelib.LpSolveLibLibrary.is_lag_trace_func;
import lpsolvelib.LpSolveLibLibrary.is_maxim_func;
import lpsolvelib.LpSolveLibLibrary.is_nativeBFP_func;
import lpsolvelib.LpSolveLibLibrary.is_nativeXLI_func;
import lpsolvelib.LpSolveLibLibrary.is_negative_func;
import lpsolvelib.LpSolveLibLibrary.is_obj_in_basis_func;
import lpsolvelib.LpSolveLibLibrary.is_piv_mode_func;
import lpsolvelib.LpSolveLibLibrary.is_piv_rule_func;
import lpsolvelib.LpSolveLibLibrary.is_presolve_func;
import lpsolvelib.LpSolveLibLibrary.is_scalemode_func;
import lpsolvelib.LpSolveLibLibrary.is_scaletype_func;
import lpsolvelib.LpSolveLibLibrary.is_semicont_func;
import lpsolvelib.LpSolveLibLibrary.is_trace_func;
import lpsolvelib.LpSolveLibLibrary.is_unbounded_func;
import lpsolvelib.LpSolveLibLibrary.is_use_names_func;
import lpsolvelib.LpSolveLibLibrary.lp_solve_version_func;
import lpsolvelib.LpSolveLibLibrary.lphandle_intfunc;
import lpsolvelib.LpSolveLibLibrary.lphandleint_func;
import lpsolvelib.LpSolveLibLibrary.lphandleint_intfunc;
import lpsolvelib.LpSolveLibLibrary.lphandlestr_func;
import lpsolvelib.LpSolveLibLibrary.make_lp_func;
import lpsolvelib.LpSolveLibLibrary.print_constraints_func;
import lpsolvelib.LpSolveLibLibrary.print_debugdump_func;
import lpsolvelib.LpSolveLibLibrary.print_duals_func;
import lpsolvelib.LpSolveLibLibrary.print_lp_func;
import lpsolvelib.LpSolveLibLibrary.print_objective_func;
import lpsolvelib.LpSolveLibLibrary.print_scales_func;
import lpsolvelib.LpSolveLibLibrary.print_solution_func;
import lpsolvelib.LpSolveLibLibrary.print_str_func;
import lpsolvelib.LpSolveLibLibrary.print_tableau_func;
import lpsolvelib.LpSolveLibLibrary.put_abortfunc_func;
import lpsolvelib.LpSolveLibLibrary.put_bb_branchfunc_func;
import lpsolvelib.LpSolveLibLibrary.put_bb_nodefunc_func;
import lpsolvelib.LpSolveLibLibrary.put_logfunc_func;
import lpsolvelib.LpSolveLibLibrary.put_msgfunc_func;
import lpsolvelib.LpSolveLibLibrary.read_LP_func;
import lpsolvelib.LpSolveLibLibrary.read_MPS_func;
import lpsolvelib.LpSolveLibLibrary.read_XLI_func;
import lpsolvelib.LpSolveLibLibrary.read_basis_func;
import lpsolvelib.LpSolveLibLibrary.read_params_func;
import lpsolvelib.LpSolveLibLibrary.reportfunc;
import lpsolvelib.LpSolveLibLibrary.reset_basis_func;
import lpsolvelib.LpSolveLibLibrary.reset_params_func;
import lpsolvelib.LpSolveLibLibrary.resize_lp_func;
import lpsolvelib.LpSolveLibLibrary.set_BFP_func;
import lpsolvelib.LpSolveLibLibrary.set_XLI_func;
import lpsolvelib.LpSolveLibLibrary.set_actionfunc;
import lpsolvelib.LpSolveLibLibrary.set_add_rowmode_func;
import lpsolvelib.LpSolveLibLibrary.set_anti_degen_func;
import lpsolvelib.LpSolveLibLibrary.set_basis_func;
import lpsolvelib.LpSolveLibLibrary.set_basiscrash_func;
import lpsolvelib.LpSolveLibLibrary.set_basisvar_func;
import lpsolvelib.LpSolveLibLibrary.set_bb_depthlimit_func;
import lpsolvelib.LpSolveLibLibrary.set_bb_floorfirst_func;
import lpsolvelib.LpSolveLibLibrary.set_bb_rule_func;
import lpsolvelib.LpSolveLibLibrary.set_binary_func;
import lpsolvelib.LpSolveLibLibrary.set_bounds_func;
import lpsolvelib.LpSolveLibLibrary.set_bounds_tighter_func;
import lpsolvelib.LpSolveLibLibrary.set_break_at_first_func;
import lpsolvelib.LpSolveLibLibrary.set_break_at_value_func;
import lpsolvelib.LpSolveLibLibrary.set_col_name_func;
import lpsolvelib.LpSolveLibLibrary.set_column_func;
import lpsolvelib.LpSolveLibLibrary.set_columnex_func;
import lpsolvelib.LpSolveLibLibrary.set_constr_type_func;
import lpsolvelib.LpSolveLibLibrary.set_debug_func;
import lpsolvelib.LpSolveLibLibrary.set_epsb_func;
import lpsolvelib.LpSolveLibLibrary.set_epsd_func;
import lpsolvelib.LpSolveLibLibrary.set_epsel_func;
import lpsolvelib.LpSolveLibLibrary.set_epsint_func;
import lpsolvelib.LpSolveLibLibrary.set_epslevel_func;
import lpsolvelib.LpSolveLibLibrary.set_epsperturb_func;
import lpsolvelib.LpSolveLibLibrary.set_epspivot_func;
import lpsolvelib.LpSolveLibLibrary.set_improve_func;
import lpsolvelib.LpSolveLibLibrary.set_infinite_func;
import lpsolvelib.LpSolveLibLibrary.set_int_func;
import lpsolvelib.LpSolveLibLibrary.set_lag_trace_func;
import lpsolvelib.LpSolveLibLibrary.set_lowbo_func;
import lpsolvelib.LpSolveLibLibrary.set_lp_name_func;
import lpsolvelib.LpSolveLibLibrary.set_mat_func;
import lpsolvelib.LpSolveLibLibrary.set_maxim_func;
import lpsolvelib.LpSolveLibLibrary.set_maxpivot_func;
import lpsolvelib.LpSolveLibLibrary.set_minim_func;
import lpsolvelib.LpSolveLibLibrary.set_mip_gap_func;
import lpsolvelib.LpSolveLibLibrary.set_multiprice_func;
import lpsolvelib.LpSolveLibLibrary.set_negrange_func;
import lpsolvelib.LpSolveLibLibrary.set_obj_bound_func;
import lpsolvelib.LpSolveLibLibrary.set_obj_fn_func;
import lpsolvelib.LpSolveLibLibrary.set_obj_fnex_func;
import lpsolvelib.LpSolveLibLibrary.set_obj_func;
import lpsolvelib.LpSolveLibLibrary.set_obj_in_basis_func;
import lpsolvelib.LpSolveLibLibrary.set_outputfile_func;
import lpsolvelib.LpSolveLibLibrary.set_outputstream_func;
import lpsolvelib.LpSolveLibLibrary.set_partialprice_func;
import lpsolvelib.LpSolveLibLibrary.set_pivoting_func;
import lpsolvelib.LpSolveLibLibrary.set_preferdual_func;
import lpsolvelib.LpSolveLibLibrary.set_presolve_func;
import lpsolvelib.LpSolveLibLibrary.set_print_sol_func;
import lpsolvelib.LpSolveLibLibrary.set_pseudocosts_func;
import lpsolvelib.LpSolveLibLibrary.set_rh_func;
import lpsolvelib.LpSolveLibLibrary.set_rh_range_func;
import lpsolvelib.LpSolveLibLibrary.set_rh_vec_func;
import lpsolvelib.LpSolveLibLibrary.set_row_func;
import lpsolvelib.LpSolveLibLibrary.set_row_name_func;
import lpsolvelib.LpSolveLibLibrary.set_rowex_func;
import lpsolvelib.LpSolveLibLibrary.set_scalelimit_func;
import lpsolvelib.LpSolveLibLibrary.set_scaling_func;
import lpsolvelib.LpSolveLibLibrary.set_semicont_func;
import lpsolvelib.LpSolveLibLibrary.set_sense_func;
import lpsolvelib.LpSolveLibLibrary.set_simplextype_func;
import lpsolvelib.LpSolveLibLibrary.set_solutionlimit_func;
import lpsolvelib.LpSolveLibLibrary.set_timeout_func;
import lpsolvelib.LpSolveLibLibrary.set_trace_func;
import lpsolvelib.LpSolveLibLibrary.set_unbounded_func;
import lpsolvelib.LpSolveLibLibrary.set_upbo_func;
import lpsolvelib.LpSolveLibLibrary.set_use_names_func;
import lpsolvelib.LpSolveLibLibrary.set_var_branch_func;
import lpsolvelib.LpSolveLibLibrary.set_var_weights_func;
import lpsolvelib.LpSolveLibLibrary.set_verbose_func;
import lpsolvelib.LpSolveLibLibrary.solve_func;
import lpsolvelib.LpSolveLibLibrary.str_add_column_func;
import lpsolvelib.LpSolveLibLibrary.str_add_constraint_func;
import lpsolvelib.LpSolveLibLibrary.str_add_lag_con_func;
import lpsolvelib.LpSolveLibLibrary.str_set_obj_fn_func;
import lpsolvelib.LpSolveLibLibrary.str_set_rh_vec_func;
import lpsolvelib.LpSolveLibLibrary.time_elapsed_func;
import lpsolvelib.LpSolveLibLibrary.unscale_func;
import lpsolvelib.LpSolveLibLibrary.userabortfunc;
import lpsolvelib.LpSolveLibLibrary.write_LP_func;
import lpsolvelib.LpSolveLibLibrary.write_MPS_func;
import lpsolvelib.LpSolveLibLibrary.write_XLI_func;
import lpsolvelib.LpSolveLibLibrary.write_basis_func;
import lpsolvelib.LpSolveLibLibrary.write_freeMPS_func;
import lpsolvelib.LpSolveLibLibrary.write_freemps_func;
import lpsolvelib.LpSolveLibLibrary.write_lp_func;
import lpsolvelib.LpSolveLibLibrary.write_mps_func;
import lpsolvelib.LpSolveLibLibrary.write_params_func;
/**
 * -------------------------------------------------------------------------<br>
 * <i>native declaration : lp_lib.h:3724</i><br>
 * This file was autogenerated by <a href="http://jnaerator.googlecode.com/">JNAerator</a>,<br>
 * a tool written by <a href="http://ochafik.com/">Olivier Chafik</a> that <a href="http://code.google.com/p/jnaerator/wiki/CreditsAndLicense">uses a few opensource projects.</a>.<br>
 * For help, please visit <a href="http://nativelibs4java.googlecode.com/">NativeLibs4Java</a> , <a href="http://rococoa.dev.java.net/">Rococoa</a>, or <a href="http://jna.dev.java.net/">JNA</a>.
 */
public class lprec extends Structure {
	/**
	 * Full list of exported functions made available in a quasi object-oriented fashion<br>
	 * C type : add_column_func*
	 */
	public add_column_func add_column;
	/** C type : add_columnex_func* */
	public add_columnex_func add_columnex;
	/** C type : add_constraint_func* */
	public add_constraint_func add_constraint;
	/** C type : add_constraintex_func* */
	public add_constraintex_func add_constraintex;
	/** C type : add_lag_con_func* */
	public add_lag_con_func add_lag_con;
	/** C type : add_SOS_func* */
	public add_SOS_func add_SOS;
	/** C type : column_in_lp_func* */
	public column_in_lp_func column_in_lp;
	/** C type : copy_lp_func* */
	public copy_lp_func copy_lp;
	/** C type : default_basis_func* */
	public default_basis_func default_basis;
	/** C type : del_column_func* */
	public del_column_func del_column;
	/** C type : del_constraint_func* */
	public del_constraint_func del_constraint;
	/** C type : delete_lp_func* */
	public delete_lp_func delete_lp;
	/** C type : dualize_lp_func* */
	public dualize_lp_func dualize_lp;
	/** C type : free_lp_func* */
	public free_lp_func free_lp;
	/** C type : get_anti_degen_func* */
	public get_anti_degen_func get_anti_degen;
	/** C type : get_basis_func* */
	public get_basis_func get_basis;
	/** C type : get_basiscrash_func* */
	public get_basiscrash_func get_basiscrash;
	/** C type : get_bb_depthlimit_func* */
	public get_bb_depthlimit_func get_bb_depthlimit;
	/** C type : get_bb_floorfirst_func* */
	public get_bb_floorfirst_func get_bb_floorfirst;
	/** C type : get_bb_rule_func* */
	public get_bb_rule_func get_bb_rule;
	/** C type : get_bounds_tighter_func* */
	public get_bounds_tighter_func get_bounds_tighter;
	/** C type : get_break_at_value_func* */
	public get_break_at_value_func get_break_at_value;
	/** C type : get_col_name_func* */
	public get_col_name_func get_col_name;
	/** C type : get_columnex_func* */
	public get_columnex_func get_columnex;
	/** C type : get_constr_type_func* */
	public get_constr_type_func get_constr_type;
	/** C type : get_constr_value_func* */
	public get_constr_value_func get_constr_value;
	/** C type : get_constraints_func* */
	public get_constraints_func get_constraints;
	/** C type : get_dual_solution_func* */
	public get_dual_solution_func get_dual_solution;
	/** C type : get_epsb_func* */
	public get_epsb_func get_epsb;
	/** C type : get_epsd_func* */
	public get_epsd_func get_epsd;
	/** C type : get_epsel_func* */
	public get_epsel_func get_epsel;
	/** C type : get_epsint_func* */
	public get_epsint_func get_epsint;
	/** C type : get_epsperturb_func* */
	public get_epsperturb_func get_epsperturb;
	/** C type : get_epspivot_func* */
	public get_epspivot_func get_epspivot;
	/** C type : get_improve_func* */
	public get_improve_func get_improve;
	/** C type : get_infinite_func* */
	public get_infinite_func get_infinite;
	/** C type : get_lambda_func* */
	public get_lambda_func get_lambda;
	/** C type : get_lowbo_func* */
	public get_lowbo_func get_lowbo;
	/** C type : get_lp_index_func* */
	public get_lp_index_func get_lp_index;
	/** C type : get_lp_name_func* */
	public get_lp_name_func get_lp_name;
	/** C type : get_Lrows_func* */
	public get_Lrows_func get_Lrows;
	/** C type : get_mat_func* */
	public get_mat_func get_mat;
	/** C type : get_mat_byindex_func* */
	public get_mat_byindex_func get_mat_byindex;
	/** C type : get_max_level_func* */
	public get_max_level_func get_max_level;
	/** C type : get_maxpivot_func* */
	public get_maxpivot_func get_maxpivot;
	/** C type : get_mip_gap_func* */
	public get_mip_gap_func get_mip_gap;
	/** C type : get_multiprice_func* */
	public get_multiprice_func get_multiprice;
	/** C type : get_nameindex_func* */
	public get_nameindex_func get_nameindex;
	/** C type : get_Ncolumns_func* */
	public get_Ncolumns_func get_Ncolumns;
	/** C type : get_negrange_func* */
	public get_negrange_func get_negrange;
	/** C type : get_nz_func* */
	public get_nz_func get_nonzeros;
	/** C type : get_Norig_columns_func* */
	public get_Norig_columns_func get_Norig_columns;
	/** C type : get_Norig_rows_func* */
	public get_Norig_rows_func get_Norig_rows;
	/** C type : get_Nrows_func* */
	public get_Nrows_func get_Nrows;
	/** C type : get_obj_bound_func* */
	public get_obj_bound_func get_obj_bound;
	/** C type : get_objective_func* */
	public get_objective_func get_objective;
	/** C type : get_orig_index_func* */
	public get_orig_index_func get_orig_index;
	/** C type : get_origcol_name_func* */
	public get_origcol_name_func get_origcol_name;
	/** C type : get_origrow_name_func* */
	public get_origrow_name_func get_origrow_name;
	/** C type : get_partialprice_func* */
	public get_partialprice_func get_partialprice;
	/** C type : get_pivoting_func* */
	public get_pivoting_func get_pivoting;
	/** C type : get_presolve_func* */
	public get_presolve_func get_presolve;
	/** C type : get_presolveloops_func* */
	public get_presolveloops_func get_presolveloops;
	/** C type : get_primal_solution_func* */
	public get_primal_solution_func get_primal_solution;
	/** C type : get_print_sol_func* */
	public get_print_sol_func get_print_sol;
	/** C type : get_pseudocosts_func* */
	public get_pseudocosts_func get_pseudocosts;
	/** C type : get_ptr_constraints_func* */
	public get_ptr_constraints_func get_ptr_constraints;
	/** C type : get_ptr_dual_solution_func* */
	public get_ptr_dual_solution_func get_ptr_dual_solution;
	/** C type : get_ptr_lambda_func* */
	public get_ptr_lambda_func get_ptr_lambda;
	/** C type : get_ptr_primal_solution_func* */
	public get_ptr_primal_solution_func get_ptr_primal_solution;
	/** C type : get_ptr_sensitivity_obj_func* */
	public get_ptr_sensitivity_obj_func get_ptr_sensitivity_obj;
	/** C type : get_ptr_sensitivity_objex_func* */
	public get_ptr_sensitivity_objex_func get_ptr_sensitivity_objex;
	/** C type : get_ptr_sensitivity_rhs_func* */
	public get_ptr_sensitivity_rhs_func get_ptr_sensitivity_rhs;
	/** C type : get_ptr_variables_func* */
	public get_ptr_variables_func get_ptr_variables;
	/** C type : get_rh_func* */
	public get_rh_func get_rh;
	/** C type : get_rh_range_func* */
	public get_rh_range_func get_rh_range;
	/** C type : get_row_func* */
	public get_row_func get_row;
	/** C type : get_rowex_func* */
	public get_rowex_func get_rowex;
	/** C type : get_row_name_func* */
	public get_row_name_func get_row_name;
	/** C type : get_scalelimit_func* */
	public get_scalelimit_func get_scalelimit;
	/** C type : get_scaling_func* */
	public get_scaling_func get_scaling;
	/** C type : get_sensitivity_obj_func* */
	public get_sensitivity_obj_func get_sensitivity_obj;
	/** C type : get_sensitivity_objex_func* */
	public get_sensitivity_objex_func get_sensitivity_objex;
	/** C type : get_sensitivity_rhs_func* */
	public get_sensitivity_rhs_func get_sensitivity_rhs;
	/** C type : get_simplextype_func* */
	public get_simplextype_func get_simplextype;
	/** C type : get_solutioncount_func* */
	public get_solutioncount_func get_solutioncount;
	/** C type : get_solutionlimit_func* */
	public get_solutionlimit_func get_solutionlimit;
	/** C type : get_status_func* */
	public get_status_func get_status;
	/** C type : get_statustext_func* */
	public get_statustext_func get_statustext;
	/** C type : get_timeout_func* */
	public get_timeout_func get_timeout;
	/** C type : get_total_iter_func* */
	public get_total_iter_func get_total_iter;
	/** C type : get_total_nodes_func* */
	public get_total_nodes_func get_total_nodes;
	/** C type : get_upbo_func* */
	public get_upbo_func get_upbo;
	/** C type : get_var_branch_func* */
	public get_var_branch_func get_var_branch;
	/** C type : get_var_dualresult_func* */
	public get_var_dualresult_func get_var_dualresult;
	/** C type : get_var_primalresult_func* */
	public get_var_primalresult_func get_var_primalresult;
	/** C type : get_var_priority_func* */
	public get_var_priority_func get_var_priority;
	/** C type : get_variables_func* */
	public get_variables_func get_variables;
	/** C type : get_verbose_func* */
	public get_verbose_func get_verbose;
	/** C type : get_working_objective_func* */
	public get_working_objective_func get_working_objective;
	/** C type : has_BFP_func* */
	public has_BFP_func has_BFP;
	/** C type : has_XLI_func* */
	public has_XLI_func has_XLI;
	/** C type : is_add_rowmode_func* */
	public is_add_rowmode_func is_add_rowmode;
	/** C type : is_anti_degen_func* */
	public is_anti_degen_func is_anti_degen;
	/** C type : is_binary_func* */
	public is_binary_func is_binary;
	/** C type : is_break_at_first_func* */
	public is_break_at_first_func is_break_at_first;
	/** C type : is_constr_type_func* */
	public is_constr_type_func is_constr_type;
	/** C type : is_debug_func* */
	public is_debug_func is_debug;
	/** C type : is_feasible_func* */
	public is_feasible_func is_feasible;
	/** C type : is_infinite_func* */
	public is_infinite_func is_infinite;
	/** C type : is_int_func* */
	public is_int_func is_int;
	/** C type : is_integerscaling_func* */
	public is_integerscaling_func is_integerscaling;
	/** C type : is_lag_trace_func* */
	public is_lag_trace_func is_lag_trace;
	/** C type : is_maxim_func* */
	public is_maxim_func is_maxim;
	/** C type : is_nativeBFP_func* */
	public is_nativeBFP_func is_nativeBFP;
	/** C type : is_nativeXLI_func* */
	public is_nativeXLI_func is_nativeXLI;
	/** C type : is_negative_func* */
	public is_negative_func is_negative;
	/** C type : is_obj_in_basis_func* */
	public is_obj_in_basis_func is_obj_in_basis;
	/** C type : is_piv_mode_func* */
	public is_piv_mode_func is_piv_mode;
	/** C type : is_piv_rule_func* */
	public is_piv_rule_func is_piv_rule;
	/** C type : is_presolve_func* */
	public is_presolve_func is_presolve;
	/** C type : is_scalemode_func* */
	public is_scalemode_func is_scalemode;
	/** C type : is_scaletype_func* */
	public is_scaletype_func is_scaletype;
	/** C type : is_semicont_func* */
	public is_semicont_func is_semicont;
	/** C type : is_SOS_var_func* */
	public is_SOS_var_func is_SOS_var;
	/** C type : is_trace_func* */
	public is_trace_func is_trace;
	/** C type : is_unbounded_func* */
	public is_unbounded_func is_unbounded;
	/** C type : is_use_names_func* */
	public is_use_names_func is_use_names;
	/** C type : lp_solve_version_func* */
	public lp_solve_version_func lp_solve_version;
	/** C type : make_lp_func* */
	public make_lp_func make_lp;
	/** C type : print_constraints_func* */
	public print_constraints_func print_constraints;
	/** C type : print_debugdump_func* */
	public print_debugdump_func print_debugdump;
	/** C type : print_duals_func* */
	public print_duals_func print_duals;
	/** C type : print_lp_func* */
	public print_lp_func print_lp;
	/** C type : print_objective_func* */
	public print_objective_func print_objective;
	/** C type : print_scales_func* */
	public print_scales_func print_scales;
	/** C type : print_solution_func* */
	public print_solution_func print_solution;
	/** C type : print_str_func* */
	public print_str_func print_str;
	/** C type : print_tableau_func* */
	public print_tableau_func print_tableau;
	/** C type : put_abortfunc_func* */
	public put_abortfunc_func put_abortfunc;
	/** C type : put_bb_nodefunc_func* */
	public put_bb_nodefunc_func put_bb_nodefunc;
	/** C type : put_bb_branchfunc_func* */
	public put_bb_branchfunc_func put_bb_branchfunc;
	/** C type : put_logfunc_func* */
	public put_logfunc_func put_logfunc;
	/** C type : put_msgfunc_func* */
	public put_msgfunc_func put_msgfunc;
	/** C type : read_LP_func* */
	public read_LP_func read_LP;
	/** C type : read_MPS_func* */
	public read_MPS_func read_MPS;
	/** C type : read_XLI_func* */
	public read_XLI_func read_XLI;
	/** C type : read_params_func* */
	public read_params_func read_params;
	/** C type : read_basis_func* */
	public read_basis_func read_basis;
	/** C type : reset_basis_func* */
	public reset_basis_func reset_basis;
	/** C type : reset_params_func* */
	public reset_params_func reset_params;
	/** C type : resize_lp_func* */
	public resize_lp_func resize_lp;
	/** C type : set_add_rowmode_func* */
	public set_add_rowmode_func set_add_rowmode;
	/** C type : set_anti_degen_func* */
	public set_anti_degen_func set_anti_degen;
	/** C type : set_basisvar_func* */
	public set_basisvar_func set_basisvar;
	/** C type : set_basis_func* */
	public set_basis_func set_basis;
	/** C type : set_basiscrash_func* */
	public set_basiscrash_func set_basiscrash;
	/** C type : set_bb_depthlimit_func* */
	public set_bb_depthlimit_func set_bb_depthlimit;
	/** C type : set_bb_floorfirst_func* */
	public set_bb_floorfirst_func set_bb_floorfirst;
	/** C type : set_bb_rule_func* */
	public set_bb_rule_func set_bb_rule;
	/** C type : set_BFP_func* */
	public set_BFP_func set_BFP;
	/** C type : set_binary_func* */
	public set_binary_func set_binary;
	/** C type : set_bounds_func* */
	public set_bounds_func set_bounds;
	/** C type : set_bounds_tighter_func* */
	public set_bounds_tighter_func set_bounds_tighter;
	/** C type : set_break_at_first_func* */
	public set_break_at_first_func set_break_at_first;
	/** C type : set_break_at_value_func* */
	public set_break_at_value_func set_break_at_value;
	/** C type : set_column_func* */
	public set_column_func set_column;
	/** C type : set_columnex_func* */
	public set_columnex_func set_columnex;
	/** C type : set_col_name_func* */
	public set_col_name_func set_col_name;
	/** C type : set_constr_type_func* */
	public set_constr_type_func set_constr_type;
	/** C type : set_debug_func* */
	public set_debug_func set_debug;
	/** C type : set_epsb_func* */
	public set_epsb_func set_epsb;
	/** C type : set_epsd_func* */
	public set_epsd_func set_epsd;
	/** C type : set_epsel_func* */
	public set_epsel_func set_epsel;
	/** C type : set_epsint_func* */
	public set_epsint_func set_epsint;
	/** C type : set_epslevel_func* */
	public set_epslevel_func set_epslevel;
	/** C type : set_epsperturb_func* */
	public set_epsperturb_func set_epsperturb;
	/** C type : set_epspivot_func* */
	public set_epspivot_func set_epspivot;
	/** C type : set_unbounded_func* */
	public set_unbounded_func set_unbounded;
	/** C type : set_improve_func* */
	public set_improve_func set_improve;
	/** C type : set_infinite_func* */
	public set_infinite_func set_infinite;
	/** C type : set_int_func* */
	public set_int_func set_int;
	/** C type : set_lag_trace_func* */
	public set_lag_trace_func set_lag_trace;
	/** C type : set_lowbo_func* */
	public set_lowbo_func set_lowbo;
	/** C type : set_lp_name_func* */
	public set_lp_name_func set_lp_name;
	/** C type : set_mat_func* */
	public set_mat_func set_mat;
	/** C type : set_maxim_func* */
	public set_maxim_func set_maxim;
	/** C type : set_maxpivot_func* */
	public set_maxpivot_func set_maxpivot;
	/** C type : set_minim_func* */
	public set_minim_func set_minim;
	/** C type : set_mip_gap_func* */
	public set_mip_gap_func set_mip_gap;
	/** C type : set_multiprice_func* */
	public set_multiprice_func set_multiprice;
	/** C type : set_negrange_func* */
	public set_negrange_func set_negrange;
	/** C type : set_obj_bound_func* */
	public set_obj_bound_func set_obj_bound;
	/** C type : set_obj_fn_func* */
	public set_obj_fn_func set_obj_fn;
	/** C type : set_obj_fnex_func* */
	public set_obj_fnex_func set_obj_fnex;
	/** C type : set_obj_func* */
	public set_obj_func set_obj;
	/** C type : set_obj_in_basis_func* */
	public set_obj_in_basis_func set_obj_in_basis;
	/** C type : set_outputfile_func* */
	public set_outputfile_func set_outputfile;
	/** C type : set_outputstream_func* */
	public set_outputstream_func set_outputstream;
	/** C type : set_partialprice_func* */
	public set_partialprice_func set_partialprice;
	/** C type : set_pivoting_func* */
	public set_pivoting_func set_pivoting;
	/** C type : set_preferdual_func* */
	public set_preferdual_func set_preferdual;
	/** C type : set_presolve_func* */
	public set_presolve_func set_presolve;
	/** C type : set_print_sol_func* */
	public set_print_sol_func set_print_sol;
	/** C type : set_pseudocosts_func* */
	public set_pseudocosts_func set_pseudocosts;
	/** C type : set_rh_func* */
	public set_rh_func set_rh;
	/** C type : set_rh_range_func* */
	public set_rh_range_func set_rh_range;
	/** C type : set_rh_vec_func* */
	public set_rh_vec_func set_rh_vec;
	/** C type : set_row_func* */
	public set_row_func set_row;
	/** C type : set_rowex_func* */
	public set_rowex_func set_rowex;
	/** C type : set_row_name_func* */
	public set_row_name_func set_row_name;
	/** C type : set_scalelimit_func* */
	public set_scalelimit_func set_scalelimit;
	/** C type : set_scaling_func* */
	public set_scaling_func set_scaling;
	/** C type : set_semicont_func* */
	public set_semicont_func set_semicont;
	/** C type : set_sense_func* */
	public set_sense_func set_sense;
	/** C type : set_simplextype_func* */
	public set_simplextype_func set_simplextype;
	/** C type : set_solutionlimit_func* */
	public set_solutionlimit_func set_solutionlimit;
	/** C type : set_timeout_func* */
	public set_timeout_func set_timeout;
	/** C type : set_trace_func* */
	public set_trace_func set_trace;
	/** C type : set_upbo_func* */
	public set_upbo_func set_upbo;
	/** C type : set_use_names_func* */
	public set_use_names_func set_use_names;
	/** C type : set_var_branch_func* */
	public set_var_branch_func set_var_branch;
	/** C type : set_var_weights_func* */
	public set_var_weights_func set_var_weights;
	/** C type : set_verbose_func* */
	public set_verbose_func set_verbose;
	/** C type : set_XLI_func* */
	public set_XLI_func set_XLI;
	/** C type : solve_func* */
	public solve_func solve;
	/** C type : str_add_column_func* */
	public str_add_column_func str_add_column;
	/** C type : str_add_constraint_func* */
	public str_add_constraint_func str_add_constraint;
	/** C type : str_add_lag_con_func* */
	public str_add_lag_con_func str_add_lag_con;
	/** C type : str_set_obj_fn_func* */
	public str_set_obj_fn_func str_set_obj_fn;
	/** C type : str_set_rh_vec_func* */
	public str_set_rh_vec_func str_set_rh_vec;
	/** C type : time_elapsed_func* */
	public time_elapsed_func time_elapsed;
	/** C type : unscale_func* */
	public unscale_func unscale;
	/** C type : write_lp_func* */
	public write_lp_func write_lp;
	/** C type : write_LP_func* */
	public write_LP_func write_LP;
	/** C type : write_mps_func* */
	public write_mps_func write_mps;
	/** C type : write_MPS_func* */
	public write_MPS_func write_MPS;
	/** C type : write_freemps_func* */
	public write_freemps_func write_freemps;
	/** C type : write_freeMPS_func* */
	public write_freeMPS_func write_freeMPS;
	/** C type : write_XLI_func* */
	public write_XLI_func write_XLI;
	/** C type : write_basis_func* */
	public write_basis_func write_basis;
	/** C type : write_params_func* */
	public write_params_func write_params;
	/**
	 * Spacer<br>
	 * C type : int*
	 */
	public IntByReference alignmentspacer;
	/**
	 * Problem description<br>
	 * The name of the model<br>
	 * C type : char*
	 */
	public Pointer lp_name;
	/**
	 * Problem sizes<br>
	 * The total number of variables, including slacks
	 */
	public int sum;
	public int rows;
	public int columns;
	/** No of non-Lagrangean equality constraints in the problem */
	public int equalities;
	/** Count of bounded variables */
	public int boundedvars;
	public int INTfuture1;
	/**
	 * Memory allocation sizes<br>
	 * The allocated memory for row+column-sized data
	 */
	public int sum_alloc;
	/** The allocated memory for row-sized data */
	public int rows_alloc;
	/** The allocated memory for column-sized data */
	public int columns_alloc;
	/**
	 * Model status and solver result variables<br>
	 * The base model was read from a file
	 */
	public byte source_is_file;
	/** The model has been built entirely from row and column additions */
	public byte model_is_pure;
	/** Has this lp pased the 'test' */
	public byte model_is_valid;
	/** Specify if bounds will be tightened or overriden at bound setting */
	public byte tighten_on_set;
	/** Flag to indicate if names for rows and columns are used */
	public byte names_used;
	/** Flag to indicate if names for rows are used */
	public byte use_row_names;
	/** Flag to indicate if names for columns are used */
	public byte use_col_names;
	/** Print information on Lagrange progression */
	public byte lag_trace;
	/** Print information on simplex progression */
	public byte spx_trace;
	/** TRUE to print extra debug information */
	public byte bb_trace;
	/** TRUE if the handle should be closed at delete_lp() */
	public byte streamowned;
	/** TRUE if the objective function is in the basis matrix */
	public byte obj_in_basis;
	/** Simplex solver feasibility/mode code */
	public int spx_status;
	/** Extra status variable for lag_solve */
	public int lag_status;
	/** number of equal-valued solutions found (up to solutionlimit) */
	public int solutioncount;
	/** upper number of equal-valued solutions kept track of */
	public int solutionlimit;
	/** Optimal non-MIP solution base */
	public double real_solution;
	/**
	 * sum_alloc+1 : Solution array of the next to optimal LP,<br>
	 * Index   0           : Objective function value,<br>
	 * Indeces 1..rows     : Slack variable values,<br>
	 * Indeced rows+1..sum : Variable values<br>
	 * C type : double*
	 */
	public DoubleByReference solution;
	/**
	 * sum_alloc+1 : Solution array of optimal 'Integer' LP,<br>
	 * structured as the solution array above<br>
	 * C type : double*
	 */
	public DoubleByReference best_solution;
	/**
	 * sum_alloc+1 : Final solution array expanded for deleted variables<br>
	 * C type : double*
	 */
	public DoubleByReference full_solution;
	/**
	 * Array of reduced cost scaling norms (DEVEX and Steepest Edge)<br>
	 * C type : double*
	 */
	public DoubleByReference edgeVector;
	/**
	 * sum+1: Reduced costs of the last simplex<br>
	 * C type : double*
	 */
	public DoubleByReference drow;
	/**
	 * sum+1: Indeces of non-zero reduced costs of the last simplex<br>
	 * C type : int*
	 */
	public IntByReference nzdrow;
	/**
	 * rows_alloc+1 : The dual variables of the last LP<br>
	 * C type : double*
	 */
	public DoubleByReference duals;
	/**
	 * sum_alloc+1: Final duals array expanded for deleted variables<br>
	 * C type : double*
	 */
	public DoubleByReference full_duals;
	/**
	 * sum_alloc+1 :The sensitivity on dual variables/reduced costs<br>
	 * of the last LP<br>
	 * C type : double*
	 */
	public DoubleByReference dualsfrom;
	/**
	 * sum_alloc+1 :The sensitivity on dual variables/reduced costs<br>
	 * of the last LP<br>
	 * C type : double*
	 */
	public DoubleByReference dualstill;
	/**
	 * columns_alloc+1 :The sensitivity on objective function<br>
	 * of the last LP<br>
	 * C type : double*
	 */
	public DoubleByReference objfrom;
	/**
	 * columns_alloc+1 :The sensitivity on objective function<br>
	 * of the last LP<br>
	 * C type : double*
	 */
	public DoubleByReference objtill;
	/**
	 * columns_alloc+1 :The value of the variables when objective value<br>
	 * is at its from value of the last LP<br>
	 * C type : double*
	 */
	public DoubleByReference objfromvalue;
	/**
	 * Unused pointer - Placeholder for OF not part of B<br>
	 * C type : double*
	 */
	public DoubleByReference orig_obj;
	/**
	 * Special vector used to temporarily change the OF vector<br>
	 * C type : double*
	 */
	public DoubleByReference obj;
	/** Number of iterations in the current/last simplex */
	public long current_iter;
	/** Number of iterations over all B&B steps */
	public long total_iter;
	/** Number of bound swaps in the current/last simplex */
	public long current_bswap;
	/** Number of bount swaps over all B&B steps */
	public long total_bswap;
	/** The number of solve() performed in this model */
	public int solvecount;
	/** Number of pivots between refactorizations of the basis */
	public int max_pivots;
	/**
	 * Various execution parameters<br>
	 * Set desired combination of primal and dual simplex algorithms
	 */
	public int simplex_strategy;
	/** Specifies the current simplex mode during solve; see simplex_strategy */
	public int simplex_mode;
	/** Set amount of run-time messages and results */
	public int verbose;
	/** TRUE to print optimal solution; AUTOMATIC skips zeros */
	public int print_sol;
	/**
	 * Output stream, initialized to STDOUT<br>
	 * C type : FILE*
	 */
	public PointerByReference outstream;
	/**
	 * Main Branch and Bound settings<br>
	 * Determines branching strategy at the individual variable level;<br>
	 * the setting here overrides the bb_floorfirst setting<br>
	 * C type : unsigned char*
	 */
	public Pointer bb_varbranch;
	/** Strategy for selecting row and column entering/leaving */
	public int piv_strategy;
	/** Internal working rule-part of piv_strategy above */
	public int _piv_rule_;
	/** Rule for selecting B&B variables */
	public int bb_rule;
	/**
	 * Set BRANCH_FLOOR for B&B to set variables to floor bound first;<br>
	 * conversely with BRANCH_CEILING, the ceiling value is set first
	 */
	public byte bb_floorfirst;
	/** TRUE to stop at first feasible solution */
	public byte bb_breakfirst;
	/** Internal variable indicating active pricing loop order */
	public byte _piv_left_;
	public byte BOOLfuture1;
	/** Relative convergence criterion for iterated scaling */
	public double scalelimit;
	/** OR-ed codes for data scaling */
	public int scalemode;
	/** Set to non-zero for iterative improvement */
	public int improve;
	/** Anti-degen strategy (or none) TRUE to avoid cycling */
	public int anti_degen;
	/** PRESOLVE_ parameters for LP presolving */
	public int do_presolve;
	/** Maximum number of presolve loops */
	public int presolveloops;
	/** The number of bound relaxation retries performed */
	public int perturb_count;
	/**
	 * Row and column names storage variables<br>
	 * rows_alloc+1<br>
	 * C type : hashelem**
	 */
	public lpsolvehash.hashelem.ByReference[] row_name;
	/**
	 * columns_alloc+1<br>
	 * C type : hashelem**
	 */
	public lpsolvehash.hashelem.ByReference[] col_name;
	/**
	 * hash table to store row names<br>
	 * C type : hashtable*
	 */
	public lpsolvehash.hashtable.ByReference rowname_hashtab;
	/**
	 * hash table to store column names<br>
	 * C type : hashtable*
	 */
	public lpsolvehash.hashtable.ByReference colname_hashtab;
	/**
	 * Optionally specify continuous rows/column blocks for partial pricing<br>
	 * C type : partialrec*
	 */
	public lpsolvelib.partialrec.ByReference rowblocks;
	/** C type : partialrec* */
	public lpsolvelib.partialrec.ByReference colblocks;
	/**
	 * Row and column type codes<br>
	 * sum_alloc+1 : TRUE if variable must be integer<br>
	 * C type : unsigned char*
	 */
	public Pointer var_type;
	/**
	 * Data for multiple pricing<br>
	 * C type : multirec*
	 */
	public lpsolvetypes.multirec.ByReference multivars;
	/** The divisor used to set or augment pricing block */
	public int multiblockdiv;
	/**
	 * Variable (column) parameters<br>
	 * The current number of basic fixed variables in the model
	 */
	public int fixedvars;
	/** Number of variables required to be integer */
	public int int_vars;
	/** Number of semi-continuous variables */
	public int sc_vars;
	/**
	 * sum_columns+1 : TRUE if variable is semi-continuous;<br>
	 * value replaced by conventional lower bound during solve<br>
	 * C type : double*
	 */
	public DoubleByReference sc_lobound;
	/**
	 * columns+1: Index of twin variable if variable is free<br>
	 * C type : int*
	 */
	public IntByReference var_is_free;
	/**
	 * columns: Priority-mapping of variables<br>
	 * C type : int*
	 */
	public IntByReference var_priority;
	/**
	 * Pointer to record containing GUBs<br>
	 * C type : SOSgroup*
	 */
	public lpsolvesos.SOSgroup.ByReference GUB;
	/** Number of variables in the sos_priority list */
	public int sos_vars;
	/** Number of integers in SOS'es above */
	public int sos_ints;
	/**
	 * Pointer to record containing all SOS'es<br>
	 * C type : SOSgroup*
	 */
	public lpsolvesos.SOSgroup.ByReference SOS;
	/**
	 * Priority-sorted list of variables (no duplicates)<br>
	 * C type : int*
	 */
	public IntByReference sos_priority;
	/**
	 * Optionally specify list of active rows/columns used in multiple pricing<br>
	 * rows+1: bsolved solution vector for reduced costs<br>
	 * C type : double*
	 */
	public DoubleByReference bsolveVal;
	/**
	 * rows+1: Non-zero indeces of bsolveVal<br>
	 * C type : int*
	 */
	public IntByReference bsolveIdx;
	/**
	 * RHS storage<br>
	 * rows_alloc+1 : The RHS after scaling and sign<br>
	 * changing, but before 'Bound transformation'<br>
	 * C type : double*
	 */
	public DoubleByReference orig_rhs;
	/**
	 * rows_alloc+1 : The RHS of the current simplex tableau<br>
	 * C type : double*
	 */
	public DoubleByReference rhs;
	/**
	 * Row (constraint) parameters<br>
	 * rows_alloc+1 : Row/constraint type coding<br>
	 * C type : int*
	 */
	public IntByReference row_type;
	/**
	 * Optionally specify data for dual long-step<br>
	 * C type : multirec*
	 */
	public lpsolvetypes.multirec.ByReference longsteps;
	/**
	 * Original and working row and variable bounds<br>
	 * sum_alloc+1 : Bound before transformations<br>
	 * C type : double*
	 */
	public DoubleByReference orig_upbo;
	/**
	 * " " : Upper bound after transformation and B&B work<br>
	 * C type : double*
	 */
	public DoubleByReference upbo;
	/**
	 * "       "<br>
	 * C type : double*
	 */
	public DoubleByReference orig_lowbo;
	/**
	 * " " : Lower bound after transformation and B&B work<br>
	 * C type : double*
	 */
	public DoubleByReference lowbo;
	/**
	 * User data and basis factorization matrices (ETA or LU, product form)<br>
	 * C type : MATrec*
	 */
	public lpsolvematrix.MATrec.ByReference matA;
	/** C type : INVrec* */
	public PointerByReference invB;
	/**
	 * Basis and bounds<br>
	 * The linked list of B&B bounds<br>
	 * C type : BBrec*
	 */
	public lpsolvemipbb.BBrec.ByReference bb_bounds;
	/**
	 * The bounds at the lowest B&B level<br>
	 * C type : BBrec*
	 */
	public lpsolvemipbb.BBrec.ByReference rootbounds;
	/**
	 * The linked list of B&B bases<br>
	 * C type : basisrec*
	 */
	public lpsolvelib.basisrec.ByReference bb_basis;
	/** C type : basisrec* */
	public lpsolvelib.basisrec.ByReference rootbasis;
	/**
	 * Objective monitoring record for stalling/degeneracy handling<br>
	 * C type : OBJmonrec*
	 */
	public lpsolvetypes.OBJmonrec.ByReference monitor;
	/**
	 * Scaling parameters<br>
	 * sum_alloc+1:0..Rows the scaling of the rows,<br>
	 * Rows+1..Sum the scaling of the columns<br>
	 * C type : double*
	 */
	public DoubleByReference scalars;
	/** TRUE if scaling is used */
	public byte scaling_used;
	/** TRUE if the columns are scaled too */
	public byte columns_scaled;
	/** Determines whether the var_to_orig and orig_to_var are fixed */
	public byte varmap_locked;
	/**
	 * Variable state information<br>
	 * TRUE is the basis is still valid
	 */
	public byte basis_valid;
	/** Basis crashing mode (or none) */
	public int crashmode;
	/**
	 * rows_alloc+1: The list of columns in the basis<br>
	 * C type : int*
	 */
	public IntByReference var_basic;
	/**
	 * Array to store current values of non-basic variables<br>
	 * C type : double*
	 */
	public DoubleByReference val_nonbasic;
	/**
	 * sum_alloc+1: TRUE if the column is in the basis<br>
	 * C type : unsigned char*
	 */
	public Pointer is_basic;
	/**
	 * "       " : TRUE if the variable is at its<br>
	 * lower bound (or in the basis), FALSE otherwise<br>
	 * C type : unsigned char*
	 */
	public Pointer is_lower;
	/**
	 * Simplex basis indicators<br>
	 * List of unacceptable pivot choices due to division-by-zero<br>
	 * C type : int*
	 */
	public IntByReference rejectpivot;
	/**
	 * Data structure for costing of node branchings<br>
	 * C type : BBPSrec*
	 */
	public lpsolvelib.BBPSrec.ByReference bb_PseudoCost;
	/** Maximum number of updates for pseudo-costs */
	public int bb_PseudoUpdates;
	/** The number of strong B&B branches performed */
	public int bb_strongbranches;
	/** Are we currently in a strong branch mode? */
	public int is_strongbranch;
	/** The number of discrete B&B objective improvement steps */
	public int bb_improvements;
	/**
	 * Solver working variables<br>
	 * The maximum |value| of the rhs vector at any iteration
	 */
	public double rhsmax;
	/** The working sum of primal and dual infeasibilities */
	public double suminfeas;
	/** Original objective weighting in primal phase 1 */
	public double bigM;
	/** Phase 1 OF/RHS offset for feasibility */
	public double P1extraVal;
	/** Phase 1 additional columns/rows for feasibility */
	public int P1extraDim;
	/** ACTION_ variables for the simplex routine */
	public int spx_action;
	/** The variable bounds were relaxed/perturbed into this simplex */
	public byte spx_perturbed;
	/** Solver working variable; signals break of the B&B */
	public byte bb_break;
	/** The solve preprocessing was performed */
	public byte wasPreprocessed;
	/** The solve presolver was invoked */
	public byte wasPresolved;
	public int INTfuture2;
	/**
	 * Lagragean solver storage and parameters<br>
	 * C type : MATrec*
	 */
	public lpsolvematrix.MATrec.ByReference matL;
	/**
	 * Array of Lagrangean rhs vector<br>
	 * C type : double*
	 */
	public DoubleByReference lag_rhs;
	/**
	 * Array of GT, LT or EQ<br>
	 * C type : int*
	 */
	public IntByReference lag_con_type;
	/**
	 * Lambda values (Lagrangean multipliers)<br>
	 * C type : double*
	 */
	public DoubleByReference lambda;
	/** The Lagrangian lower OF bound */
	public double lag_bound;
	/** The Lagrangian convergence criterion */
	public double lag_accept;
	/**
	 * Solver thresholds<br>
	 * Limit for dynamic range
	 */
	public double infinite;
	/** Limit for negative variable range */
	public double negrange;
	/** Default machine accuracy */
	public double epsmachine;
	/** Input data precision / rounding of data values to 0 */
	public double epsvalue;
	/** For rounding RHS values to 0/infeasibility */
	public double epsprimal;
	/** For rounding reduced costs to zero */
	public double epsdual;
	/** Pivot reject tolerance */
	public double epspivot;
	/** Perturbation scalar */
	public double epsperturb;
	/** The solution tolerance for final validation */
	public double epssolution;
	/**
	 * Branch & Bound working parameters<br>
	 * Indicator that the last solvelp() gave an improved B&B solution
	 */
	public int bb_status;
	/** Solver B&B working variable (recursion depth) */
	public int bb_level;
	/** The deepest B&B level of the last solution */
	public int bb_maxlevel;
	/** The maximum B&B level allowed */
	public int bb_limitlevel;
	/** Total number of nodes processed in B&B */
	public long bb_totalnodes;
	/** The B&B level of the last / best solution */
	public int bb_solutionlevel;
	/** Size of the B&B cut pool */
	public int bb_cutpoolsize;
	/** Currently used cut pool */
	public int bb_cutpoolused;
	/** General purpose B&B parameter (typically for testing) */
	public int bb_constraintOF;
	/**
	 * The type of the currently used cuts<br>
	 * C type : int*
	 */
	public IntByReference bb_cuttype;
	/**
	 * The B&B state of the variable; 0 means inactive<br>
	 * C type : int*
	 */
	public IntByReference bb_varactive;
	/**
	 * Changes to upper bounds during the B&B phase<br>
	 * C type : DeltaVrec*
	 */
	public lpsolvematrix.DeltaVrec.ByReference bb_upperchange;
	/**
	 * Changes to lower bounds during the B&B phase<br>
	 * C type : DeltaVrec*
	 */
	public lpsolvematrix.DeltaVrec.ByReference bb_lowerchange;
	/** Minimum OF step value; computed at beginning of solve() */
	public double bb_deltaOF;
	/**
	 * User-settable value for the objective function deemed<br>
	 * to be sufficiently good in an integer problem
	 */
	public double bb_breakOF;
	/** "Dual" bound / limit to final optimal MIP solution */
	public double bb_limitOF;
	/**
	 * Set initial "at least better than" guess for objective function<br>
	 * (can significantly speed up B&B iterations)
	 */
	public double bb_heuristicOF;
	/** The OF value of the previous BB simplex */
	public double bb_parentOF;
	/** The unadjusted OF value for the current best solution */
	public double bb_workOF;
	/**
	 * Internal work arrays allocated as required<br>
	 * C type : presolveundorec*
	 */
	public lpsolvelib.presolveundorec.ByReference presolve_undo;
	/** C type : workarraysrec* */
	public lpsolveutils.workarraysrec.ByReference workarrays;
	/**
	 * MIP parameters<br>
	 * Margin of error in determining if a float value is integer
	 */
	public double epsint;
	/** Absolute MIP gap */
	public double mip_absgap;
	/** Relative MIP gap */
	public double mip_relgap;
	/** Time/timer variables and extended status text */
	public double timecreate;
	public double timestart;
	public double timeheuristic;
	public double timepresolved;
	public double timeend;
	public NativeLong sectimeout;
	/**
	 * Extended status message text set via explain()<br>
	 * C type : char*
	 */
	public Pointer ex_status;
	/** C type : void* */
	public Pointer hBFP;
	/** C type : BFPchar* */
	public BFPchar bfp_name;
	/** C type : BFPbool_lpintintint* */
	public BFPbool_lpintintint bfp_compatible;
	/** C type : BFPbool_lpintintchar* */
	public BFPbool_lpintintchar bfp_init;
	/** C type : BFP_lp* */
	public BFP_lp bfp_free;
	/** C type : BFPbool_lpint* */
	public BFPbool_lpint bfp_resize;
	/** C type : BFPint_lp* */
	public BFPint_lp bfp_memallocated;
	/** C type : BFPbool_lp* */
	public BFPbool_lp bfp_restart;
	/** C type : BFPbool_lp* */
	public BFPbool_lp bfp_mustrefactorize;
	/** C type : BFPint_lp* */
	public BFPint_lp bfp_preparefactorization;
	/** C type : BFPint_lpintintboolbool* */
	public BFPint_lpintintboolbool bfp_factorize;
	/** C type : BFP_lp* */
	public BFP_lp bfp_finishfactorization;
	/** C type : BFP_lp* */
	public BFP_lp bfp_updaterefactstats;
	/** C type : BFPlreal_lpintintreal* */
	public BFPlreal_lpintintreal bfp_prepareupdate;
	/** C type : BFPreal_lplrealreal* */
	public BFPreal_lplrealreal bfp_pivotRHS;
	/** C type : BFPbool_lpbool* */
	public BFPbool_lpbool bfp_finishupdate;
	/** C type : BFP_lprealint* */
	public BFP_lprealint bfp_ftran_prepare;
	/** C type : BFP_lprealint* */
	public BFP_lprealint bfp_ftran_normal;
	/** C type : BFP_lprealint* */
	public BFP_lprealint bfp_btran_normal;
	/** C type : BFP_lprealintrealint* */
	public BFP_lprealintrealint bfp_btran_double;
	/** C type : BFPint_lp* */
	public BFPint_lp bfp_status;
	/** C type : BFPint_lpbool* */
	public BFPint_lpbool bfp_nonzeros;
	/** C type : BFPbool_lp* */
	public BFPbool_lp bfp_implicitslack;
	/** C type : BFPint_lp* */
	public BFPint_lp bfp_indexbase;
	/** C type : BFPint_lp* */
	public BFPint_lp bfp_rowoffset;
	/** C type : BFPint_lp* */
	public BFPint_lp bfp_pivotmax;
	/** C type : BFPbool_lpint* */
	public BFPbool_lpint bfp_pivotalloc;
	/** C type : BFPint_lp* */
	public BFPint_lp bfp_colcount;
	/** C type : BFPbool_lp* */
	public BFPbool_lp bfp_canresetbasis;
	/** C type : BFPreal_lp* */
	public BFPreal_lp bfp_efficiency;
	/** C type : BFPrealp_lp* */
	public BFPrealp_lp bfp_pivotvector;
	/** C type : BFPint_lp* */
	public BFPint_lp bfp_pivotcount;
	/** C type : BFPint_lpint* */
	public BFPint_lpint bfp_refactcount;
	/** C type : BFPbool_lp* */
	public BFPbool_lp bfp_isSetI;
	/** C type : BFPint_lpintrealcbintint* */
	public BFPint_lpintrealcbintint bfp_findredundant;
	/** C type : void* */
	public Pointer hXLI;
	/** C type : XLIchar* */
	public XLIchar xli_name;
	/** C type : XLIbool_lpintintint* */
	public XLIbool_lpintintint xli_compatible;
	/** C type : XLIbool_lpcharcharcharint* */
	public XLIbool_lpcharcharcharint xli_readmodel;
	/** C type : XLIbool_lpcharcharbool* */
	public XLIbool_lpcharcharbool xli_writemodel;
	/**
	 * Miscellaneous internal functions made available externally<br>
	 * C type : userabortfunc*
	 */
	public userabortfunc userabort;
	/** C type : reportfunc* */
	public reportfunc report;
	/** C type : explainfunc* */
	public explainfunc explain;
	/** C type : getvectorfunc* */
	public getvectorfunc get_lpcolumn;
	/** C type : getpackedfunc* */
	public getpackedfunc get_basiscolumn;
	/** C type : get_OF_activefunc* */
	public get_OF_activefunc get_OF_active;
	/** C type : getMDOfunc* */
	public getMDOfunc getMDO;
	/** C type : invertfunc* */
	public invertfunc invert;
	/** C type : set_actionfunc* */
	public set_actionfunc set_action;
	/** C type : is_actionfunc* */
	public is_actionfunc is_action;
	/** C type : clear_actionfunc* */
	public clear_actionfunc clear_action;
	/**
	 * User program interface callbacks<br>
	 * C type : lphandle_intfunc*
	 */
	public lphandle_intfunc ctrlc;
	/**
	 * User-specified "owner process ID"<br>
	 * C type : void*
	 */
	public Pointer ctrlchandle;
	/** C type : lphandlestr_func* */
	public lphandlestr_func writelog;
	/**
	 * User-specified "owner process ID"<br>
	 * C type : void*
	 */
	public Pointer loghandle;
	/** C type : lphandlestr_func* */
	public lphandlestr_func debuginfo;
	/** C type : lphandleint_func* */
	public lphandleint_func usermessage;
	public int msgmask;
	/**
	 * User-specified "owner process ID"<br>
	 * C type : void*
	 */
	public Pointer msghandle;
	/** C type : lphandleint_intfunc* */
	public lphandleint_intfunc bb_usenode;
	/**
	 * User-specified "owner process ID"<br>
	 * C type : void*
	 */
	public Pointer bb_nodehandle;
	/** C type : lphandleint_intfunc* */
	public lphandleint_intfunc bb_usebranch;
	/**
	 * User-specified "owner process ID"<br>
	 * C type : void*
	 */
	public Pointer bb_branchhandle;
	/**
	 * replacement of static variables<br>
	 * The name of a row/column<br>
	 * C type : char*
	 */
	public Pointer rowcol_name;
	public lprec() {
		super();
	}
	protected List<? > getFieldOrder() {
		return Arrays.asList("add_column", "add_columnex", "add_constraint", "add_constraintex", "add_lag_con", "add_SOS", "column_in_lp", "copy_lp", "default_basis", "del_column", "del_constraint", "delete_lp", "dualize_lp", "free_lp", "get_anti_degen", "get_basis", "get_basiscrash", "get_bb_depthlimit", "get_bb_floorfirst", "get_bb_rule", "get_bounds_tighter", "get_break_at_value", "get_col_name", "get_columnex", "get_constr_type", "get_constr_value", "get_constraints", "get_dual_solution", "get_epsb", "get_epsd", "get_epsel", "get_epsint", "get_epsperturb", "get_epspivot", "get_improve", "get_infinite", "get_lambda", "get_lowbo", "get_lp_index", "get_lp_name", "get_Lrows", "get_mat", "get_mat_byindex", "get_max_level", "get_maxpivot", "get_mip_gap", "get_multiprice", "get_nameindex", "get_Ncolumns", "get_negrange", "get_nonzeros", "get_Norig_columns", "get_Norig_rows", "get_Nrows", "get_obj_bound", "get_objective", "get_orig_index", "get_origcol_name", "get_origrow_name", "get_partialprice", "get_pivoting", "get_presolve", "get_presolveloops", "get_primal_solution", "get_print_sol", "get_pseudocosts", "get_ptr_constraints", "get_ptr_dual_solution", "get_ptr_lambda", "get_ptr_primal_solution", "get_ptr_sensitivity_obj", "get_ptr_sensitivity_objex", "get_ptr_sensitivity_rhs", "get_ptr_variables", "get_rh", "get_rh_range", "get_row", "get_rowex", "get_row_name", "get_scalelimit", "get_scaling", "get_sensitivity_obj", "get_sensitivity_objex", "get_sensitivity_rhs", "get_simplextype", "get_solutioncount", "get_solutionlimit", "get_status", "get_statustext", "get_timeout", "get_total_iter", "get_total_nodes", "get_upbo", "get_var_branch", "get_var_dualresult", "get_var_primalresult", "get_var_priority", "get_variables", "get_verbose", "get_working_objective", "has_BFP", "has_XLI", "is_add_rowmode", "is_anti_degen", "is_binary", "is_break_at_first", "is_constr_type", "is_debug", "is_feasible", "is_infinite", "is_int", "is_integerscaling", "is_lag_trace", "is_maxim", "is_nativeBFP", "is_nativeXLI", "is_negative", "is_obj_in_basis", "is_piv_mode", "is_piv_rule", "is_presolve", "is_scalemode", "is_scaletype", "is_semicont", "is_SOS_var", "is_trace", "is_unbounded", "is_use_names", "lp_solve_version", "make_lp", "print_constraints", "print_debugdump", "print_duals", "print_lp", "print_objective", "print_scales", "print_solution", "print_str", "print_tableau", "put_abortfunc", "put_bb_nodefunc", "put_bb_branchfunc", "put_logfunc", "put_msgfunc", "read_LP", "read_MPS", "read_XLI", "read_params", "read_basis", "reset_basis", "reset_params", "resize_lp", "set_add_rowmode", "set_anti_degen", "set_basisvar", "set_basis", "set_basiscrash", "set_bb_depthlimit", "set_bb_floorfirst", "set_bb_rule", "set_BFP", "set_binary", "set_bounds", "set_bounds_tighter", "set_break_at_first", "set_break_at_value", "set_column", "set_columnex", "set_col_name", "set_constr_type", "set_debug", "set_epsb", "set_epsd", "set_epsel", "set_epsint", "set_epslevel", "set_epsperturb", "set_epspivot", "set_unbounded", "set_improve", "set_infinite", "set_int", "set_lag_trace", "set_lowbo", "set_lp_name", "set_mat", "set_maxim", "set_maxpivot", "set_minim", "set_mip_gap", "set_multiprice", "set_negrange", "set_obj_bound", "set_obj_fn", "set_obj_fnex", "set_obj", "set_obj_in_basis", "set_outputfile", "set_outputstream", "set_partialprice", "set_pivoting", "set_preferdual", "set_presolve", "set_print_sol", "set_pseudocosts", "set_rh", "set_rh_range", "set_rh_vec", "set_row", "set_rowex", "set_row_name", "set_scalelimit", "set_scaling", "set_semicont", "set_sense", "set_simplextype", "set_solutionlimit", "set_timeout", "set_trace", "set_upbo", "set_use_names", "set_var_branch", "set_var_weights", "set_verbose", "set_XLI", "solve", "str_add_column", "str_add_constraint", "str_add_lag_con", "str_set_obj_fn", "str_set_rh_vec", "time_elapsed", "unscale", "write_lp", "write_LP", "write_mps", "write_MPS", "write_freemps", "write_freeMPS", "write_XLI", "write_basis", "write_params", "alignmentspacer", "lp_name", "sum", "rows", "columns", "equalities", "boundedvars", "INTfuture1", "sum_alloc", "rows_alloc", "columns_alloc", "source_is_file", "model_is_pure", "model_is_valid", "tighten_on_set", "names_used", "use_row_names", "use_col_names", "lag_trace", "spx_trace", "bb_trace", "streamowned", "obj_in_basis", "spx_status", "lag_status", "solutioncount", "solutionlimit", "real_solution", "solution", "best_solution", "full_solution", "edgeVector", "drow", "nzdrow", "duals", "full_duals", "dualsfrom", "dualstill", "objfrom", "objtill", "objfromvalue", "orig_obj", "obj", "current_iter", "total_iter", "current_bswap", "total_bswap", "solvecount", "max_pivots", "simplex_strategy", "simplex_mode", "verbose", "print_sol", "outstream", "bb_varbranch", "piv_strategy", "_piv_rule_", "bb_rule", "bb_floorfirst", "bb_breakfirst", "_piv_left_", "BOOLfuture1", "scalelimit", "scalemode", "improve", "anti_degen", "do_presolve", "presolveloops", "perturb_count", "row_name", "col_name", "rowname_hashtab", "colname_hashtab", "rowblocks", "colblocks", "var_type", "multivars", "multiblockdiv", "fixedvars", "int_vars", "sc_vars", "sc_lobound", "var_is_free", "var_priority", "GUB", "sos_vars", "sos_ints", "SOS", "sos_priority", "bsolveVal", "bsolveIdx", "orig_rhs", "rhs", "row_type", "longsteps", "orig_upbo", "upbo", "orig_lowbo", "lowbo", "matA", "invB", "bb_bounds", "rootbounds", "bb_basis", "rootbasis", "monitor", "scalars", "scaling_used", "columns_scaled", "varmap_locked", "basis_valid", "crashmode", "var_basic", "val_nonbasic", "is_basic", "is_lower", "rejectpivot", "bb_PseudoCost", "bb_PseudoUpdates", "bb_strongbranches", "is_strongbranch", "bb_improvements", "rhsmax", "suminfeas", "bigM", "P1extraVal", "P1extraDim", "spx_action", "spx_perturbed", "bb_break", "wasPreprocessed", "wasPresolved", "INTfuture2", "matL", "lag_rhs", "lag_con_type", "lambda", "lag_bound", "lag_accept", "infinite", "negrange", "epsmachine", "epsvalue", "epsprimal", "epsdual", "epspivot", "epsperturb", "epssolution", "bb_status", "bb_level", "bb_maxlevel", "bb_limitlevel", "bb_totalnodes", "bb_solutionlevel", "bb_cutpoolsize", "bb_cutpoolused", "bb_constraintOF", "bb_cuttype", "bb_varactive", "bb_upperchange", "bb_lowerchange", "bb_deltaOF", "bb_breakOF", "bb_limitOF", "bb_heuristicOF", "bb_parentOF", "bb_workOF", "presolve_undo", "workarrays", "epsint", "mip_absgap", "mip_relgap", "timecreate", "timestart", "timeheuristic", "timepresolved", "timeend", "sectimeout", "ex_status", "hBFP", "bfp_name", "bfp_compatible", "bfp_init", "bfp_free", "bfp_resize", "bfp_memallocated", "bfp_restart", "bfp_mustrefactorize", "bfp_preparefactorization", "bfp_factorize", "bfp_finishfactorization", "bfp_updaterefactstats", "bfp_prepareupdate", "bfp_pivotRHS", "bfp_finishupdate", "bfp_ftran_prepare", "bfp_ftran_normal", "bfp_btran_normal", "bfp_btran_double", "bfp_status", "bfp_nonzeros", "bfp_implicitslack", "bfp_indexbase", "bfp_rowoffset", "bfp_pivotmax", "bfp_pivotalloc", "bfp_colcount", "bfp_canresetbasis", "bfp_efficiency", "bfp_pivotvector", "bfp_pivotcount", "bfp_refactcount", "bfp_isSetI", "bfp_findredundant", "hXLI", "xli_name", "xli_compatible", "xli_readmodel", "xli_writemodel", "userabort", "report", "explain", "get_lpcolumn", "get_basiscolumn", "get_OF_active", "getMDO", "invert", "set_action", "is_action", "clear_action", "ctrlc", "ctrlchandle", "writelog", "loghandle", "debuginfo", "usermessage", "msgmask", "msghandle", "bb_usenode", "bb_nodehandle", "bb_usebranch", "bb_branchhandle", "rowcol_name");
	}
	public static class ByReference extends lprec implements Structure.ByReference {
		
	};
	public static class ByValue extends lprec implements Structure.ByValue {
		
	};
}
