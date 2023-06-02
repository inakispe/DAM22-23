package Bingo;

import java.util.ArrayList;

public class Bingo extends Thread{
	
	private Bola b;
	String nombre;
	boolean stopHilo;
	ArrayList <Bola> bolas= new ArrayList<>();
	//TiempoFijo
	private static final long TFIJO=1000;
	private static final double espera=2000;
	private static final double MAX_NUMERO=100;
	
	public Bingo (Bola b) {
		nombre="BINGO";
		//Aqui se inicializa
		this.b=b;
	}

	@Override
	public void run () {
		System.out.println(nombre+"empezamos");
		while(!stopHilo) {
			try {
			System.out.println(nombre+" moviendo el bombo");
			Thread.sleep((long) (Math.random()*espera)+TFIJO);
			synchronized (b){
				int n= (int)(Math.random()*MAX_NUMERO);
				System.out.println("El bingo ha sacado el numero: "+ n);
				//te devuelve el numero al azar pero hay que añadir al setBingo
				b.setBola(n);
				//Aquí es donde vamos a notificar a los otros hilos para que se activen
				b.notifyAll();
				bolas.add(b);
					if (bolas.size()>4) {
						stopHilo=true;
						System.out.println("Uno de los concursantes ha ganado el Bingo");
						}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	public void stopHilo() {
		// TODO Esbozo de método generado automáticamente
		stopHilo=true;
	}
}
