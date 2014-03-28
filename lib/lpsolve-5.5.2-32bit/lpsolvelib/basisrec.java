package lpsolvelib;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.ptr.IntByReference;
import java.util.Arrays;
import java.util.List;
/**
 * <i>native declaration : lp_lib.h:2691</i><br>
 * This file was autogenerated by <a href="http://jnaerator.googlecode.com/">JNAerator</a>,<br>
 * a tool written by <a href="http://ochafik.com/">Olivier Chafik</a> that <a href="http://code.google.com/p/jnaerator/wiki/CreditsAndLicense">uses a few opensource projects.</a>.<br>
 * For help, please visit <a href="http://nativelibs4java.googlecode.com/">NativeLibs4Java</a> , <a href="http://rococoa.dev.java.net/">Rococoa</a>, or <a href="http://jna.dev.java.net/">JNA</a>.
 */
public class basisrec extends Structure {
	public int level;
	/** C type : int* */
	public IntByReference var_basic;
	/** C type : unsigned char* */
	public Pointer is_basic;
	/** C type : unsigned char* */
	public Pointer is_lower;
	public int pivots;
	/** C type : _basisrec* */
	public basisrec.ByReference previous;
	public basisrec() {
		super();
	}
	protected List<? > getFieldOrder() {
		return Arrays.asList("level", "var_basic", "is_basic", "is_lower", "pivots", "previous");
	}
	/**
	 * @param var_basic C type : int*<br>
	 * @param is_basic C type : unsigned char*<br>
	 * @param is_lower C type : unsigned char*<br>
	 * @param previous C type : _basisrec*
	 */
	public basisrec(int level, IntByReference var_basic, Pointer is_basic, Pointer is_lower, int pivots, basisrec.ByReference previous) {
		super();
		this.level = level;
		this.var_basic = var_basic;
		this.is_basic = is_basic;
		this.is_lower = is_lower;
		this.pivots = pivots;
		this.previous = previous;
	}
	public static class ByReference extends basisrec implements Structure.ByReference {
		
	};
	public static class ByValue extends basisrec implements Structure.ByValue {
		
	};
}
