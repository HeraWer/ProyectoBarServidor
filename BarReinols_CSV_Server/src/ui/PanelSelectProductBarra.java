package ui;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import com.example.barreinolds.Category;
import com.example.barreinolds.Main;
import com.example.barreinolds.Product;

public class PanelSelectProductBarra extends JPanel {
	
	private ProductButton pb;
	private JPanel panelProds;
	private JButton backButton;
	private JPanel mainPanel;
	
	public PanelSelectProductBarra() {
		super();
		mainPanel = new JPanel(new CardLayout());
		createAllProds(Main.getProductosBar());
		
		addPanels();
		this.setBackground(ColorsClass.DARKBACKGROUND);
	}
	
	private void createAllProds(ArrayList<Product> aLProduct) {
		panelProds = new JPanel(new FlowLayout(FlowLayout.CENTER, 10,10));
		panelProds.setBackground(ColorsClass.DARKBACKGROUND);
		for(Product p : aLProduct) {
			pb = new ProductButton(p);
			panelProds.add(pb);
		}
		mainPanel.add(panelProds, "ALLPRODS");
	}
	
	private void addPanels() {
		for(Category c : Main.getCategoriasBar()) {
			panelProds = new JPanel(new FlowLayout(FlowLayout.LEFT, 10,10));
			panelProds.setBackground(ColorsClass.DARKBACKGROUND);
			
			addButtons(c);
			mainPanel.add(panelProds, c.getId() + c.getNombre());
		}
	}
	
	private void addButtons(Category c) {
		for(Product p : c.getaLProducts()) {
			pb = new ProductButton(p);
			panelProds.add(pb);
		}
		backButton = new JButton("VOLVER");
		setConfigToBackButton(backButton);
		panelProds.add(backButton);
		
	}
	
	private void setConfigToBackButton(JButton b) {
		b.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout)mainPanel.getLayout();
				cl.show(mainPanel, "ALLPRODS");
			}
		});
		
		b.setBackground(ColorsClass.DARKBACKGROUNDBLUE);
		b.setForeground(ColorsClass.WHITE);
		b.setPreferredSize(new Dimension(150 , 75));
	}
	
	public void showCard(Category c) {
		CardLayout cl = (CardLayout)mainPanel.getLayout();
		cl.show(mainPanel, c.getId() + c.getNombre());
	}
	
	

}
