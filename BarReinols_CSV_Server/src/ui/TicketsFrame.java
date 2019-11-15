package ui;

import java.io.IOException;

import javax.swing.JInternalFrame;
import javax.swing.JTabbedPane;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;

import org.xml.sax.SAXException;

import bar.Main;
import bar.Product;
import bar.Ticket;
import xmlManager.XMLTicketManager;

/*
 * Clase que extiende de un JInternalFrame, permitiendo
 * crear un JInternalFrame personalizado.
 * 
 * En este caso, el Frame creado es el de los tickets de cocina.
 */
public class TicketsFrame extends JInternalFrame {

	// Atributos de la clase
	private MainWindow parent;
	private JTabbedPane tabPane;
	
	/*
	 * Constructor en el que se le pasa como parametro el JFrame 
	 * principal de la aplicación.
	 */
	public TicketsFrame(MainWindow parent) {
		super("Comandes", true, true, true, true);
		this.parent = parent;
		initialize();
		try {
			modify();
		} catch (NumberFormatException | XPathExpressionException | TransformerException | ParserConfigurationException
				| SAXException | IOException e) {
			e.printStackTrace();
		}
		add();
	}
	
	/*
	 * Metodo que incializa los componentes del JInternalFrame
	 */
	public void initialize() {
		tabPane = new JTabbedPane();
	}
	
	/*
	 * Metodo que configura y modifica los diferentes componentes 
	 * de dentro del JInternalFrame.
	 */
	public void modify() throws TransformerException, ParserConfigurationException, SAXException, IOException, NumberFormatException, XPathExpressionException {
		
		// Creamos las pestañas con todas las mesas disponibles que haya.
		for(int i = tabPane.getTabCount(); i < Main.numTaules; i++) {
			PanelTickets panel = new PanelTickets(parent);
			tabPane.addTab("Mesa " + String.valueOf(i + 1), panel);
		}
	}
	
	/*
	 * Metodo que añade componentes dentro del JInternalFrame o dentro
	 * de otros componentes contenidos en el Frame.
	 */
	public void add() {
		this.add(tabPane);
	}
	
	/*
	 * //////////////// DEPRECATED ////////////////
	 * Metodo que crea una pestaña nueva (Comanda) dependiendo de la mesa
	 * en la que se ha hecho click en la "Card" de mesas
	 * //////////////// DEPRECATED ////////////////
	 */
	public void crearComanda(Ticket t) {
		PanelTickets panel = new PanelTickets(parent);
		tabPane.addTab("Mesa " + String.valueOf(t.getTable()), panel);
		
		int tabs = tabPane.getTabCount();
		for(int i = 0; i < tabs; i++) {
			if(tabPane.getTitleAt(i).equals("Mesa " + String.valueOf(t.getTable()))) {
				tabPane.setSelectedIndex(i);
			}
		}		
	}
	
	public void repaintFrame() {
		if(Main.numTaules < tabPane.getTabCount()) {
			int oldTabs = tabPane.getTabCount();
			for(int i = oldTabs - 1; i >= Main.numTaules; i--) {
				tabPane.remove(i);
			}
		}else {
			try {
				modify();
			} catch (NumberFormatException | XPathExpressionException | TransformerException
					| ParserConfigurationException | SAXException | IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	/*
	 * Metodo que busca una pestaña de una mesa en concreto.
	 * Devuelve true si la encuentra, false en caso de que no
	 * Se le pasa por parametro el numero de mesa.
	 */
	public boolean searchTab(int numTaula) {
		int tabs = tabPane.getTabCount();
		for(int i = 0; i < tabs; i++) {
			if(tabPane.getTitleAt(i).equals("Mesa " + String.valueOf(numTaula))) {
				tabPane.setSelectedIndex(i);
				return true;
			}
		}
		return false;
	}
	
	/*
	 * Metodo que añade una comanda en una mesa determinada.
	 * Recorre cada producto de la comanda y lo añade al JTable.
	 */
	public void setTicketOnTable(Ticket t) throws TransformerException, ParserConfigurationException, SAXException, IOException, XPathExpressionException {
		PanelTickets numTabbedTable = (PanelTickets)tabPane.getComponentAt(t.getTable() - 1);
		XMLTicketManager xmlTM = new XMLTicketManager("xml/pedidoMesa" + String.valueOf(t.getTable()) + ".xml", t);
		for(Product p : t.getALProduct()) {
			xmlTM.createProduct(p);
			numTabbedTable.getProductsTable().addProduct(p);
			
		}
	}
	
	

	
}
