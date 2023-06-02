import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class clienteTCPChallenge {
	public static String ip;
	public static int port;
	public static String pregunta;
	public static String respuesta;
	public static int PUNTUACION;
	public static int numeroPregunta=0;
	
	public static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		// TODO Esbozo de método generado automáticamente
		if (args.length < 2) {
			System.out.println("Tienes que introducir más argumentos");
			return;
		} else {
			port = Integer.parseInt(args[1]);
			ip = args[0];
		}
		
		try {
			Socket socket = new Socket(ip, port);
			// Encargado de enviar
			DataOutputStream outTCP = new DataOutputStream(socket.getOutputStream());

			// Encargado de leer
			DataInputStream inTCP = new DataInputStream(socket.getInputStream());
			do {
			pregunta= inTCP.readUTF();
			
			System.out.println("Pregunta: " + pregunta);

			respuesta = sc.nextLine();

			outTCP.writeUTF(respuesta);
			
			outTCP.flush();
			
			PUNTUACION= inTCP.readInt();
			
			System.out.println(PUNTUACION);
			
			numeroPregunta++;
			
			} while (numeroPregunta!=10);
			
			socket.close();
			outTCP.close();
			inTCP.close();
			sc.close();
		} catch (IOException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
	}


}
