package ui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.example.barreinolds.Main;

public class CambioWindow extends JPanel {
	
	private JPanel panelButtons;
	private JTextField entregadoCliente;
	private JTextField devolverCliente;
	
	private JLabel entregadoLabel;
	private JLabel devolverLabel;
	
	private JButton aceptar;
	private float totalIva;
	
	public CambioWindow(float totalIva) {
		super();
		this.totalIva = totalIva;
		this.setLayout(new GridBagLayout());
		
		panelButtons = new JPanel(new GridLayout(0, 3, 5, 5));
		panelButtons.setBackground(ColorsClass.LIGHTCOLOR);
		
		entregadoCliente = new JTextField("0");
		entregadoCliente.setPreferredSize(new Dimension(0, 30));
		devolverCliente = new JTextField("0");
		devolverCliente.setPreferredSize(new Dimension(0, 30));
		devolverCliente.setEditable(false);
		
		entregadoLabel = new JLabel("Entregado por el cliente: ");
		entregadoLabel.setFont(new Font("Verdana", Font.BOLD, 14));
		entregadoLabel.setForeground(ColorsClass.WHITE);
		
		devolverLabel = new JLabel("A devolver al cliente: ");
		devolverLabel.setFont(new Font("Verdana", Font.BOLD, 14));
		devolverLabel.setForeground(ColorsClass.WHITE);
		
		createButtons();
		
		aceptar = new JButton("Calcular cambio");
		aceptar.setBackground(ColorsClass.DARKBLUE);
		aceptar.setForeground(ColorsClass.WHITE);
		aceptar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if(tools.Validations.checkParseInt(entregadoCliente.getText().replace(',', '.'))) {
					float aDevolver = Float.parseFloat(entregadoCliente.getText().replace(',', '.')) - CambioWindow.this.totalIva;
					devolverCliente.setText(String.valueOf(aDevolver));
				}else {
					JOptionPane.showMessageDialog(Main.m, "Debe ser un numero!", "Error de formato!", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		prepareGridBagLayout();
		
		this.setBackground(ColorsClass.LIGHTCOLOR);
		
	}
	
	private void createButtons() {
		JButton number;
		JButton coma = new JButton(",");
		JButton delete = new JButton("Del.");
		for(int i = 9; i >= 0; i--) {
			if(i == 0) {
				setOthersConfig(coma);
				panelButtons.add(coma);
			}
			number = new JButton(String.valueOf(i));
			setNumberConfigs(number);
			panelButtons.add(number);
			if(i == 0) {
				setOthersConfig(delete);
				panelButtons.add(delete);
			}
		}
	}
	
	private void setNumberConfigs(JButton b) {
		b.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if(entregadoCliente.getText().equals("0"))
					entregadoCliente.setText(b.getText());
				else
					entregadoCliente.setText(entregadoCliente.getText() + b.getText());
			}
		});
		
		b.setPreferredSize(new Dimension(75, 75));
		b.setBackground(ColorsClass.DARKBLUE);
		b.setForeground(ColorsClass.WHITE);
	}
	
	private void setOthersConfig(JButton b) {
		b.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(b.getText().equals(",")) {
					if(noComaOnTextfield()) {
						entregadoCliente.setText(entregadoCliente.getText() + ".");
					}
				}
				if(b.getText().equals("Del.")) {
					if(entregadoCliente.getText().length() > 0) {
						entregadoCliente.setText(entregadoCliente.getText().substring(0, entregadoCliente.getText().length() - 1));
					}
				}
			}
		});
		
		b.setPreferredSize(new Dimension(75, 75));
		b.setBackground(ColorsClass.DARKBLUE);
		b.setForeground(ColorsClass.WHITE);
	}
	
	private boolean noComaOnTextfield() {
		for(int i = 0; i < entregadoCliente.getText().length(); i++) {
			if(entregadoCliente.getText().charAt(i) == '.')
				return false;
		}
		return true;
	}

	private void prepareGridBagLayout() {
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.anchor = GridBagConstraints.WEST;
		gbc.insets = new Insets(3, 0, 3, 0);
		this.add(entregadoLabel, gbc);
		
		gbc.gridy = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		this.add(entregadoCliente, gbc);
		
		gbc.fill = GridBagConstraints.NONE;
		gbc.gridy = 2;
		this.add(devolverLabel, gbc);
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridy = 3;
		this.add(devolverCliente, gbc);
		
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weighty = 1;
		gbc.gridy = 4;
		this.add(panelButtons, gbc);
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weighty = 0;
		gbc.gridy = 5;
		this.add(aceptar, gbc);
	}

	public JTextField getEntregadoCliente() {
		return entregadoCliente;
	}

	public void setEntregadoCliente(JTextField entregadoCliente) {
		this.entregadoCliente = entregadoCliente;
	}

	public JTextField getDevolverCliente() {
		return devolverCliente;
	}

	public void setDevolverCliente(JTextField devolverCliente) {
		this.devolverCliente = devolverCliente;
	}
	
	public void setTotalIva(float totalIva) {
		this.totalIva = totalIva;
	}
	
}
