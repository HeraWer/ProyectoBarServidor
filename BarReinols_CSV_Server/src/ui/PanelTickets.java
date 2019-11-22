package ui;

import java.awt.BorderLayout;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.text.SimpleDateFormat;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

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
	CocinaJTable productsTable;
	JPanel jTablePanel;
	JPanel labelsPanel;
	JLabel mesaInfoLabel;
	JLabel camareroLabel;
	JLabel fechaLabel;
	JLabel horaLabel;
	MainWindow parent;
	JScrollPane jScroll;
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

	}

	/*
	 * Metodo que inicializa cada componente.
	 */
	public void initialize() {
		// mesaInfoLabel.setForeground(new Color(255,255,255));
		productsTable = new CocinaJTable(parent);
		jTablePanel = new JPanel(new BorderLayout());
		labelsPanel = new JPanel(new GridLayout(2, 2, 10, 0));
		mesaInfoLabel = new JLabel("MESA " + numMesa);
		camareroLabel = new JLabel("CAMARERO: ");
		fechaLabel = new JLabel("FECHA: ");
		horaLabel = new JLabel("HORA: ");

	}

	/*
	 * Metodo que modifica y configura cada componente.
	 */
	public void prepareGridBagConstraints() {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.weightx = 1;
		gbc.insets = new Insets(10, 0, 0, 0);
		this.add(labelsPanel, gbc);

		gbc.gridy = 1;
		gbc.weighty = 1;
		gbc.fill = GridBagConstraints.BOTH;
		this.add(jTablePanel, gbc);
	}

	/*
	 * Metodo que añade los componentes dentro de la instancia de esta clase o
	 * dentro de otro componente dentro de la instancia de esta clase.
	 */
	public void add() {
		jScroll = new JScrollPane(productsTable);
		jTablePanel.add(jScroll, BorderLayout.CENTER);
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

	/*
	 * Metodo que devuelve el JTable de esta clase.
	 */
	public CocinaJTable getProductsTable() {
		return this.productsTable;
	}

}
