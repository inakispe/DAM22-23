package PingPong;

public class JugadorA implements Runnable {
	private static final Object cerrojo = new Object();
	private static final long tiempoEspera = 2000;
	private static boolean esTurno = true;
	String mensaje;

	public JugadorA(String mensaje) {
		this.mensaje = mensaje;
	}

	@Override
	public void run() {
		try {
			// Accion de entrada
			synchronized (cerrojo) {
				//CUIDADO CON EL PARENTESIS QUE TE DA FALLO
				while ((mensaje.equalsIgnoreCase("PING") && !esTurno) || (mensaje.equalsIgnoreCase("PONG") && esTurno)) {
					// Si la sala principal esta llena esperar a que se vacíe
					cerrojo.wait();
				}
				//Espera, manda el mensaje y lo cambia
				System.out.println(mensaje);
				esTurno = !esTurno;
				cerrojo.notify();
			}
			Thread.sleep(tiempoEspera);
			// Accion de salida
			// Cambiar el turno y notificar al otro jugador

		} catch (InterruptedException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
	}

}