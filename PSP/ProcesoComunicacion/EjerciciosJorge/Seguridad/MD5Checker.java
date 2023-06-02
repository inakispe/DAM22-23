package Seguridad;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
 * Este codigo se tiene que hacer en linux por el comando.
 * @author ipere
 *
 */
public class MD5Checker {
	private static final int MD5_LENGTH=32;
	
	public static void main(String[] args) {
		// TODO Esbozo de método generado automáticamente
		HashMap<String, String> mapa= new HashMap<String,String>(); //Con el HashMapa??
		mapa.put("Hola", "h");
		mapa.put("Mal","m");
		mapa.put("Adios", "bye");
		for (String k:mapa.keySet()) {
				//Utilizo y calculo su md5Sum
			String md5Calculado= calcularMd5(k);
			//Obtengo el m5sum del hashmap
			String m5Almacenado=mapa.get(k);
			//Compruebo si son iguales
			if (md5Calculado.equals(m5Almacenado)) {
				System.out.println(k+"Tienes el MD5 bien");
			} else {
				System.out.println("Error en la suma de verificacion");
			}
		}
	}

	private static String calcularMd5(String k) {
		// TODO Esbozo de método generado automáticamente
		Process sbd;

		try {
			sbd = new ProcessBuilder("md5sum").start();
			DataOutputStream out = new DataOutputStream(null);
			out.write(k.getBytes());
			BufferedReader in = new BufferedReader(new InputStreamReader(sbd.getInputStream()));
			String md5sum=in.readLine();
			md5sum= md5sum.substring(0, MD5_LENGTH);
			in.close();
			out.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return null;
	}

}
