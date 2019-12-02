package ui;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

import com.example.barreinolds.Category;
import com.example.barreinolds.Main;

public class PanelSelectCategoryBarra extends JPanel {
	

	private static final long serialVersionUID = 1L;
	private ArrayList<CategoryButton> botonesCategoria;
	
	public PanelSelectCategoryBarra() {
		super();
		botonesCategoria = new ArrayList<CategoryButton>();
		initialize();
		
		try {
			createCategoryButtons();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		modify();
		//mainPanel.add(panelCategories, "Categorias");
		this.setBackground(ColorsClass.DARKBACKGROUND);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));		
	}
	
	private void initialize() {
	}
	
	private void modify() {
		this.setBackground(ColorsClass.DARKBACKGROUND);
	}
	
	private void createCategoryButtons() throws IOException {
		CategoryButton cb;
		for(Category c : Main.getCategoriasBar()) {
			cb = new CategoryButton(c);
			botonesCategoria.add(cb);
			this.add(cb);
			this.add(Box.createRigidArea(new Dimension(0, 5)));
		}
	}
	
}
