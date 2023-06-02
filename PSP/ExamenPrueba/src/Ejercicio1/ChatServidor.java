package Ejercicio1;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class ChatServidor {
	public static final int TAMANIOBUFFER = 1000;
	public static String mensajeCliente;
	public static final int NUM_PARAMS = 1;
	public static String mensajeVuelta;
	public static int port;
	
	public static void main(String[] args) {
		if (args.length != NUM_PARAMS) {
			System.out.println("Error en número de parámetros");
		} else {
			port = Integer.parseInt(args[0]);
		}
		try {
			DatagramSocket socketUDP = new DatagramSocket(port);
			
			byte[] bufer = new byte[TAMANIOBUFFER];
			System.out.println("Iniciando el servidor UDP");
			// Lo metemos en un while porque acepta todas las peticiones
			while (true) {
				DatagramPacket peticion = new DatagramPacket(bufer, bufer.length);
				// Leemos si nos hacen una petición en el socket
				socketUDP.receive(peticion);
				System.out.println(peticion.toString());
				System.out.println("Bienvenido al chat: " + peticion.getAddress());
				mensajeCliente = peticion.toString();
				mensajeVuelta=formarRespuesta();
				bufer=mensajeVuelta.getBytes();
				// Construimos el Datagram para darle respuesta
				DatagramPacket respuesta = new DatagramPacket(bufer, bufer.length,
						peticion.getAddress(), peticion.getPort());

				// Enviamos la respuest
				socketUDP.send(respuesta);
			}
		} catch (SocketException e) {
			System.out.println("Se esta usando el Socket");
		} catch (Exception e) {
			System.out.println("IO: " + e.getMessage());
		}

	}

	public static String formarRespuesta() {

		int numVocales = contarVocales(mensajeCliente);
		int numConsonantes = contarConsonantes(mensajeCliente);
		int numParametros = contarOtra(mensajeCliente);

		String resultado = numVocales + ":" + numConsonantes + ":" + numParametros;
		
		return resultado;

	}


	public static int contarVocales(String frase) {
		String vocales = "aeiouAEIOU";
		int contador = 0;

		for (int i = 0; i < frase.length(); i++) {
			char caracter = frase.charAt(i);
			if (vocales.contains(Character.toString(caracter))) {
				contador++;
			}
		}

		return contador;
	}

	public static int contarConsonantes(String frase) {
		String consonantes = "bcdfghjklmnpqrstvwxyzBCDFGHJKLMNPQRSTVWXYZ";
		int contador = 0;

		for (int i = 0; i < frase.length(); i++) {
			char caracter = frase.charAt(i);
			if (consonantes.contains(Character.toString(caracter))) {
				contador++;
			}
		}

		return contador;
	}
	public static int contarOtra(String frase) {
		String consonantes = "bcdfghjklmnpqrstvwxyzBCDFGHJKLMNPQRSTVWXYZ";
		String vocales = "aeiouAEIOU";
		int contador = 0;
		for (int i = 0; i < frase.length(); i++) {
			char caracter = frase.charAt(i);
			if (consonantes.contains(Character.toString(caracter))==false||vocales.contains(Character.toString(caracter))==false) {
				contador++;
			}
		}

		return contador;
	}
}
