package VectorPrimos;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {

		threadPrincipal();

	}
	public static void threadPrincipal() {
		new Thread(() -> {
			
			ArrayList<Integer> arrayPrimos = generadoresPrimos();
			
			comprobador(arrayPrimos);
			
		}).start();
		;
	}
	private static void comprobador(ArrayList<Integer> arrayPrimos) {
		
		ArrayList<Thread> threads = new ArrayList<>();
        ArrayList<Boolean> resultados = new ArrayList<>();

        for (int i = 0; i < arrayPrimos.size(); i += 2) {
            int indicePar = i;
            int indiceImpar = i + 1;

            if (indiceImpar < arrayPrimos.size()) {
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        int par = arrayPrimos.get(indicePar);
                        int impar = arrayPrimos.get(indiceImpar);
                        int suma = par + impar;
                        boolean resultado = esPrimo(suma);
                        resultados.add(resultado);
                    }
                });

                threads.add(thread);
                thread.start();
            }
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
            System.out.println("La suma " + (i + 1) + " es primo: " + resultados.get(i));
        }
    }
		

	private static ArrayList<Integer> generadoresPrimos() {
		ArrayList<Integer> arrayPrimos = new ArrayList<>();

		ArrayList<Thread> threads = new ArrayList<>();

		while (arrayPrimos.size() < 256) {
			int primo = (int) (Math.random() * 1000);

			Thread thread = new Thread(new Runnable() {
				@Override
				public void run() {
					if (esPrimo(primo)) {
						arrayPrimos.add(primo);
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
		
		return arrayPrimos;
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
