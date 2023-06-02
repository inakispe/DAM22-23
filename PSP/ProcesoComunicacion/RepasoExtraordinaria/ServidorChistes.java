import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.URL;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

//Crear un método  que haga la consulta a la api que te 
//devuelva la consulta en un HTML y despues se envia al correo

//Haces 10 thread, con 10 join para esperar a ejecutarse, revisar el  
public class ServidorChistes {
	public static final int NUM_PARAMS = 3;
	public static int puerto;
	public static String credencial;
	public static String password;
	public static final String CHISTE = "c";
	public static final String DATOS = "d";
	public static final String APIChiste="https://v2.jokeapi.dev/joke/any";
	
	public static void main(String[] args) {
		// TODO Esbozo de método generado automáticamente
		if (args.length != NUM_PARAMS) {
			System.out.println("Error en número de parámetros");
		} else {
			puerto = Integer.parseInt(args[0]);
			credencial = args[1];
			password = args[2];
		}
		try (ServerSocket serverSocket = new ServerSocket(puerto)) {
			// El sevidor escucha
			System.out.println("Running server on " + puerto + "...");

			while (true) {
				// Gestionar Clientes
				Socket socket = serverSocket.accept();
				ConexionChistes conectar = new ConexionChistes(socket);
				Thread hilo = new Thread(conectar);
				hilo.start();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

class ConexionChistes implements Runnable {
	private Socket socket;
	private String email;
	private String letra;
	private int lugar;

	public ConexionChistes(Socket socket) {
		// TODO Esbozo de constructor generado automáticamente
		this.socket = socket;
	}

	@Override
	public void run() {
		try {
			obtenerPeticion();
			// enviarRespuesta();
		} catch (IOException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}

	}

	public void obtenerPeticion() throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		String lineaPeticion = input.readLine();
		System.out.println(lineaPeticion);
		// "//s" separa cualquier espacio en blanco o tabulacion
		String[] tokens = lineaPeticion.split("/;/");
		letra = tokens[0];
		lugar = Integer.parseInt(tokens[1]);
		email = tokens[2];
	}

	public void buscarAPI(String letra, int numero) {
		if (letra.equalsIgnoreCase(ServidorChistes.CHISTE)) {

		} else if (letra.equalsIgnoreCase(ServidorChistes.DATOS)) {

		} else {
			System.out.println("La API no existe");
		}
	}

	public void leerAPI(String webUrl) throws MalformedURLException {
		URL url = new URL(webUrl);
		BufferedReader in;
		try {
			in = new BufferedReader(new InputStreamReader(url.openStream()));
			StringBuilder html = new StringBuilder();
			String line;
			while ((line = in.readLine()) != null) {
				html.append(line);
				in.close();
			}
		} catch (IOException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}

	}

}
