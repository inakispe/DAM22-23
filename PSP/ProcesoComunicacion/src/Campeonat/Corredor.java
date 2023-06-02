package Campeonat;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Random;

import Campeonato.Campeonato;

public class Corredor implements Runnable {
	DatagramSocket socketUDP;
	int dorsal;
	static final long TIEMPO = 1000;
	private static final int METROS_TOTALES = 1000;
	private static final String COMENZAR = "Comenzar";
	int metrosTotales;
	int metrosAvanzados;
	static final int MINAVANCE = 50;
	static final int MAXAVANCE = 100;
	private static String comentarioCarrera;
	public Corredor(DatagramSocket socketUDP, int dorsal) {
		this.socketUDP = socketUDP;
		this.dorsal = dorsal;
	}

	@Override
	public void run() {
		// TODO Esbozo de método generado automáticamente
		while (true) {
			byte[] buffer = new byte[1024];
			DatagramPacket peticion = new DatagramPacket(buffer, buffer.length); // Que sea una peticion de tamaño y
																					// tipo de contenido del buffer
			try {
				socketUDP.receive(peticion);
			} catch (IOException e) {
				// TODO Bloque catch generado automáticamente
				e.printStackTrace();
			} // Aqui como recive la señal de que esta haciendo una peticion
			System.out.println("Recibo la informacion desde el cliente");
			String mensaje = new String(peticion.getData()); // ´
			int puertoCliente = peticion.getPort();// Aqui cogemos el puerto de la petición para mandarle el mensaje
			// de vuelta
			String mensajeSin=eliminaUltimaLetra(mensaje);
			if (mensajeSin.equalsIgnoreCase(COMENZAR)) {
				synchronized (Campeonato.señalSalida) {
					{
						while (metrosAvanzados <= METROS_TOTALES) {
							Random random = new Random();
							int avance = random.nextInt(MAXAVANCE - MINAVANCE + 1) + MINAVANCE;
							metrosAvanzados = +avance;
							comentarioCarrera="Dorsal: " + dorsal + " ,avanza: " + metrosAvanzados;
							InetAddress direccionIP = peticion.getAddress();
							buffer = comentarioCarrera.getBytes();
							DatagramPacket respuesta = new DatagramPacket(buffer, buffer.length, direccionIP, puertoCliente);
							try {
								socketUDP.send(respuesta);
								Thread.sleep(TIEMPO);
							} catch (InterruptedException e) {
								// TODO Bloque catch generado automáticamente
								e.printStackTrace();
							} catch (IOException e) {
								// TODO Bloque catch generado automáticamente
								e.printStackTrace();
							}

						}

					}
				}
			} else {
				System.out.println("La carrera no va a empezar");
			}
		}
	}
	private static String eliminaUltimaLetra(String entrada) {
		return entrada.substring(0, entrada.length()-1);
	}
}