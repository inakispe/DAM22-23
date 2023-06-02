package UDPTCP;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClienteNumeros {
	static final String localhosString = "localhost";

	public static void main(String[] args) {
		// TODO Esbozo de método generado automáticamente
		DataInputStream in = null;
		DataOutputStream out = null;
		if (args.length < 1)
			return;
		int port = Integer.parseInt(args[0]);
		//
		try {
			Socket sc = new Socket(localhosString, port);
			in = new DataInputStream(sc.getInputStream()); // Puente del cliente al servidor

			int numero = in.readInt();
			out.write(numero);
			if (esPrimo(numero)) {
				out.write(numero);
			}

		} catch (UnknownHostException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		} // Cliente

	}

	public static boolean esPrimo(int numero) {
		boolean primo = true;
		while (primo) {
			if (numero % 2 == 0)
				primo = false;
		}
		return primo;
	}

}
