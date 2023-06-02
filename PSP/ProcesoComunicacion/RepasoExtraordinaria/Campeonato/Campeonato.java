package Campeonato;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Campeonato {
	public static final int NUMERO_CORREDORES = 3;
	public static Object señalSalida = new Object();
	public static final int PUERTOSERVIDOR = 2322;
	public final static String palabra = "La carrera no va a iniciar";

	public static void main(String[] args) {
		try {
			DatagramSocket socketUDP = new DatagramSocket(PUERTOSERVIDOR);
			byte[] bufer = new byte[1000];
			System.out.println("Iniciando el servidor UDP");
			// Lo metemos en un while porque acepta todas las peticiones
			Thread[] corredores = new Thread[NUMERO_CORREDORES];
			for (int i = 0; i < NUMERO_CORREDORES; i++) {
				// Primero se crean los hilos
				corredores[i] = new Thread(new Corredor((i + 1),PUERTOSERVIDOR));
			}
			for (Thread corredor : corredores) {
				corredor.start();
			}
			for (Thread corredor : corredores) {

				try {
					corredor.join();
				} catch (InterruptedException e) {
					// TODO Bloque catch generado automáticamente
					e.printStackTrace();
				}
			}

			while (true) {
				DatagramPacket peticion = new DatagramPacket(bufer, bufer.length);
				// Leemos si nos hacen una petición en el socket
				socketUDP.receive(peticion);
				System.out.println("Bienvenido al chat: " + peticion.getAddress());
				if (peticion.toString().equalsIgnoreCase("salida")) {
					señalSalida = true;
					synchronized (señalSalida) {
						señalSalida.notifyAll();
					}

				} /*else {
					bufer = palabra.getBytes();
					DatagramPacket respuesta = new DatagramPacket(bufer, bufer.length,
							InetAddress.getByName("255.255.255.255"), PUERTOSERVIDOR);
					System.out.println("Enviamos respuesta al cliente");
					socketUDP.send(respuesta);
				}*/

			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}
}
