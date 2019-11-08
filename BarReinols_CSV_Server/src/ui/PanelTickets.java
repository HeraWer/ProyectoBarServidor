package ui;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class PanelTickets extends JPanel {
	
	TicketsJTable productsTable;
	JPanel jTablePanel;
	JPanel buttonsPanel;
	MainWindow parent;
	JScrollPane jScroll;
	
	public PanelTickets(MainWindow parent) {
		super(new BorderLayout());
		this.parent = parent;
		initialize();
		add();
	}
	
	public void initialize() {
		productsTable = new TicketsJTable(parent);
		jTablePanel = new JPanel(new BorderLayout());
	}
	
	public void modify() {
		
	}
	
	public void add() {
		jScroll = new JScrollPane(productsTable);
		jTablePanel.add(jScroll, BorderLayout.CENTER);
		this.add(jTablePanel, BorderLayout.CENTER);
	}
	
	public TicketsJTable getProductsTable() {
		return this.productsTable;
	}
}
