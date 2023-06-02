package FicheroUDP;

import java.io.BufferedReader;
import java.io.FileReader;


public class Productor extends Thread {

	static String line;
	static int port;
	static Buffer buffer;
	BufferedReader reader;
	
	public Productor(String line, Buffer buffer) {
		// TODO Esbozo de constructor generado autom�ticamente
		this.line = line;
		this.buffer = buffer;
	}
	@Override
	public void run() {
		try {
			reader = new BufferedReader (new FileReader (line));
			String palabra="";
			while((palabra=reader.readLine())!=null) {
				if(rescatarPalindromos(palabra) && palabra.length()>1) {
					buffer.producir(palabra);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public boolean rescatarPalindromos(String palabra){
		String palabraRevertida = new StringBuilder(palabra).reverse().toString();
		if(palabra.equals(palabraRevertida)) {
			return true;
		}
		else {
			return false;
		}
	}

	/**@Override
	public void run() {
		// TODO Esbozo de m�todo generado autom�ticamente
		try {
			leerFichero(line);
		} catch (MalformedURLException e) {
			// TODO Bloque catch generado autom�ticamente
			e.printStackTrace();
		}
	}

	public static void leerFichero(String webUrl) throws MalformedURLException {
		URL url = new URL(webUrl);
		BufferedReader in;
		String palabra = null;
		String palabraInvert = new StringBuilder(palabra).reverse().toString();
		try {
			in = new BufferedReader(new InputStreamReader(url.openStream()));
			while ((palabra = in.readLine()) != null && palabraInvert == palabra) {
				buffer.producir(palabra);
				in.close();
			}
		} catch (IOException e) {
			// TODO Bloque catch generado autom�ticamente
			e.printStackTrace();
		}

	}*/

}
