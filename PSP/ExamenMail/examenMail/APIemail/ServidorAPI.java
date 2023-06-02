package APIemail;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class ServidorAPI {
	public static final int NUM_PARAMS = 3;
	public static int puerto;
	public static String credencial;
	public static String password;
	public static final String CHISTE = "c";
	public static final String DATOS = "d";
	public static final String APIChiste="https://v2.jokeapi.dev/joke/any";
	public static final String APIDato= "numbersapi.p.rapidapi.com";
    
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
		private static String correo;
		private static String letra;
		private static int lugar;
		public final static String msjFallo="Los argumentos estan mal introducidos";
		private static final String SERVIDOR = "smtp.educa.madrid.org";
	    private static final int PORT = 587;
	    private static final String REMITENTE = "ipereiroeusa@gmail.com";
	    private static final String SUBJECT = "Examen";
	    private static final String MSG_ENVIADO = "Correo electrónico enviado exitosamente.";
	    
		public ConexionChistes(Socket socket) {
			// TODO Esbozo de constructor generado automáticamente
			this.socket = socket;
		}

		@Override
		public void run() {
			try {				
				obtenerPeticion();
				enviarCorreo();
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
			correo = tokens[2];
		}

		public static String buscarAPI() throws MalformedURLException {
			StringBuilder contenido = null;
			if (letra.equalsIgnoreCase(ServidorAPI.CHISTE)) {
				for (int i=0; i<lugar;i++) {
					contenido.append(leerAPI(ServidorAPI.APIChiste));	
				}
				
			} else if (letra.equalsIgnoreCase(ServidorAPI.DATOS)) {
				for (int i=0; i<lugar;i++) {
					contenido.append(leerAPI(ServidorAPI.APIDato));				
					}
			} else {
				return msjFallo;
			}
			
			return contenido.toString();
		}

		public static String leerAPI(String webUrl) throws MalformedURLException {
			URL url = new URL(webUrl);
			BufferedReader in;
			StringBuilder html = new StringBuilder();
			try {
				in = new BufferedReader(new InputStreamReader(url.openStream()));
				String line;
				while ((line = in.readLine()) != null) {
					html.append(line);
					in.close();
				}
			} catch (IOException e) {
				// TODO Bloque catch generado automáticamente
				e.printStackTrace();
			}
			return html.toString();

		}
		//Resolucion DNS, IP, TCP 
		
		
	    private static void enviarCorreo() throws MalformedURLException {
	        String contenido = buscarAPI();
	    	Email email = new SimpleEmail();
	    	//
	        email.setHostName(SERVIDOR);
	        email.setSmtpPort(PORT);

	        email.setAuthentication(ServidorAPI.credencial, ServidorAPI.password);
	        email.setSSLOnConnect(true);
	        try {
	            email.setFrom(REMITENTE);
	            email.setSubject(SUBJECT);
	            email.setMsg(contenido);
	            email.addTo("ipereiroeusa@gmail.com");
	            email.send();
	            System.out.println(contenido);
	        } catch (EmailException e) {
	            e.printStackTrace();
	        }
	    }


	}

