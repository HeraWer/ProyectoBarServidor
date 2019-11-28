package tools;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class UIMethods {

	public static void clearTable(JTable table) {
		((DefaultTableModel)table.getModel()).setRowCount(0);
	}
	
}
