package Ejercicio3;

import java.util.ArrayList;
import java.util.concurrent.ThreadPoolExecutor;

public class Main {
	public static final int ZERO = 0;
	public static final int TAMANOARRAYENTERO = 3200;
	public static final int CANTIDADPRIMOS = 10;
	public static final int NUMEROTHREADS=3;

	public static void main(String[] args) {

		threadPrincipal();

	}

	public static void threadPrincipal() {

		new Thread(() -> {
			ArrayList<Integer> arrayEnteros = generadorArray();

			comprobador(arrayEnteros);

		}).start();
	}

	private static void comprobador(ArrayList<Integer> arrayEnteros) {
		//Aqui añadir el tamaño del array que sea de 3
		ArrayList<Thread> threads = new ArrayList<>();
		
		ArrayList<String> resultados = new ArrayList<>();
		// Ahora aqui vamos con los numeros aleatorio
		
		//Y aqui terminar de arreglarlo para que salga
		for (int i = ZERO; i < NUMEROTHREADS; i++) {
			int indice1 = (int) (Math.random() * TAMANOARRAYENTERO);
			int indice2 = (int) (Math.random() * TAMANOARRAYENTERO);
			int indice3 = (int) (Math.random() * TAMANOARRAYENTERO);
			Thread thread = new Thread(new Runnable() {
				@Override
				public void run() {
					int numero1 = arrayEnteros.get(indice1);
					int numero2 = arrayEnteros.get(indice2);
					int numero3 = arrayEnteros.get(indice3);
					int suma = numero1 + numero2 + numero3;
					String resultado;
					int numerosPrimos = 0;
					while (numerosPrimos == CANTIDADPRIMOS) {
						if (esPrimo(suma) == true) {
							resultado = numero1 + ":" + numero2 + ":" + numero3;
							resultados.add(resultado);
							numerosPrimos++;
						}
					}
				}
			});
			threads.add(thread);
			thread.start();
		}
		// Esperar a que todos los hilos terminen
		for (Thread thread : threads) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		// Imprimir los resultados
		for (int i = 0; i < resultados.size(); i++) {
			System.out.println(resultados.get(i));
		}

	}

	public static ArrayList<Integer> generadorArray() {
		ArrayList<Integer> arrayEnteros = new ArrayList<>();
		while (arrayEnteros.size() <= 3200) {
			int entero = (int) (Math.random() * 10);
			arrayEnteros.add(entero);
		}
		return arrayEnteros;
	}

	public static boolean esPrimo(int numero) {
		if (numero <= 1) {
			return false;
		}
		for (int i = 2; i <= Math.sqrt(numero); i++) {
			if (numero % i == 0) {
				return false;
			}
		}
		return true;
	}
}
