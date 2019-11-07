package ui;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MainMenuBar extends JMenuBar{
	private JMenu ventanaMenu;
	private JMenuItem switchMainFrame;
	private JMenuItem switchTablesFrame;
	
	private JMenu configMenu;
	private JMenuItem configTables;
	
	private MainWindow parent;
	
	
	public MainMenuBar(MainWindow parent) {
		this.parent = parent;
		initialize();
		add();
		setListeners();
	}
	
	private void initialize() {
		configMenu = new JMenu("Configuración");
		configTables = new JMenuItem("Cambiar numero de mesas");
		
		ventanaMenu = new JMenu("Ventana");
		switchMainFrame = new JMenuItem(MainWindow.MAINFRAMECARD);
		switchTablesFrame = new JMenuItem(MainWindow.TABLESFRAMECARD);
	}
	
	private void add() {
		this.add(configMenu);
		configMenu.add(configTables);
		
		this.add(ventanaMenu);
		ventanaMenu.add(switchMainFrame);
		ventanaMenu.add(switchTablesFrame);
	}

	private void setListeners() {
		
		configTables.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				new configTablesDialog(parent).setVisible(true);
			}
		});
		
		switchMainFrame.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				CardLayout cLayout = (CardLayout) parent.getContentPane().getLayout();
				cLayout.show(parent.getContentPane(), MainWindow.MAINFRAMECARD);
			}

		});

		switchTablesFrame.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				CardLayout cLayout = (CardLayout) parent.getContentPane().getLayout();
				cLayout.show(parent.getContentPane(), MainWindow.TABLESFRAMECARD);
			}
		});
		
	}
	
	public JMenuItem getSwitchTablesFrame() {
		return switchTablesFrame;
	}
}