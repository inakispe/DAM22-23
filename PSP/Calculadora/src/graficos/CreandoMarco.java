package graficos;
import javax.swing.*;
public class CreandoMarco {

	public static void main(String[] args) {
		// TODO Esbozo de m�todo generado autom�ticamente
		
		//Instanciamos la clase miMarco
		miMarco marco1= new miMarco();
		//Ahora la acemos visible
		marco1.setVisible(true);
		//Que hacemos cuando se cierre, el m�todo pide un int,
		//si usamos el JFrame nos da 4 opciones seg�n lo que queramos hacer
		marco1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		/**
		 * marco1.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		 	Te esconde la ventana y sigue funcionando.
		 */
		
	}

}
 class miMarco extends JFrame{
	 
	 public miMarco() {
		 //M�todo al que se le introduce ancho y altura
		 //Tener en cuenta el monitor con el que trabajamos
		 //setSize(500,300);
		 //M�todo para colocar en la pantalla
		 //setLocation(500,300);
		 //M�todo que acumula los m�todos anteriores (Pimero Location, Segundo Size)
		 setBounds (500,300,25,25);
		 //Metodo que permite redimensionar la ventana
		 setResizable(true);
		 
		 setTitle("Primera Aplicaci�n");
	 }
 }

