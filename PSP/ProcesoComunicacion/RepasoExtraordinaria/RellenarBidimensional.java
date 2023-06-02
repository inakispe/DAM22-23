	import java.io.BufferedReader;
	import java.io.FileReader;
	import java.io.IOException;


public class RellenarBidimensional {

	    public static void main(String[] args) {
	        String[][] miArrayBidimensional = new String[10][6];
	        String archivo = "C:\\Users\\ipere\\Desktop\\hola.txt";
	        String separador = ".";

	        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
	            String linea;
	            int fila = 0;

	            while ((linea = br.readLine()) != null) {
	                String[] elementos = linea.split(separador);

	                for (int i = 0; i < elementos.length; i++) {
	                    miArrayBidimensional[fila][i] = elementos[i];
	                }

	                fila++;
	            }
	        } catch (IOException e) {
	            System.out.println("Error al leer el archivo " + archivo + ": " + e.getMessage());
	        }

	        // Imprimir los valores del array
	        for (int i = 0; i < miArrayBidimensional.length; i++) {
	            for (int j = 0; j < miArrayBidimensional[i].length; j++) {
	                System.out.print(miArrayBidimensional[i][j] + " ");
	            }
	            System.out.println();
	        }
	    }
	}


