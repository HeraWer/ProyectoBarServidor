package ui;

import javax.swing.JInternalFrame;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;

import org.xml.sax.SAXException;

import xmlManager.XMLFileManager;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;

public class TablesFrame extends JInternalFrame {

	private ArrayList<JButton> aLTables;
	private XMLFileManager xfm;
	private JButton table;
	
	public TablesFrame() {
		aLTables = new ArrayList<JButton>();
		
		try {
			createButtons();
		} catch (NumberFormatException | XPathExpressionException e) {
			e.printStackTrace();
		}
		this.setLayout(new GridLayout(0, 10, 5, 5));
		this.pack();
	}
	
	public void eliminaButton() {
		
	}
	
	public void createButtons() throws NumberFormatException, XPathExpressionException {
		try {
			xfm = new XMLFileManager();
		} catch (TransformerException | ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
		int numTaules = Integer.parseInt(xfm.getElementTextContent("//mesas/cantidad"));
		aLTables = new ArrayList<JButton>();
		for(int i = 0; i < numTaules; i++) {
			table = new JButton("Mesa " + String.valueOf(i + 1));
			aLTables.add(table);
			this.add(aLTables.get(i));
		}
		
		this.repaint();
	}
	
}
