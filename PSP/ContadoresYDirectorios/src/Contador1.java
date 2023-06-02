import java.io.PrintStream;

import javax.swing.JLabel;



	//Creas un contador ejecutable por eso llamamos al Runnable
	public class Contador1 implements Runnable {
		private static final long INTERVALO= 100;
		JLabel _salida;
		int cont=0; 		
			public Contador1(JLabel etiqueta) {
				// TODO Esbozo de constructor generado automáticamente
			}

		public void run() {
			try {
				while(true) {
					cont++;
					_salida.setText(Integer.toString(cont));
					
				}
		} catch (Exception e) {
			// TODO: handle exception
		}
		}
	}

