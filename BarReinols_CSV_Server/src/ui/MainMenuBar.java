package ui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.concurrent.CountDownLatch;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;

import org.xml.sax.SAXException;

import com.example.barreinolds.Camarero;
import com.example.barreinolds.Main;
import com.example.barreinolds.Product;
import com.example.barreinolds.Ticket;

/*
 * Clase principal que desciende de un JMenuBar para 
 * poder crear un JMenuBar personalizado y añadirle 
 * los componentes directamente en la clase.
 */
public class MainMenuBar extends JMenuBar {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Atributos de la clase.
	private JMenu ventanaMenu;
	private JMenuItem switchTicketsCocinaFrame;
	private JMenuItem switchTablesFrame;

	private JMenuItem testingMenuItem;

	private JMenu configMenu;
	private JMenuItem configTables;

	private MainWindow parent;

	/*
	 * Constructor de la clase, al cual se le pasa la ventana principal como
	 * parametro.
	 */
	public MainMenuBar(MainWindow parent) {
		this.parent = parent;

		initialize();
		configMenu.setEnabled(false);
		ventanaMenu.setEnabled(false);
		add();
		setListeners();
		
		this.setBackground(new Color(47, 64, 88));
		configMenu.setForeground(new Color(255,255,255));
		ventanaMenu.setForeground(new Color(255,255,255));
	}

	/*
	 * Metodo que incializa cada uno de los componentes que pertenecen a esta clase.
	 */
	private void initialize() {
		configMenu = new JMenu("Configuración");
		configTables = new JMenuItem("Cambiar numero de mesas");

		ventanaMenu = new JMenu("Ventana");
		switchTicketsCocinaFrame = new JMenuItem(MainWindow.COCINAFRAMECARD);
		switchTablesFrame = new JMenuItem(MainWindow.BARRAFRAMECARD);

		testingMenuItem = new JMenuItem("Testing");

	}

	/*
	 * Metodo que añade los menuItem a los diferentes menus del JMenuBar y añade
	 * esos mismos menus al JMenuBar
	 */
	private void add() {
		this.add(configMenu);
		configMenu.add(configTables);
		configMenu.add(testingMenuItem);

		this.add(ventanaMenu);
		ventanaMenu.add(switchTicketsCocinaFrame);
		ventanaMenu.add(switchTablesFrame);
	}

	/*
	 * Metodo que añade los listeners a los menuItems.
	 */
	private void setListeners() {

		// Añadimos el listener al menuItem de configurar numero de mesas.
		configTables.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// En cuanto se clique, abrimos el dialogo de configuracion de mesas
				new ConfigTablesDialog(new CountDownLatch(1)).setVisible(true);
				parent.resetUIForUpdates();
				parent.getTablesFrame().onTableNumChangeCreateButtons();
			}
		});

		// El listener del menuItem de la ventana de cocina
		switchTicketsCocinaFrame.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				CardLayout cLayout = (CardLayout) parent.getMainPanel().getLayout();
				cLayout.show(parent.getMainPanel(), MainWindow.COCINAFRAMECARD);
			}

		});

		// El listener del menuItem de la ventana de barra
		switchTablesFrame.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				CardLayout cLayout = (CardLayout) parent.getMainPanel().getLayout();
				cLayout.show(parent.getMainPanel(), MainWindow.BARRAFRAMECARD);
			}
		});

		testingMenuItem.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				EventQueue.invokeLater(new Runnable() {

					public void run() {
						Ticket t = tools.Search.searchForTicket(2);
						if(t == null)
							t = new Ticket(2);
						t.setCamarero(new Camarero(3, "Erik Cabezuelo", "ecabezue"));
						t.setDatetime(new Timestamp(System.currentTimeMillis()));
						//t.getProductosComanda().remove(2);
						Product p;
						p = new Product(1, "Coca Cola", "a", "1.60", 5, null, null);
						t.getProductosComanda().add(p);
						p = new Product(14, "Ensalada del mar", "a", "6.95", 10, null, null);
						t.getProductosComanda().add(p);
						p = new Product(4, "Cerveza", "a", "1.70", 8, null, null);
						t.getProductosComanda().add(p);
						try {
							Main.sendTicket(t);
						} catch (XPathExpressionException | TransformerException | ParserConfigurationException
								| SAXException | IOException | SQLException e) {
							e.printStackTrace();
						}
						parent.resetUIForUpdates();
					}
				});
			}

		});

	}

	/*
	 * Metodo que devuelve el JMenuItem de la ventana de barra
	 */
	public JMenuItem getSwitchTablesFrame() {
		return switchTablesFrame;
	}

	public JMenu getVentanaMenu() {
		return ventanaMenu;
	}

	public void setVentanaMenu(JMenu ventanaMenu) {
		this.ventanaMenu = ventanaMenu;
	}

	public JMenu getConfigMenu() {
		return configMenu;
	}

	public void setConfigMenu(JMenu configMenu) {
		this.configMenu = configMenu;
	}
	
	
}