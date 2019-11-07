package ui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MainWindow extends JFrame {

	private JInternalFrame mainFrame;
	private JInternalFrame tablesFrame;
	private MainMenuBar mainMenu;
	

	public final static String MAINFRAMECARD = "Pantalla principal";
	public final static String TABLESFRAMECARD = "Pantalla de las mesas";

	public MainWindow() {
		super("Bar Reinols");
		initialize();
		modify();
		add();
		//setListeners();
	}

	public void initialize() {
		mainFrame = new JInternalFrame();
		tablesFrame = new JInternalFrame();
		mainMenu = new MainMenuBar(this);
		
	}

	public void modify() {
		this.setJMenuBar(mainMenu);
		this.getContentPane().setLayout(new CardLayout());
		mainFrame.setBackground(Color.black);
		tablesFrame.setBackground(Color.red);
		this.setVisible(true);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize(new Dimension((int) (screen.width * 0.8), (int) (screen.height * 0.8)));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public void add() {
		this.getContentPane().add(mainFrame, MAINFRAMECARD);
		this.getContentPane().add(tablesFrame, TABLESFRAMECARD);
	}

	public static void main(String[] args) {
		MainWindow m = new MainWindow();
		new configTablesDialog(m).setVisible(true);
	}
}
