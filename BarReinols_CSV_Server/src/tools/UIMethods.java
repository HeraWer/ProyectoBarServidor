package tools;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class UIMethods {

	public static void clearTable(JTable table) {
		((DefaultTableModel)table.getModel()).setRowCount(0);
	}
	
	public static BufferedImage resizeToJLabel(BufferedImage img) {
		Image tmp = img.getScaledInstance(55, 55, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(55, 55, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return resized;
	}
	
}
