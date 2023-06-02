import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ListaPrimosTCP {
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

	private Socket socket;
	public static int num1;
 	public static int num2;
	public static final int indice1 = 0;
	public static final int indice2 = 1;

	public ManejadorSolicitud(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		// TODO Esbozo de método generado automáticamente
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
		String[] numeros = tokens[1].split("/"); // separo los numeros
		num1 = Integer.parseInt(numeros[0]);
		num2 = Integer.parseInt(numeros[1]);
		System.out.println(num1 + " " + num2);
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

	private String construirHTML() {
		//Creamos un StringBuilder que hace que modifiquemos el String
		StringBuilder html= new StringBuilder();
		List <Integer> cantidadPrimos= new ArrayList<>();
		//Hacemos un foreach que recorra el Array
		for (Integer primo:cantidadPrimos) {
			html.append("<ul>\n");
			html.append("<li> "+primo+" <li>");
			html.append("<\n>");
		} 
		html.append("</ul>\n");
		return html.toString();
	
	}

	public static boolean esPrimo(int numero) {
		boolean primo = true;
		while (primo) {
			if (numero % 2 == 0)
				primo = false;
		}
		return primo;
	}

	public List<Integer> numerosPrimos() {
		List<Integer> cantidadPrimos = new ArrayList<>();

		for (int i = num1; i < num2; i++) {
			if (esPrimo(i)) {
				cantidadPrimos.add(i);
			}
		}
		return cantidadPrimos;
	}

	}
