package ui;

import javax.swing.DefaultListSelectionModel;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import bar.Product;

/*
 * Esta clase extiende de JTable, lo que crea una JTable personalizada
 * con todos los componentes que debe tener, como por ejemplo el modelo
 * de tabla, el modelo de seleccion (ListSelectionModel) y los titulos
 */
public class TicketsJTable extends JTable {
	
	/*
	 * Atributos que necesita si o si esta clase.
	 */
	private MainWindow parent;
	private DefaultTableModel tableModel;
	private ListSelectionModel listModel;
	private final String[] titles = { "Listo", "ID Producto", "Nombre", "Precio", "Cantidad" };
	
	/*
	 * Constructor que recibe la ventana principal (El JFrame)
	 */
	public TicketsJTable(MainWindow parent) {
		super(new DefaultTableModel());
		tableModel = (DefaultTableModel) this.getModel();
		this.parent = parent;
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
		
		// Seteamos el modo de selección de la tabla
		listModel.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		
		tableModel.setDataVector(new Object[][] {
			{true, "1", "Camaron", "27,95", "5"},
			{true, "1", "Camaron", "27,95", "5"},
			{true, "1", "Camaron", "27,95", "5"},
			{true, "1", "Camaron", "27,95", "5"},
			{true, "1", "Camaron", "27,95", "5"},
			{true, "1", "Camaron", "27,95", "5"},
			{true, "1", "Camaron", "27,95", "5"},
			{true, "1", "Camaron", "27,95", "5"},
			{true, "1", "Camaron", "27,95", "5"},
			{true, "1", "Camaron", "27,95", "5"},
			{true, "1", "Camaron", "27,95", "5"},
		}, titles);
		
		// Añadimos el modelo de seleccion a la tabla
		this.setSelectionModel(listModel);
		
		// Líneas necesarias que agregan un cell editor y un cell renderer a la columna con indice 0
		// Para poder agregar un JCheckBox a esta
		this.getColumnModel().getColumn(0).setCellEditor(new JCheckBox_Cell(new JCheckBox()));
		this.getColumnModel().getColumn(0).setCellRenderer(new JCheckBox_Rendered());
	}
	
	/*
	 * Metodo que añade un producto a la comanda
	 */
	public void addProduct(Product p) {
		tableModel.addRow(new Object[] { false, p.getId(), p.getName(), p.getPrice(), p.getQuantity() });
		
	}

}
