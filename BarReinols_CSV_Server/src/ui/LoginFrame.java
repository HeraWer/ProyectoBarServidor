package ui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.example.barreinolds.Camarero;
import com.example.barreinolds.Main;

import javax.swing.JButton;

public class LoginFrame extends JInternalFrame {

	public static final int NUMEROLETRAS = 29;
	private JLabel infoLabel;
	private JLabel titleLabel;
	private JTextField inputCamarero;
	private ArrayList<JButton> aLButtonsLetras;
	private JButton deleteButton;
	private JButton whiteSpaceButton;
	private JButton enter;

	private JPanel mainPanel;
	private JPanel lettersPanel;

	public LoginFrame() {
		super("Login de camarero");
		initialize();
		createButtonsForLettersPanel();
		modify();
		this.getContentPane().setLayout(new FlowLayout());
		prepareGridBagLayout();
		setListeners();
		
		this.setBorder(null);
		
	}

	public void initialize() {
		inputCamarero = new JTextField();
		aLButtonsLetras = new ArrayList<JButton>();
		titleLabel = new JLabel("Introduce el nombre de usuario del camarero:");
		enter = new JButton("Entrar");
		deleteButton = new JButton("<--");
		whiteSpaceButton = new JButton("Espacio");
		infoLabel = new JLabel();
		lettersPanel = new JPanel(new GridLayout(3, 10, 10, 10));
		mainPanel = new JPanel(new GridBagLayout());
	}

	public void createButtonsForLettersPanel() {
		JButton charButton;
		for (int i = 65; i <= 90; i++) {
			char c = (char) i;
			
			charButton = new JButton(String.valueOf(c));
			charButton.setFont(new Font("Verdana", Font.BOLD, 16));
			lettersPanel.add(charButton);
			aLButtonsLetras.add(charButton);
			setListenerForCharButton(charButton);
			charButton.setPreferredSize(new Dimension(75,75));
			charButton.setBackground(new Color(47, 64, 88));
			charButton.setForeground(new Color(255,255,255));
			
			if (i == 83) {
				charButton = new JButton("ç");
				lettersPanel.add(charButton);
				charButton.setFont(new Font("Verdana", Font.BOLD, 16));
				charButton.setBackground(new Color(47, 64, 88));
				charButton.setForeground(new Color(255,255,255));
				aLButtonsLetras.add(charButton);
				setListenerForCharButton(charButton);
				charButton.setPreferredSize(new Dimension(75,75));
				charButton = new JButton("ñ");
				lettersPanel.add(charButton);
				charButton.setFont(new Font("Verdana", Font.BOLD, 16));
				charButton.setBackground(new Color(47, 64, 88));
				charButton.setForeground(new Color(255,255,255));
				aLButtonsLetras.add(charButton);
				setListenerForCharButton(charButton);
				charButton.setPreferredSize(new Dimension(75,75));
			}
		}

		deleteButton.setPreferredSize(new Dimension(75, 75));
		deleteButton.setFont(new Font("Verdana", Font.BOLD, 16));
		deleteButton.setBackground(new Color(47, 64, 88));
		deleteButton.setForeground(new Color(255,255,255));
		lettersPanel.add(deleteButton);

	}

	public void modify() {
		titleLabel.setFont(new Font("Verdana", Font.BOLD, 24));
		infoLabel.setFont(new Font("Verdana", Font.BOLD, 12));
		infoLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		whiteSpaceButton.setPreferredSize(new Dimension(0, 50));
		whiteSpaceButton.setBackground(new Color(47, 64, 88));
		whiteSpaceButton.setForeground(new Color(255,255,255));
		inputCamarero.setPreferredSize(new Dimension(0, 50));
		inputCamarero.setFont(new Font("Verdana", Font.BOLD, 20));
		enter.setPreferredSize(new Dimension(100, 100));
		enter.setMaximumSize(new Dimension(100, 100));
		enter.setBackground(new Color(47, 64, 88));
		enter.setForeground(new Color(255,255,255));
		mainPanel.setBackground(new Color(47, 49, 56));
		lettersPanel.setBackground(new Color(47, 49, 56));
		
		titleLabel.setForeground(new Color(255,255,255));
		infoLabel.setForeground(new Color(255,255,255));
	}

	public void prepareGridBagLayout() {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.weightx = 1;
		gbc.gridwidth = 2;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		mainPanel.add(titleLabel, gbc);

		gbc.gridy = 1;
		mainPanel.add(inputCamarero, gbc);

		gbc.gridy = 2;
		gbc.weighty = 1;
		mainPanel.add(lettersPanel, gbc);

		gbc.gridwidth = 1;
		gbc.gridy = 3;
		mainPanel.add(whiteSpaceButton, gbc);
		
		gbc.weightx = 0;
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.EAST;
		gbc.gridx = 1;
		gbc.gridy = 3;
		mainPanel.add(enter, gbc);
		
		gbc.gridy = 4;
		gbc.weighty = 0;
		mainPanel.add(infoLabel, gbc);
		
		this.getContentPane().add(mainPanel);
	}

	public void setListenerForCharButton(JButton charButton) {
		charButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				inputCamarero.setText(inputCamarero.getText() + charButton.getText().toLowerCase());
			}
		});

		
	}

	public void setListeners() {
		deleteButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				if (inputCamarero.getText().length() != 0)
					inputCamarero.setText(inputCamarero.getText().substring(0, inputCamarero.getText().length() - 1));
			}
		});

		whiteSpaceButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String text = inputCamarero.getText();
				inputCamarero.setText(text + String.valueOf(" "));
			}
		});
		
		enter.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Camarero c;
				c = tools.Search.searchForCamareroByName(inputCamarero.getText());
				if (c == null) {
					infoLabel.setText("No se ha encontrado el camarero introducido!");
				} else {
					Main.camareroSeleccionado = c;
					JOptionPane.showMessageDialog(LoginFrame.this, "Iniciada sesion como " + c.getNombre(),
							"Login correcto", JOptionPane.INFORMATION_MESSAGE);
					((MainMenuBar) Main.m.getJMenuBar()).getConfigMenu().setEnabled(true);
					((MainMenuBar) Main.m.getJMenuBar()).getVentanaMenu().setEnabled(true);
					((CardLayout) Main.m.getMainPanel().getLayout()).show(Main.m.getMainPanel(),
							MainWindow.COCINAFRAMECARD);
					;
				}

			}
		});
	}
}
