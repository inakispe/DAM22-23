
import javax.swing.JLabel;
public class Contador implements Runnable {
	private static final long INTERVALO=100;
	JLabel _salida;
	int cont=0;
	
	public Contador(JLabel salida) {
		_salida=salida;
		}
	
	@Override
	public void run() {
		parar();
		
		while(true) {
				cont++;
				_salida.setText(Integer.toString(cont));
			try {	
					Thread.sleep(INTERVALO);
				
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	private void parar() {
		
	}
	private void continuar() {
		
	}
}