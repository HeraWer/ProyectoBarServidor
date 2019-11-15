package ui;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/*
 * Clase principal que desciende de un JMenuBar para 
 * poder crear un JMenuBar personalizado y añadirle 
 * los componentes directamente en la clase.
 */
public class MainMenuBar extends JMenuBar{
	
	// Atributos de la clase.
	private JMenu ventanaMenu;
	private JMenuItem switchTicketsCocinaFrame;
	private JMenuItem switchTablesFrame;
	
	private JMenu configMenu;
	private JMenuItem configTables;
	
	private MainWindow parent;
	
	/*
	 * Constructor de la clase, al cual se le pasa
	 * la ventana principal como parametro.
	 */
	public MainMenuBar(MainWindow parent) {
		this.parent = parent;
		
		initialize();
		add();
		setListeners();
	}
	
	/*
	 * Metodo que incializa cada uno de los componentes que pertenecen
	 * a esta clase.
	 */
	private void initialize() {
		configMenu = new JMenu("Configuración");
		configTables = new JMenuItem("Cambiar numero de mesas");
		
		ventanaMenu = new JMenu("Ventana");
		switchTicketsCocinaFrame = new JMenuItem(MainWindow.MAINFRAMECARD);
		switchTablesFrame = new JMenuItem(MainWindow.TABLESFRAMECARD);
	}
	
	/*
	 * Metodo que añade los menuItem a los diferentes menus del JMenuBar
	 * y añade esos mismos menus al JMenuBar
	 */
	private void add() {
		this.add(configMenu);
		configMenu.add(configTables);
		
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
				new configTablesDialog(parent).setVisible(true);
			}
		});
		
		// El listener del menuItem de la ventana de cocina
		switchTicketsCocinaFrame.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				CardLayout cLayout = (CardLayout) parent.getContentPane().getLayout();
				cLayout.show(parent.getContentPane(), MainWindow.MAINFRAMECARD);
			}

		});

		// El listener del menuItem de la ventana de barra
		switchTablesFrame.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				CardLayout cLayout = (CardLayout) parent.getContentPane().getLayout();
				cLayout.show(parent.getContentPane(), MainWindow.TABLESFRAMECARD);
			}
		});
		
	}
	
	/*
	 * Metodo que devuelve el JMenuItem de la ventana de barra
	 */
	public JMenuItem getSwitchTablesFrame() {
		return switchTablesFrame;
	}
}