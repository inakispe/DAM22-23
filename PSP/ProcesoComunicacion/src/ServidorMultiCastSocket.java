import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class ServidorMultiCastSocket {
	//Se crea el socket multicast. No hace falta especificar puerto:
	MulticastSocket ms = new MulticastSocket ();
	//Se define el Puerto multicast:
	int Puerto = 12345;
	//Se crea el grupo multicast, aqui dice donde esta
	InetAddress grupo = InetAddress.getByName("225.0.0.1");
	String msg = "Bienvenidos!!";
	//Se crea el datagrama:
	DatagramPacket paquete = new DatagramPacket
	(msg.getBytes(), msg .length(),grupo, Puerto);
	//Se envia el paquete al grupo:
	ms.send(paquete);
	//Se cierra el socket:
	ms.close();

}
