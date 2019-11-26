	package ui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.CountDownLatch;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;

import org.xml.sax.SAXException;

import com.example.barreinolds.Main;
import com.example.barreinolds.Ticket;

/*
 * Metodo que extiende de JFrame.
 * Esta clase es la ventana principal de la aplicación.
 */
public class MainWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Atributos de la clase.
	private LoginFrame loginFrame;
	private CocinaFrame cocinaFrame;
	private BarraFrame barraFrame;
	private MainMenuBar mainMenu;
	private JPanel mainPanel;
	public static JLabel statusLabel;

	public final static String LOGINFRAMECARD = "Login";
	public final static String COCINAFRAMECARD = "Cocina";
	public final static String BARRAFRAMECARD = "Barra";

	/*
	 * Constructor del Frame principal.
	 */
	public MainWindow() {
		super("Bar Reinols");
		initialize();
		modify();
		
		
		add();
		Main.latch.countDown();
		
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		MediaTracker tracker = new MediaTracker(this);
		Image image = toolkit.getImage("res/init_loading_logo.png");
		this.setIconImage(image);
		// setListeners();
	}

	/*
	 * Metodo que incializa los componentes de esta clase.
	 */
	public void initialize() {
		cocinaFrame = new CocinaFrame(this);
		barraFrame = new BarraFrame(this);
		loginFrame = new LoginFrame();
		mainMenu = new MainMenuBar(this);
		mainPanel = new JPanel(new CardLayout());
		statusLabel = new JLabel("");
	}

	/*
	 * Metodo que configura y modifica los componentes que pertenezcan a esta clase.
	 */
	public void modify() {
		// Le añadimos el JMenuBar
		this.setJMenuBar(mainMenu);

		// Le añadimos el CardLayout al ContentPane del Frame principal.
		this.getContentPane().setLayout(new BorderLayout());

		// Hacemos la ventana visible.

		Font f = new Font("Verdana", Font.BOLD, 14);
		statusLabel.setFont(f);
		statusLabel.setAlignmentX(LEFT_ALIGNMENT);
		
		// Le añadimos un tamaño a la ventana. Sera proporcional dependiendo
		// de la resolucion de pantalla; cogiendo un 80% de esta.
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize(new Dimension((int) (screen.width * 0.8), (int) (screen.height * 0.8)));

		// En el momento que se cierre la ventana, se cierra la aplicacion
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setBackground(new Color(36, 35, 32));
		cocinaFrame.setBackground(new Color(68, 72, 82));
		loginFrame.setBackground(new Color(68, 72, 82));
		barraFrame.setBackground(new Color(68, 72, 82));
	}

	/*
	 * Metodo que añade componentes a la instancia de esta clase y a los componentes
	 * que esten dentro de esta clase.
	 */
	public void add() {
		this.mainPanel.add(loginFrame, LOGINFRAMECARD);
		this.mainPanel.add(cocinaFrame, COCINAFRAMECARD);
		this.mainPanel.add(barraFrame, BARRAFRAMECARD);
		this.getContentPane().add(mainPanel, BorderLayout.CENTER);
		this.getContentPane().add(statusLabel, BorderLayout.SOUTH);
	}
	
	public void loadTickets() {
		for(Ticket t : Main.getTickets()) {
			if(t.getMesa() < Main.numTaules)
				try {
					Main.sendTicket(t);
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		Main.latch.countDown();
	}

	/*
	 * Metodo que devuelve el JInternalFrame personalizado de la barra.
	 */
	public BarraFrame getTablesFrame() {
		return barraFrame;
	}

	/*
	 * Metodo que devuelve el JInternalFrame personalizado de los tickets de cocina.
	 */
	public CocinaFrame getTicketsFrame() {
		return cocinaFrame;
	}

	/*
	 * Metodo que resetea la interfaz de usuario para volver a mostrar los
	 * componentes despues de cambios de la configuracion.
	 */
	public void resetUIForUpdates() {
		cocinaFrame.repaintFrame();
		barraFrame.checkBusyTables();
		
	}
	
	public JPanel getMainPanel() {
		return mainPanel;
	}

}
