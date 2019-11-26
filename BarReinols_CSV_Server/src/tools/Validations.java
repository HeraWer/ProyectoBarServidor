package tools;

import java.util.InputMismatchException;

/*
 * Clase donde se guardan todas las validaciones que deban hacerse.
 * 
 * Validaciones se entiende comprobar valores, como por ejemplo,
 * la longitud de un String, si un valor es numerico o si es mayor
 * a cero.
 */
public class Validations {

	/*
	 * Metodo que comprueba si un valor que se le pasa por parametro
	 * en formato String se puede parsear a Integer. Si se puede parsear,
	 * quiere decir que es un valor numerico y entero.
	 * 
	 * Devuelve un booleano dependiendo de si lo consigue o no.
	 */
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
