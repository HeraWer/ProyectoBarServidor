package ui;


import java.awt.Color;
import java.awt.Component;

import javax.swing.JCheckBox;
import javax.swing.JTable;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

/*
 * Clase que renderiza el JCheckBox dentro de la JTable.
 */
public class JCheckBox_Rendered extends JCheckBox implements TableCellRenderer{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * Metodo que define el renderizado del JCheckBox y hace la funcion de seleccion del mismo.	
	 */
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {

		DefaultTableModel dtm = ((DefaultTableModel)table.getModel());
		((DefaultTableCellRenderer)table.getCellRenderer(row, 1)).setBackground(new Color(86, 119, 166));
		((DefaultTableCellRenderer)table.getCellRenderer(row, 1)).setForeground(ColorsClass.WHITE);
		setBackground(new Color(86, 119, 166));
		setSelected(value != null && ((Boolean) value).booleanValue());
		return this;
	}
}