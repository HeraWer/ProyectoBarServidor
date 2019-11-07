package ui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;

import org.xml.sax.SAXException;

import xmlManager.XMLFileManager;

public class MainWindow extends JFrame {

	private JInternalFrame mainFrame;
	private TablesFrame tablesFrame;
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
		tablesFrame = new TablesFrame();
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
	
	public TablesFrame getTablesFrame() {
		return tablesFrame;
	}
	
	public void resetTables() {
		tablesFrame.dispose();
		tablesFrame = new TablesFrame();
		this.add(tablesFrame, TABLESFRAMECARD);
		mainMenu.getSwitchTablesFrame().doClick();
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
			while(xml.isElementEquals("//mesas/cantidad", ""))
				new configTablesDialog(m).setVisible(true);
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
	}
}
