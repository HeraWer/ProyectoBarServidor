package tools;

import java.util.InputMismatchException;

public class Validations {

	public static boolean checkParseInt(String num) {
		if(num.equals(""))
			return false;
		try {
			Integer.parseInt(num);
			return true;
		}catch(InputMismatchException e) {
			return false;
		}
	}
	
}
