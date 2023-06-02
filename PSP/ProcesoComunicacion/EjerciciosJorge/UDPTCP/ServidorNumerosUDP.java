package UDPTCP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class ServidorNumerosUDP {
	public static final int port1 =10001;
	public static final int port2 =10002;
	public static final int port3 =10003;
	public static final int port4 =10004;
	public static void main(String[] args) {
		if (args.length < 1)
			return;
		int port = Integer.parseInt(args[0]);
		byte[] buffer = new byte[1024];

		try {
			DatagramSocket socketUDP = new DatagramSocket(port); /*
																	 * se crea el socket en este puerto, recordemos que
																	 * el servidor no sabe quien se conecta
																	 */
			while (true) {

				DatagramPacket peticion = new DatagramPacket(buffer, buffer.length); // Que sea una peticion de tamaño y
																						// tipo de contenido del buffer

				socketUDP.receive(peticion); // Aqui como recive la señal de que esta haciendo una peticion
				System.out.println("Recibo la informacion desde el cliente");
				String mensaje = new String(peticion.getData()); // ´
				System.out.println(mensaje);
				int puertoCliente = peticion.getPort();// Aqui cogemos el puerto de la petición para mandarle el mensaje
														// de vuelta
				InetAddress direccionIP = peticion.getAddress();
				mensaje = "Hola mundo desde el servidor";
				buffer = mensaje.getBytes();
				DatagramPacket respuesta = new DatagramPacket(buffer, buffer.length, direccionIP, puertoCliente);
				System.out.println("Enviamos respuesta al cliente");
				socketUDP.send(respuesta); // Aqui enviamos la información al socket
			}
		} catch (IOException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
	}
}
