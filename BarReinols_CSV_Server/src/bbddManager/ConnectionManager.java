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
		return conn;
	}
	
}
