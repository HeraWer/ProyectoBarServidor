package bbddManager;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TablesManager extends ConnectionManager implements ScriptsInterface {

	static String[] tableNames = {
			"categorias",
			"camareros",
			"productos",
			"comandas",
			"details_comandas",
			"facturas",
			"details_facturas",
			"mesa_maestra_configuracion", 
 	};
	
	public static void initializeDatabase() throws SQLException, FileNotFoundException {
		for(int i = 0; i < tableNames.length; i++) {
			if(!tableExists(tableNames[i])) {
				ui.InitApp.infoLabel.setText("Creando tabla " + tableNames[i]);	
				createTable(tableNames[i]);
				ui.InitApp.infoLabel.setText("Insertando datos de la tabla " + tableNames[i]);
				insertDataOnTable(tableNames[i]);
			}
		}
	}
	
	public static boolean tableExists(String table) throws SQLException {
		DatabaseMetaData dbm = getConnection().getMetaData();
		ResultSet getTable = dbm.getTables(null, null, table, null);
		if(getTable.next())
			return true;
		return false;
	}
	
	public static void createTable(String table) throws SQLException {
		Statement stmnt = getConnection().createStatement();
		switch(table) {
		case "mesa_maestra_configuracion":
			stmnt.executeUpdate(createMesaConfig);
			break;
		case "details_facturas":
			stmnt.executeUpdate(createDetailsFacturas);
			break;
		case "facturas":
			stmnt.executeUpdate(createFacturas);
			break;
		case "details_comandas":
			stmnt.executeUpdate(createDetailsComanda);
			break;
		case "comandas":
			stmnt.executeUpdate(createComandas);
			break;
		case "productos":
			stmnt.executeUpdate(createProductos);
			break;
		case "camareros":
			stmnt.executeUpdate(createCamareros);
			break;
		case "categorias":
			stmnt.executeUpdate(createCategorias);
			break;
		}
	}
	
	public static void insertDataOnTable(String table) throws SQLException, FileNotFoundException {
		Statement stmnt = getConnection().createStatement();
		PreparedStatement pstmnt;
		FileInputStream fis;
		switch(table) {
		case "categorias":
			for(int i = 0; i < insertIntoCategorias.length; i++) {
				fis = new FileInputStream(categoryImages[i]);
				pstmnt =  getConnection().prepareStatement(insertIntoCategorias[i]);
				pstmnt.setBinaryStream(1, fis);
				pstmnt.executeUpdate();
			}
			break;
		case "productos":
			for(int i = 0; i < insertIntoProductos.length; i++) {
				fis = new FileInputStream(productImages[i]);
				pstmnt =  getConnection().prepareStatement(insertIntoProductos[i]);
				pstmnt.setBinaryStream(1, fis);
				pstmnt.executeUpdate();
			}
			break;
		case "camareros":
			for(int i = 0; i < insertIntoCamareros.length; i++) {
				stmnt.executeUpdate(insertIntoCamareros[i]);
			}
			break;
		}
	}
	
}
