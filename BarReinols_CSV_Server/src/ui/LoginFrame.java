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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.xml.bind.DatatypeConverter;

import com.example.barreinolds.Camarero;
import com.example.barreinolds.Main;

import javax.swing.JButton;

public class LoginFrame extends JInternalFrame {

	public static final int NUMEROLETRAS = 29;
	private JLabel infoLabel;
	private JLabel titleLabel;
	private JTextField inputCamarero;
	private JPasswordField passCamarero;
	private ArrayList<JButton> aLButtonsLetras;
	private JButton deleteButton;
	private JButton whiteSpaceButton;
	private JButton enter;
	
	private JButton mayus;
	
	private int inputFocus = 0;
	private boolean upperActivated;

	private JPanel mainPanel;
	private JPanel lettersPanel;

	public LoginFrame() {
		super("Login de camarero");
		upperActivated = false;
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
		passCamarero = new JPasswordField();
		passCamarero.setEchoChar('●');
		aLButtonsLetras = new ArrayList<JButton>();
		titleLabel = new JLabel("Introduce el nombre de usuario del camarero:");
		enter = new JButton("Entrar");
		deleteButton = new JButton("<--");
		whiteSpaceButton = new JButton("Espacio");
		infoLabel = new JLabel();
		lettersPanel = new JPanel(new GridLayout(4, 10, 10, 10));
		mainPanel = new JPanel(new GridBagLayout());
		mayus = new JButton("\u25B2");

	}
	
	public void setUppercase() {
		for(JButton b : aLButtonsLetras)
			b.setText(b.getText().toUpperCase());
	}
	
	public void setLowercase() {
		for(JButton b : aLButtonsLetras)
			b.setText(b.getText().toLowerCase());
	}

	public void createButtonsForLettersPanel() {
		JButton charButton;
		for(int i = 0; i <= 9; i++) {
			charButton = new JButton(String.valueOf(i));
			configButton(charButton);
			setListenerForCharButton(charButton);
			lettersPanel.add(charButton);
		}
		for (int i = 97; i <= 122; i++) {
			char c = (char) i;

			charButton = new JButton(String.valueOf(c));
			configButton(charButton);
			lettersPanel.add(charButton);
			aLButtonsLetras.add(charButton);
			setListenerForCharButton(charButton);

			if (i == 115) {
				charButton = new JButton("ç");
				lettersPanel.add(charButton);
				configButton(charButton);
				aLButtonsLetras.add(charButton);
				setListenerForCharButton(charButton);
				
				charButton = new JButton("ñ");
				lettersPanel.add(charButton);
				configButton(charButton);
				aLButtonsLetras.add(charButton);
				setListenerForCharButton(charButton);
			}
		}
		
		configButton(mayus);
		mayus.setFont(new Font("Arial", Font.BOLD, 10));
		lettersPanel.add(mayus);

		deleteButton.setPreferredSize(new Dimension(75, 75));
		deleteButton.setFont(new Font("Verdana", Font.BOLD, 16));
		deleteButton.setBackground(ColorsClass.DARKBLUE);
		deleteButton.setForeground(ColorsClass.WHITE);
		lettersPanel.add(deleteButton);

	}
	
	public void configButton(JButton button) {
		button.setPreferredSize(new Dimension(75, 75));
		button.setFont(new Font("Verdana", Font.BOLD, 16));
		button.setBackground(ColorsClass.DARKBLUE);
		button.setForeground(ColorsClass.WHITE);
	}

	public void modify() {
		titleLabel.setFont(new Font("Verdana", Font.BOLD, 24));
		infoLabel.setFont(new Font("Verdana", Font.BOLD, 12));
		infoLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		whiteSpaceButton.setPreferredSize(new Dimension(0, 50));
		whiteSpaceButton.setBackground(ColorsClass.DARKBLUE);
		whiteSpaceButton.setForeground(ColorsClass.WHITE);

		inputCamarero.setPreferredSize(new Dimension(0, 50));
		inputCamarero.setFont(new Font("Verdana", Font.BOLD, 20));

		passCamarero.setPreferredSize(new Dimension(0, 50));
		passCamarero.setFont(new Font("Verdana", Font.BOLD, 20));

		enter.setPreferredSize(new Dimension(100, 100));
		enter.setMaximumSize(new Dimension(100, 100));
		enter.setBackground(ColorsClass.DARKBLUE);
		enter.setForeground(ColorsClass.WHITE);
		mainPanel.setBackground(new Color(47, 49, 56));
		lettersPanel.setBackground(new Color(47, 49, 56));

		titleLabel.setForeground(ColorsClass.WHITE);
		infoLabel.setForeground(ColorsClass.WHITE);
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
		mainPanel.add(passCamarero, gbc);

		gbc.gridy = 3;
		gbc.weighty = 1;
		mainPanel.add(lettersPanel, gbc);

		gbc.gridwidth = 1;
		gbc.gridy = 4;
		mainPanel.add(whiteSpaceButton, gbc);

		gbc.weightx = 0;
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.gridx = 1;
		gbc.gridy = 4;
		mainPanel.add(enter, gbc);

		gbc.gridy = 5;
		gbc.gridx = 0;
		gbc.gridwidth = 2;
		gbc.weighty = 0;
		mainPanel.add(infoLabel, gbc);

		this.getContentPane().add(mainPanel);
	}

	public void setListenerForCharButton(JButton charButton) {
		charButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if(inputFocus == 0)
					inputCamarero.setText(inputCamarero.getText() + charButton.getText());
				else
					passCamarero.setText(getPassword() + charButton.getText());
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
					MessageDigest m = null;
					try {
						m = MessageDigest.getInstance("MD5");
					} catch (NoSuchAlgorithmException e1) {
						e1.printStackTrace();
					}
					
					byte[] passMD5 = m.digest(getPassword().getBytes());
					
					if (c.getPassword().equals(DatatypeConverter.printHexBinary(passMD5).toLowerCase())) {
						Main.camareroSeleccionado = c;
						JOptionPane.showMessageDialog(LoginFrame.this, "Iniciada sesion como " + c.getNombre(),
								"Login correcto", JOptionPane.INFORMATION_MESSAGE);
						((MainMenuBar) Main.m.getJMenuBar()).getConfigMenu().setEnabled(true);
						((MainMenuBar) Main.m.getJMenuBar()).getVentanaMenu().setEnabled(true);
						((CardLayout) Main.m.getMainPanel().getLayout()).show(Main.m.getMainPanel(),
								MainWindow.COCINAFRAMECARD);

					}else
						infoLabel.setText("Contraseña incorrecta!");
				}

			}
		});

		inputCamarero.addKeyListener(new KeyAdapter() {

			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER)
					enter.doClick();
			}

		});
		
		inputCamarero.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				inputFocus = 0;
			}
		});

		passCamarero.addKeyListener(new KeyAdapter() {

			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER)
					enter.doClick();
			}

		});
		
		passCamarero.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				inputFocus = 1;
			}
		});
		
		mayus.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if(upperActivated) {
					upperActivated = false;
					setLowercase();
					mayus.setBackground(ColorsClass.DARKBLUE);
				}else {
					upperActivated = true;
					setUppercase();
					mayus.setBackground(ColorsClass.ACTIVATED);
				}
					
			}
		});
			
			
	}

	public String getPassword() {
		String password = "";
		for (int i = 0; i < passCamarero.getPassword().length; i++) {
			password = password + passCamarero.getPassword()[i];
		}
		return password;
	}
}
