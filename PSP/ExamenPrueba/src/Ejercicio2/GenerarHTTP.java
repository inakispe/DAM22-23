package Ejercicio2;

import java.io.BufferedReader;
import java.io.Console;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class GenerarHTTP {

	public static int port;
	public static final int sizeBuffer = 1024;
	public static final int sizeargs = 1;

	public static void main(String[] args) {

		byte[] receiveData = new byte[sizeBuffer];
		if (args.length < sizeargs) {
			System.out.println("Tienes que introducir algún argumento");
			return;
		} else if (args.length > 2) {
			System.out.println("Ha introducido demasiados argumentos");
		} else {
			port = Integer.parseInt(args[0]);
		}

		try (ServerSocket serverSocket = new ServerSocket(port)) {
			// El sevidor escucha
			System.out.println("Running server on " + port + "...");

			while (true) {
				// Gestionar Clientes
				Socket socket = serverSocket.accept();
				ManejadorSolicitud manejadorSolicitud = new ManejadorSolicitud(socket);
				Thread hilo = new Thread(manejadorSolicitud);
				hilo.start();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
	class ManejadorSolicitud implements Runnable {

		private static Socket socket;
		public static String DNIsinLetra;
	 	public static char letraVerificada;
	 	public static char letraDni;
	 	public static String DNI;
		public static final String OK = "OK";
		public static final String KO = "KO";
		public ManejadorSolicitud(Socket socket) {
			this.socket = socket;
		}
		@Override
		public void run() {
			try {
				procesarPeticion();
				enviarRespuesta();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		private static void procesarPeticion() throws IOException {
			BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String lineaPeticion = input.readLine();
			System.out.println(lineaPeticion);
			// Se separan los espacios en blanco o las tabulaciones
			String[] tokesns = lineaPeticion.split("/");
			System.out.println(tokesns[3]);
			DNI = tokesns[3];
			letraDni=verLetra();
			letraVerificada=obtenerLetra();
			System.out.println(letraVerificada+"y"+letraDni);
		}

	public static char verLetra() {
		char letra = ' ';
		char[] caracteres = new char[DNI.length()];

		for (int i = 0; i < DNI.length(); i++) {
			caracteres[i] = DNI.charAt(i);
		}
		letra = caracteres[caracteres.length];
		return letra;
	}

	private static char obtenerLetra() {
		char letra = 0;
		String dniSL=eliminaUltimaLetra(DNI);
		int numeros = Integer.parseInt(dniSL);
		int resto = numeros % 23;

		switch (resto) {

		case 0:
			letra = 'T';
			break;
		case 1:
			letra = 'R';
			break;
		case 2:
			letra = 'W';
			break;
		case 3:
			letra = 'A';
			break;
		case 4:
			letra = 'G';
			break;
		case 5:
			letra = 'M';
			break;
		case 6:
			letra = 'Y';
			break;
		case 7:
			letra = 'F';
			break;
		case 8:
			letra = 'P';
			break;
		case 9:
			letra = 'D';
			break;
		case 10:
			letra = 'X';
			break;
		case 11:
			letra = 'Y';
			break;
		case 12:
			letra = 'W';
			break;
		case 13:
			letra = 'J';
			break;
		case 14:
			letra = 'Z';
			break;
		case 15:
			letra = 'S';
			break;
		case 16:
			letra = 'Q';
			break;
		case 17:
			letra = 'V';
			break;
		case 18:
			letra = 'H';
			break;
		case 19:
			letra = 'W';
			break;
		case 20:
			letra = 'C';
			break;
		case 21:
			letra = 'K';
		case 23:
			letra = 'E';
			break;
		}

		return letra;
	}

	private static String eliminaUltimaLetra(String entrada) {
		return entrada.substring(0, entrada.length() - 1);
	}

	

	private String construirHTML() {
		// Creamos un StringBuilder que hace que modifiquemos el String
		StringBuilder html = new StringBuilder();
		// Hacemos un foreach que recorra el Array

		if (letraVerificada != letraDni) {
			html.append("<h1><span style=\"color: red;\">" + KO + "</span></h1>");
		} else {
			html.append("<h1>" + OK + " <h1>");
		}
		return html.toString();

	}
	public void enviarRespuesta() throws IOException{
		String respuesta;
		String contenido=construirHTML();
		respuesta = "HTTP/1.1 200 OK\r\n"
	            + "Content-Type: text/html\r\n" //La respuesta entiendo que es siempre igual y aqui pone el tipo html.
	            + "Content-Length: " + contenido.length() + "\r\n"
	            + "\r\n"
	            + contenido;
		PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
		out.println(respuesta);
	}
}