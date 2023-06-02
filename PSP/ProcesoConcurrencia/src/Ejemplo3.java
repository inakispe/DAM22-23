import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Ejemplo3 {
public static void main (String [] args ) {
	Process p;
	String comando="CMD /C svchost.exe";
	try {
		p= Runtime.getRuntime().exec(comando);
		InputStream ip= p.getInputStream();
		BufferedReader br= new BufferedReader(new InputStreamReader(ip));
		String linea;
	} catch (IOException e) {
		// TODO Bloque catch generado automáticamente
		e.printStackTrace();
	}
	
}
}
