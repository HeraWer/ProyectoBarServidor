package bar;

import java.io.Serializable;
import java.util.ArrayList;

public class Ticket implements Serializable{
	
	private ArrayList<Product> aLProduct;
	private int table;
	
	public Ticket() {
		
	}
	
	public Ticket(int table) {
		aLProduct = new ArrayList<Product>();
		this.table = table;
	}
	
	public int getTable() {
		return this.table;
	}
	
	
	
}
