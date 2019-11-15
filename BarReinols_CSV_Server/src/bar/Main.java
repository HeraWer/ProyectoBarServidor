package bar;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;

import org.xml.sax.SAXException;

import serverConnection.MainServer;
import ui.MainWindow;
import ui.TicketsFrame;
import ui.configTablesDialog;
import xmlManager.XMLConfigManager;
import xmlManager.XMLTicketManager;

/*
 * Clase principal que llama a la interfaz grafica del proyecto
 * Tiene una ArrayList con los tickets disponibles.
 */
public class Main {

	private static ArrayList<Ticket> tickets = new ArrayList<Ticket>();

	public static boolean emptyTables;
	public static int numTaules;

	/*
	 * Metodo que devuelve el arraylist de tickets
	 */
	public static ArrayList<Ticket> getTickets() {
		return tickets;
	}

	/*
	 * Metodo que llama al metodo que guarda comandas (Tickets) en la JTable.
	 */
	public static void sendTicket(Ticket t, TicketsFrame tm) throws TransformerException, ParserConfigurationException,
			SAXException, IOException, XPathExpressionException {
		tickets.add(t);
		tm.setTicketOnTable(t);	
	}

	/*
	 * Metodo main de la aplicacion.
	 */
	public static void main(String[] args) {

		// Comprobamos las mesas disponibles del fichero config.xml
		XMLConfigManager xmlConfigManager = null;
		
		XMLTicketManager xmlTicketManager = null; 
		try {
			xmlConfigManager = new XMLConfigManager("xml/config.xml");
			if (!xmlConfigManager.isElementEquals("//mesas/cantidad", "")) {
				numTaules = Integer.parseInt(xmlConfigManager.getElementTextContent("//mesas/cantidad"));
				emptyTables = true;
			}
			
			
			
		} catch (TransformerException e1) {
			e1.printStackTrace();
		} catch (ParserConfigurationException e1) {
			e1.printStackTrace();
		} catch (SAXException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}

		// Abrimos la ventana principal
		MainWindow m = new MainWindow();
		
		try {
			xmlTicketManager = new XMLTicketManager();
			xmlTicketManager.lookForTickets(m);
		} catch (XPathExpressionException | SAXException | IOException | TransformerException
				| ParserConfigurationException e1) {
			e1.printStackTrace();
		}
		
		

		// Si las mesas estan vacias llamamos al dialogo de configuracion
		if(numTaules <= 0)
			new configTablesDialog(m).setVisible(true);

		// Abrimos el server
		try {
			MainServer mS = new MainServer(m);
		} catch (ClassNotFoundException | IOException | TransformerException | ParserConfigurationException
				| SAXException | XPathExpressionException e) {
			e.printStackTrace();
		}

	}

}
