package Campeonat;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import PyCUDP.Consumir;

public class Campeonato {
	public static final int NUMERO_CORREDORES = 10;
	public static int port;
	public static int NUM_PARAMS = 1;
	Object señalSalida= new Object();
	public static void main(String[] args) {

		if (args.length != NUM_PARAMS) {
			System.out.println("Error en n�mero de par�metros");
		} else {
			port = Integer.parseInt(args[0]);
		}
		try {
			DatagramSocket socketUDP = new DatagramSocket(port);
			
			Thread[] corredores = new Thread[NUMERO_CORREDORES];
			for (int i = 0; i < NUMERO_CORREDORES; i++) {
				// Primero se crean los hilos
				corredores[i] = new Thread(new Corredor(socketUDP, (i + 1)));
				corredores[i].start();
			} /**
				 * se crea el socket en este puerto, recordemos que el servidor no sabe quien se
				 * conecta
				 */

		} catch (IOException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}

	}
}
