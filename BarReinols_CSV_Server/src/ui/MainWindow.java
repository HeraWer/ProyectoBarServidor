package ui;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;

public class MainWindow extends JFrame {

	private TicketsFrame ticketsFrame;
	private TablesFrame tablesFrame;
	private MainMenuBar mainMenu;

	public final static String MAINFRAMECARD = "Pantalla principal";
	public final static String TABLESFRAMECARD = "Pantalla de las mesas";

	public MainWindow() {
		super("Bar Reinols");
		initialize();
		modify();
		add();
		// setListeners();
	}

	public void initialize() {
		ticketsFrame = new TicketsFrame(this);
		tablesFrame = new TablesFrame(this);
		mainMenu = new MainMenuBar(this);

	}

	public void modify() {
		this.setJMenuBar(mainMenu);
		this.getContentPane().setLayout(new CardLayout());
		this.setVisible(true);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize(new Dimension((int) (screen.width * 0.8), (int) (screen.height * 0.8)));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public void add() {
		this.getContentPane().add(ticketsFrame, MAINFRAMECARD);
		this.getContentPane().add(tablesFrame, TABLESFRAMECARD);
	}

	public TablesFrame getTablesFrame() {
		return tablesFrame;
	}

	public TicketsFrame getTicketsFrame() {
		return ticketsFrame;
	}

	public void resetUIForUpdates() {
		tablesFrame.dispose();
		tablesFrame = new TablesFrame(this);
		this.add(tablesFrame, TABLESFRAMECARD);
		mainMenu.getSwitchTablesFrame().doClick();
	}
	
}
