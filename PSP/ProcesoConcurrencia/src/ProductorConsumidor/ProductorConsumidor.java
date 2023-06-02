package ProductorConsumidor;

import java.nio.Buffer;
import java.util.Random;

//Consumidor si hay tarta le resto 1 a la variable
//Consumidor si no hay tarta aviso al cocinero y me duermo
//Cocinero no duermo esperando a que me llamen
//Cocinero si me llaman produzco los trozos de tarta
public class ProductorConsumidor implements Runnable {
	private static int CONSUMIDORES = 10;
	private static int COCINERO = 1;
	private static int DURMIENDO = 1000;
	private boolean consumidor;
	private static int TARTA = 0;
	private static Random cerrojo = new Random(); // Los cerrojos siempre son estaticos
	@Override
	public void run() {
		while (true) { // Creamos bucle infinito
			if (consumidor) {
				Consumiendo();

			} else {
			}
		}
	}

	private void Consumiendo() {
		//Synchronized para que entre todo
		synchronized (cerrojo) {
			if (TARTA > 0) {
				TARTA--;
				System.out.println("Quedan porciones " + TARTA+" porciones de tarta");
				//Se pone un sleep para que vaya quitando poco a poco
				try {
					Thread.sleep(DURMIENDO); // Creamos tiempos para que los hilos vayan más lentos
				} catch (InterruptedException e) {
					// TODO Bloque catch generado automáticamente
					e.printStackTrace();
				}
			} else {
				cerrojo.notifyAll(); // Si no hay tarta activamos todos, recordemos que no se puede activar uno solo
				try {
					cerrojo.wait(); // Y aqui nos quedamos esperando a que nos vuelvan a llamar.
				} catch (InterruptedException e) {
					// TODO Bloque catch generado automáticamente
					e.printStackTrace();
				}
			}
		}
	}

	private void Cocinando() {
		
		synchronized (cerrojo) {
			if (TARTA == 0) { /**
								 * Cuando tarta es 0, el cocinero actualiza y notifica al resto de que hay tarta
							 */
				TARTA = 10;
				System.out.print("Soy el concinero y ya hay " + TARTA +" de tarta.");
				System.out.println(" ");
				cerrojo.notifyAll(); // Notificamos a todos
			}
			try {
				cerrojo.wait(); // Una vez he activado syncronized me salgo del if y me quedo esperando
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

	}

	public ProductorConsumidor(boolean consumidor) {
		super();
		this.consumidor = consumidor;
	}

	public static void main(String[] args) {
		// TODO Esbozo de método generado automáticamente
		Thread[] hilos = new Thread[CONSUMIDORES + COCINERO];
		for (int i = 0; i < hilos.length; i++) {
			Runnable runnable = null;
			if (i != 0) { //Si i no es igual a cero es que hay consumidores y se activa la creacion de hilos
				runnable = new ProductorConsumidor(true);
			} else {
				runnable = new ProductorConsumidor(false);
			}
			hilos[i] = new Thread(runnable);
			hilos[i].start();
		}
		for (int i = 0; i < hilos.length; i++) {
			try {
				hilos[i].join();

			} catch (Exception e) {
				// TODO: handle exception
			}

		}

	}
}
