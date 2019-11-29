package com.example.barreinolds;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;

import org.xml.sax.SAXException;

import serverConnection.MainServer;
import ui.ConfigTablesDialog;
import ui.InitApp;
import ui.MainWindow;

/*
 * Clase principal que llama a la interfaz grafica del proyecto
 * Tiene una ArrayList con los tickets disponibles.
 */
public class Main {
	public static Camarero camareroSeleccionado;
	public static CountDownLatch latch;
	private static ArrayList<Ticket> ticketsBar = new ArrayList<Ticket>();
	private static ArrayList<Camarero> camarerosBar = new ArrayList<Camarero>();
	private static ArrayList<Category> categoriasBar = new ArrayList<Category>();
	private static ArrayList<Product> productosBar = new ArrayList<Product>();
	public static boolean emptyTables;
	public static int numTaules;
	public static MainWindow m;

	/*
	 * Metodo que devuelve el arraylist de tickets
	 */
	public static ArrayList<Ticket> getTickets() {
		return ticketsBar;
	}

	public static void setTickets(ArrayList<Ticket> tickets) {
		ticketsBar = tickets;
	}

	public static ArrayList<Camarero> getCamareros() {
		return camarerosBar;
	}

	public static void setCamareros(ArrayList<Camarero> camareros) {
		camarerosBar = camareros;
	}

	public static ArrayList<Category> getCategoriasBar() {
		return categoriasBar;
	}

	public static void setCategoriasBar(ArrayList<Category> categorias) {
		categoriasBar = categorias;
	}

	public static ArrayList<Product> getProductosBar() {
		return productosBar;
	}

	public static void setProductosBar(ArrayList<Product> productosBar) {
		Main.productosBar = productosBar;
	}

	/*
	 * Metodo que llama al metodo que guarda comandas (Tickets) en la JTable.
	 */
	public static void sendTicket(Ticket t) throws SQLException {
		tools.Search.deleteTicket(t.getMesa());
		if(t.getProductosComanda().size() == 0) {
			m.getTicketsFrame().setTicketOnTable(t);
			m.getTablesFrame().setTicketOnTable(t.getMesa());
			bbddManager.TicketDBManager.deleteComanda(t.getMesa());
			return;
		}
		ticketsBar.add(t);
		

		m.getTicketsFrame().setTicketOnTable(t);
		m.getTablesFrame().setTicketOnTable(t.getMesa());
		bbddManager.TicketDBManager.insertComanda(t);
	}

	/*
	 * Metodo main de la aplicacion.
	 */
	public static void main(String[] args) {
		
		
		latch = new CountDownLatch(2);
		new InitApp();
		try {
			latch.await();
		} catch (InterruptedException e2) {
			e2.printStackTrace();
		}
		
		latch = new CountDownLatch(1);
		m = new MainWindow();
		
		try {
			latch.await();
		} catch (InterruptedException e3) {
			e3.printStackTrace();
		}
		
		m.setVisible(true);
		// Abrimos la ventana principal

		

		latch = new CountDownLatch(1);
		if (Main.numTaules == 0)
			new ConfigTablesDialog(latch).setVisible(true);
		else
			latch.countDown();
		
		latch = new CountDownLatch(1);
		m.loadTickets();
		
		try {
			latch.await();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		m.resetUIForUpdates();
		m.getTablesFrame().onTableNumChangeCreateButtons();
		
		
		
		// Abrimos el server
		try {
			MainServer mS = new MainServer(m);
		} catch (ClassNotFoundException | IOException | SQLException e) {
			e.printStackTrace();
		}

	}

}
