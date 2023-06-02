package Bingo;

public class DomingoDeBingo {
	//Se inicializa el ArrayListFuera

	public static void main(String[] args) {
		//Primero llamamos al objeto que activa los otros hilos
		Bola b= new Bola();
		
		Binguero paco = new Binguero("Paco", b);
		/*No solo se crea el objeto, si no que se crea un 
		hilo nuevo para que vayan todos a la vez */
		new Thread(paco).start();
		Binguero jorge= new Binguero("Jorge", b);
		new Thread(jorge).start();
		Binguero maria = new Binguero("Maria", b);
		new Thread(maria).start();
		
		Bingo bingo = new Bingo(b);
		new Thread(bingo).start();
	}

}
