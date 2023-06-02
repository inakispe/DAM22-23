import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class LeerFile {
	/*
	 * Modifica el Repaso7 para que al ejecutar el programa la entrada al proceso se
	 * obtenga de un fichero
	 */
	// File directorio=new File(".\\bin");
	// ProcessBuilder pb=new ProcessBuilder("java","psp_argumentos.Repaso7");
	// pb.directory(directorio);

	public static void main(String[] args) throws IOException {
	/*
	Modifica el Repaso7 para que al ejecutar el programa 
	la entrada al proceso se obtenga a partir de un fichero de texto
	*/
	//File directorio=new File(".\\bin");
	//File variables=new File("E:\\cadena.txt");
	//FileReader fr=new FileReader(variables);
	String texto="";
	String salida="*";
	String fin="";
	//Se ejecuta el proceso
	
	Process p=pb.start();
	
	try {
		FileReader fr= new FileReader("cadena.txt");
		BufferedReader bf=new BufferedReader(fr);
		OutputStream os=p.getOutputStream();
		while((texto=bf.readLine())!=null) {
			os.write((texto+"\n").getBytes());
		do {
			texto=bf.readLine();
			if(texto.equals(salida)) {
				System.exit(2);
			}
				System.out.println(texto);
			}while(!texto.equals(fin));
		}catch(Exception e){
			System.out.println("ERROR");
		}

	InputStream is = p.getInputStream();		
	 int c;
	 while ((c = is.read()) != -1)
		System.out.print((char) c);
	 is.close();

	// COMPROBACION DE ERROR - 0 bien - 1 mal
	int exitVal;
	try {
		exitVal = p.waitFor();
		System.out.println("Valor de Salida: " + exitVal);
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
}
