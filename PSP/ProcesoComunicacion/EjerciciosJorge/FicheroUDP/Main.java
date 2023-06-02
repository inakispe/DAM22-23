package FicheroUDP;


public class Main {
	public static int port;
	public static String line;
	public static final int NUM_PARAMS = 2;
	public static final int tamanioBuffer = 1;

	public static void main(String[] args) {
		Buffer buffer = new Buffer(tamanioBuffer);

		if (args.length != NUM_PARAMS) {
			System.out.println("Error en n�mero de par�metros");
		} else {
			port = Integer.parseInt(args[0]);
			line = args[1];
		}
		
		Productor productor= new Productor(line, buffer);
		Consumidor consumidor = new Consumidor(buffer, port);
		productor.start();
		consumidor.start();

	}

}