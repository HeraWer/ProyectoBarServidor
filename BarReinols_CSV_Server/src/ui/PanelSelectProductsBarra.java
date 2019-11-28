package ui;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

import com.example.barreinolds.Category;
import com.example.barreinolds.Main;

public class PanelSelectProductsBarra extends JScrollPane {
	

	private static final long serialVersionUID = 1L;
	private ArrayList<CategoryButton> botonesCategoria;
	private ProductsFromCategoryPanel productsPanel;
	private JPanel panelCategories;
	private JPanel mainPanel;
	
	public PanelSelectProductsBarra() {
		super();
		// JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
		botonesCategoria = new ArrayList<CategoryButton>();
		initialize();
		createCategoryButtons();
		modify();
		mainPanel.add(panelCategories, "Categorias");
		mainPanel.setBackground(ColorsClass.DARKBACKGROUND);
		panelCategories.setBackground(ColorsClass.DARKBACKGROUND);
		
		this.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		this.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
	}
	
	private void initialize() {
		mainPanel = new JPanel(new CardLayout());
		productsPanel = new ProductsFromCategoryPanel();
		panelCategories = new JPanel(new FlowLayout());
	}
	
	private void modify() {
		this.setBackground(ColorsClass.DARKBACKGROUND);
		panelCategories.setPreferredSize(new Dimension(430 , 300));
		this.setViewportView(mainPanel);
	}
	
	private void createCategoryButtons() {
		CategoryButton cb;
		for(Category c : Main.getCategoriasBar()) {
			cb = new CategoryButton(c.getId(), c.getNombre());
			botonesCategoria.add(cb);
			panelCategories.add(cb);
		}
	}
	
}
