package bbddManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.barreinolds.Product;
import com.example.barreinolds.Ticket;

public class FacturaDBManager extends ConnectionManager {

	/*
	 * String createFacturas = "CREATE TABLE facturas (" +
	 * "	id_factura INT NOT NULL AUTO_INCREMENT," + "	mesa INT NOT NULL," +
	 * "	id_camarero INT NOT NULL, " + "	datetime TIMESTAMP NOT NULL," +
	 * "	precio_final FLOAT(5,2)," + "    PRIMARY KEY (id_factura)" + ")";
	 */

	public static void insertFactura(Ticket t, float totalPrice) throws SQLException {
		String insertIntoComanda = "INSERT INTO facturas(mesa, id_camarero, datetime, precio_final_noiva, precio_final_iva) VALUES(?, ?, ?, ?, ?)";
		PreparedStatement pstmnt = getConnection().prepareStatement(insertIntoComanda);
		pstmnt.setInt(1, t.getMesa());
		pstmnt.setInt(2, t.getCamarero().getId());
		pstmnt.setTimestamp(3, tools.DataFormat.addHours(t.getDatetime(), 1));
		pstmnt.setFloat(4, totalPrice);
		pstmnt.setFloat(5, (totalPrice + (totalPrice * 0.1f)));
		pstmnt.executeUpdate();

		/*
		 * String createDetailsFacturas = "CREATE TABLE details_facturas (" +
		 * "    id_factura INT NOT NULL," + "    nombre_producto VARCHAR(50) NOT NULL,"
		 * + "    precio_producto INT NOT NULL," + "    cantidad INT NOT NULL," +
		 * "    CONSTRAINT id_factura_fk FOREIGN KEY (id_factura) REFERENCES facturas (id_factura)"
		 * + ")";
		 */
		String insertIntoDetailsComanda = "INSERT INTO details_facturas(id_factura, nombre_producto, precio_producto, cantidad) VALUES (?, ?, ?, ?)";
		int IDFactura = getIDFactura(t);
		if (IDFactura != 0) {
			for (Product p : t.getProductosComanda()) {
				pstmnt = getConnection().prepareStatement(insertIntoDetailsComanda);
				pstmnt.setInt(1, IDFactura);
				pstmnt.setString(2, p.getName());
				pstmnt.setFloat(3, Float.parseFloat(p.getPrice()));
				pstmnt.setInt(4, p.getCantidad());
				pstmnt.executeUpdate();
			}
		}else {
			System.out.println("ID FACTURA ES 0");
		}
	}

	public static int getIDFactura(Ticket t) throws SQLException {
		//String ts = tools.DataFormat.getSQLTimestampFormat(t.getDatetime());
		String select = "SELECT id_factura FROM facturas WHERE mesa = " + t.getMesa() + " ORDER BY 1 DESC";
		ResultSet rs = getConnection().createStatement().executeQuery(select);
		if (rs.next()) {
			return rs.getInt("id_factura");
		} else {
			return 0;
		}
	}

}
