package serverConnection;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.swing.JOptionPane;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;

import org.xml.sax.SAXException;

import com.example.barreinolds.Camarero;
import com.example.barreinolds.Main;
import com.example.barreinolds.Message;
import com.example.barreinolds.Product;
import com.example.barreinolds.Ticket;

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
	private ServerSocket ss;
	private ObjectInputStream inputClient;
	private ObjectOutputStream outputServer;
	private Ticket t;

	/*
	 * Constructor de la clase en la que se enciende el servidor.
	 */
	public MainServer(MainWindow m) throws SQLException, IOException, ClassNotFoundException {
		System.out.println("IP: " + InetAddress.getLocalHost().toString());
		/*
		 * Datos de prueba para rellenar la tabla, si ya existe el fichero de comanda no
		 * los añadira, puesto que ya deben estar creados a partir del fichero
		 */

		/*t = new Ticket(2);
		t.setCamarero(new Camarero(3, "Erik Cabezuelo", "ecabezue"));
		t.setDatetime(new Timestamp(System.currentTimeMillis()));
		Product p;
		p = new Product(1, "Coca Cola", "a",
				"1.60", 5, null, null);
		t.getProductosComanda().add(p);
		p = new Product(2, "Fanta naranja", "a",
				"1.60", 3, null, null);
		t.getProductosComanda().add(p);
		p = new Product(4, "Cerveza", "a",
				"1.70", 8, null,null);
		t.getProductosComanda().add(p);
		Main.sendTicket(t, m);
		m.resetUIForUpdates();*/

		// Abrimos el socket del server
		ss = new ServerSocket(PORT);
		
		while (true) {
			
			// Aceptamos las solicitudes que nos envian
			MainWindow.statusLabel.setText("Aplicación lista. Esperando conexión de móvil.");
			System.out.println("Aplicación lista. Esperando conexión de móvil.");
			socket = ss.accept();
			System.out.println("Nueva conexión de móvil realizada.");
			MainWindow.statusLabel.setText("Nueva conexión de móvil realizada.");

			
			// Cogemos el outputStream del socket
			outputServer = new ObjectOutputStream(socket.getOutputStream());

			// Cogemos el inputStream del socket
			inputClient = new ObjectInputStream(socket.getInputStream());

			

			/*if (enviado) {
				System.out.println("Enviando número de mesas y lista de camareros a móvil...");
				MainWindow.statusLabel.setText("Enviando número de mesas y lista de camareros a móvil...");
				outputServer.writeObject(Main.getCamareros());
				outputServer.writeObject((Integer) Main.numTaules);
				enviado = false;
				System.out.println("Mesas y camareros enviados a móvil.");
				MainWindow.statusLabel.setText("Mesas y camareros enviados a móvil.");
				continue;
			}*/
			// Esperamos objeto
			System.out.println("Esperando comanda del móvil...");
			MainWindow.statusLabel.setText("Esperando comanda del móvil...");

			// Recibimos el objeto enviado por el camarero (PDA)
			Object received = inputClient.readObject();
			
			if (received != null) {
				// Si el objeto recibido es un String == CAMAREROS
				if (received.getClass().equals(Message.class) && ((Message) received).getRequest().equals("CAMAREROS")) {

					System.out.println("Enviando camareros...");
					MainWindow.statusLabel.setText("Enviando camareros...");
					// Le enviamos el array de camareros al camarero
					outputServer.writeObject(Main.getCamareros());
					outputServer.flush();

					// Si el objeto recibido es un String == NUMMESAS
				} else if (received.getClass().equals(Message.class) && ((Message) received).getRequest().equals("NUMMESAS")) {

					System.out.println("Enviando numeros de mesas...");
					MainWindow.statusLabel.setText("Enviando numero de mesas...");
					// Le enviamos el numero de mesas al camarero
					outputServer.writeObject((Integer) Main.numTaules);
					outputServer.flush();

					// Si el objeto recibido es un String == COBRARMESA
				} else if (received.getClass().equals(Message.class) && ((Message) received).getRequest().contains("COBRARMESA")) {
					String stringMesa = ((Message) received).getRequest().substring(10);
					int numMesa = Integer.parseInt(stringMesa);

					// Si el objeto recibido es un String == CATEGORIAS
				} else if (received.getClass().equals(Message.class) && ((Message) received).getRequest().equals("CATEGORIAS")) {
					System.out.println("Enviando categorias...");
					MainWindow.statusLabel.setText("Enviando categorias...");
					outputServer.writeObject(Main.getCategoriasBar());
					outputServer.flush();
				} else if (received.getClass().equals(Message.class) && ((Message) received).getRequest().equals("RECUPERARTICKET")) {					
					System.out.println("Enviando tickets...");
					MainWindow.statusLabel.setText("Enviando tickets...");
					outputServer.writeObject(Main.getTickets());
					outputServer.flush();
				} else {
				
					System.out.println("Comanda recibida.");
					MainWindow.statusLabel.setText("Comanda recibida.");
					
					// Instanciamos el objeto como Ticket
					t = ((Ticket) received);
					
					
					// Enviamos el ticket recibido a la aplicacion
					Main.sendTicket(t);
					MainWindow.statusLabel.setText("Comanda insertada!");
					System.out.println("Comanda insertada!");

					// Actualizamos la interfaz
					m.resetUIForUpdates();
				}
			} else {
				System.out.println("Se ha recibido una comanda nula!");
				MainWindow.statusLabel.setText("Se ha recibido una comanda nula!");
			}

		}
	}

}
