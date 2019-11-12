package xmlManager;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;

import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class XMLConfigManager extends XMLFileManager {

	public XMLConfigManager(String ruta)
			throws TransformerException, ParserConfigurationException, SAXException, IOException, XPathExpressionException {
		super(ruta);
		
		if(!xmlFile.exists())
			createConfig();
		
		// Como siempre estará creado en este punto, lo parseamos
		doc = builder.parse(xmlFile);
		
		// Añadimos la IP en el config.xml
		setIPOnConfigFile();
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
	
	public void setIPOnConfigFile() throws XPathExpressionException, UnknownHostException, TransformerException, SAXException, IOException {
		this.writeInElement("//server/ip", InetAddress.getLocalHost().getHostAddress());
	}
	
	

}
