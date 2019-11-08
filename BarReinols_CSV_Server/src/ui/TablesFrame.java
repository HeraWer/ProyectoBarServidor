package ui;

import javax.swing.JInternalFrame;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;

import org.xml.sax.SAXException;

import bar.Ticket;
import xmlManager.XMLFileManager;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;

public class TablesFrame extends JInternalFrame {

	private ArrayList<JButton> aLTables;
	private XMLFileManager xfm;
	private JButton table;
	private MainWindow parent;

	public TablesFrame(MainWindow parent) {
		this.parent = parent;
		aLTables = new ArrayList<JButton>();

		createButtons();

		this.setLayout(new FlowLayout());
		this.pack();
	}

	public void eliminaButton() {

	}

	public void createButtons() {
		try {
			xfm = new XMLFileManager();
		} catch (TransformerException | ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
		int numTaules = 0;
		try {
			numTaules = Integer.parseInt(xfm.getElementTextContent("//mesas/cantidad"));
		} catch (NumberFormatException | XPathExpressionException e) {
			e.printStackTrace();
		}
		aLTables = new ArrayList<JButton>();
		for (int i = 0; i < numTaules; i++) {
			table = new JButton("Mesa " + String.valueOf(i + 1));
			table.setPreferredSize(new Dimension(100, 100));
			aLTables.add(table);
			this.add(aLTables.get(i));
			this.setListeners(table);
		}

		this.repaint();
	}

	public void setListeners(JButton button) {
		button.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				/*int numTaula = Integer.parseInt(button.getText().split(" ")[1]);
				ArrayList<bar.Ticket> tickets = bar.Main.getTickets();
				TicketsFrame tf = parent.getTicketsFrame();
				if (!tf.searchTab(numTaula)) {
					Ticket t = new Ticket(numTaula);
					tf.crearComanda(t);
					tickets.add(new Ticket(numTaula));
				}

				CardLayout cLayout = (CardLayout) parent.getContentPane().getLayout();
				cLayout.show(parent.getContentPane(), MainWindow.MAINFRAMECARD);*/
			}
		});
	}

}
