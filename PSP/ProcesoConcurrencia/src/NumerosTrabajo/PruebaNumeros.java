package NumerosTrabajo;

import java.util.ArrayList;

public class PruebaNumeros implements Runnable {
	int startIndex;
	int endIndex;
	static int numeroDividir;
	ArrayList<Integer> arrayPrimos;
	public PruebaNumeros(int startIndex, int endIndex, int numeroDividir) {
		super();
		this.startIndex = startIndex;
		this.endIndex = endIndex;
		this.numeroDividir = numeroDividir;
	}

	@Override
	public void run() {
		// TODO Esbozo de método generado automáticamente
		for (int i=startIndex;i<endIndex;i++) {
			if (esPrimo(i)&&dividirEntero(i)) {
				System.out.println(i);;
			}
		}
	}

	public static boolean esPrimo(int numero) {
		if (numero <= 1) {
		}			return false; // Los números menores o iguales a 1 no son primos
		}

		for (int i = 2; i <= Math.sqrt(numero); i++) {
			if (numero % i == 0) {
				return false; // Si el número es divisible por algún número entre 2 y su raíz cuadrada, no es
								// primo
			}
		}

		return true; // El número es primo
	}
	public static boolean dividirEntero (int i) {
		return (numeroDividir%i)==0;
	}
	public ArrayList<Integer> getArrayPrimos() {
		return arrayPrimos;
	}
	
}
