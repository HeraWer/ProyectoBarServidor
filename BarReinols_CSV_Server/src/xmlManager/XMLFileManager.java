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
import org.xml.sax.SAXException;


/*
 * Esta clase gestiona los ficheros XML de configuracion, como
 * el de config.xml y el de productos.xml
 */
public class XMLFileManager {
	
	// Atributos de la clase
	private File configFile;
	private DocumentBuilderFactory dbf;
	private Document doc;
	private DocumentBuilder builder;

	/*
	 * Constructor que crea el documento y lo parsea, 
	 * o lo parsea directamente si existe.
	 */
	public XMLFileManager() throws TransformerException, ParserConfigurationException, SAXException, IOException {
		
		// Seleccionamos el fichero de config.xml
		configFile = new File("xml/config.xml");
		
		// Objetos para modelar y parsear el XML
		dbf = DocumentBuilderFactory.newInstance();
		builder = dbf.newDocumentBuilder();
		doc = builder.newDocument();
		
		// Si el archivo config no existe, llamamos al metodo que lo crea
		if(!configFile.exists())
			createConfig();
		
		// Como siempre estará creado en este punto, lo parseamos
		doc = builder.parse(configFile);
	}
	
	/*
	 * Metodo que crea el fichero config.xml en el caso que no exista
	 */
	public void createConfig() throws TransformerException, SAXException, IOException {
		
		// Creamos el elemento raiz
		Element root = doc.createElement("config");;
		doc.appendChild(root);
		
		// Elementos de la parte de server, donde guardamos la IP del server
		Element server = doc.createElement("server");
		root.appendChild(server);
		
		Element ip = doc.createElement("ip");
		server.appendChild(ip);
		
		// ¡¡¡COMPROBAR!!!
		Element pda = doc.createElement("pda");
		root.appendChild(pda);
		
		Element pdaName = doc.createElement("nom");
		pda.appendChild(pdaName);
		
		// Elementos de la parte de mesas, que asigna el numero de mesas disponibles
		Element tables = doc.createElement("mesas");
		root.appendChild(tables);
		
		Element tablesQuant = doc.createElement("cantidad");
		tables.appendChild(tablesQuant);
		
		// Llamamos al metodo que escribe el fichero
		writeFile();
	}
	
	/*
	 * Metodo que nos devuelve un elemento pasandole como parametro
	 * una expresion de XPath.
	 */
	public Element getElement(String xpathExpression) throws XPathExpressionException {
		XPath xpath = XPathFactory.newInstance().newXPath();
		return (Element) xpath.evaluate(xpathExpression, doc, XPathConstants.NODE);
	}
	
	/*
	 * Metodo que comprueba si el texto de un elemento es igual al texto que
	 * le pasmaos por parametro. Se le pasa una expresion de XPath para encontrar
	 * el elemento.
	 * 
	 * Retorna un booleano dependiendo de si es igual o no.
	 */
	public boolean isElementEquals(String xPath, String text) throws XPathExpressionException {
		Element quantity = getElement(xPath);
		if(quantity.getTextContent().equals(text))
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
		StreamResult sr = new StreamResult(new FileWriter(configFile));
		transf.transform(source, sr);
		
		// Volvemos a parsear el fichero una vez hechos los cambios.
		doc = builder.parse(configFile);
	}	
	
}
