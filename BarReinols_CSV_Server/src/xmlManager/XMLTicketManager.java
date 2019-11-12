package xmlManager;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;

import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import bar.Product;
import bar.Ticket;

public class XMLTicketManager extends XMLFileManager{
	
	private Ticket t;
	
	public XMLTicketManager(String ruta, Ticket t) throws TransformerException, ParserConfigurationException, SAXException, IOException {
		super(ruta);
		
		this.t = t;
		
		// Si el archivo de la mesa no existe, llamamos al metodo que lo crea
		if(!xmlFile.exists())
			createTicketsFile(t);
		
		// Como siempre estará creado en este punto, lo parseamos
		doc = builder.parse(xmlFile);
	}
	
	public void createProduct(Product p) throws XPathExpressionException, TransformerException, SAXException, IOException {
		Element pedido = this.getElement("//Pedido");
			
		Element product = doc.createElement("Producto");
		pedido.appendChild(product);
			
		Element pName = doc.createElement("Nombre");
		pName.setTextContent(p.getName());
		product.appendChild(pName);
			
		Element pQuantity = doc.createElement("Cantidad");
		pQuantity.setTextContent(String.valueOf(p.getQuantity()));
		product.appendChild(pQuantity);
			
		Element pPrice = doc.createElement("Precio");
		pPrice.setTextContent(p.getPrice());
		product.appendChild(pPrice);
		
		writeFile();
	}
	
	public void createTicketsFile(Ticket t) throws TransformerException, SAXException, IOException {
		Element root = doc.createElement("Bar");
		doc.appendChild(root);
		
		Element table = doc.createElement("Mesa");
		Attr idTable = doc.createAttribute("id");
		idTable.setValue(String.valueOf(t.getTable()));
		table.setAttributeNode(idTable);
		root.appendChild(table);
		
		Element pedido = doc.createElement("Pedido");
		table.appendChild(pedido);
		
		for(Product p : t.getALProduct()) {
			
		}

		writeFile();
	}
	
	
}
