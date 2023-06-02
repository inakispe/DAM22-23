package CesarHTTPTCP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ManejadorSolicitud implements Runnable {
	Socket socket;
	public static final String CIFRAR = "CIFRAR";
	public static final String DESCIFRAR = "DESCIFRAR";
	public static String palabraEncontrada;

	public ManejadorSolicitud(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		try {
			obtenerNumeroDeUrl();
			enviarRespuesta();
		} catch (IOException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
	}

	public void obtenerNumeroDeUrl() throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		String lineaPeticion = input.readLine();
		System.out.println(lineaPeticion);
		// "//s" separa cualquier espacio en blanco o tabulacion
		String[] tokens = lineaPeticion.split("\\s");
		// String[] numeros = tokens[1].split("/"); // separo los numeros
		String palabra = tokens[0];
		String palabra2 = tokens[1];
		int numeroCifrado = Integer.parseInt(tokens[2]);

		if (palabra.equalsIgnoreCase(CIFRAR)) {
			palabraEncontrada = cifradoCesar(palabra, numeroCifrado);
		} else if (palabra.equalsIgnoreCase(DESCIFRAR)) {
			palabraEncontrada = descifradoCesar(palabra, numeroCifrado);
		}
		System.out.println(palabraEncontrada);
	}

	private String construirHTML() {
		// Creamos un StringBuilder que hace que modifiquemos el String
		StringBuilder html = new StringBuilder();
		html.append("<ul>\n");
		html.append("<li> " + palabraEncontrada + " <li>");
		html.append("<\n>");
		html.append("</ul>\n");

		return html.toString();

	}

	public void enviarRespuesta() throws IOException {
		String respuesta;
		String contenido = construirHTML();
		respuesta = "HTTP/1.1 200 OK\r\n" + "Content-Type: text/html\r\n" // La respuesta entiendo que es siempre igual
																			// y aqui pone el tipo html.
				+ "Content-Length: " + contenido.length() + "\r\n" + "\r\n" + contenido;
		PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
		out.println(respuesta);
	}

	private String descifradoCesar(String palabra, int numeroCifrado) {
		StringBuilder descifrado = new StringBuilder();
		for (int i = 0; i < palabra.length(); i++) {
			char caracter = palabra.charAt(i);

			if (Character.isUpperCase(caracter)) {
				// Convertir el caracter a su equivalente descifrado en may�sculas
				char descifradoCaracter = (char) ((caracter - numeroCifrado - 'A' + 26) % 26 + 'A');
				descifrado.append(descifradoCaracter);
			} else if (Character.isLowerCase(caracter)) {
				// Convertir el caracter a su equivalente descifrado en min�sculas
				char descifradoCaracter = (char) ((caracter - numeroCifrado - 'a' + 26) % 26 + 'a');
				descifrado.append(descifradoCaracter);
			} else {
				// El caracter no es una letra, dejarlo sin descifrar
				descifrado.append(caracter);
			}
		}

		return descifrado.toString();

	}

	private String cifradoCesar(String palabra, int numeroCifrado) {
		StringBuilder cifrado = new StringBuilder();

		for (int i = 0; i < palabra.length(); i++) {
			char caracter = palabra.charAt(i);

			if (Character.isUpperCase(caracter)) {
				// Convertir el caracter a su equivalente cifrado en may�sculas
				char cifradoCaracter = (char) ((caracter + numeroCifrado - 'A') % 26 + 'A');
				cifrado.append(cifradoCaracter);
			} else if (Character.isLowerCase(caracter)) {
				// Convertir el caracter a su equivalente cifrado en min�sculas
				char cifradoCaracter = (char) ((caracter + numeroCifrado - 'a') % 26 + 'a');
				cifrado.append(cifradoCaracter);
			} else {
				// El caracter no es una letra, dejarlo sin cifrar
				cifrado.append(caracter);
			}
		}
		return cifrado.toString();

	}

}
