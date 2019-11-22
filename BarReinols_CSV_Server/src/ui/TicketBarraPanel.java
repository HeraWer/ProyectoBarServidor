package ui;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Vector;

import javax.swing.DefaultListSelectionModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;

import org.xml.sax.SAXException;

import com.example.barreinolds.Main;
import com.example.barreinolds.Product;
import com.example.barreinolds.Ticket;

import xmlManager.XMLTicketManager;

public class TicketBarraPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String TOTALSINIVA = "Total s/IVA: ";
	public static final String TOTALCONIVA = "Total c/IVA: ";

	private JButton cobrarButton;
	private JTable ticketTable;
	private DefaultTableModel tableModel;
	private DefaultListSelectionModel selectionModel;
	private JScrollPane jScroll;

	private JPanel infoPanel;

	private JLabel infoNumMesa;
	private JLabel totalSinIva;
	private JLabel totalConIva;
	private MainWindow parent;
	private int numMesa;

	private String[] titlesTable = { "ID", "Nombre", "Precio", "Cantidad", "Total" };

	public TicketBarraPanel(int numMesa, MainWindow parent) {
		super(new GridBagLayout());
		this.parent = parent;
		this.numMesa = numMesa;
		initialize();
		modify();
		prepareGridBagLayout();
		setListeners();
	}

	public void initialize() {
		infoPanel = new JPanel(new GridLayout(3, 1, 3, 0));

		infoNumMesa = new JLabel("Mesa " + numMesa);

		totalSinIva = new JLabel("Total s/IVA: ");

		totalConIva = new JLabel("Total c/IVA: ");

		cobrarButton = new JButton("Cobrar mesa");

		tableModel = new DefaultTableModel();

		selectionModel = new DefaultListSelectionModel();

		ticketTable = new JTable(tableModel);

		jScroll = new JScrollPane(ticketTable);
	}

	public void modify() {
		Font fMesa = new Font("Verdana", Font.BOLD, 24);
		infoNumMesa.setFont(fMesa);
		Font fInfo = new Font("Verdana", Font.BOLD, 16);
		totalSinIva.setFont(fInfo);
		totalConIva.setFont(fInfo);

		tableModel.setColumnIdentifiers(titlesTable);
		selectionModel.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		ticketTable.setSelectionModel(selectionModel);

		setTableModifiers();

		infoPanel.add(infoNumMesa);
		infoPanel.add(totalSinIva);
		infoPanel.add(totalConIva);

		cobrarButton.setPreferredSize(new Dimension(200, 80));
	}

	public void updateLabels() {

		Ticket t = tools.Search.searchForTicket(numMesa);
		if (t != null) {
			float totalTicket = 0;
			totalTicket = getTotalTicket(t);
			totalSinIva.setText(TOTALSINIVA + String.valueOf(totalTicket) + " €");
			totalConIva.setText(
					TOTALCONIVA + String.valueOf(tools.NumberFormat.round(totalTicket + (totalTicket * 0.1f))) + " €");
		} else {
			totalSinIva.setText(TOTALSINIVA);
			totalConIva.setText(TOTALCONIVA);
		}
	}

	public void prepareGridBagLayout() {
		GridBagConstraints gbc = new GridBagConstraints();

		gbc.weighty = 1;
		gbc.weightx = 0.8;
		gbc.gridheight = 2;
		gbc.insets = new Insets(5, 0, 5, 0);
		gbc.fill = GridBagConstraints.BOTH;
		this.add(jScroll, gbc);

		gbc.weightx = 0.2;
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.NORTH;
		gbc.weighty = 0;
		gbc.gridy = 0;
		gbc.gridx = 1;
		this.add(infoPanel, gbc);

		gbc.gridy = 2;
		gbc.gridx = 0;
		this.add(cobrarButton, gbc);
	}

	public void setListeners() {
		cobrarButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Ticket t = tools.Search.searchForTicket(numMesa);

				try {
					bbddManager.FacturaDBManager.insertFactura(t, getTotalTicket(t));
					bbddManager.TicketDBManager.deleteComanda(numMesa);
				} catch (SQLException e2) {
					e2.printStackTrace();
				}

				parent.getTicketsFrame().clearTicketCocina(t.getMesa());
				Main.getTickets().remove(t);

				clearTicket();

			}
		});
	}

	public void addTicketToTable() {
		Ticket t = tools.Search.searchForTicket(numMesa);
		if (t != null) {
			clearDeleted(t);
			for (Product p : t.getProductosComanda()) {
				String total = String.valueOf(Float.parseFloat(p.getPrice().replace(',', '.')) * p.getCantidad());
				total = String.valueOf(tools.NumberFormat.round(Float.parseFloat(total)));
				int posProduct = tools.Search.checkProductOnTable(p, ticketTable);
				if (posProduct != -1) {
					ticketTable.setValueAt(p.getCantidad(), posProduct, 3);
					ticketTable.setValueAt(total, posProduct, 4);
				} else {
					Vector<Object> v = new Vector<Object>();
					v.add(p.getId());
					v.add(p.getName());
					v.add(p.getPrice());
					v.add(String.valueOf(p.getCantidad()));
					v.add(total);

					EventQueue.invokeLater(new Runnable() {
						public void run() {
							((DefaultTableModel) ticketTable.getModel()).addRow(v);
						}
					});
				}
			}
			this.updateLabels();
		}
	}

	public void clearDeleted(Ticket t) {

		boolean existe = false;
		for (int i = ticketTable.getRowCount() - 1; i >= 0; i--) {
			Object value = ticketTable.getValueAt(i, 0);
			int idAtTable = (Integer) value;
			for (Product p : t.getProductosComanda()) {
				if (p.getId() == idAtTable) {
					existe = true;
				}
			}
			if (!existe) {
				((DefaultTableModel) ticketTable.getModel()).removeRow(i);
			}

		}
	}

	public void setTableModifiers() {
		ticketTable.setColumnSelectionAllowed(false);
		ticketTable.setCellSelectionEnabled(false);
		ticketTable.setRowSelectionAllowed(true);
		ticketTable.setFillsViewportHeight(true);
	}

	public void clearTicket() {

		for (int i = ticketTable.getRowCount() - 1; i >= 0; i--) {
			((DefaultTableModel) ticketTable.getModel()).removeRow(i);
		}
		((DefaultTableModel) ticketTable.getModel()).fireTableDataChanged();

		this.updateLabels();

	}

	public void removeRows() {
		for (int i = ticketTable.getRowCount() - 1; i >= 0; i--) {
			((DefaultTableModel) ticketTable.getModel()).removeRow(i);
		}
		ticketTable.repaint();
	}

	public float getTotalTicket(Ticket t) {
		float total = 0;

		for (Product p : t.getProductosComanda()) {
			total = total + (Float.parseFloat(p.getPrice()) * p.getCantidad());
		}
		/*
		 * for (int i = 0; i < ticketTable.getRowCount(); i++) {
		 * total = total + Float.parseFloat((String) (ticketTable.getValueAt(i, 4)));
		 * }
		 */
		return tools.NumberFormat.round(total);

	}

	public void clearRow(int row) {
		((DefaultTableModel) ticketTable.getModel()).removeRow(row);
	}

	public JButton getCobrarButton() {
		return cobrarButton;
	}

	public void setCobrarButton(JButton cobrarButton) {
		this.cobrarButton = cobrarButton;
	}

	public JTable getTicketTable() {
		return ticketTable;
	}

	public void setTicketTable(JTable ticketTable) {
		this.ticketTable = ticketTable;
	}

	public DefaultTableModel getTableModel() {
		return tableModel;
	}

	public void setTableModel(DefaultTableModel tableModel) {
		this.tableModel = tableModel;
	}

	public DefaultListSelectionModel getSelectionModel() {
		return selectionModel;
	}

	public void setSelectionModel(DefaultListSelectionModel selectionModel) {
		this.selectionModel = selectionModel;
	}

	public JScrollPane getjScroll() {
		return jScroll;
	}

	public void setjScroll(JScrollPane jScroll) {
		this.jScroll = jScroll;
	}

	public String[] getTitlesTable() {
		return titlesTable;
	}

	public void setTitlesTable(String[] titlesTable) {
		this.titlesTable = titlesTable;
	}

}
