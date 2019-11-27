package ui;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import com.example.barreinolds.Ticket;

/*
 * Clase que extiende de un JPanel con el fin de crear un JPanel personalizado.
 * En este caso es el JPanel que contiene la comanda de una mesa en concreto
 * en forma de JTable.
 */
public class PanelTickets extends JPanel {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Atributos de la clase.
	CocinaJTable notServedProductsTable;
	CocinaJTable servedProductsTable;
	// JPanel jTablePanel;
	JPanel labelsPanel;
	JLabel mesaInfoLabel;
	JLabel camareroLabel;
	JLabel fechaLabel;
	JLabel horaLabel;
	MainWindow parent;
	JScrollPane notServedJScroll;
	JScrollPane servedJScroll;
	JButton serveButton;
	JButton returnButton;
	int numMesa;

	/*
	 * Constructor al que se le pasa la JFrame principal.
	 */
	public PanelTickets(MainWindow parent, int numMesa) {
		super(new GridBagLayout());

		this.numMesa = numMesa;
		this.parent = parent;
		initialize();
		add();
		prepareGridBagConstraints();
		setListeners();
		modify();
		this.setBackground(new Color(68, 72, 82));
		labelsPanel.setBackground(new Color(68, 72, 82));

	}

	/*
	 * Metodo que inicializa cada componente.
	 */
	public void initialize() {
		// mesaInfoLabel.setForeground(new Color(255,255,255));
		notServedProductsTable = new CocinaJTable(parent);
		servedProductsTable = new CocinaJTable(parent);
		// jTablePanel = new JPanel(new BorderLayout());
		labelsPanel = new JPanel(new GridLayout(2, 2, 10, 0));
		mesaInfoLabel = new JLabel("MESA " + numMesa);
		camareroLabel = new JLabel("CAMARERO: ");
		fechaLabel = new JLabel("FECHA: ");
		horaLabel = new JLabel("HORA: ");

		serveButton = new JButton("Servir");
		returnButton = new JButton("Retornar");

		notServedJScroll = new JScrollPane(notServedProductsTable);

		servedJScroll = new JScrollPane(servedProductsTable);

	}

	/*
	 * Metodo que modifica y configura cada componente.
	 */
	public void prepareGridBagConstraints() {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.weightx = 1;
		gbc.gridwidth = 3;
		gbc.insets = new Insets(10, 0, 0, 0);
		this.add(labelsPanel, gbc);

		gbc.gridwidth = 1;
		gbc.gridy = 1;
		gbc.gridheight = 2;
		gbc.weighty = 1;
		gbc.fill = GridBagConstraints.BOTH;
		this.add(notServedJScroll, gbc);

		gbc.gridx = 2;
		this.add(servedJScroll, gbc);

		gbc.weightx = 0;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		// gbc.weighty = 0;
		gbc.anchor = GridBagConstraints.SOUTH;
		gbc.gridx = 1;
		this.add(serveButton, gbc);

		gbc.anchor = GridBagConstraints.NORTH;
		gbc.gridy = 2;
		this.add(returnButton, gbc);

	}
	
	public void modify() {
		mesaInfoLabel.setForeground(new Color(255,255,255));
		camareroLabel.setForeground(new Color(255,255,255));
		fechaLabel.setForeground(new Color(255,255,255));
		horaLabel.setForeground(new Color(255,255,255));
		
		servedJScroll.getViewport().setBackground(new Color(36, 35, 32));
		notServedJScroll.getViewport().setBackground(new Color(36, 35, 32));
		
		serveButton.setBackground(new Color(47, 64, 88));
		serveButton.setForeground(new Color(255,255,255));
		
		returnButton.setBackground(new Color(47, 64, 88));
		returnButton.setForeground(new Color(255,255,255));
	}
	

	/*
	 * Metodo que añade los componentes dentro de la instancia de esta clase o
	 * dentro de otro componente dentro de la instancia de esta clase.
	 */
	public void add() {

		// jTablePanel.add(jScroll, BorderLayout.CENTER);
		// this.add(jTablePanel, BorderLayout.CENTER);

		labelsPanel.add(mesaInfoLabel);
		labelsPanel.add(camareroLabel);
		labelsPanel.add(fechaLabel);
		labelsPanel.add(horaLabel);
	}

	public void updateLabels() {
		Ticket t = tools.Search.searchForTicket(numMesa);
		if (t != null) {

			camareroLabel.setText("CAMARERO: " + t.getCamarero().getNombre());
			String dateToDisplay = new SimpleDateFormat("dd/MM/yyyy").format(t.getDatetime());
			String hourToDisplay = new SimpleDateFormat("HH:mm:ss").format(t.getDatetime());
			fechaLabel.setText("FECHA: " + dateToDisplay);
			horaLabel.setText("HORA: " + hourToDisplay);

		} else {
			camareroLabel.setText("Camarero: ");
			fechaLabel.setText("Fecha: ");
			horaLabel.setText("Hora: ");
		}
	}

	public void clearLabels() {
		camareroLabel.setText("Camarero: ");
		fechaLabel.setText("Fecha: ");
		horaLabel.setText("Hora: ");
	}

	public void setListeners() {
		serveButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				try {
					serveProducts();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			
		});
		
		returnButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				try {
					returnProducts();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
	}

	public void serveProducts() throws SQLException {
		Vector<Object> v;
		for (int i = notServedProductsTable.getRowCount() - 1; i >= 0; i--) {
			
			if ((Boolean)notServedProductsTable.getValueAt(i, 5)) {
				v = new Vector<Object>();
				int idProduct = (Integer) notServedProductsTable.getValueAt(i, 0);
				tools.Search.searchForProductOnTicket(tools.Search.searchForTicket(numMesa), idProduct).setServed(true);
				v.add(idProduct);
				v.add(notServedProductsTable.getValueAt(i, 1));
				v.add(notServedProductsTable.getValueAt(i, 2));
				v.add(notServedProductsTable.getValueAt(i, 3));
				v.add(true);
				v.add(true);
				((DefaultTableModel)servedProductsTable.getModel()).addRow(v);
				((DefaultTableModel)notServedProductsTable.getModel()).removeRow(i);
				bbddManager.TicketDBManager.serveProduct(idProduct, PanelTickets.this.numMesa);
			}

		}
	}
	
	public void returnProducts() throws SQLException {
		Vector<Object> v;
		for (int i = servedProductsTable.getRowCount() - 1; i >= 0; i--) {
			
			if (!(Boolean)servedProductsTable.getValueAt(i, 5)) {
				v = new Vector<Object>();
				int idProduct = (Integer) servedProductsTable.getValueAt(i, 0);
				tools.Search.searchForProductOnTicket(tools.Search.searchForTicket(numMesa), idProduct).setServed(false);
				v.add(idProduct);
				v.add(servedProductsTable.getValueAt(i, 1));
				v.add(servedProductsTable.getValueAt(i, 2));
				v.add(servedProductsTable.getValueAt(i, 3));
				v.add(false);
				v.add(false);
				((DefaultTableModel)notServedProductsTable.getModel()).addRow(v);
				((DefaultTableModel)servedProductsTable.getModel()).removeRow(i);
				bbddManager.TicketDBManager.returnProduct(idProduct, PanelTickets.this.numMesa);
			}

		}
	}

	/*
	 * Metodo que devuelve el JTable de esta clase.
	 */
	public CocinaJTable getNotServedProductsTable() {
		return this.notServedProductsTable;
	}
	
	public CocinaJTable getServedProductsTable() {
		return this.servedProductsTable;
	}

}
