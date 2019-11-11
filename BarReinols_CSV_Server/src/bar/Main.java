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
import xmlManager.XMLFileManager;

/*
 * Clase principal que llama a la interfaz grafica del proyecto
 * Tiene una ArrayList con los tickets disponibles.
 */
public class Main {

	private static ArrayList<Ticket> tickets = new ArrayList<Ticket>();
	
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
	public static void sendTicket(Ticket t,TicketsFrame tm) throws TransformerException, ParserConfigurationException, SAXException, IOException {
		tm.setTicketOnTable(t);
	}

	/*
	 * Metodo main de la aplicacion.
	 */
	public static void main(String[] args) {
		
		
		XMLFileManager xml = null;
		try {
			xml = new XMLFileManager("xml/config.xml");
			numTaules = Integer.parseInt(xml.getElementTextContent("//mesas/cantidad"));
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
		
		MainWindow m = new MainWindow();
		try {
			while (xml.isElementEquals("//mesas/cantidad", ""))
				new configTablesDialog(m).setVisible(true);
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
		try {
			MainServer mS = new MainServer(m);
		} catch (ClassNotFoundException | IOException | TransformerException | ParserConfigurationException | SAXException e) {
			e.printStackTrace();
		}

	}

}
