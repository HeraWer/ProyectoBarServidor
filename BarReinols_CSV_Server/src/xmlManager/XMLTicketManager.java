package xmlManager;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;

import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import bar.Main;
import bar.Product;
import bar.Ticket;
import ui.MainWindow;

public class XMLTicketManager extends XMLFileManager {

	private Ticket t;

	/*
	 * Constructor principal, se debera llamar cada vez que se deba gestionar un fichero de ticket.
	 */
	public XMLTicketManager(String ruta, Ticket t) throws TransformerException, ParserConfigurationException,
			SAXException, IOException, XPathExpressionException {
		super(ruta);

		this.t = t;

		// Si el archivo de la mesa no existe, llamamos al metodo que lo crea
		if (!xmlFile.exists())
			createTicketsFile();

		// Como siempre estará creado en este punto, lo parseamos
		doc = builder.parse(xmlFile);
	}

	/*
	 * Constructor vacio para gestionar la creacion de comandas a partir de archivo
	 */
	public XMLTicketManager() throws ParserConfigurationException {
		super();
	}
	
	/*
	 * Metodo que comprueba si existen ficheros de comandas para poder generarlos en la tabla
	 */
	public void lookForTickets(MainWindow m) throws SAXException, IOException, XPathExpressionException,
			TransformerException, ParserConfigurationException {
		File f;
		Ticket recovery = new Ticket();
		ArrayList<Ticket> aLTicket = new ArrayList<Ticket>();
		for (int i = 1; i <= Main.numTaules; i++) {
			f = new File("xml/pedidoMesa" + i + ".xml");
			if (f.exists()) {
				recovery = recoveryFromFile(f, i);
				Main.sendTicket(recovery, m.getTicketsFrame());
			}
		}

	}

	/*
	 * Recuperamos los datos desde el fichero XML de la comanda si existe, y creamos comandas en base a eso
	 */
	public Ticket recoveryFromFile(File f, int numMesa) throws SAXException, IOException, XPathExpressionException {
		doc = builder.parse(f);
		// El ticket que retornaremos si hace falta rellenarlo
		Ticket recovered = new Ticket(numMesa);
		// Cogemos los productos del xml
		NodeList products = doc.getElementsByTagName("Producto");
		// Hacemos un arrayList de los productos para añadirla a la comanda
		ArrayList<Product> productsTicket = new ArrayList<Product>();
		Product p;
		
		// Recorremos todos los productos y los vamos añadiendo al arraylist
		for (int i = 0; i < products.getLength(); i++) {
			p = new Product();
			Node product = products.item(i);
			p.setId(product.getAttributes().item(0).getNodeValue());
			p.setName(this.getElement("//Producto[@id='" + p.getId() + "']/Nombre").getTextContent());
			p.setQuantity(Integer.parseInt(this.getElement("//Producto[@id='" + p.getId() + "']/Cantidad").getTextContent()));
			p.setPrice(this.getElement("//Producto[@id='" + p.getId() + "']/Precio").getTextContent());
			productsTicket.add(p);
		}
		recovered.setALProduct(productsTicket);

		return recovered;

	}

	/*
	 * Creamos el producto y lo escribimos en el XML
	 */
	public void createProduct(Product p)
			throws XPathExpressionException, TransformerException, SAXException, IOException {
		Element pedido = this.getElement("//Pedido");
		// Si el elemento ya existe, no lo vuelve a crear, solo deberia haber cambiado la cantidad
		if (elementExists("//Producto[@id='" + p.getId() + "']")) {
			this.writeInElement("//Producto[@id='" + p.getId() + "']/Cantidad", String.valueOf(p.getQuantity()));
		} else {
			// Si no existe, creamos el elemento
			Element product = doc.createElement("Producto");
			Attr idProduct = doc.createAttribute("id");
			idProduct.setValue(p.getId());
			product.setAttributeNode(idProduct);
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
		
	}

	/*
	 * Metodo que crea el archivo de tickets si no existe, en el momento que se recibe una comanda
	 */
	public void createTicketsFile() throws TransformerException, SAXException, IOException, XPathExpressionException {
		Element root = doc.createElement("Bar");
		doc.appendChild(root);

		Element table = doc.createElement("Mesa");
		Attr idTable = doc.createAttribute("id");
		idTable.setValue(String.valueOf(t.getTable()));
		table.setAttributeNode(idTable);
		root.appendChild(table);

		Element pedido = doc.createElement("Pedido");
		table.appendChild(pedido);

		for (Product p : t.getALProduct()) {
			createProduct(p);
		}

		writeFile();
	}

}
