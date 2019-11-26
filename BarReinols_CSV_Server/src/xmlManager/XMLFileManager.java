package xmlManager;

import java.io.File;
import java.io.FileWriter;
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
import org.w3c.dom.NodeList;

import org.xml.sax.SAXException;

/*
 * Esta clase gestiona los ficheros XML de configuracion, como
 * el de config.xml y el de productos.xml
 */
public abstract class XMLFileManager {
	
	// Atributos de la clase
	protected File xmlFile;
	private DocumentBuilderFactory dbf;
	protected Document doc;
	protected DocumentBuilder builder;

	/*
	 * Constructor para la gestion del documento config.
	 */
	public XMLFileManager(String ruta) throws TransformerException, ParserConfigurationException, SAXException, IOException {
		
		// Seleccionamos el fichero de config.xml
		xmlFile = new File(ruta);
		
		// Objetos para modelar y parsear el XML
		dbf = DocumentBuilderFactory.newInstance();
		builder = dbf.newDocumentBuilder();
		doc = builder.newDocument();
	}
	
	public XMLFileManager() throws ParserConfigurationException {
		dbf = DocumentBuilderFactory.newInstance();
		builder = dbf.newDocumentBuilder();
		doc = builder.newDocument();
	}
	
	/*
	 * Metodo que nos devuelve un elemento pasandole como parametro
	 * una expresion de XPath.
	 */
	public Element getElement(String xpathExpression) throws XPathExpressionException {
		XPath xpath = XPathFactory.newInstance().newXPath();
		return (Element) xpath.evaluate(xpathExpression, doc, XPathConstants.NODE);
	}
	
	public NodeList getElementsList(String xpathExpression) throws XPathExpressionException {
		XPath xpath = XPathFactory.newInstance().newXPath();
		return ((NodeList) (xpath.evaluate(xpathExpression, doc, XPathConstants.NODESET)));
	}
	
	/*
	 * Metodo que comprueba si el texto de un elemento es igual al texto que
	 * le pasmaos por parametro. Se le pasa una expresion de XPath para encontrar
	 * el elemento.
	 * 
	 * Retorna un booleano dependiendo de si es igual o no.
	 */
	public boolean isElementEquals(String xPath, String text) throws XPathExpressionException {
		Element find = getElement(xPath);
		if(text == null)
			if(find.getTextContent() == null)
				return true;
			
			
		if(find.getTextContent().equals(text))
			return true;
		return false;
	}
	
	/*
	 * Metodo que escribe contenido de texto dentro de un elemento del fichero.
	 * Se le pasa como parametro la expresion XPath para encontrar el elemento
	 * y el texto que se quiere introducir dentro del elemento.
	 */
	public void writeInElement(String xPath, String textContent) throws XPathExpressionException, TransformerException, SAXException, IOException {
		Element find = getElement(xPath);
		find.setTextContent(textContent);
		
		writeFile();
	}
	
	/*
	 * Metodo que devuelve le contenido de texto de un elemento.
	 * Se le pasa como parametro una exresion XPath para encontrar dicho elemento.
	 */
	public String getElementTextContent(String xpathExpression) throws XPathExpressionException {
		Element find = getElement(xpathExpression);
		return find.getTextContent();
	}
	
	/*
	 * Metodo que escribe los cambios hechos en el documento XML.
	 */
	public void writeFile() throws TransformerException, SAXException, IOException {
		// Objetos necesarios para poder meter cambios en el fichero
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer transf = tf.newTransformer();
		DOMSource source = new DOMSource(doc);

		FileWriter fw = new FileWriter(xmlFile);
		StreamResult sr = new StreamResult(fw);
		transf.transform(source, sr);
		fw.close();
		// Volvemos a parsear el fichero una vez hechos los cambios.
		doc = builder.parse(xmlFile);
	}
	
	public boolean elementExists(String xPath) throws XPathExpressionException {
		return getElementsList(xPath).getLength() > 0;
	}
	
}
