import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Ejemplo2InputStream {

	public static void main(String[] args) {
		// TODO Esbozo de método generado automáticamente
		//Clase procesos
		Process p = null;
		try {
			p=Runtime.getRuntime().exec("CMD /C DIR");
			InputStream i = p.getInputStream();
			BufferedReader br= new BufferedReader(new InputStreamReader(i));
			String linea;
			while((linea= br.readLine())!=null) {
				System.out.println(linea);
				br.close(); //Hay que cerrar el buffer
			}
		}catch (Exception e) {
			
		}
		int exitVal;
		try {
			/*El waitFor() espera a que el subproceso
			representado en process termine*/
			exitVal=p.waitFor();
			System.out.println("Valor de salida:"+ exitVal);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		/*Hemos completado el código con un mensaje 
	que aparece en pantalla si el comando esta mal escrito*/
		try {
			InputStream er= p.getErrorStream();
			BufferedReader ber= new BufferedReader(new InputStreamReader(er));
			String liner= null;
			while ((liner=ber.readLine()) != null ) {
				System.out.println("ERROR "+liner);
			}
		}catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

