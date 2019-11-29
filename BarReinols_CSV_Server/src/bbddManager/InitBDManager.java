package bbddManager;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.example.barreinolds.Camarero;
import com.example.barreinolds.Category;
import com.example.barreinolds.Main;
import com.example.barreinolds.Product;
import com.example.barreinolds.Ticket;

/*
 * Esta clase contiene los metodos que inicializan la base de datos y recoge los datos de ella.
 */
public class InitBDManager extends ConnectionManager {

	/*
	 * Metodo que devuelve la lista de camareros que hay en la base de datos
	 */
	public static ArrayList<Camarero> getCamareros() throws SQLException {
		ArrayList<Camarero> camareros = new ArrayList<Camarero>();
		Camarero c;
		String select = "SELECT * FROM camareros";
		Statement stmnt = getConnection().createStatement();
		ResultSet rs = stmnt.executeQuery(select);
		while (rs.next()) {
			c = new Camarero();
			c.setId(rs.getInt("id_camarero"));
			c.setNombre(rs.getString("nombre"));
			c.setUsername(rs.getString("username"));
			c.setPassword(rs.getString("password"));
			camareros.add(c);
		}
		return camareros;
	}

	/*
	 * Este metodo recupera los tickets que haya en la base de datos, en la tabla
	 * comandas.
	 * 
	 * Retorna un Array con los tickets creados.
	 */
	public static ArrayList<Ticket> getTickets() throws SQLException {
		ArrayList<Ticket> tickets = new ArrayList<Ticket>();
		Ticket t;
		ResultSet rs;
		PreparedStatement pstmnt;
		Camarero c;
		String select = "SELECT * FROM comandas WHERE mesa = ?";
		for (int i = 0; i < Main.numTaules; i++) {
			t = new Ticket();
			pstmnt = getConnection().prepareStatement(select);
			pstmnt.setInt(1, i);
			rs = pstmnt.executeQuery();
			if (rs.next()) {
				t.setMesa(rs.getInt("mesa"));
				c = tools.Search.searchForCamareroById(rs.getInt("id_camarero"));
				t.setCamarero(c);
				t.setDatetime(rs.getTimestamp("datetime"));
				t.setProductosComanda(getProductsFromTicket(t.getMesa()));
				tickets.add(t);
			}

		}
		Main.latch.countDown();
		return tickets;
	}

	/*
	 * Este metodo recupera el detalle de los productos de un ticket especifico de
	 * la base de datos. Estos datos se encuentran en details_comanda.
	 * 
	 * Retorna un array con todos los productos que haya devuelto la consulta.
	 */
	public static ArrayList<Product> getProductsFromTicket(int numMesa) throws SQLException {
		ArrayList<Product> products = new ArrayList<Product>();
		Product p;
		String select = "SELECT * FROM details_comandas WHERE mesa = " + numMesa;
		Statement stmnt = getConnection().createStatement();
		ResultSet rs = stmnt.executeQuery(select);
		
		while (rs.next()) {
			p = new Product();
			p.setId(rs.getInt("id_producto"));
			p.setName(tools.Search.getProductNameById(p.getId()));
			p.setCantidad(rs.getInt("cantidad_producto"));
			p.setPrice(String.valueOf(rs.getFloat("precio_producto")));
			p.setImage_Desktop(tools.Search.getProductImageById(p.getId()));
			p.setImage_movil(tools.Search.getProductAppImageById(p.getId()));
			p.setServed(rs.getBoolean("servido"));
			products.add(p);
		}
		return products;
	}

	/*
	 * Este metodo recupera de la base de datos las categorias disponibles del bar.
	 * 
	 * Esta consulta la hace en la tabla 'categoria'.
	 * 
	 * Retorna un array con las categorias disponibles.
	 */
	public static ArrayList<Category> getCategories() throws SQLException {
		ArrayList<Category> categories = new ArrayList<Category>();
		String select = "SELECT * FROM categorias";
		Statement stmnt = getConnection().createStatement();
		ResultSet rs = stmnt.executeQuery(select);
		Category c;
		while (rs.next()) {
			c = new Category();
			c.setId(rs.getInt("id_categoria"));
			c.setNombre(rs.getString("nombre"));
			c.setaLProducts(getProductsFromCategory(c.getId()));
			categories.add(c);
		}
		return categories;
	}

