package bar;

import java.io.Serializable;
import java.util.ArrayList;
/*
 * Esta es la clase de Tickets, o comandas.
 * Tiene un arraylist de productos y un integer del
 * numero de mesa al que pertenece.
 * 
 * Implementa la clase Serializable
 * para poder pasarsela al cliente
 */
public class Ticket implements Serializable{
	
	private ArrayList<Product> aLProduct;
	private int table;
	
	public Ticket() {
		
	}
	
	/*
	 * Constructor en el que se le pasa 
	 * el numero de mesa del ticket
	 */
	public Ticket(int table) {
		aLProduct = new ArrayList<Product>();
		this.table = table;
	}
	
	/*
	 * metodo que devuelve el numero de mesa de la comanda
	 */
	public int getTable() {
		return this.table;
	}
	
	/*
	 * Metodo que devuelve el ArrayList de productos
	 */
	public ArrayList<Product> getALProduct() {
		return this.aLProduct;
	}
	
	
}
