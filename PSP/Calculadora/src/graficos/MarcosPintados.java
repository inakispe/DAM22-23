package graficos;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MarcosPintados {

	public static void main(String[] args) {
		// TODO Esbozo de método generado automáticamente
		//MarcoContexto newMarcoContexto= new MarcoContexto();
		MarcoConDibujo newMarcoDibujo= new MarcoConDibujo();
	}

}
/**class MarcoContexto extends JFrame {
	public MarcoContexto () {
		setVisible(true);
		setSize (400,400);
		setTitle("Primer texto");
		MarcoContexto marcoContexto= new MarcoContexto();
	}
}
class MarcoPintar extends JPanel {
	public void MarcoPintar(Graphics g) {
		MarcoPintar marcoPintar= new MarcoPintar();
	}
}**/

class MarcoConDibujo extends JFrame{
	 public MarcoConDibujo() {
		 setTitle("prubaDibujo");
		 setSize(400,400);		 
		 LaminaConFiguras miLamina= new LaminaConFiguras();
		 add(miLamina);
	 }
}
class LaminaConFiguras extends JPanel {
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		//g.drawRect(50,50,200,200);		
		g.drawArc(50,100,100,200,120,150);
	}
}

