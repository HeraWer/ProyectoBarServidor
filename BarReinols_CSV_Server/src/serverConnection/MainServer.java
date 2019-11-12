package serverConnection;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;

import org.xml.sax.SAXException;

import bar.Main;
import bar.Product;
import bar.Ticket;
import ui.MainWindow;

/*
 * Clase que enciende el servidor de la aplicacion.
 * Se mantiene activo hasta que la aplicacion
 * o interfaz grafica se cierra completamente.
 */
public class MainServer {
	
	// Atributos de la clase, como el puero, el host,
	// o los diferentes Sockets.
	private final int PORT = 1234;
	private DatagramSocket dSocket;
	private byte[] buff;
	private DatagramPacket dp;
	private ObjectInputStream inputClient;
	private ByteArrayInputStream bais;
	
	/*
	 * Constructor de la clase en la que se enciende el
	 * servidor.
	 */
	public MainServer(MainWindow m) throws IOException, ClassNotFoundException, TransformerException, ParserConfigurationException, SAXException, XPathExpressionException {
		System.out.println("IP: " + InetAddress.getLocalHost().toString());
		
		
		Ticket t = new Ticket(5);
		Product p;
		for(int i = 0; i < 10; i++) {
			p = new Product(String.valueOf((int)(Math.random()*57836)), String.valueOf((int)(Math.random()*57836)),String.valueOf((int)(Math.random()*57836)),String.valueOf((int)(Math.random()*57836)),5);
			t.getALProduct().add(p);
			
		}
		Main.getTickets().add(t);
		Main.sendTicket(t, m.getTicketsFrame());
		m.resetUIForUpdates();
		
		
		
		dSocket = new DatagramSocket(PORT);
		buff = new byte[5000];
		dp = new DatagramPacket(buff, buff.length);
		dSocket.receive(dp);
		int byteCount = dp.getLength();
		bais = new ByteArrayInputStream(buff);
		inputClient = new ObjectInputStream(new BufferedInputStream(bais));
		
		// Ticket t = (Ticket) inputClient.readObject();		
		Main.getTickets().add(t);
		Main.sendTicket(t, m.getTicketsFrame());
		
		m.resetUIForUpdates();
		
		
		
	}
	
	
}
