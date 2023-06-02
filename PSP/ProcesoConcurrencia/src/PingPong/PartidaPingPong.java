package PingPong;


public class PartidaPingPong {
	public static void main(String[] args) {
		Thread Jose = new Thread(new JugadorA("Ping"));
		Thread Pablo = new Thread(new JugadorA("Pong"));
		Jose.start();
		Pablo.start();
	}
}
