

public class Bingo implements Runnable {
	private static final long TFIJO = 1500;
	private static final double ESPERA = 2000;
	private static final double MAX_NUM = 5;
	private Bola b;
	private String name;
	public Bingo(Bola b) {
		name = "BINGO";
		this.b = b;
	}
	@Override
	public void run() {
		System.out.println(name+" started");
		while(true) {
			try {
				System.out.println("******************************************");
				System.out.println(name+" moviendo el bombo");
				System.out.println("******************************************");
				Thread.sleep((long) (Math.random()* ESPERA)+TFIJO);
				synchronized (b) {
					int n = (int) (Math.random()*MAX_NUM);
					System.out.println("******************************************");
					System.out.println("Bingo. Sacando número : " + n);
					System.out.println("******************************************");
					b.setNumero(n);
					//Notificamos a todos los que estan esperando la bola
					b.notifyAll();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

