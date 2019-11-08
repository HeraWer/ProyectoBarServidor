package serverConnection;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import bar.Main;
import bar.Ticket;

public class MainServer {
	
	private final int PORT = 1234;
	private final String HOST = "localhost";
	private DatagramSocket dSocket;
	private byte[] buff;
	private DatagramPacket dp;
	private ObjectInputStream inputClient;
	private ByteArrayInputStream bais;
	private Ticket received;
	
	public MainServer() throws IOException, ClassNotFoundException {
		System.out.println("IP: " + InetAddress.getLocalHost().toString());
		dSocket = new DatagramSocket(PORT);
		buff = new byte[5000];
		dp = new DatagramPacket(buff, buff.length);
		dSocket.receive(dp);
		int byteCount = dp.getLength();
		bais = new ByteArrayInputStream(buff);
		inputClient = new ObjectInputStream(new BufferedInputStream(bais));
		Ticket t = (Ticket) inputClient.readObject();
		Main.getTickets().add(t);
		
	}
	
	
}
