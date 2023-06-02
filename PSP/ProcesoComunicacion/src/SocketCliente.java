import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
public class SocketCliente {
	static final int puerto=5000;
	public static void main(String[] args) {
		// TODO Esbozo de método generado automáticamente
		// FLUJO PARA ENTRADA ESTANDAR
		BufferedReader in = new BufferedReader
		(new InputStreamReader(System.in));
		DatagramSocket clientSocket = new DatagramSocket();
		InetAddress IPServidor = InetAddress.getLocalHost ();
		
		byte[] enviados = new byte[1024];
		System.out.println("Introduce ancho ");
		String ancho= in.readLine();
		
		System.out.println("Introduce alto");
		String alto= in.readLine();
		
		try {
			clientSocket= new Socket(host,port);
			
			DataOutputStream flujoSalida= new DataOutputStream(socketCliente.getOutputStream());
			DataInputStream flujoEntrada= new DataInputStream(socketCliente.getInputStream());
		} catch (IOException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}

	}
}
