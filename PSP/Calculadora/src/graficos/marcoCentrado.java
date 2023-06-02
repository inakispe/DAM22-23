package graficos;

import javax.swing.JFrame;
import java.awt.*;

public class marcoCentrado {

	public static void main(String[] args) {
		// TODO Esbozo de m�todo generado autom�ticamente

		MarcosCenter miMarco= new MarcosCenter();
		//Recordar esto es para que hace el programa cuando se cierra
		miMarco.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		//Los programas son invisibles por defectos, hay que ponerlo visible
		miMarco.setVisible(true);
		}
	
}
class MarcosCenter extends JFrame {
	public MarcosCenter() {
		//Static tenemos que usar el nombre del m�todo antes de usarlo
		//Con el getDefaultToolkit nos devuelve un objeto Toolkit, nos devuelve el medio donde se ejecuta el programa.
		Toolkit mipantalla=Toolkit.getDefaultToolkit();
		
		//Con el Dimension nos devuelve el tama�o o resoluci�n de la pantalla
		Dimension tamanoPantalla=mipantalla.getScreenSize();
		
		int alturaPantalla= tamanoPantalla.height;
		int anchoPantalla= tamanoPantalla.width;
		
		//Nos devuelve el tama�ano de la pantalla
		setSize(anchoPantalla/2,alturaPantalla/2);
		//Nos devuelve la localizacion de la pantalla
		setLocation(anchoPantalla/4,alturaPantalla/4);
		
		setTitle("Marco Central");
		
		Image miIcono=mipantalla.getImage("C:/Users/ipere/Desktop/Android/piedra/piedra.jpg");
		
	}
}