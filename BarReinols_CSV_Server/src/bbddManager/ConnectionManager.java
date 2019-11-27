package bbddManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager implements ConnectionInterface{

	private static Connection conn;
	
	public static void initializeJDBC() throws ClassNotFoundException, SQLException {
		Class.forName(DRIVER);
		conn = DriverManager.getConnection(CONNSTRING, USER, PASSWORD);
	}
	
	public static Connection getConnection() {
		checkConnection();
		return conn;
	}
	
	public static void checkConnection() {
		String select = "SELECT 1";
		try {
			conn.createStatement().execute(select);
		}catch(SQLException e) {
			try {
				reconnect();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	public static void reconnect() throws SQLException {
		conn = DriverManager.getConnection(CONNSTRING, USER, PASSWORD);
	}
	
}
