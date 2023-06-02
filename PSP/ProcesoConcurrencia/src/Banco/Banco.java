package Banco;

public class Banco {

	public static void main(String[] args) {
		// TODO Esbozo de método generado automáticamente
		//Es un objeto
		Cuenta c1= new Cuenta(1000);		
		//SacarDinero es un proceso que tiene interacción con otro objeto.
		SacarDinero s1= new SacarDinero("Ana", c1);
		SacarDinero s2= new SacarDinero("Pablo",c1);
		SacarDinero s3= new SacarDinero("Mario", c1);
		//Hay que empezar con el proceso
		s1.start();
		s2.start();
		s3.start();
	}

}
