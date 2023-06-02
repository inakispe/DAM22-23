package EjerciciosHilos;

import java.util.Random;

/*
 * Suma de números en paralelo: Escribe un programa en Java que genere un array de 1000 números aleatorio 
//y los sume en paralelo utilizando 4 threads. Sin números mágicos. Cada thread debe sumar una parte del array 
//y el resultado final debe ser la suma de todas las partes.
 */
public class Ejercicio1 implements Runnable {
	private int id;
	private static int ARRAY_LENGTH = 1000;
	private static int HILOS = 4;
	private static int PARTES = ARRAY_LENGTH / HILOS;
	private static Random cerrojo = new Random(); // Los cerrojos siempre son estaticos
	private static int cont = 0;
	private static int sumaPar = 0;
	private static int sumImpar = 0;

	@Override
	public void run() {
		Random numeros = new Random(System.nanoTime());
		synchronized (cerrojo) // Aquí van entrando en la cola
		{
			numeros.nextInt(10000);
		}
	}

	public static void main(String[] args) {
		// TODO Esbozo de método generado automáticamente
		int[] array= generarArray();
		int[] partes= dividirArray(partes);
		Thread[] hilos = new Thread[HILOS];
	
		int listaNumero []= new int[10];
		for (int i = 0; i < hilos.length; i++) {
			Runnable runnable = new SumaParImpar(numeros.nextInt(10));
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

	private static int[] dividirArray(int[] array) {
		// TODO Esbozo de método generado automáticamente
		int[] partes = new int[HILOS];
		for (int i = 0; i < HILOS; i++) {
			int comienzo = i * HILOS;
			int end = comienzo + PARTES;
			int sumarParte = 0;
			for (int j = comienzo; j < end; j++) {
				sumarParte += array[j];
			}
			partes[i] = sumarParte;
		}

		return partes;
	}

	private static int[] generarArray() {
		// TODO Esbozo de método generado automáticamente
		Random random = new Random();
		int[] array = new int[ARRAY_LENGTH];
		return array;
	}

}
