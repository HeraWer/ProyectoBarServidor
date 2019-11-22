package ui;

import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;

/*
 * Clase del editor del JCheckBox dentro de la JTable.
 */
public class JCheckBox_Cell extends DefaultCellEditor{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * Constructor al que se le pasa el checkbox a ser editado.
	 */
	public JCheckBox_Cell(JCheckBox cb) {
		super(cb);
	}
	
}
