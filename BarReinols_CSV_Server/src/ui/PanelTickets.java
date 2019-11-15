package ui;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

/*
 * Clase que extiende de un JPanel con el fin de crear un JPanel personalizado.
 * En este caso es el JPanel que contiene la comanda de una mesa en concreto
 * en forma de JTable.
 */
public class PanelTickets extends JPanel {
	
	// Atributos de la clase.
	TicketsJTable productsTable;
	JPanel jTablePanel;
	JPanel buttonsPanel;
	MainWindow parent;
	JScrollPane jScroll;
	
	/*
	 * Constructor al que se le pasa la JFrame principal.
	 */
	public PanelTickets(MainWindow parent) {
		super(new BorderLayout());
		this.parent = parent;
		initialize();
		add();
	}
	
	/*
	 * Metodo que inicializa cada componente.
	 */
	public void initialize() {
		productsTable = new TicketsJTable(parent);
		jTablePanel = new JPanel(new BorderLayout());
	}
	
	/*
	 * Metodo que modifica y configura cada componente.
	 */
	public void modify() {
		
	}
	
	/*
	 * Metodo que añade los componentes dentro de la instancia de esta
	 * clase o dentro de otro componente dentro de la instancia de esta
	 * clase.
	 */
	public void add() {
		jScroll = new JScrollPane(productsTable);
		jTablePanel.add(jScroll, BorderLayout.CENTER);
		this.add(jTablePanel, BorderLayout.CENTER);
	}
	
	/*
	 * Metodo que devuelve el JTable de esta clase.
	 */
	public TicketsJTable getProductsTable() {
		return this.productsTable;
	}
}
