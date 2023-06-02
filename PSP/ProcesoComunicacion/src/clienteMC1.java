import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class clienteMC1 {

	public static void main(String[] args) throws IOException {
		// TODO Esbozo de método generado automáticamente
		// Se crea el socket multicast
		int Puerto = 12345;// Puerto multicast
		MulticastSocket ms = new MulticastSocket(Puerto);
		try {
			InetAddress grupo = InetAddress.getByName("225.0.0.1");// Grupo
			// Nos unimos al grupo
			ms.joinGroup(grupo);
			String msg = "";
			byte[] buf = new byte[1000];
			while (!msg.trim().equals("*")) {
				// Recibe el paquete del servidor multicast
				DatagramPacket paquete = new DatagramPacket(buf, buf.length);
				ms.receive(paquete);
				msg = new String(paquete.getData());
				System.out.println("Recibo: " + msg.trim());
				ms.leaveGroup(grupo); // abandonamos grupo Sims .close (); //cierra socket
				System.out.println("Socket cerrado...");
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}