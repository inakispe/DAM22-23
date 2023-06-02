package Ejercicio1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class ChatCliente {
	public static final int NUM_PARAMS=3;
	public static int port;
	public static String IP;
	public static String mensaje;
	public static final int TAMANIOBUFFER = 1000;
	public static void main(String[] args) {
		if (args.length != NUM_PARAMS) {
			System.out.println("Error en número de parámetros");
		} else {
			IP = args[0];
			port = Integer.parseInt(args[1]);
			mensaje = args[2];			
		}
		try {
			byte[] bufer = new byte[TAMANIOBUFFER];
			// Los argumentos los proporciona
			// Primero se conecta con el servidor y esto se hace a través del socket
			DatagramSocket socketUDP = new DatagramSocket();
			InetAddress hostServidor = InetAddress.getByName(IP);
			bufer=mensaje.getBytes();
			// Construimos un datagrama para enviar el mensaje al servidor
			DatagramPacket peticion = new DatagramPacket(bufer, bufer.length, hostServidor, port);

			// Enviamos el datagrama
			socketUDP.send(peticion);

			// Construimos el Datagrama que contendrá la respuesta.
			DatagramPacket respuesta = new DatagramPacket(bufer, bufer.length);
			socketUDP.receive(respuesta);

			// Enviamos la respuesta del servidor a la salida estandar
			System.out.println("Respuesta: " + new String(respuesta.getData()));

			// Cerramos el socket
			socketUDP.close();

		} catch (SocketException e) {
			System.out.println("Socket: " + e.getMessage());
			// TODO: handle exception
		} catch (IOException e) {
			System.out.println("IO: " + e.getMessage());
		}
	}
}
