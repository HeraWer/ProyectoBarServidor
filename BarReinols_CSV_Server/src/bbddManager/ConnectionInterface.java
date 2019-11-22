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
	String USER = "rxRaoXg53l";
	String HOST = "remotemysql.com";
	String PASSWORD = "CbnAIC1P5I";
	String DATABASE = "rxRaoXg53l";
	String DRIVER = "com.mysql.cj.jdbc.Driver";
	String CONNSTRING = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE;

}