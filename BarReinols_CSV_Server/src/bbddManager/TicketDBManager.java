package bbddManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.example.barreinolds.Product;
import com.example.barreinolds.Ticket;

public class TicketDBManager extends ConnectionManager {

	public static void insertComanda(Ticket t) throws SQLException {
		if (!checkIfTicketExists(t)) {
			String insertIntoComanda = "INSERT INTO comandas(mesa, id_camarero, datetime) VALUES(?, ?, ?)";
			PreparedStatement pstmnt = getConnection().prepareStatement(insertIntoComanda);
			pstmnt.setInt(1, t.getMesa());
			pstmnt.setInt(2, t.getCamarero().getId());
			pstmnt.setTimestamp(3, tools.DataFormat.addHours(t.getDatetime(), 1));
			pstmnt.executeUpdate();
			
			
			String insertIntoDetailsComanda = "INSERT INTO details_comandas(mesa, id_producto, cantidad_producto, precio_producto, servido) VALUES (?, ?, ?, round(?, 2), false)";
			for (Product p : t.getProductosComanda()) {
				pstmnt = getConnection().prepareStatement(insertIntoDetailsComanda);
				pstmnt.setInt(1, t.getMesa());
				pstmnt.setInt(2, p.getId());
				pstmnt.setInt(3, p.getCantidad());
				pstmnt.setFloat(4, Float.parseFloat(p.getPrice()));
				pstmnt.executeUpdate();
			}
		}else {
			updateComanda(t);
		}
	}

	public static boolean checkIfTicketExists(Ticket t) throws SQLException {
		String select = "SELECT mesa FROM comandas WHERE mesa = " + t.getMesa();
		ResultSet rs = getConnection().createStatement().executeQuery(select);
		if (rs.next())
			return true;
		return false;
	}
	
	public static boolean checkIfProductExists(Product p, int numMesa) throws SQLException {
		String select = "SELECT * FROM details_comandas WHERE mesa = " + numMesa + " and id_producto = " + p.getId();
		ResultSet rs = getConnection().createStatement().executeQuery(select);
		if(rs.next())
			return true;
		return false;
	}
	
	public static void updateComanda(Ticket t) throws SQLException {
		String update = "UPDATE details_comandas SET cantidad_producto = ?  WHERE mesa = " + t.getMesa();
		String insert = "INSERT INTO details_comandas(mesa, id_producto, cantidad_producto, precio_producto) VALUES (?, ?, ?, ?)";
		PreparedStatement pstmntUpdate;
		PreparedStatement pstmntInsert;
		for(Product p : t.getProductosComanda()) {
			if(checkIfProductExists(p, t.getMesa())) {
				pstmntUpdate = getConnection().prepareStatement(update);
				pstmntUpdate.setInt(1, p.getCantidad());
				pstmntUpdate.executeUpdate();
			}else {
				pstmntInsert = getConnection().prepareStatement(insert);
				pstmntInsert.setInt(1, t.getMesa());
				pstmntInsert.setInt(2, p.getId());
				pstmntInsert.setInt(3, p.getCantidad());
				pstmntInsert.setFloat(4, Float.parseFloat(p.getPrice()));
				pstmntInsert.executeUpdate();
			}
		}
	}
	
	public static void deleteComanda(int numMesa) throws SQLException {
		String deleteComanda = "DELETE FROM comandas WHERE mesa = " + numMesa;
		String deleteDetailsComanda = "DELETE FROM details_comandas WHERE mesa = " + numMesa;
		
		getConnection().createStatement().executeUpdate(deleteDetailsComanda);
		getConnection().createStatement().executeUpdate(deleteComanda);
	}
	
	public static void serveProduct(int idProduct, int numMesa) throws SQLException {
		String update = "UPDATE details_comandas SET servido = true WHERE mesa = " + numMesa + " and id_producto = " + idProduct;
		Statement stmnt = getConnection().createStatement();
		stmnt.executeUpdate(update);
	}
	
	public static void returnProduct(int idProduct, int numMesa) throws SQLException {
		String update = "UPDATE details_comandas SET servido = false WHERE mesa = " + numMesa + " and id_producto = " + idProduct;
		Statement stmnt = getConnection().createStatement();
		stmnt.executeUpdate(update);
	}
	

}
