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
public class Ticket implements Serializable {
	
	private ArrayList<Product> productosComanda;
	private int mesa;
	
	public Ticket() {
		
	}
	
	/*
	 * Constructor en el que se le pasa 
	 * el numero de mesa del ticket
	 */
	public Ticket(int mesa) {
		productosComanda = new ArrayList<Product>();
		this.mesa = mesa;
	}
	
	public Ticket(ArrayList<Product> productosComanda, int mesa) {
		this.productosComanda = productosComanda;
		this.mesa = mesa;
	}
	
	/*
	 * metodo que devuelve el numero de mesa de la comanda
	 */
	public int getTable() {
		return this.mesa;
	}
	
	/*
	 * Metodo que devuelve el ArrayList de productos
	 */
	public ArrayList<Product> getALProduct() {
		return this.productosComanda;
	}
	
	public void setALProduct(ArrayList<Product> productosComanda) {
		this.productosComanda = productosComanda;
	}
	
	
}
