import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Path;

public class RepasoFile {
	//Vamos a crear un programa que ejecute otro programa
	public static void main(String[] args) throws IOException {
		// TODO Esbozo de método generado automáticamente
		//Aquí metemos el directorio donde se encuentra el ejemplo
		String path=("C:\\Users\\ipere\\Desktop\\hola.txt");
		//Aqui creamos el objeto donde se rellena
		File f = new File(path);
		//Aqui creamos el proceso que se va ejecutar y en el método que se va a ejecutar
		ProcessBuilder pb= new ProcessBuilder("java","RepasoFile.java");
		//Se usa el método directory que te indica donde se encuentra el ejecutable
		pb.directory(f);
		System.out.printf("El directorio de trabajo es %s%n",pb.directory());
		//Si queremos que se nos cargue en un fichero txt
		File fOut= new File("C:\\Users\\ipere\\Desktop\\mola.txt");
		pb.redirectOutput(fOut);
		
		//se ejecuta el proceso, el start solo ejecuta pero no muestra */
		
		try {
		 	Process p=pb.start();
			InputStream is= p.getInputStream();
			BufferedReader br= new BufferedReader(new InputStreamReader(is));
			String linea;
			while((linea=br.readLine())!= null){
				System.out.println("El proceso es "+(linea));
			}
		}catch (Exception e) {
			// TODO: handle exception
		} 
		
	}

}
