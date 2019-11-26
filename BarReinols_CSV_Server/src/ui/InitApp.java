package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.concurrent.CountDownLatch;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JWindow;

import com.example.barreinolds.Main;

public class InitApp extends JWindow {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JProgressBar loadingBar;
	private JLabel title;
	private JLabel init_loading_logo;
	public static JLabel infoLabel;
	private JPanel mainInitPanel;
	private int progressBarValue = 0;

	public InitApp() {
		super();
		this.getContentPane().setLayout(new BorderLayout());
		this.setSize(500, 500);
		this.setVisible(true);

		Point center = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
		int width = 500;
		int height = 500;
		this.setBounds(center.x - width / 2, center.y - height / 2, width, height);

		try {
			initialize();
			modify();
			prepareGridBagLayout();
			initAPP();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void initialize() throws IOException {
		loadingBar = new JProgressBar(0, 8);
		title = new JLabel("Cargando TPV...");
		BufferedImage logo = ImageIO.read(new File("res/img/init_loading_logo.png"));
		init_loading_logo = new JLabel(new ImageIcon(logo));
		infoLabel = new JLabel("Iniciando...");
		mainInitPanel = new JPanel(new GridBagLayout());
	}

	private void modify() {
		title.setFont(new Font("Verdana", Font.BOLD, 30));

		this.setBackground(new Color(68, 72, 82));

		mainInitPanel.setBackground(new Color(68, 72, 82));

		title.setForeground(new Color(255, 255, 255));
		infoLabel.setForeground(new Color(255, 255, 255));

		loadingBar.setBackground(new Color(47, 64, 88));

	}

	private void prepareGridBagLayout() {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(20, 20, 20, 20);
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		mainInitPanel.add(title, gbc);

		gbc.gridy = 1;
		mainInitPanel.add(init_loading_logo, gbc);

		gbc.gridy = 2;
		mainInitPanel.add(loadingBar, gbc);

		gbc.gridy = 3;
		mainInitPanel.add(infoLabel, gbc);

		this.getContentPane().add(mainInitPanel, BorderLayout.CENTER);
	}

	public void initAPP() throws SQLException, UnknownHostException, ClassNotFoundException, InterruptedException {
		infoLabel.setText("Inicializando JDBC Connector...");
		bbddManager.ConnectionManager.initializeJDBC();
		progressBarValue++;
		loadingBar.setValue(progressBarValue);
		infoLabel.setText("Comprobando tablas en la base de datos...");
		bbddManager.TablesManager.initializeDatabase();
		progressBarValue++;
		loadingBar.setValue(progressBarValue);
		infoLabel.setText("Recuperando numero de mesas de la base de datos...");
		Main.numTaules = bbddManager.InitBDManager.getMesas();
		progressBarValue++;
		loadingBar.setValue(progressBarValue);
		if (Main.numTaules == 0) {
			infoLabel.setText("Recuperadas 0 mesas. Introduce las mesas deseadas...");
			bbddManager.InitBDManager.setConfigOnEmptyTable(0);
			progressBarValue++;
			loadingBar.setValue(progressBarValue);
		} else {
			progressBarValue++;
			loadingBar.setValue(progressBarValue);
		}
		infoLabel.setText("Recuperando camareros de la base de datos...");
		Main.setCamareros(bbddManager.InitBDManager.getCamareros());
		progressBarValue++;
		loadingBar.setValue(progressBarValue);
		infoLabel.setText("Recuperando categorias de la base de datos...");
		Main.setCategoriasBar(bbddManager.InitBDManager.getCategories());
		progressBarValue++;
		loadingBar.setValue(progressBarValue);
		infoLabel.setText("Recuperando comandas de la base de datos...");
		Main.setTickets(bbddManager.InitBDManager.getTickets());
		progressBarValue++;
		loadingBar.setValue(progressBarValue);
		if (Main.getTickets().size() == 0)
			infoLabel.setText("No se han encontrado comandas en la base de datos.");
		else if (Main.getTickets().size() == 1)
			infoLabel.setText("Se ha encontrado 1 comanda en la base de datos.");
		else
			infoLabel.setText("Se han encontrado " + Main.getTickets().size() + " comandas en la base de datos.");
		Thread.sleep(2000);
		progressBarValue++;
		loadingBar.setValue(progressBarValue);
		Main.latch.countDown();
		this.dispose();
	}
}
