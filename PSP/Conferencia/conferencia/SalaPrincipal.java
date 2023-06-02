
public class SalaPrincipal {
	public static final int CAPACIDAD = 50;
	public static int contadorPersona=0;
	String mensaje;
	
	public static void main(String[] args) {
		int cantidadPersonas=100;
		//Comenzamos los hilos, se crean todos los que queramos
		for (int i=0;i<=cantidadPersonas;i++) {
            Thread persona = new Thread(new Personas(i,"Buenos días señores"));
            persona.start();
		}
	}
}
