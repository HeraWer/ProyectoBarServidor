package ui;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;


import com.example.barreinolds.Main;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.TreeMap;
import javax.swing.JButton;

/*
 * Clase que extiende de un JInternalFrame, permitiendo
 * crear un JInternalFrame personalizado.
 * 
 * En este caso, el Frame creado es el de barra.
 */
public class BarraFrame extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Atributos de la clase
	private ArrayList<JButton> aLTables;
	private JPanel ticketsCardPanel;
	private JPanel tablesPanel;
	private TicketBarraPanel ticketBarPanel;
	private JButton table;
	private MainWindow parent;
	private TreeMap<Integer, TicketBarraPanel> ticketsBarra; 

	/*
	 * Constructor en el que se crean botones, se inicializa el ArrayList 
	 * de JButtons de las mesas y se le añade el layout al Frame.
	 * 
	 * Se le pasa como parametro el JFrame de la ventana principal.
	 */
	public BarraFrame(MainWindow parent) {
		super("Barra", false, false, false, false);
		this.setLayout(new GridBagLayout());
		this.parent = parent;
		aLTables = new ArrayList<JButton>();
		initialize();
		createButtons();
		createTicketPanels();
		setConstraints();

	}

	/*
	 * TESTING!
	 */
	public void initialize() {
		tablesPanel = new JPanel(new GridLayout(0, 5, 5, 5));
		ticketsBarra = new TreeMap<Integer, TicketBarraPanel>();
		ticketsCardPanel = new JPanel(new CardLayout());
	}

	/*
	 * Metodo que crea los botones dependiendo del número de mesas que haya
	 * en el fichero config.xml.
	 */
	public void createButtons() {
		aLTables = new ArrayList<JButton>();
		for (int i = 1; i <= Main.numTaules; i++) {
			table = new JButton("Mesa " + i);
			table.setPreferredSize(new Dimension(100, 100));
			aLTables.add(table);
			tablesPanel.add(aLTables.get(i - 1));
			this.setListeners(table);
		}

	}
	
	public void onTableNumChangeCreateButtons() {
		if(aLTables.size() < Main.numTaules) {
			for(int i = aLTables.size() + 1; i <= Main.numTaules; i++) {
				table = new JButton("Mesa " + i);
				//table.setPreferredSize(new Dimension(100, 100));
				aLTables.add(table);
				tablesPanel.add(aLTables.get(i - 1));
				this.setListeners(table);
			}
		}else {
			for(int i = aLTables.size() - 1; i >= Main.numTaules; i--) {
				tablesPanel.remove(aLTables.get(i));
				aLTables.remove(i);
			}
		}
		onNumTablesChange();
		revalidate();
	}
	
	public void setConstraints() {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(20,20,20,20);
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.anchor = GridBagConstraints.NORTHWEST;
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		this.add(ticketsCardPanel, gbc);

		gbc.fill = GridBagConstraints.NONE;
		gbc.gridwidth = 1;
		gbc.gridx = 2;
		gbc.gridy = 1;
		gbc.weightx = 0;
		gbc.weighty = 0;
		this.add(tablesPanel, gbc);
	}

	/*
	 * Metodo que añade los listeners a los botones de las mesas.
	 */
	public void setListeners(JButton button) {
		button.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
				BarraFrame.this.showCard(Integer.parseInt(button.getText().split(" ")[1]));

			}
		});
	}
	
	public void createTicketPanels() {
		for (int i = 1; i <= Main.numTaules; i++) {
			ticketBarPanel = new TicketBarraPanel(i, parent);
			ticketsBarra.put(i, ticketBarPanel);
			ticketsCardPanel.add(ticketBarPanel, "Mesa" + i);
		}
	}
	
	public void showCard(int numMesa) {
		CardLayout cLayout = (CardLayout) ticketsCardPanel.getLayout();
		cLayout.show(ticketsCardPanel, "Mesa" + numMesa);
		//actualTable = numMesa;
	}
	
	public void onNumTablesChange() {
		if (Main.numTaules < ticketsBarra.size()) {
			for (int i = Main.numTaules + 1; i <= ticketsBarra.size(); i++) {

				ticketsCardPanel.getLayout().removeLayoutComponent(ticketsBarra.get(i));
				ticketsBarra.remove(i);

			}
		} else {
			for (int i = ticketsBarra.size() + 1; i <= Main.numTaules; i++) {
				ticketBarPanel = new TicketBarraPanel(i, parent);
				ticketsBarra.put(i, ticketBarPanel);
				ticketsCardPanel.add(ticketBarPanel, "Mesa" + i);

			}
		}
	}
	
	public void setTicketOnTable(int numMesa) {
		if(ticketsBarra.get(numMesa) != null)
		ticketsBarra.get(numMesa).addTicketToTable();
	}

	public TicketBarraPanel getTicketBarPanel() {
		return ticketBarPanel;
	}

	public void setTicketBarPanel(TicketBarraPanel ticketBarPanel) {
		this.ticketBarPanel = ticketBarPanel;
	}

	
}
