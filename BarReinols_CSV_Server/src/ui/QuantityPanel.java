package ui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.NumberFormat;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.NumberFormatter;

public class QuantityPanel extends JPanel {
	
	public static JTextField quantityField;
	private JLabel quantityLabel;
	private JPanel buttonsPanel;
	private JButton deleteButton;
	
	public QuantityPanel() {
		super(new GridBagLayout());
		initialize();
		createButtons();
		prepareGridBagLayout();
		setListeners();
	}
	
	private void initialize() {
		


		quantityField = new JTextField("0");
		quantityField.setPreferredSize(new Dimension(0,40));
		quantityField.setFont(new Font("Arial", Font.BOLD, 16));
		
		
		quantityLabel = new JLabel("Cantidad");
		quantityLabel.setFont(new Font("Arial", Font.BOLD, 20));
		quantityLabel.setForeground(ColorsClass.WHITE);
		
		buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
		buttonsPanel.setOpaque(false);
		this.setOpaque(false);
		buttonsPanel.setPreferredSize(new Dimension(245, 0));
		
		this.setPreferredSize(new Dimension(245, 0));
		this.setMaximumSize(new Dimension(245, 0));
		this.setMinimumSize(new Dimension(245, 0));
		
		deleteButton = new JButton("<-");
		configDeleteButton();
		
	}
	
	private void createButtons() {
		JButton b;
		for(int i = 9; i >= 0; i--) {
			b = new JButton(String.valueOf(i));
			configNumButton(b);
			buttonsPanel.add(b);
		}
		
		buttonsPanel.add(deleteButton);
	}
	
	private void configNumButton(JButton b) {
		b.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if(quantityField.getText().equals("0"))
					quantityField.setText(b.getText());
				else
					quantityField.setText(quantityField.getText() + b.getText());
			}
		});
		
		b.setPreferredSize(new Dimension(75, 75));
		b.setBackground(ColorsClass.DARKBLUE);
		b.setForeground(ColorsClass.WHITE);
	}
	
	private void configDeleteButton() {
		deleteButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if(quantityField.getText().length() > 1)
					quantityField.setText(quantityField.getText().substring(0, quantityField.getText().length() - 1));
				else if(quantityField.getText().length() <= 1)
					quantityField.setText("0");
			}
		});
		
		deleteButton.setPreferredSize(new Dimension(75, 75));
		deleteButton.setBackground(ColorsClass.DARKBLUE);
		deleteButton.setForeground(ColorsClass.WHITE);
	}
	
	private void setListeners(){
		quantityField.addKeyListener(new KeyAdapter() {
			
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE)
					if(quantityField.getText().length() == 1)
						quantityField.setText("0");
				if(e.getKeyCode() < KeyEvent.VK_0 || e.getKeyCode() > KeyEvent.VK_9)
					quantityField.setText(quantityField.getText().substring(0, quantityField.getText().length() - 1));
			}
		});
	}
	
	private void prepareGridBagLayout() {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5,0,5,0);
		gbc.anchor = GridBagConstraints.CENTER;
		this.add(quantityLabel, gbc);
		
		gbc.gridy = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		this.add(quantityField, gbc);
		
		gbc.gridy = 2;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weighty = 1;
		this.add(buttonsPanel, gbc);
		
	}

}
