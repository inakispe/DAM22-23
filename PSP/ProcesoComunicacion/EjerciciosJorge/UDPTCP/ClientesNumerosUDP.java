package UDPTCP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ClientesNumerosUDP {
	public static void main(String[] args) {
		if (args.length < 1)
			return;
		int port = Integer.parseInt(args[0]);
		byte[] buffer = new byte[1024];
		
		try {
			InetAddress direccion= InetAddress.getByName("localhost"); /*Esto es la direccion que vamos a mandarle al servidor
			 * se crea el socket en este puerto, recordemos que
																	 * el servidor no sabe quien se conecta
																	 */
			DatagramSocket socketUDP = new DatagramSocket();
			String mensaje="Hola mundo";
			buffer= mensaje.getBytes();
			
			DatagramPacket pregunta = new DatagramPacket(buffer, buffer.length, direccion,port); // Que sea una peticion de tamaño y
																					// tipo de contenido del buffer y nuestra direccón y puerto
			socketUDP.send(pregunta); // Aqui envia la pregunta de si se puede conectar
			
			DatagramPacket peticion= new DatagramPacket(buffer, buffer.length);
			
			socketUDP.receive(peticion); //Recibe la conexión con el servidor
			
			mensaje=new String(peticion.getData());
		
			
			System.out.println("Recibo la informacion desde el cliente"); 
			
			System.out.println(mensaje);
			
		} 
		catch (IOException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
	}

}
