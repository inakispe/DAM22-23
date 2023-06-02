import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RepasoIP {
	private static String cabecera="^.*IPv4[. ]*: (.*)$";
	//private static String pr ="^.ignacio$";
	private static boolean verdadero= true;
	//Lo que le metemos para comparar, lo que comparamos
	static void extract(String extractor, String linea, boolean verdadero) {
		//El m�todo Pattern usa el compile y se le a�ade 
		Pattern p= Pattern.compile(extractor);
		//El m�todo Matcher te dice si la linea es igual a lo que era
		Matcher m= p.matcher(linea);
		//El m�todo find te indica si lo has encontrado
		if (m.find()) {
			System.out.println(m.group(1)+ "\t");
			if (verdadero) {
				System.out.println();
			}
		}
	};
	public static void main(String[] args) {
		// TODO Esbozo de m�todo generado autom�ticamente
		Process p;
		try {
			p = new ProcessBuilder("ps","aux").start();
			BufferedReader br= new BufferedReader(new InputStreamReader(p.getInputStream()));
			String linea;
	
			while((linea=br.readLine())!=null) {
				RepasoIP.extract(cabecera,linea,verdadero);
				System.out.println(linea);
			}
	} catch (IOException E) {
		// TODO: handle exception
	}
	}

}
