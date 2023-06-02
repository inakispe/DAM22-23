package Conferencia;

public class Persona implements Runnable {
	private static final Object cerrojo = new Object();;
	public static int numeroConferenciante;
	SalaPrincipal salaPrincipal;
	public static int ID;
	public static final long tiempoEspera=20000;
	public Persona(int ID, String mensaje) {
		this.ID = ID;
	}

	@Override
	public void run() {
		//Se van sincronizando
		try {
		//Accion de entrada
		synchronized(cerrojo) {
			while (SalaPrincipal.contadorPersona>=SalaPrincipal.CAPACIDAD) {	
					//Si la sala principal esta llena esperar a que se vacíe
					cerrojo.wait();
					}
					SalaPrincipal.contadorPersona++;
					System.out.println("La persona "+ID+" ha entrado en la sala");
		}
		Thread.sleep(tiempoEspera);
        //Accion de salida
		synchronized (cerrojo) {
            SalaPrincipal.contadorPersona--;
            System.out.println("Persona " + ID + " ha salido de la sala.");
            cerrojo.notify(); // Notificar que hay espacio disponible en la sala
        }
		} catch (InterruptedException e) {
					// TODO Bloque catch generado automáticamente
					e.printStackTrace();
				}
			}
		}

