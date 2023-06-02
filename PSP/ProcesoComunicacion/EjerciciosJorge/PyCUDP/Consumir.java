package PyCUDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.SocketException;

public class Consumir extends Thread {
	private Buffer buffer;
	private int port; 
	public Consumir(Buffer buffer,int port) {
		super();
		this.buffer = buffer;
		this.port=port;
	}

	@Override
	public void run() {
		String palabra=buffer.consumir();
		DatagramSocket socketUDP;
		try {
			socketUDP = new DatagramSocket(port);

			byte[] buffer = new byte[1024];
			DatagramPacket peticion = new DatagramPacket(buffer, buffer.length); // Que sea una peticion de tamaño y
			// tipo de contenido del buffer
			socketUDP.receive(peticion); // Aqui como recive la señal de que esta haciendo una peticion
			System.out.println("Recibo la informacion desde el cliente");
			// String mensaje = new String(peticion.getData()); // ´
			int puertoCliente = peticion.getPort();// Aqui cogemos el puerto de la petición para mandarle el mensaje
													// de vuelta
			InetAddress direccionIP = peticion.getAddress();
			buffer = palabra.getBytes();
			DatagramPacket respuesta = new DatagramPacket(buffer, buffer.length, direccionIP, puertoCliente);
			System.out.println("Enviamos respuesta al cliente");
			socketUDP.send(respuesta); // Aqui enviamos la información al socket
			
			notifyAll();
		} catch (MalformedURLException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		} catch (SocketException e1) {
			// TODO Bloque catch generado automáticamente
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}

	}
	

}
