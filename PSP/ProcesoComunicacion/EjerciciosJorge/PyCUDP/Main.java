package PyCUDP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.URL;

import HTTP.GestionarClientes;

/**
 * Crea un programa que emita por Broadcast UDP palíndromos. Para ello utiliza
 * un esquema de productor/consumidor. El programa recibirá la ruta al fichero
 * con
 * [palabras](https://github.com/JorgeDuenasLerin/diccionario-espanol-txt/blob/master/0_palabras_todas.txt)
 * y el puerto UDP al que enviará la información por Broadcast. Habrá un thread
 * que buscará palíndromos en el fichero y lo añadirá a un array sincronizado.
 * Un palíndromo es una que se lee igual al derecho que al revés.
 */

public class Main {
	public static int port;
	public static String line;
	public static final int NUM_PARAMS = 2;
	public static final int tamanioBuffer = 1;

	public static void main(String[] args) {
		Buffer buffer = new Buffer(tamanioBuffer);

		if (args.length != NUM_PARAMS) {
			System.out.println("Error en número de parámetros");
		} else {
			port = Integer.parseInt(args[0]);
			line = args[1];
		}
		while (true) {
			Productor productor= new Productor(line);
			Consumir consumidor = new Consumir(buffer, port);
			productor.start();
			consumidor.start();
		}

	}

}
