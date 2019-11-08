package ui;

import java.io.IOException;

import javax.swing.JInternalFrame;
import javax.swing.JTabbedPane;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;

import org.xml.sax.SAXException;

import bar.Product;
import bar.Ticket;
import xmlManager.XMLFileManager;

public class TicketsFrame extends JInternalFrame {

	private MainWindow parent;
	private JTabbedPane tabPane;
	
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
	
	public void initialize() {
		tabPane = new JTabbedPane();
	}
	
	public void modify() throws TransformerException, ParserConfigurationException, SAXException, IOException, NumberFormatException, XPathExpressionException {
		int numTaules;
		XMLFileManager xfm = new XMLFileManager();
		numTaules = Integer.parseInt(xfm.getElementTextContent("//mesas/cantidad"));
		for(int i = 0; i < numTaules; i++) {
			PanelTickets panel = new PanelTickets(parent);
			tabPane.addTab("Mesa " + String.valueOf(i + 1), panel);
		}
	}
	
	public void add() {
		this.add(tabPane);
	}
	
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
	
	public void setTicketOnTable(Ticket t) {
		PanelTickets numTabbedTable = (PanelTickets)tabPane.getComponentAt(t.getTable() - 1);
		for(Product p : t.getALProduct()) {
			numTabbedTable.getProductsTable().addProduct(p);
		}
	}
	
	

	
}
