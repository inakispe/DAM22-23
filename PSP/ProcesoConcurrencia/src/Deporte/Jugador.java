package Deporte;

public class Jugador implements Runnable {
	private Fuerza fuerza;
	private int id;
	private final int TIEMPO = 100;

	public Jugador(int id, Fuerza fuerza) {
		this.fuerza = fuerza;
		this.id = id;
	}

	public void run() {
		System.out.println("El jugador " + id + " en posicion");
		try {
			Thread.sleep(TIEMPO);
			if (fuerza.marcadorJugador.size() != 10) {
				synchronized (fuerza) {
					fuerza.getFuerza();

					fuerza.anadirPunto(id);
					fuerza.cambiaTurno();
				}

			} else {
				fuerza.puntuacion();
			}

		} catch (InterruptedException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
	}
}
