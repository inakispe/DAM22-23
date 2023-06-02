package Bingo;


public class Binguero extends Thread {
	private Bola b;
	String nombre;
	public Binguero (String nombre, Bola b) {
		this.nombre=nombre;
		this.b=b;
	}
	@Override
	public void run (){
		while(true) {
		synchronized(b){
			//Esto es lo que hace mientras espera
			try {
			System.out.println(nombre+" esta esperando la bola");
			b.wait();
			} catch (Exception e) {
			// TODO: handle exception
			}
			//Cuando entra en el syncronized pilla la bola
			System.out.println(nombre+"ha escuchado la bola "+b.getBola());
			}
		}
	}
}		