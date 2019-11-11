package ui;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;

/*
 * Metodo que extiende de JFrame.
 * Esta clase es la ventana principal de la aplicación.
 */
public class MainWindow extends JFrame {

	// Atributos de la clase.
	private TicketsFrame ticketsFrame;
	private TablesFrame tablesFrame;
	private MainMenuBar mainMenu;

	public final static String MAINFRAMECARD = "Pantalla principal";
	public final static String TABLESFRAMECARD = "Pantalla de las mesas";

	/*
	 * Constructor del Frame principal.
	 */
	public MainWindow() {
		super("Bar Reinols");
		initialize();
		modify();
		add();
		// setListeners();
	}

	/*
	 * Metodo que incializa los componentes de esta clase.
	 */
	public void initialize() {
		ticketsFrame = new TicketsFrame(this);
		tablesFrame = new TablesFrame(this);
		mainMenu = new MainMenuBar(this);

	}

	/*
	 * Metodo que configura y modifica los componentes que 
	 * pertenezcan a esta clase.
	 */
	public void modify() {
		// Le añadimos el JMenuBar
		this.setJMenuBar(mainMenu);
		
		// Le añadimos el CardLayout al ContentPane del Frame principal.
		this.getContentPane().setLayout(new CardLayout());
		
		// Hacemos la ventana visible.
		this.setVisible(true);
		
		// Le añadimos un tamaño a la ventana. Sera proporcional dependiendo
		// de la resolucion de pantalla; cogiendo un 80% de esta.
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize(new Dimension((int) (screen.width * 0.8), (int) (screen.height * 0.8)));
		
		// En el momento que se cierre la ventana, se cierra la aplicacion
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	/*
	 * Metodo que añade componentes a la instancia de esta clase
	 * y a los componentes que esten dentro de esta clase.
	 */
	public void add() {
		this.getContentPane().add(ticketsFrame, MAINFRAMECARD);
		this.getContentPane().add(tablesFrame, TABLESFRAMECARD);
	}

	/*
	 * Metodo que devuelve el JInternalFrame personalizado de la barra.
	 */
	public TablesFrame getTablesFrame() {
		return tablesFrame;
	}

	/*
	 * Metodo que devuelve el JInternalFrame personalizado de los tickets de 
	 * cocina.
	 */
	public TicketsFrame getTicketsFrame() {
		return ticketsFrame;
	}

	/*
	 * Metodo que resetea la interfaz de usuario para volver a mostrar los componentes
	 * despues de cambios de la configuracion.
	 */
	public void resetUIForUpdates() {
		tablesFrame.dispose();
		tablesFrame = new TablesFrame(this);
		this.add(tablesFrame, TABLESFRAMECARD);
		mainMenu.getSwitchTablesFrame().doClick();
	}
	
}
