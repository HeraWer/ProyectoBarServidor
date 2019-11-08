package bar;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;

import org.xml.sax.SAXException;

import serverConnection.MainServer;
import ui.MainWindow;
import ui.configTablesDialog;
import xmlManager.XMLFileManager;

public class Main {

	private static ArrayList<Ticket> tickets = new ArrayList<Ticket>();

	public static ArrayList<Ticket> getTickets() {
		return tickets;
	}

	public static void main(String[] args) {
		XMLFileManager xml = null;
		try {
			xml = new XMLFileManager();
		} catch (TransformerException e1) {
			e1.printStackTrace();
		} catch (ParserConfigurationException e1) {
			e1.printStackTrace();
		} catch (SAXException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		MainWindow m = new MainWindow();
		try {
			while (xml.isElementEquals("//mesas/cantidad", ""))
				new configTablesDialog(m).setVisible(true);
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
		try {
			MainServer mS = new MainServer();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}

	}

}
