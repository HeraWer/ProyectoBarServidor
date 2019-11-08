package ui;

import javax.swing.DefaultListSelectionModel;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import bar.Main;
import bar.Product;
import bar.Ticket;

public class TicketsJTable extends JTable {
	
	private MainWindow parent;
	private DefaultTableModel tableModel;
	private ListSelectionModel listModel;
	private final String[] titles = { "Listo", "ID Producto", "Nombre", "Precio", "Cantidad" };
	
	public TicketsJTable(MainWindow parent) {
		super(new DefaultTableModel());
		tableModel = (DefaultTableModel) this.getModel();
		this.parent = parent;
		initialize();
		modify();
	}
	
	public void initialize() {
		listModel = new DefaultListSelectionModel();		
	}
	
	public void modify() {
		this.setModel(tableModel);
		
		this.setColumnSelectionAllowed(false);
		this.setCellSelectionEnabled(false);
		this.setRowSelectionAllowed(true);
		this.setFillsViewportHeight(true);
		
		
		
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
		
		this.setSelectionModel(listModel);
		this.getColumnModel().getColumn(0).setCellEditor(new JCheckBox_Cell(new JCheckBox()));
		this.getColumnModel().getColumn(0).setCellRenderer(new JCheckBox_Rendered());
	}
	
	public void addProduct(Product p) {
		tableModel.addRow(new Object[] { false, p.getId(), p.getName(), p.getPrice(), p.getQuantity() });
	}

}
