package ui;

import java.awt.Color;
import java.io.IOException;

import javax.swing.JInternalFrame;
import javax.swing.JTabbedPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;

import org.xml.sax.SAXException;

import com.example.barreinolds.Main;
import com.example.barreinolds.Product;
import com.example.barreinolds.Ticket;

import xmlManager.XMLTicketManager;

/*
 * Clase que extiende de un JInternalFrame, permitiendo
 * crear un JInternalFrame personalizado.
 * 
 * En este caso, el Frame creado es el de los tickets de cocina.
 */
public class CocinaFrame extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Atributos de la clase
	private MainWindow parent;
	private JTabbedPane tabPane;

	/*
	 * Constructor en el que se le pasa como parametro el JFrame principal de la
	 * aplicación.
	 */
	public CocinaFrame(MainWindow parent) {
		super("Cocina", false, false, false, false);
		this.parent = parent;
		initialize();
		
			modify();
		
		add();
		
		this.setBorder(null);
		//((BasicInternalFrameUI)this.getUI()).setNorthPane(null);
	}

	/*
	 * Metodo que incializa los componentes del JInternalFrame
	 */
	public void initialize() {
		tabPane = new JTabbedPane();
	}

	/*
	 * Metodo que configura y modifica los diferentes componentes de dentro del
	 * JInternalFrame.
	 */
	public void modify() {
		tabPane.setBackground(new Color(47, 64, 88));
		tabPane.setForeground(new Color(255,255,255));
		// Creamos las pestañas con todas las mesas disponibles que haya.
		for (int i = tabPane.getTabCount(); i < Main.numTaules; i++) {
			int numMesa = i + 1;
			PanelTickets panel = new PanelTickets(parent, i + 1);
			tabPane.addTab("Mesa " + String.valueOf(numMesa), panel);
		}

	}

	/*
	 * Metodo que añade componentes dentro del JInternalFrame o dentro de otros
	 * componentes contenidos en el Frame.
	 */
	public void add() {
		this.add(tabPane);
	}

	public void repaintFrame() {
		if (Main.numTaules < tabPane.getTabCount()) {
			int oldTabs = tabPane.getTabCount();
			for (int i = oldTabs - 1; i >= Main.numTaules; i--) {
				tabPane.remove(i);
			}
		} else {
			
				modify();
			
		}

	}

	/*
	 * Metodo que busca una pestaña de una mesa en concreto. Devuelve true si la
	 * encuentra, false en caso de que no Se le pasa por parametro el numero de
	 * mesa.
	 */
	public boolean searchTab(int numTaula) {
		int tabs = tabPane.getTabCount();
		for (int i = 0; i < tabs; i++) {
			if (tabPane.getTitleAt(i).equals("Mesa " + String.valueOf(numTaula))) {
				tabPane.setSelectedIndex(i);
				return true;
			}
		}
		return false;
	}

	/*
	 * Metodo que añade una comanda en una mesa determinada. Recorre cada producto
	 * de la comanda y lo añade al JTable.
	 */
	public void setTicketOnTable(Ticket t)  {
		if(tabPane.getTabCount() == 0)
			return;
		PanelTickets numTabbedTable = (PanelTickets) tabPane.getComponentAt(t.getMesa() - 1);
		numTabbedTable.updateLabels();
		numTabbedTable.getProductsTable().clearDeleted(t);
		for (Product p : t.getProductosComanda()) {
			numTabbedTable.getProductsTable().addProduct(p);
		}
	}

	public void clearTicketCocina(int numMesa) {
		PanelTickets numTabbedTable = (PanelTickets) tabPane.getComponentAt(numMesa - 1);
		numTabbedTable.getProductsTable().clearTable();
		numTabbedTable.clearLabels();
	}

}
