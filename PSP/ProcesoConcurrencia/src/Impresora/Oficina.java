package Impresora;

public class Oficina {
	    private static final int RECURSOS = 10;
	    public static void main(String[] args) {
	        // Se crea la impresora que se quiere imprimir
	        Impresora impresora = new Impresora();


	        // Se crean los recursos que estan esperando a la impresora
	        Thread[] recursos = new Thread[10];

	        for (int i = 0; i <RECURSOS; i++) {
	            //Aqui se crea un hilo que se mete al vector del objeto Recurso
	        	recursos[i] = new Thread(new Recurso("La impresora"+i+"esta imprimiendo", impresora));
	            recursos[i].start();
	        }
	        // Se espera a la finalización de todos los hilos.
	        for (int i = 0; i < RECURSOS; i++) {
	            try {
	                recursos[i].join();
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	        }

	        System.out.println("Impresión finalizada");
	    }
	}