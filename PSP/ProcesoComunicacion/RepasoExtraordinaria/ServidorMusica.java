import java.io.File;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.util.ArrayList;
//Thread en el main solo introducimos los argumentos para crear el SOCKET, 
//vamos creando nuevos socket ya hacemos actuaciones con Runnable en el que cada hilo vaya ejecutANDO
//Hacerlo de nuevo


public class ServidorMusica {
	public static int port;
	public static String rootDirectorio;
	public static final String PEDIR = "GET";
	public static final String EJECUTAR = "PLAY";
	public static int pista;
	public static void main(String[] args) {

		byte[] receiveData = new byte[1024];
		if (args.length < 2) {
			System.out.println("Tienes que introducir más argumentos");
			return;
		} else {
			port = Integer.parseInt(args[1]);
			rootDirectorio = args[0];
		}

		try {
			// Se crea un Socket en el puerto
			
			DatagramSocket socket = new DatagramSocket(port);

			while (true) {
				DatagramPacket peticion = new DatagramPacket(receiveData, receiveData.length);
				socket.receive(peticion);
				
				
				String accion = new String(peticion.getData());
				
				if (obtenerPalabra(accion).equalsIgnoreCase(PEDIR)) {
					String canciones = getDatos(rootDirectorio);
					enviarDatos(peticion, canciones);
				} else if (obtenerPalabra(accion).equalsIgnoreCase(EJECUTAR)) {
					playSong(rootDirectorio,accion);
				}
			}
		
		} catch (IOException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
	}
	// Crear un método que en claso de que los argumentos sean dos y el primer
	// argumento sea play accion al nuúmero de listado crero que esta el método ya
	// creado

	private static void enviarDatos(DatagramPacket manda, String canciones) {
		// TODO Esbozo de método generado automáticamente
		byte[] buffer = canciones.getBytes();
		InetAddress direccionCliente= manda.getAddress();
		int portClient=manda.getPort();
		
		DatagramPacket respuesta = new DatagramPacket(buffer, buffer.length, direccionCliente, portClient);
	}

	public static String getDatos (String rutaDirectorio) {
		File directorio = new File(rutaDirectorio);
		File[] archivos = directorio.listFiles();
		ArrayList<String> archivosWav = new ArrayList<>();
		for (File archivo : archivos) {
			if (archivo.isFile() && archivo.getName().endsWith(".wav")) {
				archivosWav.add(archivo.getName());
			}
		}
		String archivosString= archivosWav.toString();
		
		return archivosString;
	}

	public static int obtenerNumero(String texto) {
		String[] partes = texto.split("\\s");
		int numero = Integer.parseInt(partes[1]);
		return numero;
	}
	
	public static void playSong (String datosTexto, String ruta) {
		MakeSound speaker = new MakeSound();
		int numeroObtenido= obtenerNumero(datosTexto);
		File directorio = new File(ruta);
		File[] archivos = directorio.listFiles();
		//Cambiar el File archivos a String
		String rutaCancionElegida= archivos[numeroObtenido].getAbsolutePath();
		speaker.playSound(rutaCancionElegida);
	}
	public static String obtenerPalabra(String datosInfo) {
		String [] partes= datosInfo.split("\\s");
		String palabra= partes[0];
		return palabra;
	}
}
 