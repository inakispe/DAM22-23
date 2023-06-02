import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class ClienteMultiCastSocket {
	//Se crea un socket multicast en el puerto 12345:
	MulticastSocket ms = new MulticastSocket (12345);
	//Se configura la IP del grupo al que nos conectaremos:
	InetAddress grupo = InetAddress.getByName ("225.0.0.1");
	//Se une al grupo
	ms.joinGroup (grupo);
	//Recibe el paquete del servidor multicast:
	byte[] buf = new byte[1000];
	DatagramPacket recibido = new DatagramPacket(buf, buf.length);
	ms.receive(recibido);
	//Salimos del grupo multicast
	ms.leaveGroup (grupo);
	//Se cierra el socket:
	ms.close () ;

}
