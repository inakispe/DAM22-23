package EjerciciosHilos;

import java.util.Random;

/*Implementa un programa en Java
que utilice la técnica de productor-consumidor 
para compartir datos entre threads. 
El productor debe generar números 
aleatorios y el consumidor debe imprimirlos en pantalla.*/
public class Ejercicio2 implements Runnable {
	private static Random cerrojo = new Random();
	private static int CONSUMIDORES = 10;
	private static int PRODUCTORES = 1;
	private static int TIEMPO = 1000;
	boolean consumidor = true;
	Random numeros = new Random();
	private static boolean hayContenido = false;
	private static int numero;
	
	public static void main(String[] args) {
		// TODO Esbozo de método generado automáticamente
		Thread[] hilos = new Thread[PRODUCTORES + CONSUMIDORES];

		for (int i = 0; i < hilos.length; i++) {
			Runnable runnable = null;
			if (i != 0) {
				runnable = new Ejercicio2(true);
			} else {
				runnable = new Ejercicio2(false);
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

	public Ejercicio2(boolean consumidor) {
		super();
		// TODO Esbozo de constructor generado automáticamente
		this.consumidor = consumidor;
	}

	@Override
	public void run() {
		// TODO Esbozo de método generado automáticamente
		while (true) {
				Imprimiendo();
				Produciendo();
			}

		}


	private void Produciendo() {
		synchronized (cerrojo) {
				 numero= (int) numeros.nextInt(1000);
		
				hayContenido = true;
				System.out.println("Soy el productor y estoy produciendo estos numeros " + numero);
				try {
					Thread.sleep(TIEMPO);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}


	private void Imprimiendo() {
		synchronized (cerrojo) {
			if (hayContenido = true) {
				System.out.println("Soy el consumidor y estoy imprimiendo estos numero " + numero);
				hayContenido = false;
			}
		}

	}
}