package ui;

import javax.swing.JInternalFrame;
import javax.swing.JTabbedPane;

import bar.Ticket;

public class TicketsFrame extends JInternalFrame {

	private MainWindow parent;
	private JTabbedPane tabPane;
	
	public TicketsFrame(MainWindow parent) {
		super("Comandes", true, true, true, true);
		this.parent = parent;
		initialize();
		modify();
		add();
	}
	
	public void initialize() {
		tabPane = new JTabbedPane();
	}
	
	public void modify() {
		
	}
	
	public void add() {
		this.add(tabPane);
	}
	
	public void crearComanda(Ticket t) {
		PanelTickets panel = new PanelTickets(parent);
		tabPane.addTab("Mesa " + String.valueOf(t.getTable()), panel);
		
		int tabs = tabPane.getTabCount();
		for(int i = 0; i < tabs; i++) {
			if(tabPane.getTitleAt(i).equals("Mesa " + String.valueOf(t.getTable()))) {
				tabPane.setSelectedIndex(i);
			}
		}
		
		
	}
	
	public boolean searchTab(int numTaula) {
		int tabs = tabPane.getTabCount();
		for(int i = 0; i < tabs; i++) {
			if(tabPane.getTitleAt(i).equals("Mesa " + String.valueOf(numTaula))) {
				tabPane.setSelectedIndex(i);
				return true;
			}
		}
		return false;
	}
	
	

	
}
