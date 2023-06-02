package graficos;
import javax.swing.*;
public class CreandoMarco {

	public static void main(String[] args) {
		// TODO Esbozo de método generado automáticamente
		
		//Instanciamos la clase miMarco
		miMarco marco1= new miMarco();
		//Ahora la acemos visible
		marco1.setVisible(true);
		//Que hacemos cuando se cierre, el método pide un int,
		//si usamos el JFrame nos da 4 opciones según lo que queramos hacer
		marco1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		/**
		 * marco1.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		 	Te esconde la ventana y sigue funcionando.
		 */
		
	}

}
 class miMarco extends JFrame{
	 
	 public miMarco() {
		 //Método al que se le introduce ancho y altura
		 //Tener en cuenta el monitor con el que trabajamos
		 //setSize(500,300);
		 //Método para colocar en la pantalla
		 //setLocation(500,300);
		 //Método que acumula los métodos anteriores (Pimero Location, Segundo Size)
		 setBounds (500,300,25,25);
		 //Metodo que permite redimensionar la ventana
		 setResizable(true);
		 
		 setTitle("Primera Aplicación");
	 }
 }

