

	import java.io.BufferedReader;
	import java.io.IOException;
	import java.io.InputStreamReader;
	import java.util.regex.Matcher;
	import java.util.regex.Pattern;

	public class Èjercicio3ExamenPsP {
		private static String ENCUENTRA_IP="^.*IPv4[. ]*: (.*)$";
		private static boolean newLine;
		//Lo que le metemos para comparar, lo que comparamos
		private static void extract(String extractor, String linea, boolean verdadero) {
			//El método Pattern usa el compile y se le añade 
			Pattern p= Pattern.compile(extractor);
			//El método Matcher te dice si la linea es igual a lo que era
			Matcher m= p.matcher(linea);
			//El método find te indica si lo has encontrado
			if (m.find()) {
				System.out.println(m.group(1)+ "\t");
				if (verdadero) {
					System.out.println();
				}
			}
		};
		public static void main(String[] args) {
			// TODO Esbozo de método generado automáticamente
			Process p;
			try {
				p = new ProcessBuilder("CMD","/C","IPCONFIG").start();
				BufferedReader br= new BufferedReader(new InputStreamReader(p.getInputStream()));
				String linea;
				while((linea=br.readLine())!=null) {
					RepasoIP.extract(ENCUENTRA_IP,linea,true);
					System.out.println(linea);
				}
		} catch (IOException E) {
			// TODO: handle exception
		}
		}

	}
