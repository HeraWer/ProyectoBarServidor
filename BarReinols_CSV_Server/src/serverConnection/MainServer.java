package serverConnection;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

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
	private Socket socket;
	private byte[] buff;
	private ServerSocket ss;
	private DatagramPacket dPacket;
	private ObjectInputStream inputClient;
	private ByteArrayInputStream bais;

	/*
	 * Constructor de la clase en la que se enciende el servidor.
	 */
	public MainServer(MainWindow m) throws IOException, ClassNotFoundException, TransformerException,
			ParserConfigurationException, SAXException, XPathExpressionException {
		System.out.println("IP: " + InetAddress.getLocalHost().toString());

		/*
		 * Datos de prueba para rellenar la tabla, si ya existe el fichero de comanda no los añadira,
		 * puesto que ya deben estar creados a partir del fichero
		 */
		Ticket t = new Ticket(5);
		Product p;
		for (int i = 0; i < 10; i++) {
			p = new Product(String.valueOf(i), String.valueOf((int) (Math.random() * 57836)),
					String.valueOf((int) (Math.random() * 57836)), String.valueOf((int) (Math.random() * 57836)), (int)(Math.random() * 10));
			t.getALProduct().add(p);

		}
		Main.sendTicket(t, m.getTicketsFrame());
		m.resetUIForUpdates();

//		ss = new ServerSocket(PORT);
//		//while (true) {
//			socket = ss.accept();
//			buff = new byte[5000];
//			dPacket = new DatagramPacket(buff, buff.length);
//			System.out.println("Hola1");
//			socket.receive(dPacket);
//			System.out.println("Hola2");
//			int byteCount = dPacket.getLength();
//			bais = new ByteArrayInputStream(buff);
//			inputClient = new ObjectInputStream(new BufferedInputStream(bais));
//
//			Ticket t = (Ticket) inputClient.readObject();
//			System.out.println("Hola3");
//			Main.sendTicket(t, m.getTicketsFrame());
//
//			m.resetUIForUpdates();
//		//}

		/*
		 * ss = new ServerSocket(PORT); // while (true) { socket = ss.accept(); //
		 * b//uff = new byte[5000]; // dPacket = new DatagramPacket(buff, buff.length);
		 * // System.out.println("Hola1"); // System.out.println("Hola2"); // int
		 * byteCount = dPacket.getLength(); // bais = new ByteArrayInputStream(buff);
		 * inputClient = new ObjectInputStream(socket.getInputStream());
		 * System.out.println("Esperando objeto");// +
		 * inputClient.readObject().getClass().toString()); while (true) { Ticket t =
		 * (Ticket) inputClient.readObject(); System.out.println("Leido objeto"); //
		 * System.out.println("Hola3"); Main.sendTicket(t, m.getTicketsFrame());
		 * 
		 * m.resetUIForUpdates(); }
		 */
		// }

	}

}
