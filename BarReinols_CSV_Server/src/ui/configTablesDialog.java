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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;

import org.xml.sax.SAXException;

import xmlManager.XMLFileManager;

public class configTablesDialog extends JDialog {
	
	private JPanel mainPanel;
	private JLabel info;
	private JTextField setTables;
	private JButton okButton;
	private JButton cancelButton;
	private JFrame mainWindow;
	
	public configTablesDialog(JFrame mainWindow) {
		super(mainWindow, true);
		this.mainWindow = mainWindow;
		this.setSize(300, 150);
		initialize();
		modify();
		prepareLayout();
		setListeners();
	}
	
	private void initialize() {
		mainPanel = new JPanel();
		info = new JLabel("Introduce el número de mesas:");
		setTables = new JTextField();
		okButton = new JButton("Aceptar");
		cancelButton = new JButton("Cancelar");
	}
	
	private void modify() {
		mainPanel.setLayout(new GridBagLayout());
		this.setResizable(false);
		this.setLocationRelativeTo(mainWindow);
	}
	
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
	
	private void setListeners() {
		okButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if(!Validations.checkParseInt(setTables.getText())) {
					JOptionPane.showMessageDialog(configTablesDialog.this, "ERROR: El texto introducido tiene que ser un entero!", "Error en el valor", JOptionPane.ERROR_MESSAGE);
				}else {
					
					try {
						XMLFileManager xfm = new XMLFileManager();
						xfm.writeInElement("//mesas/cantidad", setTables.getText());
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
					configTablesDialog.this.dispose();
				}
			}
		});
		
		cancelButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				configTablesDialog.this.dispose();
			}
		});
	}
	
	

}
