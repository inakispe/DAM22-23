import java.util.List;

public class Bingero implements Runnable {
	String nombre;
	Bola bola;
	String carton;
	int[] numeros;

	public Bingero(String nombre, Bola bola, String carton) {
		super();
		this.nombre = nombre;
		this.bola = bola;
		this.carton = carton;
	}

	@Override
	public void run() {
		while (true) {
			// El objeto bola tiene que usarse
			synchronized (bola) {
				try {
					System.out.println(nombre + " esperando bola");
					// Cuando se notifica se ejecuta
					System.out.println("Estos son mis numeros " + carton);
					bola.wait();
					tengoBingo();
				} catch (InterruptedException e) {
					// TODO Bloque catch generado automáticamente
					e.printStackTrace();
				}

			}
		}
	}

	public int[] verNumeros(String carton) {
		String[] cadenaNumeros = carton.split("(?<=.)");
		int[] numeros = new int[cadenaNumeros.length];
		for (int i = 0; i < numeros.length; i++) {
			numeros[i] = Integer.parseInt(cadenaNumeros[i]);
		}
		return numeros;
	}

	public boolean estaDentro(int numero, int[] carton) {
		for (int i = 0; i < carton.length; i++) {
			if (carton[i] == numero) {
				return true;
			}
		}
		return false;
	}

	public void tengoBingo() {
		int bingo = 0;
		numeros=verNumeros(carton);
 		try {
			bola.wait();
			boolean encontrado = estaDentro(bola.getNumero(), numeros);
			while (bingo != 3) {
				if (encontrado) {
					System.out.println("El número " + bola.getNumero() + " está presente en la cadena.");
					bingo++;
				}
			}
			System.out.println("BINGO!!!");
			
			System.exit(bingo);
		} catch (InterruptedException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}

	}
}