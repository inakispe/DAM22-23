
public class DomingoDeBingo {

	public static void main(String[] args) {
		Bola bola = new Bola();
		//Si por ejemplo fueran dorsale con un for y un array de hilos se puede iniciar directamente
		Bingero paco= new Bingero ("Paco",bola,"345");
		//Se inicia el hilo y espera a que le llame para que suelte la bola
		new Thread(paco).start();
		//Se inicia el hilo y notifica 
		Bingo bingo = new Bingo (bola);
		new Thread(bingo).start();
	}

}
