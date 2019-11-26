package tools;

import java.math.BigDecimal;

public class NumberFormat {

	public static Float formatFloat(Float f) {
		//DecimalFormat df = new DecimalFormat( "##0.00" );
		//f = new Float(df.format(f)).floatValue();
		String strDouble = String.format("%.2f", f);
		return Float.parseFloat(strDouble);
	}
	
	public static float round(float d) {
	    BigDecimal bd = new BigDecimal(Float.toString(d));
	    bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
	    return bd.floatValue();
	}	
}
