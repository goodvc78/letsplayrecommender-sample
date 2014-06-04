package openwings.letplayrecommender.util;

public class MathUtil {

	public static double round( double v, int index) {
		return Math.round( v* Math.pow(10, index))/Math.pow(10, index) ;
	}
}
