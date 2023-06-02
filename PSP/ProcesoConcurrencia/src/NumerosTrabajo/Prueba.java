package NumerosTrabajo;

public class Prueba {
	public static final int NUM_PARAMS = 2;
	public static final int tresMil = 3000;
	public static final int numMinThreads = 2;
	public static final int numMaxThreads = 10;
	public static final int ZERO = 0;

	public static void main(String[] args) {

		int numeroDividir;

		int numeroHilos;
		// Se divide para que sea vea cuantos threads
		int cantidadNumerosTh;

		if (args.length != NUM_PARAMS) {
			System.err.println("Número de parametros erróneos");
			return;
		}
		numeroDividir = Integer.parseInt(args[0]);
		numeroHilos = Integer.parseInt(args[1]);
		if (numeroDividir < tresMil || numeroHilos < numMinThreads || numeroHilos > numMaxThreads) {
			System.err.println("Los argumentos han fallado");
			return;
		} else {
			System.out.println("Los numeros son:"+numeroDividir+" y "+ numeroHilos);
		}
		Thread[] trabajadores = new Thread[numeroHilos];
		PruebaNumeros[] divisores = new PruebaNumeros[numeroHilos];

		cantidadNumerosTh = numeroDividir / numeroHilos;
		// Cuidado con esto
		int cantidadRestante = numeroDividir % numeroHilos;

		for (int i = ZERO; i < numeroHilos; i++) {
			int starIndex = i * cantidadNumerosTh;
			int endIndex = starIndex + cantidadNumerosTh;
			if (i == numeroHilos - 1 && cantidadRestante != 0) {
				endIndex += cantidadRestante;
			}
			divisores[i] = new PruebaNumeros(starIndex, endIndex, numeroDividir);
			trabajadores[i] = new Thread(divisores[i]);
			trabajadores[i].start();
		}
		for (int i = ZERO; i < numeroHilos; i++) {
			try {
				trabajadores[i].join();
			} catch (InterruptedException e) {
				// TODO Bloque catch generado automáticamente
				e.printStackTrace();
			}
			
		}
	}
}
