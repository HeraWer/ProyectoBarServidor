package ui;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

import com.example.barreinolds.Main;
import com.example.barreinolds.Ticket;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.io.IOException;
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
	private JScrollPane jScrollTables;
	private JScrollPane jScrollCats;
	private JScrollPane jScrollProducts;
	private TicketBarraPanel ticketBarPanel;
	private JButton table;
	private MainWindow parent;
	private TreeMap<Integer, TicketBarraPanel> ticketsBarra;
	public static int actualTable = 1;
	
	private QuantityPanel quantityPanel;
	
	public static PanelSelectCategoryBarra catsBarra;
	public static PanelSelectProductBarra productsBarra;

	/*
	 * Constructor en el que se crean botones, se inicializa el ArrayList 
	 * de JButtons de las mesas y se le a�ade el layout al Frame.
	 * 
	 * Se le pasa como parametro el JFrame de la ventana principal.
	 */
	public BarraFrame(MainWindow parent) {
		super("Barra", false, false, false, false);
		this.getContentPane().setLayout(new GridBagLayout());
		this.parent = parent;
		aLTables = new ArrayList<JButton>();
		
		try {
			initialize();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		createTicketPanels();
		setConstraints();
		createButtons();
		//windowListeners();
		ticketsCardPanel.setBackground(ColorsClass.LIGHTCOLOR);
		tablesPanel.setBackground(ColorsClass.LIGHTCOLOR);
		
		this.setBorder(null);
	}

	/*
	 * TESTING!
	 */
	public void initialize() throws IOException {
		tablesPanel = new JPanel(new GridLayout(0, 3, 5, 5));
		ticketsBarra = new TreeMap<Integer, TicketBarraPanel>();
		ticketsCardPanel = new JPanel(new CardLayout());
		jScrollTables = new JScrollPane(tablesPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		jScrollTables.setPreferredSize(new Dimension(330, 300));
		jScrollTables.setMinimumSize(new Dimension(330,300));
		
		quantityPanel = new QuantityPanel();
		
		catsBarra = new PanelSelectCategoryBarra();
		jScrollCats = new JScrollPane(catsBarra, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		jScrollCats.setPreferredSize(new Dimension(233, 300));
		jScrollCats.setMinimumSize(new Dimension(233,300));

		productsBarra = new PanelSelectProductBarra();
		jScrollProducts = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		jScrollProducts.setPreferredSize(new Dimension(productsBarra.getWidth(), 300));
		jScrollProducts.setMaximumSize(new Dimension(productsBarra.getWidth(), 300));
		jScrollProducts.setMinimumSize(new Dimension(productsBarra.getWidth(), 300));
		jScrollProducts.setViewportView(productsBarra);
		
		productsBarra.setPreferredSize(new Dimension(jScrollProducts.getWidth(), 2500));
		productsBarra.setMinimumSize(new Dimension(jScrollProducts.getWidth(), 2500));
		productsBarra.setMaximumSize(new Dimension(jScrollProducts.getWidth(), 2500));
		jScrollProducts.setAutoscrolls(true);
		
		
		jScrollProducts.getVerticalScrollBar().setUnitIncrement(16);
		jScrollCats.getVerticalScrollBar().setUnitIncrement(16);
		jScrollTables.getVerticalScrollBar().setUnitIncrement(16);
		
		
		
	}
	
	/*
	 * Metodo que crea los botones dependiendo del n�mero de mesas que haya
	 * en el fichero config.xml.
	 */
	public void createButtons() {
		aLTables = new ArrayList<JButton>();
		for (int i = 1; i <= Main.numTaules; i++) {
			table = new JButton("Mesa " + i);
			setButtonConfig(table);
			aLTables.add(table);
			tablesPanel.add(aLTables.get(i - 1));
			this.setListeners(table);
		}

	}
	
	public void onTableNumChangeCreateButtons() {
		if(aLTables.size() < Main.numTaules) {
			for(int i = aLTables.size() + 1; i <= Main.numTaules; i++) {
				table = new JButton("Mesa " + i);
				setButtonConfig(table);
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
	
	public void setButtonConfig(JButton button) {
		button.setPreferredSize(new Dimension(100, 100));
		button.setMinimumSize(new Dimension(100, 100));
		button.setMaximumSize(new Dimension(100, 100));
		table.setBackground(ColorsClass.DARKBLUE);
		table.setForeground(ColorsClass.WHITE);	
	}
	
	public void setConstraints() {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10,10,10,10);
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weighty = 0.6;
		gbc.weightx = 1;
		gbc.anchor = GridBagConstraints.NORTHWEST;
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 3;
		this.getContentPane().add(ticketsCardPanel, gbc);
		
		gbc.fill = GridBagConstraints.VERTICAL;
		gbc.anchor = GridBagConstraints.EAST;
		gbc.gridx = 3;
		gbc.weightx = 0;
		gbc.gridwidth = 1;
		this.getContentPane().add(quantityPanel, gbc);
		
		gbc.gridx = 0;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.weighty = 0;
		gbc.gridy = 1;
		this.getContentPane().add(jScrollCats, gbc);
		
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.EAST;
		gbc.gridx = 3;
		gbc.gridy = 1;
		this.getContentPane().add(jScrollTables, gbc);
		
		gbc.gridx = 1;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.gridwidth = 2;
		this.getContentPane().add(jScrollProducts, gbc);
		

	}

	/*
	 * Metodo que a�ade los listeners a los botones de las mesas.
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
		actualTable = numMesa;
	}
	
	public void onNumTablesChange() {
		if (Main.numTaules <= ticketsBarra.size()) {
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
	
	public void checkBusyTables() {
		Ticket t;
		for(JButton b : aLTables) {
			t = tools.Search.searchForTicket(Integer.parseInt(b.getText().split(" ")[1]));
			if(t != null) {
				if(t.getProductosComanda().size() != 0) {
					b.setBackground(new Color(128,0,0));
				} else {
					tools.Search.deleteTicket(t.getMesa());
					b.setBackground(new Color(47,64,88));
				}
			} else {
				b.setBackground(new Color(47,64,88));
			}
		}
	}

	public TicketBarraPanel getTicketBarPanel() {
		return ticketBarPanel;
	}

	public void setTicketBarPanel(TicketBarraPanel ticketBarPanel) {
		this.ticketBarPanel = ticketBarPanel;
	}
	
	public JPanel getTablesPanel() {
		return tablesPanel;
	}
	
	public void windowListeners() {
		jScrollProducts.addComponentListener(new ComponentAdapter() {
			
			public void componentResized(ComponentEvent e) {
				jScrollProducts.getViewport().setPreferredSize(new Dimension(jScrollProducts.getWidth(), productsBarra.getHeight()));
				jScrollProducts.getViewport().setMinimumSize(new Dimension(jScrollProducts.getWidth(), productsBarra.getHeight()));
				jScrollProducts.getViewport().setMaximumSize(new Dimension(jScrollProducts.getWidth(), productsBarra.getHeight()));
				
				productsBarra.setPreferredSize(new Dimension(jScrollProducts.getWidth(), 0));
				productsBarra.setMinimumSize(new Dimension(jScrollProducts.getWidth(), 0));
				productsBarra.setMaximumSize(new Dimension(jScrollProducts.getWidth(), 0));
//				
//				jScrollProducts.setPreferredSize(new Dimension(jScrollProducts.getWidth(), 300));
//				jScrollProducts.setMaximumSize(new Dimension(productsBarra.getWidth(), 300));
//				jScrollProducts.setMinimumSize(new Dimension(productsBarra.getWidth(), 300));
			}

		});
			
	}

	
}
