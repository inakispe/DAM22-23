package UDPTCP;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

import javax.print.attribute.standard.Severity;

/**
 * Crea un programa Multihread que reciba por parámetro el puerto en el que
 * escucha en TCP. Cada Thread recibe un número, calcula si es primero y lo
 * devuelve al cliente. Crea un programa con GUI, GUITCP que utiliza el servicio
 * que has creado.
 * 
 * @author ipere
 *
 */
public class ServidorNumeros {

	public static void main(String[] args) {
		// TODO Esbozo de método generado automáticamente
        if (args.length < 1) return;
        int port = Integer.parseInt(args[0]);
		ServerSocket servidor = null; //Servidor
		Socket sc = null; //Cliente
		DataInputStream in=null;
		DataOutputStream out=null;	
		final int listNumeros=100;

		try {
			servidor = new ServerSocket(port);
			System.out.println("Servidor iniciado");
			while (true) {
				sc=servidor.accept();
				in = new DataInputStream(sc.getInputStream()); //Puente del cliente al servidor
				out = new DataOutputStream(sc.getOutputStream());//Puente del servidor al cliente
				Random azar = new Random();
				int numero=  azar.nextInt(listNumeros);
				out.write(numero); //El servidor manda los numeros
				sc.close();
				
			}
		} catch (IOException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
	}

}
