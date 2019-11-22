package bbddManager;

import java.sql.DatabaseMetaData;
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
	
	public static void initializeDatabase() throws SQLException {
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
	
	public static void insertDataOnTable(String table) throws SQLException {
		Statement stmnt = getConnection().createStatement();
		switch(table) {
		case "categorias":
			for(int i = 0; i < insertIntoCategorias.length; i++) {
				stmnt.executeUpdate(insertIntoCategorias[i]);
			}
			break;
		case "productos":
			for(int i = 0; i < insertIntoProductos.length; i++) {
				stmnt.executeUpdate(insertIntoProductos[i]);
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
