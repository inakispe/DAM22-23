import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class LecturaFicheros {
	private static String filePath;
	private static int numHilos;
	private static String cadenaCaracteres;
	public static final int numLetras=('z'-'a')+1;

	// Me lio con como especificar argumentos
	public static void main(String[] args) {
		// TODO Esbozo de método generado automáticamente

		if (args.length != 3) {
			System.out.println("Introduce los argumentos necesarios");
			System.exit(1);
		}
		filePath = args[0];
		numHilos = Integer.parseInt(args[1]);
		cadenaCaracteres = args[2];
		LecturaPalabra cuentaLetras;
		// Le meto por argumento una cantidad de Strings que no se, tambien le meto en
		// un último dato el numero de hilos que quier
		for (int f = 0; f < numHilos; f++) {
			cuentaLetras = new LecturaPalabra(filePath, cadenaCaracteres);
		}

	}

}

class LecturaPalabra implements Runnable {
	private String filePath;
	public String cadenaPalabras;

	public LecturaPalabra(String filePath, String cadenaPalabras) {
		super();
		this.filePath = filePath;
		this.cadenaPalabras = cadenaPalabras;
	}

	@Override
	public void run() {
		String line;
		// TODO Esbozo de método generado automáticamente
		try {
			BufferedReader lectura = new BufferedReader(new FileReader(filePath));
			if (cadenaPalabras == lectura.readLine()) {
				System.out.println(lectura);
			}
		} catch (FileNotFoundException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}

	}
}
