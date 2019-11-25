package ui;

import java.awt.Color;
import java.util.Vector;

import javax.swing.DefaultListSelectionModel;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import com.example.barreinolds.Product;
import com.example.barreinolds.Ticket;

/*
 * Esta clase extiende de JTable, lo que crea una JTable personalizada
 * con todos los componentes que debe tener, como por ejemplo el modelo
 * de tabla, el modelo de seleccion (ListSelectionModel) y los titulos
 */
public class CocinaJTable extends JTable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/*
	 * Atributos que necesita si o si esta clase.
	 */
	private DefaultTableModel tableModel;
	private ListSelectionModel listModel;
	private final String[] titles = { "ID Producto", "Nombre", "Precio", "Cantidad", "Listo", "Servido" };

	/*
	 * Constructor que recibe la ventana principal (El JFrame)
	 */
	public CocinaJTable(MainWindow parent) {
		super(new DefaultTableModel());
		tableModel = (DefaultTableModel) this.getModel();
		initialize();
		modify();
	}

	/*
	 * Metodo que incializa los componentes
	 */
	public void initialize() {
		listModel = new DefaultListSelectionModel();
	}

	/*
	 * Metodo que modifica los componentes, añadiendole parametros u opciones
	 */
	public void modify() {
		// Cambiamos los parametros necesarios relacionados con la tabla
		this.setColumnSelectionAllowed(false);
		this.setCellSelectionEnabled(false);
		this.setRowSelectionAllowed(true);
		this.setFillsViewportHeight(true);
		this.setOpaque(false);
		
		this.setGridColor(new Color(255,255,255));
		this.getTableHeader().setBackground(new Color(47, 64, 88));
		this.getTableHeader().setForeground(new Color(255,255,255));

		// Seteamos el modo de selección de la tabla
		listModel.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

		tableModel.setColumnIdentifiers(titles);

		// Añadimos el modelo de seleccion a la tabla
		this.setSelectionModel(listModel);

		// Líneas necesarias que agregan un cell editor y un cell renderer a la columna
		// con indice 0
		// Para poder agregar un JCheckBox a esta
		this.getColumnModel().getColumn(4).setCellEditor(new JCheckBox_Cell(new JCheckBox()));
		this.getColumnModel().getColumn(4).setCellRenderer(new JCheckBox_Rendered());

		this.getColumnModel().getColumn(5).setCellEditor(new JCheckBox_Cell(new JCheckBox()));
		this.getColumnModel().getColumn(5).setCellRenderer(new JCheckBox_Rendered());
	}

	/*
	 * Metodo que añade un producto a la comanda
	 */
	public void addProduct(Product p) {
		int posProduct = tools.Search.checkProductOnTable(p, this);
		if (posProduct != -1) {
			this.setValueAt(p.getCantidad(), posProduct, 3);
		} else {
			Vector<Object> v = new Vector<Object>();
			v.add(p.getId());
			v.add(p.getName());
			v.add(p.getPrice());
			v.add(String.valueOf(p.getCantidad()));
			v.add(false);
			v.add(false);

			tableModel.addRow(v);

		}

	}

	public void clearDeleted(Ticket t) {
		boolean existe = false;
		for (int i = this.getRowCount() - 1; i >= 0; i--) {
			Object value = this.getValueAt(i, 0);
			int idAtTable = (Integer) value;
			for (Product p : t.getProductosComanda()) {
				if (p.getId() == idAtTable) {
					existe = true;
				}
			}
			if (!existe) {
				tableModel.removeRow(i);
			}

		}
	}

	public void clearTable() {
		for (int i = this.getRowCount() - 1; i >= 0; i--) {
			tableModel.removeRow(i);
		}
	}
	
	

}
