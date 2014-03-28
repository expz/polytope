package lpsolvesos;
import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.DoubleByReference;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.IntBuffer;
import lpsolvelib.lprec;
import lpsolvematrix.DeltaVrec;
import lpsolvesos.SOSgroup.ByReference;
import lpsolveutils.LLrec;
/**
 * JNA Wrapper for library <b>LpSolveSOS</b><br>
 * This file was autogenerated by <a href="http://jnaerator.googlecode.com/">JNAerator</a>,<br>
 * a tool written by <a href="http://ochafik.com/">Olivier Chafik</a> that <a href="http://code.google.com/p/jnaerator/wiki/CreditsAndLicense">uses a few opensource projects.</a>.<br>
 * For help, please visit <a href="http://nativelibs4java.googlecode.com/">NativeLibs4Java</a> , <a href="http://rococoa.dev.java.net/">Rococoa</a>, or <a href="http://jna.dev.java.net/">JNA</a>.
 */
public class LpSolveSOSLibrary implements Library {
	public static final String JNA_LIBRARY_NAME = "LpSolveSOS";
	public static final NativeLibrary JNA_NATIVE_LIB = NativeLibrary.getInstance(LpSolveSOSLibrary.JNA_LIBRARY_NAME);
	static {
		Native.register(LpSolveSOSLibrary.JNA_LIBRARY_NAME);
	}
	/** <i>native declaration : lp_SOS.h</i> */
	public static final int SOS_COMPLETE = (int)0;
	/** <i>native declaration : lp_SOS.h</i> */
	public static final int SOS_INFEASIBLE = (int)1;
	/** <i>native declaration : lp_SOS.h</i> */
	public static final int SOS_INTERNALERROR = (int)2;
	/** <i>native declaration : lp_SOS.h</i> */
	public static final int SOS_START_SIZE = (int)10;
	/** <i>native declaration : lp_SOS.h</i> */
	public static final int SOSn = (int)2147483647;
	/** <i>native declaration : lp_SOS.h</i> */
	public static final int SOS3 = (int)-1;
	/** <i>native declaration : lp_SOS.h</i> */
	public static final int SOS1 = (int)1;
	/** <i>native declaration : lp_SOS.h</i> */
	public static final int SOS2 = (int)2;
	/** <i>native declaration : lp_SOS.h</i> */
	public static final int SOS3_INCOMPLETE = (int)-2;
	/** <i>native declaration : lp_SOS.h</i> */
	public static final int SOS_INCOMPLETE = (int)-1;
	/**
	 * SOS storage structure<br>
	 * Original signature : <code>SOSgroup* create_SOSgroup(lprec*)</code><br>
	 * <i>native declaration : lp_SOS.h:2826</i>
	 */
	public static native SOSgroup create_SOSgroup(lprec lp);
	/**
	 * Original signature : <code>void resize_SOSgroup(SOSgroup*)</code><br>
	 * <i>native declaration : lp_SOS.h:2828</i>
	 */
	public static native void resize_SOSgroup(SOSgroup group);
	/**
	 * Original signature : <code>int append_SOSgroup(SOSgroup*, SOSrec*)</code><br>
	 * <i>native declaration : lp_SOS.h:2830</i>
	 */
	public static native int append_SOSgroup(SOSgroup group, SOSrec SOS);
	/**
	 * Original signature : <code>int clean_SOSgroup(SOSgroup*, unsigned char)</code><br>
	 * <i>native declaration : lp_SOS.h:2832</i>
	 */
	public static native int clean_SOSgroup(SOSgroup group, byte forceupdatemap);
	/**
	 * Original signature : <code>void free_SOSgroup(SOSgroup**)</code><br>
	 * <i>native declaration : lp_SOS.h:2834</i><br>
	 * @deprecated use the safer method {@link #free_SOSgroup(lpsolvesos.SOSgroup.ByReference[])} instead
	 */
	@Deprecated 
	public static native void free_SOSgroup(PointerByReference group);
	/**
	 * Original signature : <code>void free_SOSgroup(SOSgroup**)</code><br>
	 * <i>native declaration : lp_SOS.h:2834</i>
	 */
	public static native void free_SOSgroup(SOSgroup.ByReference group[]);
	/**
	 * Original signature : <code>SOSrec* create_SOSrec(SOSgroup*, char*, int, int, int, int*, double*)</code><br>
	 * <i>native declaration : lp_SOS.h:2836</i><br>
	 * @deprecated use the safer methods {@link #create_SOSrec(lpsolvesos.SOSgroup, java.nio.ByteBuffer, int, int, int, java.nio.IntBuffer, java.nio.DoubleBuffer)} and {@link #create_SOSrec(lpsolvesos.SOSgroup, com.sun.jna.Pointer, int, int, int, com.sun.jna.ptr.IntByReference, com.sun.jna.ptr.DoubleByReference)} instead
	 */
	@Deprecated 
	public static native SOSrec create_SOSrec(SOSgroup group, Pointer name, int type, int priority, int size, IntByReference variables, DoubleByReference weights);
	/**
	 * Original signature : <code>SOSrec* create_SOSrec(SOSgroup*, char*, int, int, int, int*, double*)</code><br>
	 * <i>native declaration : lp_SOS.h:2836</i>
	 */
	public static native SOSrec create_SOSrec(SOSgroup group, ByteBuffer name, int type, int priority, int size, IntBuffer variables, DoubleBuffer weights);
	/**
	 * Original signature : <code>char delete_SOSrec(SOSgroup*, int)</code><br>
	 * <i>native declaration : lp_SOS.h:2838</i>
	 */
	public static native byte delete_SOSrec(SOSgroup group, int sosindex);
	/**
	 * Original signature : <code>int append_SOSrec(SOSrec*, int, int*, double*)</code><br>
	 * <i>native declaration : lp_SOS.h:2840</i><br>
	 * @deprecated use the safer methods {@link #append_SOSrec(lpsolvesos.SOSrec, int, java.nio.IntBuffer, java.nio.DoubleBuffer)} and {@link #append_SOSrec(lpsolvesos.SOSrec, int, com.sun.jna.ptr.IntByReference, com.sun.jna.ptr.DoubleByReference)} instead
	 */
	@Deprecated 
	public static native int append_SOSrec(SOSrec SOS, int size, IntByReference variables, DoubleByReference weights);
	/**
	 * Original signature : <code>int append_SOSrec(SOSrec*, int, int*, double*)</code><br>
	 * <i>native declaration : lp_SOS.h:2840</i>
	 */
	public static native int append_SOSrec(SOSrec SOS, int size, IntBuffer variables, DoubleBuffer weights);
	/**
	 * Original signature : <code>void free_SOSrec(SOSrec*)</code><br>
	 * <i>native declaration : lp_SOS.h:2842</i>
	 */
	public static native void free_SOSrec(SOSrec SOS);
	/**
	 * SOS utilities<br>
	 * Original signature : <code>int make_SOSchain(lprec*, unsigned char)</code><br>
	 * <i>native declaration : lp_SOS.h:2847</i>
	 */
	public static native int make_SOSchain(lprec lp, byte forceresort);
	/**
	 * Original signature : <code>int SOS_member_updatemap(SOSgroup*)</code><br>
	 * <i>native declaration : lp_SOS.h:2849</i>
	 */
	public static native int SOS_member_updatemap(SOSgroup group);
	/**
	 * Original signature : <code>char SOS_member_sortlist(SOSgroup*, int)</code><br>
	 * <i>native declaration : lp_SOS.h:2851</i>
	 */
	public static native byte SOS_member_sortlist(SOSgroup group, int sosindex);
	/**
	 * Original signature : <code>char SOS_shift_col(SOSgroup*, int, int, int, LLrec*, unsigned char)</code><br>
	 * <i>native declaration : lp_SOS.h:2853</i>
	 */
	public static native byte SOS_shift_col(SOSgroup group, int sosindex, int column, int delta, LLrec usedmap, byte forceresort);
	/**
	 * Original signature : <code>int SOS_member_delete(SOSgroup*, int, int)</code><br>
	 * <i>native declaration : lp_SOS.h:2855</i>
	 */
	public static native int SOS_member_delete(SOSgroup group, int sosindex, int member);
	/**
	 * Original signature : <code>int SOS_get_type(SOSgroup*, int)</code><br>
	 * <i>native declaration : lp_SOS.h:2857</i>
	 */
	public static native int SOS_get_type(SOSgroup group, int sosindex);
	/**
	 * Original signature : <code>int SOS_infeasible(SOSgroup*, int)</code><br>
	 * <i>native declaration : lp_SOS.h:2859</i>
	 */
	public static native int SOS_infeasible(SOSgroup group, int sosindex);
	/**
	 * Original signature : <code>int SOS_member_index(SOSgroup*, int, int)</code><br>
	 * <i>native declaration : lp_SOS.h:2861</i>
	 */
	public static native int SOS_member_index(SOSgroup group, int sosindex, int member);
	/**
	 * Original signature : <code>int SOS_member_count(SOSgroup*, int)</code><br>
	 * <i>native declaration : lp_SOS.h:2863</i>
	 */
	public static native int SOS_member_count(SOSgroup group, int sosindex);
	/**
	 * Original signature : <code>int SOS_memberships(SOSgroup*, int)</code><br>
	 * <i>native declaration : lp_SOS.h:2865</i>
	 */
	public static native int SOS_memberships(SOSgroup group, int column);
	/**
	 * Original signature : <code>int* SOS_get_candidates(SOSgroup*, int, int, unsigned char, double*, double*)</code><br>
	 * <i>native declaration : lp_SOS.h:2867</i><br>
	 * @deprecated use the safer methods {@link #SOS_get_candidates(lpsolvesos.SOSgroup, int, int, byte, java.nio.DoubleBuffer, java.nio.DoubleBuffer)} and {@link #SOS_get_candidates(lpsolvesos.SOSgroup, int, int, byte, com.sun.jna.ptr.DoubleByReference, com.sun.jna.ptr.DoubleByReference)} instead
	 */
	@Deprecated 
	public static native IntByReference SOS_get_candidates(SOSgroup group, int sosindex, int column, byte excludetarget, DoubleByReference upbound, DoubleByReference lobound);
	/**
	 * Original signature : <code>int* SOS_get_candidates(SOSgroup*, int, int, unsigned char, double*, double*)</code><br>
	 * <i>native declaration : lp_SOS.h:2867</i>
	 */
	public static native IntByReference SOS_get_candidates(SOSgroup group, int sosindex, int column, byte excludetarget, DoubleBuffer upbound, DoubleBuffer lobound);
	/**
	 * Original signature : <code>int SOS_is_member(SOSgroup*, int, int)</code><br>
	 * <i>native declaration : lp_SOS.h:2869</i>
	 */
	public static native int SOS_is_member(SOSgroup group, int sosindex, int column);
	/**
	 * Original signature : <code>char SOS_is_member_of_type(SOSgroup*, int, int)</code><br>
	 * <i>native declaration : lp_SOS.h:2871</i>
	 */
	public static native byte SOS_is_member_of_type(SOSgroup group, int column, int sostype);
	/**
	 * Original signature : <code>char SOS_set_GUB(SOSgroup*, int, unsigned char)</code><br>
	 * <i>native declaration : lp_SOS.h:2873</i>
	 */
	public static native byte SOS_set_GUB(SOSgroup group, int sosindex, byte state);
	/**
	 * Original signature : <code>char SOS_is_GUB(SOSgroup*, int)</code><br>
	 * <i>native declaration : lp_SOS.h:2875</i>
	 */
	public static native byte SOS_is_GUB(SOSgroup group, int sosindex);
	/**
	 * Original signature : <code>char SOS_is_marked(SOSgroup*, int, int)</code><br>
	 * <i>native declaration : lp_SOS.h:2877</i>
	 */
	public static native byte SOS_is_marked(SOSgroup group, int sosindex, int column);
	/**
	 * Original signature : <code>char SOS_is_active(SOSgroup*, int, int)</code><br>
	 * <i>native declaration : lp_SOS.h:2879</i>
	 */
	public static native byte SOS_is_active(SOSgroup group, int sosindex, int column);
	/**
	 * Original signature : <code>char SOS_is_full(SOSgroup*, int, int, unsigned char)</code><br>
	 * <i>native declaration : lp_SOS.h:2881</i>
	 */
	public static native byte SOS_is_full(SOSgroup group, int sosindex, int column, byte activeonly);
	/**
	 * Original signature : <code>char SOS_can_activate(SOSgroup*, int, int)</code><br>
	 * <i>native declaration : lp_SOS.h:2883</i>
	 */
	public static native byte SOS_can_activate(SOSgroup group, int sosindex, int column);
	/**
	 * Original signature : <code>char SOS_set_marked(SOSgroup*, int, int, unsigned char)</code><br>
	 * <i>native declaration : lp_SOS.h:2885</i>
	 */
	public static native byte SOS_set_marked(SOSgroup group, int sosindex, int column, byte asactive);
	/**
	 * Original signature : <code>char SOS_unmark(SOSgroup*, int, int)</code><br>
	 * <i>native declaration : lp_SOS.h:2887</i>
	 */
	public static native byte SOS_unmark(SOSgroup group, int sosindex, int column);
	/**
	 * Original signature : <code>int SOS_fix_unmarked(SOSgroup*, int, int, double*, double, unsigned char, int*, DeltaVrec*)</code><br>
	 * <i>native declaration : lp_SOS.h:2889</i><br>
	 * @deprecated use the safer methods {@link #SOS_fix_unmarked(lpsolvesos.SOSgroup, int, int, java.nio.DoubleBuffer, double, byte, java.nio.IntBuffer, lpsolvematrix.DeltaVrec)} and {@link #SOS_fix_unmarked(lpsolvesos.SOSgroup, int, int, com.sun.jna.ptr.DoubleByReference, double, byte, com.sun.jna.ptr.IntByReference, lpsolvematrix.DeltaVrec)} instead
	 */
	@Deprecated 
	public static native int SOS_fix_unmarked(SOSgroup group, int sosindex, int variable, DoubleByReference bound, double value, byte isupper, IntByReference diffcount, DeltaVrec changelog);
	/**
	 * Original signature : <code>int SOS_fix_unmarked(SOSgroup*, int, int, double*, double, unsigned char, int*, DeltaVrec*)</code><br>
	 * <i>native declaration : lp_SOS.h:2889</i>
	 */
	public static native int SOS_fix_unmarked(SOSgroup group, int sosindex, int variable, DoubleBuffer bound, double value, byte isupper, IntBuffer diffcount, DeltaVrec changelog);
	/**
	 * Original signature : <code>int SOS_fix_list(SOSgroup*, int, int, double*, int*, unsigned char, DeltaVrec*)</code><br>
	 * <i>native declaration : lp_SOS.h:2891</i><br>
	 * @deprecated use the safer methods {@link #SOS_fix_list(lpsolvesos.SOSgroup, int, int, java.nio.DoubleBuffer, java.nio.IntBuffer, byte, lpsolvematrix.DeltaVrec)} and {@link #SOS_fix_list(lpsolvesos.SOSgroup, int, int, com.sun.jna.ptr.DoubleByReference, com.sun.jna.ptr.IntByReference, byte, lpsolvematrix.DeltaVrec)} instead
	 */
	@Deprecated 
	public static native int SOS_fix_list(SOSgroup group, int sosindex, int variable, DoubleByReference bound, IntByReference varlist, byte isleft, DeltaVrec changelog);
	/**
	 * Original signature : <code>int SOS_fix_list(SOSgroup*, int, int, double*, int*, unsigned char, DeltaVrec*)</code><br>
	 * <i>native declaration : lp_SOS.h:2891</i>
	 */
	public static native int SOS_fix_list(SOSgroup group, int sosindex, int variable, DoubleBuffer bound, IntBuffer varlist, byte isleft, DeltaVrec changelog);
	/**
	 * Original signature : <code>int SOS_is_satisfied(SOSgroup*, int, double*)</code><br>
	 * <i>native declaration : lp_SOS.h:2893</i><br>
	 * @deprecated use the safer methods {@link #SOS_is_satisfied(lpsolvesos.SOSgroup, int, java.nio.DoubleBuffer)} and {@link #SOS_is_satisfied(lpsolvesos.SOSgroup, int, com.sun.jna.ptr.DoubleByReference)} instead
	 */
	@Deprecated 
	public static native int SOS_is_satisfied(SOSgroup group, int sosindex, DoubleByReference solution);
	/**
	 * Original signature : <code>int SOS_is_satisfied(SOSgroup*, int, double*)</code><br>
	 * <i>native declaration : lp_SOS.h:2893</i>
	 */
	public static native int SOS_is_satisfied(SOSgroup group, int sosindex, DoubleBuffer solution);
	/**
	 * Original signature : <code>char SOS_is_feasible(SOSgroup*, int, double*)</code><br>
	 * <i>native declaration : lp_SOS.h:2895</i><br>
	 * @deprecated use the safer methods {@link #SOS_is_feasible(lpsolvesos.SOSgroup, int, java.nio.DoubleBuffer)} and {@link #SOS_is_feasible(lpsolvesos.SOSgroup, int, com.sun.jna.ptr.DoubleByReference)} instead
	 */
	@Deprecated 
	public static native byte SOS_is_feasible(SOSgroup group, int sosindex, DoubleByReference solution);
	/**
	 * Original signature : <code>char SOS_is_feasible(SOSgroup*, int, double*)</code><br>
	 * <i>native declaration : lp_SOS.h:2895</i>
	 */
	public static native byte SOS_is_feasible(SOSgroup group, int sosindex, DoubleBuffer solution);
}
