package bbddManager;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.SQLException;

import com.example.barreinolds.Camarero;
import com.example.barreinolds.Main;
import java.sql.CallableStatement;

public class CamareroDBManager extends ConnectionManager {

	public static void insertCamarero(Camarero c) throws SQLException, IOException {
		String procedure = "{CALL insert_employee(?, ?, ?, ?)}";
		CallableStatement cstmnt = getConnection().prepareCall(procedure);
		cstmnt.setString(1, c.getNombre());
		cstmnt.setString(2, c.getUsername());
		cstmnt.setString(3, c.getPassword());
		ByteArrayInputStream bais = new ByteArrayInputStream(c.getImageEmployee());
		cstmnt.setBinaryStream(4, bais);
		cstmnt.execute();
		Main.setCamareros(InitBDManager.getCamareros());
	}

}