	/*
	 * Este metodo retorna la lista de productos para una categoria especifica.
	 * 
	 * Estos datos se encuentran en la tabla productos de la base de datos.
	 */
	public static ArrayList<Product> getProductsFromCategory(int categoryId) throws SQLException {
		ArrayList<Product> products = new ArrayList<Product>();
		String select = "SELECT * FROM productos WHERE id_categoria = " + categoryId;
		Statement stmnt = getConnection().createStatement();
		ResultSet rs = stmnt.executeQuery(select);
		Product p;
		while (rs.next()) {
			p = new Product();
			p.setId(rs.getInt("id_producto"));
			p.setName(rs.getString("nombre"));
			p.setPrice(String.valueOf(rs.getFloat("precio_producto")));
			p.setDescription(rs.getString("descripcion"));
			p.setImage_Desktop(rs.getString("imagen"));
			p.setImage_movil(rs.getString("image_movil"));
			products.add(p);
		}
		return products;
	}
	
	/*
	 * Este metodo nos retorna todos los productos disponibles en la base de datos
	 */
	public static ArrayList<Product> getAllProducts() throws SQLException{
		ArrayList<Product> aLP = new ArrayList<Product>();
		String select = "SELECT * FROM productos";
		Statement stmnt = getConnection().createStatement();
		ResultSet rs = stmnt.executeQuery(select);
		Product p;
		while(rs.next()) {
			p = new Product();
			p.setId(rs.getInt("id_producto"));
			p.setName(rs.getString("nombre"));
			p.setPrice(String.valueOf(rs.getFloat("precio_producto")));
			p.setDescription(rs.getString("descripcion"));
			p.setImage_Desktop(rs.getString("imagen"));
			p.setImage_movil(rs.getString("image_movil"));
			aLP.add(p);
		}
		return aLP;
	}

	/*
	 * Este metodo retorna el numero de mesas disponibles que se encuentra en la
	 * tabla mesa_maestra_configuracion.
	 */
	public static int getMesas() throws SQLException {
		String select = "SELECT cantidad_mesa FROM mesa_maestra_configuracion";
		Statement stmnt = getConnection().createStatement();
		ResultSet rs = stmnt.executeQuery(select);
		if (rs.next()) {
			return rs.getInt("cantidad_mesa");
		}
		return 0;
	}

	/*
	 * Este metodo inserta los datos de configuracion de la aplicación en la base de
	 * datos en caso de que no existan.
	 */
	public static void setConfigOnEmptyTable(int numMesa) throws UnknownHostException, SQLException {
		if (isTableEmpty("mesa_maestra_configuracion")) {
			String ip = InetAddress.getLocalHost().getHostAddress();
			String insert = "INSERT INTO mesa_maestra_configuracion VALUES ('" + ip + "', 'DESKTOP', " + numMesa + ")";
			getConnection().createStatement().executeUpdate(insert);
		}
	}

	/*
	 * Este metodo actualiza las mesas disponibles de la app en la base de datos.
	 */
	public static void updateTables(int numMesa) throws SQLException {
		String update = "UPDATE mesa_maestra_configuracion SET cantidad_mesa = " + numMesa;
		getConnection().createStatement().executeUpdate(update);
	}

	/*
	 * Este metodo comprueba si una tabla que se le pasa por parametro de la base de
	 * datos esta vacia.
	 */
	public static boolean isTableEmpty(String table) throws SQLException {
		String select = "SELECT * FROM " + table;
		ResultSet rs = getConnection().createStatement().executeQuery(select);
		return !rs.next();
	}
	

}
