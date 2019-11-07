package xmlManager;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;


public class XMLFileManager {
	private File f;
	private DocumentBuilderFactory dbf;
	private Document doc;
	private DocumentBuilder builder;

	public XMLFileManager() throws TransformerException, ParserConfigurationException, SAXException, IOException {
		f = new File("xml/config.xml");
		dbf = DocumentBuilderFactory.newInstance();
		builder = dbf.newDocumentBuilder();
		doc = builder.newDocument();
		
		if(!f.exists())
			createConfig();
		
		doc = builder.parse(f);
	}
	
	public void createConfig() throws TransformerException {
		
		Element root = doc.createElement("config");;
		doc.appendChild(root);
		
		Element server = doc.createElement("server");
		root.appendChild(server);
		
		Element ip = doc.createElement("ip");
		server.appendChild(ip);
		
		Element pda = doc.createElement("pda");
		root.appendChild(pda);
		
		Element pdaName = doc.createElement("nom");
		pda.appendChild(pdaName);
		
		Element tables = doc.createElement("mesas");
		root.appendChild(tables);
		
		Element tablesQuant = doc.createElement("cantidad");
		tables.appendChild(tablesQuant);
		
		writeFile();
	}
	
	public Element getElement(String xpathExpression) throws XPathExpressionException {
		XPath xpath = XPathFactory.newInstance().newXPath();
		return (Element) xpath.evaluate(xpathExpression, doc, XPathConstants.NODE);
	}
	
	public boolean isElementEquals(String xPath, String text) throws XPathExpressionException {
		Element quantity = getElement(xPath);
		if(quantity.getTextContent().equals(text))
			return false;
		return true;
	}
	
	public void writeInElement(String xPath, String textContent) throws XPathExpressionException, TransformerException {
		Element find = getElement(xPath);
		find.setTextContent(textContent);
		writeFile();
	}
	
	public void writeFile() throws TransformerException {
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer transf = tf.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult sr = new StreamResult(f);
		transf.transform(source, sr);
	}
	
	
	
}
