import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class ChatCliente {
		static int PUERTOSERVIDOR = 2222;

	public static void main(String[] args) {
		// TODO Esbozo de m�todo generado autom�ticamente
		try {
			// Los argumentos los proporciona
			// Primero se conecta con el servidor y esto se hace a trav�s del socket
			DatagramSocket socketUDP = new DatagramSocket();
			byte[] mensaje = args[0].getBytes();
			InetAddress hostServidor = InetAddress.getByName("192.168.56.142");
			
			// Construimos un datagrama para enviar el mensaje al servidor
			DatagramPacket peticion = new DatagramPacket(mensaje, args[0].length(), hostServidor, PUERTOSERVIDOR);
			
			//Enviamos el datagrama
			socketUDP.send(peticion);
			
			//Construimos el Datagrama que contendr� la respuesta.
			byte [] bufer = new byte[1000];
			DatagramPacket respuesta= new DatagramPacket(bufer, bufer.length);
			socketUDP.receive(respuesta);
			
			//Enviamos la respuesta del servidor a la salida estandar
			System.out.println("Respuesta: "+ new String(respuesta.getData()));
			
			//Cerramos el socket
			socketUDP.close();
			
		} catch (SocketException e) {
			System.out.println("Socket: " + e.getMessage());
			// TODO: handle exception
		} catch (IOException e) {
			System.out.println("IO: " + e.getMessage());
		}
	}

}
