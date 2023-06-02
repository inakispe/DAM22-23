package PyCUDP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

public class PruebaFichero {
	public static void main(String[] args) throws MalformedURLException {
		String webURL = "https://raw.githubusercontent.com/JorgeDuenasLerin/diccionario-espanol-txt/master/0_palabras_todas.txt";
		String codificacion = StandardCharsets.ISO_8859_1.toString();
		URL url = new URL(webURL);

		BufferedReader in;
		try {
			URLConnection conexion = url.openConnection();
			String tipoContenido = conexion.getContentType();
			int indiceInicioCodificacion = tipoContenido.indexOf("charset=");
			if (indiceInicioCodificacion != -1) {
				codificacion = tipoContenido.substring(indiceInicioCodificacion + 8);
			}
			in = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
			StringBuilder html = new StringBuilder();
			String line;
			while ((line = in.readLine()) != null) {
				html.append(line);
				in.close();
			}
		} catch (IOException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}

	}
}
