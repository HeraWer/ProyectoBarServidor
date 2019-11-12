package ui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import tools.Validations;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;

import org.xml.sax.SAXException;

import bar.Main;
import xmlManager.XMLConfigManager;
import xmlManager.XMLFileManager;

/*
 * Clase que crea el dialogo de configuración de mesas.
 * Extiende de JDialog, para crear un dialogo personalizado.
 */
public class configTablesDialog extends JDialog {
	
	// Atributos de la clase.
	private JPanel mainPanel;
	private JLabel info;
	private JTextField setTables;
	private JButton okButton;
	private JButton cancelButton;
	private MainWindow mainWindow;
	
	/*
	 * Constructor del componente
	 */
	public configTablesDialog(MainWindow mainWindow) {
		super(mainWindow, true);
		this.mainWindow = mainWindow;
		this.setSize(300, 150);
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
	 * Metodo que modifica los componentes, cosas como
	 * poder cambiarle el tamaño, darle posicion, o añadirle
	 * el layout.
	 */
	private void modify() {
		mainPanel.setLayout(new GridBagLayout());
		this.setResizable(false);
		this.setLocationRelativeTo(mainWindow);
	}
	
	/*
	 * Metodo que prepara el GridBagLayout para mostrar
	 * los diferentes componentes con las grid bag constraints
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
		 * Le añadimos un boton al listener, para que escriba el valor dentro del XML de mesas
		 * y vuelva a resetear la UI para mostrar las mesas en su Frame.
		 * Ademas comprueba si el valor introducido es numerico.
		 */
		okButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if(!Validations.checkParseInt(setTables.getText())) {
					JOptionPane.showMessageDialog(configTablesDialog.this, "ERROR: El texto introducido tiene que ser un entero!", "Error en el valor", JOptionPane.ERROR_MESSAGE);
				}else {
					
					try {
						XMLConfigManager xcm = new XMLConfigManager("xml/config.xml");
						xcm.writeInElement("//mesas/cantidad", setTables.getText());
						Main.numTaules = Integer.parseInt(setTables.getText());
					} catch (TransformerException e1) {
						e1.printStackTrace();
					} catch (ParserConfigurationException e1) {
						e1.printStackTrace();
					} catch (SAXException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					} catch (XPathExpressionException e1) {
						e1.printStackTrace();
					}
						
						mainWindow.resetUIForUpdates();
						mainWindow.getTablesFrame().createButtons();
					
					configTablesDialog.this.dispose();
				}
			}
		});
		
		/*
		 * Si le damos al boton de cancelar, se cierra el dialogo.
		 */
		cancelButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				configTablesDialog.this.dispose();
			}
		});
	}
	
	

}
