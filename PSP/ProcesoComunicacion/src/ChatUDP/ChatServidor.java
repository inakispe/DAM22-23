package ChatUDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class ChatServidor {

	static int PUERTOSERVIDOR = 2222;

	public static void main(String[] args) {
		// TODO Esbozo de método generado automáticamente
		try {
			DatagramSocket socketUDP = new DatagramSocket(PUERTOSERVIDOR);
			byte[] bufer = new byte[1000];
			System.out.println("Iniciando el servidor UDP");
			// Lo metemos en un while porque acepta todas las peticiones
			while (true) {
				DatagramPacket peticion = new DatagramPacket(bufer, bufer.length);
				// Leemos si nos hacen una petición en el socket
				socketUDP.receive(peticion);
				System.out.println("Bienvenido al chat: " + peticion.getAddress());

				// Construimos el Datagram para darle respuesta
				DatagramPacket respuesta = new DatagramPacket(peticion.getData(), peticion.getLength(),
						peticion.getAddress(), peticion.getPort());

				// Enviamos la respuest
				socketUDP.send(respuesta);
			}
		} catch (SocketException e) {
			System.out.println("Se esta usando el Socket");
		} catch (Exception e) {
			System.out.println("IO: "+ e.getMessage());
		}
	}
}
