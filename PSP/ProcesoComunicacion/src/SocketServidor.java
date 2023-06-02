import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServidor {

	public static void main(String[] args) {
		// TODO Esbozo de método generado automáticamente

		int port= 6000;
		try (ServerSocket servidor= new ServerSocket(port)) {
			
			System.out.println("El servidor esta en el puerto"+servidor.getLocalPort());
			
			while (true) {
				Socket socket = servidor.accept();				
                DataInputStream input = new DataInputStream(socket.getInputStream());
                DataOutputStream output = new DataOutputStream(socket.getOutputStream());
    			
                while (!msg.trim().equals("*")) {
    				// Recibe el paquete del servidor multicast
    				DatagramPacket paquete = new DatagramPacket(buf, buf.length);
    				ms.receive(paquete);
    				msg = new String(paquete.getData());
    				System.out.println("Recibo: " + msg.trim());
    				ms.leaveGroup(grupo); // abandonamos grupo Sims .close (); //cierra socket
    				System.out.println("Socket cerrado...");
    			}
			}
		} catch (IOException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
		
	}

}
