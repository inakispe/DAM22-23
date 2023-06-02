package FicheroUDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.SocketException;


public class Consumidor extends Thread {
	private static final InetAddress InetAdress = null;
	private Buffer buffer;
	private int port;

	public Consumidor(Buffer buffer, int port) {
		super();
		this.buffer = buffer;
		this.port = port;
	}

	@Override
	public void run() {
		String palabra = buffer.consumir();
		DatagramSocket socketUDP = null;
		try {
			socketUDP = new DatagramSocket();

			byte[] buffer = new byte[1024];
			//DatagramPacket peticion = new DatagramPacket(buffer, buffer.length); // Que sea una peticion de tama�o y
			// tipo de contenido del buffer
			//socketUDP.receive(peticion); // Aqui como recive la se�al de que esta haciendo una peticion
			//System.out.println("Recibo la informacion desde el cliente");
			// String mensaje = new String(peticion.getData()); // �
			//int puertoCliente = peticion.getPort();// Aqui cogemos el puerto de la petici�n para mandarle el mensaje
													// de vuelta
			
			//InetAddress direccionIP = peticion.getAddress();
			while (true) {
			buffer = palabra.getBytes();
			DatagramPacket respuesta = new DatagramPacket(buffer,
					buffer.length,
					InetAddress.getByName("255.255.255.255"),
					port);
			System.out.println("Enviamos respuesta al cliente");
			socketUDP.send(respuesta); // Aqui enviamos la informaci�n al socket
			}
			//notifyAll();
		} catch (MalformedURLException e) {
			// TODO Bloque catch generado autom�ticamente
			e.printStackTrace();
		} catch (SocketException e1) {
			// TODO Bloque catch generado autom�ticamente
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Bloque catch generado autom�ticamente
			e.printStackTrace();
		} finally {
			if (socketUDP!=null) {
				socketUDP.close();
			}
		}

	}
}
