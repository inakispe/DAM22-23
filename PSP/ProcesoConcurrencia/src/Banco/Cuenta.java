package Banco;

public class Cuenta {
	private int saldo;

	Cuenta(int s) {
		saldo = s;
	} // Constructor que inicializa el saldo

	int getSaldo() {
		return saldo;
	} // Devuelve el saldo

	void restarSaldo(int cantidad) {
		saldo = saldo - cantidad;
	}

	/*
	 * El Retirar dinero devuelve la cantidad que con la que se queda
	 */
	public synchronized void RetirarDinero(int cant, String nom) {
		if (getSaldo() >= cant) {
			System.out.println("SE VA A RETIRAR SALDO (ACTUAL ES: " + getSaldo() + ")");
			try {
				Thread.sleep(500);// Manda el sleep para que se duerma mientras que se hace el otro
									// métoso(restar).
			} catch (InterruptedException ex) {
			}
			restarSaldo(cant);
			System.out.println(nom + " retira => " + cant + " ACTUAL(" + getSaldo() + ")");
		} else {
			System.out.println(nom + " No puede retirar dinero, NO HAY SALDO" + getSaldo());
		}
		if (getSaldo() < 0) {
			System.out.println("SALDO NEGATIVO => " + getSaldo());
		}
	}// RetirarDinero
}// Cuenta
