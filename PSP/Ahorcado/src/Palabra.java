import java.io.File;
import java.io.FileReader;

public class Palabra {
	
	String palabra;
	
	public Palabra() {
		// TODO Esbozo de constructor generado automáticamente
	}

	public String getPalabra() {
		return palabra;
	}

	public void setPalabra(String palabra) {
		this.palabra = palabra;
	}
	public static String Leer (File file) {
		String palabraServidor="";
		
		try {
			FileReader fileReader= new FileReader(file);
			int read;
			while ((read=fileReader.read())!=-1) {
				palabraServidor=charToString(((char)read));
			}
		} catch (Exception e) {
			System.err.println("Error de apertura");
		}
		
		return palabraServidor;
	}

	private static String charToString(char c) {
		// TODO Esbozo de método generado automáticamente
		return String.valueOf(c);
	}
	public static boolean Fallo(String palabra, String Letra) {
		boolean fallo=false;
		if (palabra.indexOf(Letra)==-1) {
			fallo=true;
		} else {
			fallo=true;
		}
		return fallo;
	}
}
