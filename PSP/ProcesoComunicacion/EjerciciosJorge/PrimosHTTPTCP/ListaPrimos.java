package PrimosHTTPTCP;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ListaPrimos {
	public static int port;
	public static final int sizeBuffer = 1024;
	public static final int sizeargs = 1;

	public static void main(String[] args) {

		byte[] receiveData = new byte[sizeBuffer];
		if (args.length < sizeargs) {
			System.out.println("Tienes que introducir algún argumento");
			return;
		} else if (args.length > 2) {
			System.out.println("Ha introducido demasiados argumentos");
		} else {
			port = Integer.parseInt(args[0]);
		}

		try (ServerSocket serverSocket = new ServerSocket(port)) {
			// El sevidor escucha
			System.out.println("Running server on " + port + "...");

			while (true) {
				// Gestionar Clientes
				Socket socket = serverSocket.accept();
				ManejadorSolicitud manejadorSolicitud = new ManejadorSolicitud(socket);
				Thread hilo = new Thread(manejadorSolicitud);
				hilo.start();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

