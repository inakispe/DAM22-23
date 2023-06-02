import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Escribir_fichero {
	
	public static void main(String[] args) {
		int contador=0;
		int dato_entra[]= new int[128]; 
		
		try {
			FileInputStream archivo_lectura = new FileInputStream("C:/Users/ipere/Desktop/autor.txt.txt");
			boolean final_ar = false;
			while (!final_ar) {
				int byte_entrada = archivo_lectura.read();
				if (byte_entrada !=-1)
					dato_entra[contador]=byte_entrada;
				else
					final_ar = true;
				System.out.println(dato_entra[contador]);
				contador++;
			}
			archivo_lectura.close();
		} catch (IOException e) {
			System.out.println("No se ha encontrado archivo");

		}
		crea_ficherto (dato_entra);
	}
	static void crea_ficherto(int datos_fichero[]) {
		try {
			FileOutputStream fichero_nuevo= new FileOutputStream ("C:/Users/ipere/Desktop/autor_copia.txt");
			for (int i=0;i<datos_fichero.length;i++) {
				fichero_nuevo.write(datos_fichero[i]);
			}
		} catch (IOException e) {}
	}
}

/*
 * class imprimirFichero{ public void escribir () { try { FileOutputStream
 * archivo_escritura= new FileOutputStream ("C:/Users/ipere/Desktop/copia.txt");
 * 
 * } catch (IOException x) { System.out.println(); } }
 * 
 * } }
 */
}
