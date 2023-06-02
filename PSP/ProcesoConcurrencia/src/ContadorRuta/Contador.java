package ContadorRuta;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Contador implements Runnable {
	String linea;
	int contadorLetras;
	String lineasContadas;
	public static final int numLetras=('z'-'a')+1;
	private int[] contadores;
	public static final int EOF=-1;
	public Contador(String linea) {
		// TODO Esbozo de constructor generado automáticamente
		this.linea= linea;
		contadores=new int[numLetras];
	}

		@Override
		public void run() {
			
			// TODO Esbozo de método generado automáticamente
			try {
				BufferedReader lectura = new BufferedReader(new FileReader(linea));
				int c;
				while ((c=lectura.read())!=EOF) {
					if('a' <= c && c <= 'z') {
						contadores[c-'a']++;
					}else if('A' <= c && c <= 'Z') {
						contadores[c-'A']++;
					}
					
					//Se reemplaza los espacios por nada
					//lineasContadas.replaceAll("\s", "");
					//contadorLetras=lineasContadas.length();	
					//System.out.println(contadorLetras);
				}
				//Los archivos de lectura siempre cerrarlos
				lectura.close();
			} catch (FileNotFoundException e) {
				// TODO Bloque catch generado automáticamente
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Bloque catch generado automáticamente
				e.printStackTrace();
			}

		}
		public int getCount(char c) {
			if('a' <= c && c <= 'z') {
				return contadores[c-'a'];
			}else if('A' <= c && c <= 'Z') {
				return contadores[c-'A'];
			}
			return 0;
		}
		
		public int[] getCounter() {
			return contadores;
		}

	}

