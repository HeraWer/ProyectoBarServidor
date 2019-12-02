package bbddManager;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.example.barreinolds.Camarero;
import com.example.barreinolds.Main;

public class CamareroDBManager extends ConnectionManager{
	
	public static void insertCamarero(Camarero c) throws SQLException, IOException {
		String insert = "INSERT INTO camareros(nombre, username, password, image_blob) VALUES ('" + c.getNombre() + "', '" + c.getUsername() + "', '" + c.getPassword() + "', ?)";
		PreparedStatement pstmnt = getConnection().prepareStatement(insert);
		ByteArrayInputStream bais = new ByteArrayInputStream(c.getImageEmployee());
		pstmnt.setBinaryStream(1, bais);
		pstmnt.executeUpdate();
		Main.setCamareros(InitBDManager.getCamareros());
	}

}
