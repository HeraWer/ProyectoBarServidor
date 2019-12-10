package ui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.example.barreinolds.Main;
import com.example.barreinolds.Product;
import com.example.barreinolds.Ticket;

public class ProductButton extends JPanel {

	private Product product;
	private JLabel productLabel;
	private JLabel imgProduct;

	public ProductButton(Product product) throws IOException {
		super(new GridBagLayout());
		this.product = product;
		
		this.productLabel = new JLabel("<html>" + product.getName() + "</html>");
		this.productLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		this.productLabel.setAlignmentX(JLabel.CENTER);
		this.productLabel.setFont(new Font("Verdana", Font.BOLD, 16));
		this.productLabel.setForeground(ColorsClass.WHITE);
		ByteArrayInputStream bais = new ByteArrayInputStream(product.getImgBlob());
		BufferedImage productImg = ImageIO.read(bais);
		BufferedImage productImgResized = tools.UIMethods.resizeToJLabel(productImg);
		imgProduct = new JLabel(new ImageIcon(productImgResized));
		imgProduct.setPreferredSize(new Dimension(55, 55));
		imgProduct.setMinimumSize(new Dimension(55, 55));
		imgProduct.setMaximumSize(new Dimension(55, 55));
		

		this.setPreferredSize(new Dimension(260, 75));
		this.setMaximumSize(new Dimension(260, 75));
		this.setMinimumSize(new Dimension(260, 75));
		this.setBackground(ColorsClass.DARKBLUE);
		this.setBorder(BorderFactory.createLineBorder(ColorsClass.ROWSBACKGROUND, 1, true));
		
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
				if (Integer.parseInt(QuantityPanel.quantityField.getText()) > 0) {
					Ticket t = tools.Search.searchForTicket(BarraFrame.actualTable);
					if (t == null)
						t = new Ticket(BarraFrame.actualTable, Main.camareroSeleccionado,
								new Timestamp(System.currentTimeMillis()));
					Product search = tools.Search.searchForProductOnTicket(t, product.getId());
					if (search != null) {
						search.setCantidad(
								search.getCantidad() + Integer.valueOf(QuantityPanel.quantityField.getText()));
					} else {
						Product p = new Product(product.getId(), product.getName(), product.getDescription(),
								product.getPrice(), Integer.parseInt(QuantityPanel.quantityField.getText()),
								product.getImage_Desktop(), product.getImage_movil(), product.getImgBlob());
						t.getProductosComanda().add(p);
					}
					try {
						Main.sendTicket(t);
						Main.m.resetUIForUpdates();
						QuantityPanel.quantityField.setText("0");
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
	}

	public void prepareGridBag() {
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.insets = new Insets(5, 5, 5, 5);
		this.add(imgProduct, gbc);

		gbc.gridx = 1;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 1;
		gbc.weighty = 1;
		this.add(productLabel, gbc);
	}

}
