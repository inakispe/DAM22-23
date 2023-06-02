package ContadorRuta;


public class Main {
	private static int numeroContadores;
	public static void main(String[] args) {
		//Creamos el array de hilos y los hilos que van dentro de la clase
		Thread[] trabajadores= new Thread[args.length];
		Contador[] contadores= new Contador[args.length];
		
		//Hacemos un array porque se arranca un hilo por cada fichero
		for (int i=0; i<args.length; i++) {
			//Aqui por constructor se manda la linea del fichero
			contadores[i]=new Contador(args[i]);
			trabajadores[i]= new Thread(contadores[i]);
			trabajadores[i].start();
		}

		//El programa principal esperar� a que terminen los Threads
		for(int i=0;i<trabajadores.length;i++) {
			try {
				trabajadores[i].join();
			} catch (InterruptedException e) {				
				e.printStackTrace();
			}
		}	
		//HASTA AQUI CASI SIEMPRE ES IGUAL
		//Aqui se hace un Array con los numeros 
		int[] arrayGeneral=new int[Contador.numLetras]; 
		for(int i=0;i< contadores.length;i++) {
			int [] esteTioHaContado =contadores[i].getCounter();
			for(int t=0; t<arrayGeneral.length;t++) {
				arrayGeneral[t]+=esteTioHaContado[t];
			}
		}
		//Aqui se pone las letras
		for(char c='a';c<='z';c++) {
			System.out.println(c+ " : "+ arrayGeneral[c-'a']);	
		}
	}
}