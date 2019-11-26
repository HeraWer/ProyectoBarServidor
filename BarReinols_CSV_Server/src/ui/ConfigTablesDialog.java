package ui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.SQLException;
import java.util.concurrent.CountDownLatch;

import tools.Validations;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;



import com.example.barreinolds.Main;


/*
 * Clase que crea el dialogo de configuración de mesas.
 * Extiende de JDialog, para crear un dialogo personalizado.
 */
public class ConfigTablesDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Atributos de la clase.
	private JPanel mainPanel;
	private JLabel info;
	private JTextField setTables;
	private JButton okButton;
	private JButton cancelButton;
	private CountDownLatch latch;

	/*
	 * Constructor del componente
	 */
	public ConfigTablesDialog(CountDownLatch latch) {
		super(Main.m, true);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.setSize(300, 150);
		this.latch = latch;
		initialize();
		modify();
		prepareLayout();
		setListeners();
	}

	/*
	 * Metodo que inicializa los diferentes componentes que tiene el dialogo.
	 */
	private void initialize() {
		mainPanel = new JPanel();
		info = new JLabel("Introduce el número de mesas:");
		setTables = new JTextField();
		okButton = new JButton("Aceptar");
		cancelButton = new JButton("Cancelar");
	}

	/*
	 * Metodo que modifica los componentes, cosas como poder cambiarle el tamaño,
	 * darle posicion, o añadirle el layout.
	 */
	private void modify() {
		mainPanel.setLayout(new GridBagLayout());
		this.setResizable(false);
		this.setLocationRelativeTo(Main.m);
	}

	/*
	 * Metodo que prepara el GridBagLayout para mostrar los diferentes componentes
	 * con las grid bag constraints
	 */
	private void prepareLayout() {
		GridBagConstraints gbc = new GridBagConstraints();

		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.weightx = 1;
		gbc.gridwidth = 2;
		gbc.fill = GridBagConstraints.BOTH;

		mainPanel.add(info, gbc);

		gbc.gridy = 1;
		mainPanel.add(setTables, gbc);

		gbc.gridwidth = 1;

		gbc.gridy = 2;
		mainPanel.add(cancelButton, gbc);

		gbc.gridx = 1;
		mainPanel.add(okButton, gbc);

		this.getContentPane().add(mainPanel);
	}

	/*
	 * Metodo que añade los listeners a los botones del dialogo.
	 */
	private void setListeners() {

		/*
		 * Le añadimos un boton al listener, para que escriba el valor dentro del XML de
		 * mesas y vuelva a resetear la UI para mostrar las mesas en su Frame. Ademas
		 * comprueba si el valor introducido es numerico.
		 */
		okButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (!Validations.checkParseInt(setTables.getText())) {
					JOptionPane.showMessageDialog(ConfigTablesDialog.this,
							"¡ERROR: El texto introducido tiene que ser un entero!", "Formato invalido!",
							JOptionPane.ERROR_MESSAGE);
					return;
				} else {
					Main.numTaules = Integer.parseInt(setTables.getText());
					if (Main.numTaules > 0) {
						try {
							bbddManager.InitBDManager.updateTables(Main.numTaules);
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						latch.countDown();
						ConfigTablesDialog.this.dispose();
					} else {
						JOptionPane.showMessageDialog(ConfigTablesDialog.this,
								"¡ERROR: Las mesas deben ser un numero mayor a 0!", "Formato invalido!",
								JOptionPane.ERROR_MESSAGE);
						return;
					}

				}
			}
		});

		/*
		 * Si le damos al boton de cancelar, se cierra el dialogo.
		 */
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (Main.numTaules > 0) {
					latch.countDown();
					ConfigTablesDialog.this.dispose();
				}
				else
					JOptionPane.showMessageDialog(ConfigTablesDialog.this, "¡ERROR: Las mesas no se pueden dejar a 0!",
							"Formato invalido!", JOptionPane.ERROR_MESSAGE);
			}
		});
	}

}
