import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Arrays;

import javax.xml.crypto.Data;
/**
 * 
 * @author ipere
 *	El código no está completo porque faltaría 
 *un código para separar el comando del resto, la cosa es que no recuerdo como es el enunciado 
 */
public class ServicioCuadrados {

	private static final int NUM_PARAMS = 2;
	static int prReceive;
	static int prSend;
	private static final String FIN = "fin";
	private static final int BUFFER_SIZE = 0;
	private static final String CARACTER_SEPARADOR = " ";
	private static final String BROADCAST="255.255.255";
	public static void main(String[] args) {
		// TODO sbozo de método generado automáticamente
		// Procesar datos
		if (args.length != NUM_PARAMS) {
			System.out.println("Error en número de parámetros");
		}
		// Crea dos puertos
		try {
			prReceive = Integer.parseInt(args[0]);
			prSend = Integer.parseInt(args[1]);
		} catch (Exception e) {
			System.err.println("Error en parámetros");
			System.err.println(e);
		}
		// Creamos dos Socket unos para recibir y otro para enviar
		try {
			DatagramSocket receiver = new DatagramSocket(prReceive);
			DatagramSocket sender = new DatagramSocket(prSend);
			String comando = "";
			// Ya hemos dividio la info
		
			while (!comando.equalsIgnoreCase(FIN)) {
				comando = recibeInfo(receiver);
				int port = 0;
				enviaInfo(sender, generarCuadrado(comando), port);			
			}
		} catch (SocketException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}

	}

	private static void enviaInfo(DatagramSocket sender, String generarCuadrado, int port) {
		byte[] buffer = generarCuadrado.getBytes();
		DatagramPacket paquete;
		try {
			paquete = new DatagramPacket(buffer, buffer.length, InetAddress.getByName(BROADCAST), port );
			sender.send(paquete);
		} catch (UnknownHostException e1) {
			// TODO Bloque catch generado automáticamente
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
	}
	

	private static String generarCuadrado(String comando) {
		String cuadrado = "";
		// Vamos a hacerlo por línea de String y separarlo

		String[] params = comando.split(CARACTER_SEPARADOR);
		int altura = Integer.parseInt(params[0]);
		int ancho = Integer.parseInt(params[1]);
		int simbolo = params[2].charAt(0);

		for (int i = 0; i < altura; i++) {
			// Pintar fila
			// Si es primera o ultima se escribe así
			if (i == 0 || i == altura - 1) {
				for (int j = 0; j < ancho; j++) {
					cuadrado += simbolo;
				}
			} else {
				for (int j = 0; j < ancho; j++) {
					if (j == 0 || j == ancho - 1) {
						cuadrado += simbolo;
					} else {
						cuadrado += " ";
					}
				}
			}
			cuadrado += "/n";
		}

		return cuadrado;
	}

	private static String recibeInfo(DatagramSocket receiver) {
		//Te creas un DatagramPacket que reciba 
		//Declaramos un array de byte
		byte[]buffer= new byte[BUFFER_SIZE];
		//Creamos un paquete UDP 
		DatagramPacket paquete = new DatagramPacket(buffer, BUFFER_SIZE);
		String comandos= "";
		
			//Recibes el DataGramPacket con el receiver del Socket
			try {
				receiver.receive(paquete);
				comandos= Arrays.toString(paquete.getData());

			} catch (IOException e) {
				// TODO Bloque catch generado automáticamente
				e.printStackTrace();
			}
	
		return comandos;
	}

}
