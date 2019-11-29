package ui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.example.barreinolds.Product;

public class ProductButton extends JPanel{
	
	private Product product;
	private JLabel productLabel;
	
	public ProductButton(Product product) {
		super(new GridBagLayout());
		this.product = product;
		this.productLabel = new JLabel("<html>" + product.getName() + "</html>");
		this.productLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		this.productLabel.setAlignmentX(JLabel.CENTER);
		this.setPreferredSize(new Dimension(260, 75));
		this.setMaximumSize(new Dimension(260, 75));
		this.setMinimumSize(new Dimension(260, 75));
		this.setBackground(ColorsClass.DARKBLUE);
		this.setBorder(BorderFactory.createLineBorder(ColorsClass.ROWSBACKGROUND, 1, true));
		this.productLabel.setFont(new Font("Verdana", Font.BOLD, 16));
		this.productLabel.setForeground(ColorsClass.WHITE);
		setListeners();
		prepareGridBag();
	}
	
	private void setListeners() {
		this.addMouseListener(new MouseAdapter() {
			
			public void mouseEntered(MouseEvent e) {
				ProductButton.this.setBorder(BorderFactory.createLineBorder(ColorsClass.ROWSBACKGROUND, 3, true));
			}
			
			public void mouseExited(MouseEvent e) {
				ProductButton.this.setBorder(BorderFactory.createLineBorder(ColorsClass.ROWSBACKGROUND, 1, true));
			}
			public void mouseReleased(MouseEvent e) {
				ProductButton.this.setBackground(ColorsClass.DARKBLUE);
			}
			
			public void mousePressed(MouseEvent e) {
				ProductButton.this.setBackground(ColorsClass.ACTIVATED);
			}
			
			public void mouseClicked(MouseEvent e) {
				// CardLayout a productos
			}
		});
	}
	
	public void prepareGridBag() {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5,5,5,5);
		
		JButton button = new JButton();
		button.setPreferredSize(new Dimension(55,55));
		button.setMinimumSize(new Dimension(55,55));
		this.add(button, gbc);
		
		gbc.gridx = 1;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 1;
		gbc.weighty = 1;
		this.add(productLabel, gbc);
	}

}
