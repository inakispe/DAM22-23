package Banco;
//Este usa como objeto Cuenta
public class SacarDinero extends Thread {
	private Cuenta c;
	String nom;
	int numero=(int)(Math.random()*10+1);
	public SacarDinero(String nombre, Cuenta c) {
		this.nom=nombre;
		this.c=c;
	}
	public void run () {
		//En este proceso vamos a llamar al método y le vamos a meter por parametro numero y nombre
		for(int x=1; x<=4; x++) {
			c.RetirarDinero(numero, nom);
		}
	}

}
