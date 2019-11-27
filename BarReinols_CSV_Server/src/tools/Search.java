package tools;

import javax.swing.JTable;

import com.example.barreinolds.Camarero;
import com.example.barreinolds.Category;
import com.example.barreinolds.Main;
import com.example.barreinolds.Product;
import com.example.barreinolds.Ticket;

public class Search {
	
	public static Ticket searchForTicket(int numMesa) {
		
		for(Ticket t : Main.getTickets()) {
			if(t.getMesa() == numMesa)
				return t;
		}
		return null;
	}
	
	public static Product searchForProductOnTicket(Ticket t, int idProduct) {
		for(Product p : t.getProductosComanda()) {
			if(p.getId() == idProduct) {
				return p;
			}
		}
		return null;
	}
	
	public static void deleteTicket(int numMesa) {
		for(Ticket t : Main.getTickets()) {
			if(t.getMesa() == numMesa) {
				Main.getTickets().remove(t);
				return;
			}
		}
	}
	
	public static int checkProductOnTable(Product p, JTable table) {
		for(int i = 0; i < table.getRowCount(); i++) {
			if(Integer.parseInt(table.getValueAt(i, 0).toString()) == p.getId())
				return i;
		}
		return -1;
	}
	
	public static Camarero searchForCamareroByName(String user) {
		for(Camarero c : Main.getCamareros()) {
			if(c.getUsername().equals(user))
				return c;
		}
		
		return null;
	}
	
	public static Camarero searchForCamareroById(int id) {
		for(Camarero c : Main.getCamareros()) {
			if(c.getId() == id)
				return c;
		}
		
		return null;
	}
	
	public static String getProductNameById(int id) {
		for(Category c : Main.getCategoriasBar()) {
			for(Product p : c.getaLProducts()) {
				if(p.getId() == id) 
					return p.getName();
			}
		}
		return null;
	}
	
	public static Category getCategoryOfProduct(Product p) {
		for(Category c : Main.getCategoriasBar()) {
			for(Product pCat : c.getaLProducts()) {
				if(pCat.getId() == p.getId())
					return c;
			}
		}
		return null;
	}
	
	public static Category getCategoryById(int id) {
		for(Category c : Main.getCategoriasBar()) {
			if(c.getId() == id) {
				return c;
			}
		}
		return null;
	}
	
	public static String getProductImageById(int id) {
		for(Category c : Main.getCategoriasBar()) {
			for(Product p : c.getaLProducts()) {
				if(p.getId() == id) 
					return p.getImage_Desktop();
			}
		}
		return null;
	}
	
	public static String getProductAppImageById(int id) {
		for(Category c : Main.getCategoriasBar()) {
			for(Product p : c.getaLProducts()) {
				if(p.getId() == id) 
					return p.getImage_movil();
			}
		}
		return null;
	}
	
	public static float getTotalFromTicket(Ticket t) {
		return 0;
	}
	
	public static void checkDuplicateProducts(Ticket t) {
		for(int i = 0; i < t.getProductosComanda().size(); i++) {
			for(int j = 0; j < t.getProductosComanda().size(); j++) {
				if(i >= 0 && j != i && t.getProductosComanda().get(i).getId() == t.getProductosComanda().get(j).getId()) {
					t.getProductosComanda().remove(i);
					i--;
					break;
				}
			}
		}
	}
	
}
