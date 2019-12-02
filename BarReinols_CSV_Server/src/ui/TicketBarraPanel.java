package ui;

import java.awt.Color;
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

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
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


	public static float totalIVA;
	
	private String[] titlesTable = { "ID", "Nombre", "Precio", "Cantidad", "Total" };
	
	private CambioWindow cambioWindow;

	public TicketBarraPanel(int numMesa, MainWindow parent) {
		super(new GridBagLayout());
		this.parent = parent;
		this.numMesa = numMesa;
		initialize();
		modify();
		prepareGridBagLayout();
		setListeners();

		this.setBackground(new Color(68, 72, 82));

		infoPanel.setBackground(new Color(68, 72, 82));

		jScroll.getViewport().setBackground(new Color(36, 35, 32));

		ticketTable.getTableHeader().setBackground(ColorsClass.DARKBLUE);
		ticketTable.getTableHeader().setForeground(ColorsClass.WHITE);

		infoNumMesa.setForeground(ColorsClass.WHITE);
		totalSinIva.setForeground(ColorsClass.WHITE);
		totalConIva.setForeground(ColorsClass.WHITE);

		cobrarButton.setBackground(ColorsClass.DARKBLUE);
		cobrarButton.setForeground(ColorsClass.WHITE);
	}

	public void initialize() {
		infoPanel = new JPanel();
		infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));

		infoNumMesa = new JLabel("Mesa " + numMesa);
		infoNumMesa.setAlignmentX(JLabel.CENTER_ALIGNMENT);

		totalSinIva = new JLabel("Total s/IVA: ");
		totalSinIva.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		
		totalConIva = new JLabel("Total c/IVA: ");
		totalConIva.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		
		cobrarButton = new JButton("Cobrar mesa");

		tableModel = new DefaultTableModel();

		selectionModel = new DefaultListSelectionModel();

		ticketTable = new JTable(tableModel);

		jScroll = new JScrollPane(ticketTable);
		
		cambioWindow = new CambioWindow();
		
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
		
		infoPanel.add(Box.createRigidArea(new Dimension(0, 30)));
		infoPanel.add(cambioWindow);

		cobrarButton.setPreferredSize(new Dimension(200, 80));
	}

	public void updateLabels() {

		Ticket t = tools.Search.searchForTicket(numMesa);
		if (t != null && t.getProductosComanda().size() != 0) {
			float totalTicket = 0;
			totalTicket = getTotalTicket(t);
			totalSinIva.setText(TOTALSINIVA + String.valueOf(totalTicket) + " €");
			totalIVA = tools.NumberFormat.round(totalTicket + (totalTicket * 0.1f));
			totalConIva.setText(
					TOTALCONIVA + String.valueOf(totalIVA) + " €");
		} else {
			totalSinIva.setText(TOTALSINIVA);
			totalConIva.setText(TOTALCONIVA);
		}
	}

	public void prepareGridBagLayout() {
		GridBagConstraints gbc = new GridBagConstraints();

		gbc.weighty = 0.7;
		gbc.weightx = 0.8;
		gbc.gridheight = 2;
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.fill = GridBagConstraints.BOTH;
		this.add(jScroll, gbc);

		gbc.gridheight = 1;
		gbc.weightx = 0.2;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.weighty = 0;
		gbc.gridy = 0;
		gbc.gridx = 1;
		this.add(infoPanel, gbc);

		gbc.gridy = 2;
		gbc.weighty = 0.3;
		gbc.ipadx = 10;
		gbc.ipady = 10;
		gbc.gridx = 0;
		gbc.anchor = GridBagConstraints.CENTER;
		this.add(cobrarButton, gbc);
	}

	public void setListeners() {
		cobrarButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Ticket t = tools.Search.searchForTicket(numMesa);
				if (t != null) {
					try {
						bbddManager.FacturaDBManager.insertFactura(t, getTotalTicket(t));
						bbddManager.TicketDBManager.deleteComanda(numMesa);
					} catch (SQLException e2) {
						e2.printStackTrace();
					}

					parent.getTicketsFrame().clearTicketCocina(t.getMesa());
					Main.getTickets().remove(t);
					
					cambioWindow.getEntregadoCliente().setText("0");
					cambioWindow.getDevolverCliente().setText("0");

					tools.UIMethods.clearTable(ticketTable);
					Main.m.getTablesFrame().checkBusyTables();
					
					updateLabels();
					
					Main.m.resetUIForUpdates();

				}

			}
		});
	}

	public void addTicketToTable() {
		Ticket t = tools.Search.searchForTicket(numMesa);
		tools.UIMethods.clearTable(ticketTable);
		if (t != null) {
			for (Product p : t.getProductosComanda()) {
				String total = String.valueOf(Float.parseFloat(p.getPrice().replace(',', '.')) * p.getCantidad());
				total = String.valueOf(tools.NumberFormat.round(Float.parseFloat(total)));
				Vector<Object> v = new Vector<Object>();
				v.add(p.getId());
				v.add(p.getName());
				v.add(p.getPrice());
				v.add(String.valueOf(p.getCantidad()));
				v.add(total);
				tableModel.addRow(v);
			}
		}
		this.updateLabels();
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
				tableModel.removeRow(i);
			}

		}
	}

	public void setTableModifiers() {
		ticketTable.setColumnSelectionAllowed(false);
		ticketTable.setCellSelectionEnabled(false);
		ticketTable.setRowSelectionAllowed(true);
		ticketTable.setFillsViewportHeight(true);
		ticketTable.setOpaque(false);
	}

	public void clearTicket() {

		for (int i = ticketTable.getRowCount() - 1; i >= 0; i--) {
			tableModel.removeRow(i);
		}
		tableModel.fireTableDataChanged();

		this.updateLabels();

	}

	public void removeRows() {
		for (int i = ticketTable.getRowCount() - 1; i >= 0; i--) {
			tableModel.removeRow(i);
		}
		ticketTable.repaint();
	}

	public float getTotalTicket(Ticket t) {
		float total = 0;
		if (t != null) {
			for (Product p : t.getProductosComanda()) {
				total = total + (Float.parseFloat(p.getPrice()) * p.getCantidad());
			}
		}
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
