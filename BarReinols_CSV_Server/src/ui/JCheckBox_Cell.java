package ui;

import java.awt.Color;
import java.awt.Component;

import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;

public class JCheckBox_Cell extends DefaultCellEditor{
	
	public JCheckBox_Cell(JCheckBox cb) {
		super(cb);
	}
	
	/*public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		cb = new JCheckBox();
		cb.setSelected(!isSelected);
		return cb;
	}*/

}
