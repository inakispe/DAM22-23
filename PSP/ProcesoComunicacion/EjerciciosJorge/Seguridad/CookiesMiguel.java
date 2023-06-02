package Seguridad;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;

import javax.net.ssl.SSLSocketFactory;

public class CookiesMiguel {
	private final static String URL = "aulavirtual33.educa.madrid.org";
	private final static String USER = "miguel.segoviafreeman";
	private final static String PASSWORD = "";
	private static String nombreCookie = "";
	private static HashMap<String, String> cookieHash;

	public static void main(String[] args) {
		// establezco la conexi�n SSL al servidor
		SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
		try {
			// metodo que har� el post para conseguir la cookie
			socketPost(factory);
			// metodo que har� el get para conseguir el listado de cursos
			socketGet(factory);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void socketGet(SSLSocketFactory factory) throws UnknownHostException, IOException {
		System.out.println("NOMBRE COOKIE: " + nombreCookie + " COOKIE: " + cookieHash.get(nombreCookie));
		// Creas un socket en la URL que queramos en el puerto 443 que viene predefinido

		Socket socket = factory.createSocket(URL, 443);
		// Manda un mensaje
		PrintWriter out = new PrintWriter(socket.getOutputStream());
		BufferedReader getIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		// Coge esta url GET
		out.print("GET /ies.juandelacierva.madrid/my/ HTTP/1.1\r\n");
		// Le suma la URL que queremos
		out.print("Host: " + URL + "\r\n");
		// NombreCookie coge el nombre de la HashMap (Colecci�n de Key-Value,
		// nombreCookie es Key)
		out.print("Cookie: " + nombreCookie + "=" + cookieHash.get(nombreCookie) + "\r\n");
		out.print("\r\n");
		out.flush();
		String line;
		// Mientra no se ha nulo lee linea
		while ((line = getIn.readLine()) != null) {
			System.out.println(line);
		}
		// codigo para extraer cursos en el caso de que funcionase ...
	}

	private static void socketPost(SSLSocketFactory factory) throws IOException, UnknownHostException {
		Socket socket = factory.createSocket(URL, 443);
		extraerCookie(socket);
	}
	//La extraer Cookie conecta con la socket 
	private static void extraerCookie(Socket socket) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		PrintWriter out = new PrintWriter(socket.getOutputStream());
		
	//Aqui hacemos un POST para pushear nuestro USER y PASSWORD	
		String postBody = "username=" + USER + "&password=" + PASSWORD;
		out.print("POST /ies.juandelacierva.madrid/login/index.php HTTP/1.1\r\n");
		out.print("Host: " + URL + "\r\n");
		out.print("Content-Type: application/x-www-form-urlencoded\r\n");
		out.print("Content-Length: " + postBody.length() + "\r\n");
		out.print("Connection: close\r\n");
		out.print("\r\n");
		out.print(postBody);
		out.flush();

		String cookie = "";
		String line;
		while ((line = in.readLine()) != null) {
			System.out.println(line);
			if (line.startsWith("Set-Cookie: Moodle")) {
				cookie = line.split(";")[0].split("=")[1];
				nombreCookie = line.split(": ")[1].split("=")[0];
			}
		}
		cookieHash = new HashMap<>();
		cookieHash.put(nombreCookie, cookie);
		System.out.println("ESTA ES: " + cookie);
		in.close();
		out.close();
		socket.close();
	}
}