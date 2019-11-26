package ui;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;


import bar.Main;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;

/*
 * Clase que extiende de un JInternalFrame, permitiendo
 * crear un JInternalFrame personalizado.
 * 
 * En este caso, el Frame creado es el de barra.
 */
public class TablesFrame extends JInternalFrame {

	// Atributos de la clase
	private ArrayList<JButton> aLTables;
	private JPanel tablesPanel;
	private JPanel ticketBarPanel;
	private JButton table;
	private MainWindow parent;

	/*
	 * Constructor en el que se crean botones, se inicializa el ArrayList 
	 * de JButtons de las mesas y se le añade el layout al Frame.
	 * 
	 * Se le pasa como parametro el JFrame de la ventana principal.
	 */
	public TablesFrame(MainWindow parent) {
		this.setLayout(new GridBagLayout());
		this.parent = parent;
		aLTables = new ArrayList<JButton>();
		initialize();
		createButtons();
		setConstraints();

		this.pack();
	}

	/*
	 * TESTING!
	 */
	public void initialize() {
		tablesPanel = new JPanel(new GridLayout(0, 4, 2, 2));
		ticketBarPanel = new JPanel(new FlowLayout());
	}

	/*
	 * Metodo que crea los botones dependiendo del número de mesas que haya
	 * en el fichero config.xml.
	 */
	public void createButtons() {
		tablesPanel.removeAll();
		tablesPanel.revalidate();
		aLTables = new ArrayList<JButton>();
		for (int i = 0; i < Main.numTaules; i++) {
			table = new JButton("Mesa " + String.valueOf(i + 1));
			table.setPreferredSize(new Dimension(100, 100));
			aLTables.add(table);
			tablesPanel.add(aLTables.get(i));
			this.setListeners(table);
		}
		tablesPanel.revalidate();

	}
	
	public void setConstraints() {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);
		
		gbc.weightx = 1;
		gbc.weighty = 1;
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		this.add(ticketBarPanel, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 1;
		
		this.add(tablesPanel, gbc);
	}

	/*
	 * Metodo que añade los listeners a los botones de las mesas.
	 */
	public void setListeners(JButton button) {
		button.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				/*int numTaula = Integer.parseInt(button.getText().split(" ")[1]);
				ArrayList<bar.Ticket> tickets = bar.Main.getTickets();
				TicketsFrame tf = parent.getTicketsFrame();
				if (!tf.searchTab(numTaula)) {
					Ticket t = new Ticket(numTaula);
					tf.crearComanda(t);
					tickets.add(new Ticket(numTaula));
				}

				CardLayout cLayout = (CardLayout) parent.getContentPane().getLayout();
				cLayout.show(parent.getContentPane(), MainWindow.MAINFRAMECARD);*/
			}
		});
	}

}
