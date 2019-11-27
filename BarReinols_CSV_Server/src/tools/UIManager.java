package tools;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class UIManager {

	public static void clearTable(JTable table) {
		((DefaultTableModel)table.getModel()).setRowCount(0);
	}
	
}
