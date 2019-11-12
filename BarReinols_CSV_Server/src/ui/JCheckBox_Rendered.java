package ui;

import java.awt.Component;

import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/*
 * Clase que renderiza el JCheckBox dentro de la JTable.
 */
public class JCheckBox_Rendered extends JCheckBox implements TableCellRenderer{
	
	/*
	 * Metodo que define el renderizado del JCheckBox y hace la funcion de seleccion del mismo.	
	 */
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		setSelected(value != null && ((Boolean) value).booleanValue());
		return this;
	}
}