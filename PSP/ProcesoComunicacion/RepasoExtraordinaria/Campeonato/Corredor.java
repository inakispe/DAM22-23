package Campeonato;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Random;

public class Corredor implements Runnable {
	static final long TIEMPO = 1000;
	int dorsal;
	private static final int METROS_TOTALES = 1000;
	int metrosTotales;
	int metrosAvanzados;
	static final int MINAVANCE = 50;
	static final int MAXAVANCE = 100;
	int puertoServidor;
	byte[] buffer = new byte[1024];

	public Corredor(int dorsal, int puertoservidor) {
		this.dorsal = dorsal;
		this.puertoServidor = puertoservidor;
	}

	@Override
	public void run() {
		//System.out.println("Soy el corredor " + dorsal + " voy a comenzar la carrera");
		try {
			DatagramSocket socketUDP= new DatagramSocket(puertoServidor);
			// El sycronized se puede hacer con cualquier objeto
			while (true) {
				DatagramPacket peticion = new DatagramPacket(buffer, buffer.length);
				socketUDP.receive(peticion);
				
			}
			
			synchronized (Campeonato.señalSalida) 
				{
					Campeonato.señalSalida.wait();

				}
				while (metrosAvanzados <= METROS_TOTALES) {
					String mensajeAvance = MetrosAvances();
					try {
						Thread.sleep(TIEMPO);
					} catch (InterruptedException e) {
						// TODO Bloque catch generado automáticamente
						e.printStackTrace();
					}

				}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public String MetrosAvances() {
		String mensaje = null;
		Random random = new Random();
		int avance = random.nextInt(MAXAVANCE - MINAVANCE + 1) + MINAVANCE;
		metrosAvanzados = +avance;
		mensaje = "Dorsal: " + dorsal + " ,avanza: " + metrosAvanzados;
		return mensaje;

	}
}
