package NumerosTrabajo;

import java.util.ArrayList;

public class DivisorNumeros implements Runnable {
	private int startIndex = 1;
	private int endIndex;
	private static int numero;
	ArrayList<Integer> listadoDivisores;
	public DivisorNumeros(int startIndex, int endIndex, int numero) {
		super();
		this.startIndex = startIndex;
		this.endIndex = endIndex;
		this.numero = numero;
	}

	@Override
	public void run() {
		// TODO Esbozo de método generado automáticamente
		for(int i=startIndex;i<endIndex;i++) {
			if((dividirEntero(i)) && esPrimo(i)) {
				System.out.println(" "+i);
			}
		}
	}
	public static boolean dividirEntero (int i) {
		return (numero%i)==0;
	}
    public static boolean esPrimo(int numero) {
        if (numero <= 1) {
            return false; // Los números menores o iguales a 1 no son primos
        }

        for (int i = 2; i <= Math.sqrt(numero); i++) {
            if (numero % i == 0) {
                return false; // Si el número es divisible por algún número entre 2 y su raíz cuadrada, no es primo
            }
        }

        return true; // El número es primo
    }

	public ArrayList<Integer> getListadoDivisores() {
		return listadoDivisores;
	}

}
