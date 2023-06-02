package Deporte;

public class Olimpiadas {
	public static void main(String[] args) {
		Fuerza f= new Fuerza(4,5);
		//Partida partida = new Partida(f);
		//new Thread(partida).start();
		
		Jugador J1 = new Jugador(1,f);
		new Thread(J1).start();
		Jugador J2 = new Jugador(2,f);
		new Thread(J2).start();
		
		System.out.println("TODOS ESTAN EN SUS PUESTOS");
	}
}