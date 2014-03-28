package lpsolvetypes;
import com.sun.jna.Structure;
import java.util.Arrays;
import java.util.List;
/**
 * <i>native declaration : lp_types.h:2291</i><br>
 * This file was autogenerated by <a href="http://jnaerator.googlecode.com/">JNAerator</a>,<br>
 * a tool written by <a href="http://ochafik.com/">Olivier Chafik</a> that <a href="http://code.google.com/p/jnaerator/wiki/CreditsAndLicense">uses a few opensource projects.</a>.<br>
 * For help, please visit <a href="http://nativelibs4java.googlecode.com/">NativeLibs4Java</a> , <a href="http://rococoa.dev.java.net/">Rococoa</a>, or <a href="http://jna.dev.java.net/">JNA</a>.
 */
public class OBJmonrec extends Structure {
	/** C type : lprec* */
	public lpsolvelib.lprec.ByReference lp;
	public int oldpivstrategy;
	public int oldpivrule;
	public int pivrule;
	public int ruleswitches;
	/** C type : int[2] */
	public int[] limitstall = new int[2];
	public int limitruleswitches;
	/** C type : int[5] */
	public int[] idxstep = new int[5];
	public int countstep;
	public int startstep;
	public int currentstep;
	public int Rcycle;
	public int Ccycle;
	public int Ncycle;
	public int Mcycle;
	public int Icount;
	public double thisobj;
	public double prevobj;
	/** C type : double[5] */
	public double[] objstep = new double[5];
	public double thisinfeas;
	public double previnfeas;
	public double epsvalue;
	/** C type : char[10] */
	public byte[] spxfunc = new byte[10];
	public byte pivdynamic;
	public byte isdual;
	public byte active;
	public OBJmonrec() {
		super();
	}
	protected List<? > getFieldOrder() {
		return Arrays.asList("lp", "oldpivstrategy", "oldpivrule", "pivrule", "ruleswitches", "limitstall", "limitruleswitches", "idxstep", "countstep", "startstep", "currentstep", "Rcycle", "Ccycle", "Ncycle", "Mcycle", "Icount", "thisobj", "prevobj", "objstep", "thisinfeas", "previnfeas", "epsvalue", "spxfunc", "pivdynamic", "isdual", "active");
	}
	public static class ByReference extends OBJmonrec implements Structure.ByReference {
		
	};
	public static class ByValue extends OBJmonrec implements Structure.ByValue {
		
	};
}
