import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.print.attribute.standard.Severity;

//EL CONTENIDO SE RELLENA CON EL BUFFEREAD y apartir de ahi se recorrer
//El SCO
public class TCPChallenge {

	public static int port;
	public static String rootDirectorio;
	byte[] receiveData = new byte[1024];
	public static String[] contenidoTexto;
	public static String textoDescifrado;
	public static String separacion = ".";
	public static int numeroRespuesta;
	public static int respuestaCorrecta;
	public static int puntuacion;
	public static String pregunta;
	public static int numeroVueltas;

	public static void main(String[] args) {
		if (args.length < 2) {
			System.out.println("Tienes que introducir más argumentos");
			return;
		} else {
			port = Integer.parseInt(args[1]);
			rootDirectorio = args[0];

		}

		try {

			// Instanciamos objeto ServerSocket
			ServerSocket server = new ServerSocket(port);
			File ruta = new File(rootDirectorio);
			// Leemos el File y lo guardamos en un Buffer
			BufferedReader in = new BufferedReader(new FileReader(ruta));
			String contenidoTexto = leerArchivo(rootDirectorio);
			// Está escuchando continuamente

			while (true) {

				// Server está a la escucha y acepta la conexión
				Socket conCliente = server.accept();

				// Utilizamos thread para cada cliente un hilo
				new Thread(() -> {

					// A través de un método realizamos la conexión
					try {
						establecerConexion(conCliente, in);
					} catch (FileNotFoundException e) {
						// TODO Bloque catch generado automáticamente
						e.printStackTrace();
					}

				}).start();
				conCliente.close();
			}

		} catch (UnknownHostException h) {
			h.printStackTrace();
		} catch (IOException i) {
			i.printStackTrace();
		}
		;
	}

	private static void establecerConexion(Socket conCliente, String in) {
		// TODO Esbozo de método generado automáticamente
		String res1;
		String res2;
		String res3;
		String res4;
		// Encargado de enviar
		DataOutputStream dataOutputStream = new DataOutputStream(null);
		// Encargado de recibir
		DataInputStream dataInputStream = new DataInputStream(null);
		// Tengo dos ideas esto o un array bidireccional
		try {
			dataOutputStream = new DataOutputStream(conCliente.getOutputStream());
		} catch (IOException e1) {
			// TODO Bloque catch generado automáticamente
			e1.printStackTrace();
		}
		// Aqui vamos a obtener el número, que es la respuesta
		respuestaCorrecta = obtenerRespuesta(in);
		// Enviamos las preguntas y las posibles respuestas al cliente
		pregunta = obtenerPregunta(in);

		try {
			dataOutputStream.writeUTF(pregunta);

			// Aqui le enviamos las respuestas posibles
			String[] respuestas = null;
			respuestas = variasRespuestas(in);
			for (int i = 0; i < respuestas.length; i++) {
				dataOutputStream.writeUTF(respuestas[i]);
			}
			// Ahora obtenemos la respuesta del cliente
			dataInputStream = new DataInputStream(conCliente.getInputStream());
			numeroRespuesta = dataInputStream.read();
			if (numeroRespuesta == respuestaCorrecta) {
				puntuacion = puntuacion + 1;
			} else {
				puntuacion = puntuacion - 1;
			}
			// Mandamos de vuelta la puntuación
			dataOutputStream.write(puntuacion);

			dataOutputStream.flush();
		} catch (IOException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
	}

	public static String leerArchivo(String nombreArchivo) {
		String line = null;
		String contenido = null;
		StringBuilder stringBuilder = new StringBuilder();
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(nombreArchivo))) {
			while ((line = bufferedReader.readLine()) != null) {
				stringBuilder.append(line);
				stringBuilder.append(System.lineSeparator());
				contenido = stringBuilder.toString(); // Guarda el contenido del archivo en una variable tipo String

			}
		} catch (IOException e) {
			System.err.format("Ha ocurrido un error al leer el archivo: %s%n", e);
		}

		return contenido;
	}

	public static String obtenerPregunta(String contenidoTexto2) {
		// Aqui creamos el objeto donde se rellena
		String[] elementos = contenidoTexto2.split(separacion);
		pregunta = elementos[1];
		return pregunta;

	}

	public static int obtenerRespuesta(String textoCifrado) {
		int respuesta = 0;
		String[] partes = textoCifrado.split(separacion);
		respuesta = Integer.parseInt(partes[0]);
		return respuesta;

	}

	public static String[] variasRespuestas(String textoCifrado) {
		String[] respuestasPosibles = null;
		for (int i = 0; i < respuestasPosibles.length; i++) {
			respuestasPosibles.add(textoCifrado.split(separacion));
		}
		respuestasPosibles = textoCifrado.split(separacion);
		return respuestasPosibles;

	}
}
