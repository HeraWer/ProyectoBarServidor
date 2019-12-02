package bbddManager;

/*
 * Interfaz con los datos del server SQL
 * Para entrar a PHPMyAdmin poner el enlace 
 * siguiente en el navegador: 
 * 
 * https://remotemysql.com/phpmyadmin/sql.php?db=rxRaoXg53l
 */
public interface ConnectionInterface {

	String PORT = "3306";
	String USER = "admin";
	String HOST = "localhost";
	String PASSWORD = "P@ssw0rd";
	String DATABASE = "bar_reinols";
	String DRIVER = "com.mysql.cj.jdbc.Driver";
	String CONNSTRING = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE;

}