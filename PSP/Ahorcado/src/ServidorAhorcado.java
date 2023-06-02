import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorAhorcado {
	static String MENSAJE_FINAL = "Adios";
	static String BIENVENIDO = "Bienvenido nuevo jugador";
	static String VARIASLETRAS="ABCDEFGHIJKLMOPQRSTUVXYZ";

	public static void main(String[] args) {
		// TODO Esbozo de método generado automáticamente
		// if (args.length<1) return;
		int port = 2222;
		StringBuffer sbuffer = new StringBuffer();
		StringBuffer palbuffer = new StringBuffer();
		try (ServerSocket serverSocket = new ServerSocket(port)) {
			System.out.println("Servidor esta conectado en el puerto" + port);
			while (true) {
				Socket socket = serverSocket.accept();
				System.out.println("Nuevo jugador");
				String palString = "Paraguas";
				int tamanoPal = palString.length();
				
				palbuffer.append(palString);
				
				new Thread(() -> {
					try {
						DataInputStream in = new DataInputStream(socket.getInputStream());
						DataOutputStream out = new DataOutputStream(socket.getOutputStream());
						
						for (int i = 0; i < tamanoPal; i++) {
							sbuffer.append("_");
						}
						out.writeUTF(BIENVENIDO);
						out.writeUTF(sbuffer.toString());
						
						

						String s = in.readUTF();

						s.charAt(0);

						// Modificar esta zona

						int posicion = palbuffer.indexOf(s);
						
						if (posicion == -1) {
							out.writeUTF("Has fallado");
						} else {
							sbuffer.insert(posicion, s);
							out.writeUTF(sbuffer.toString());
						}

						in.close();
						out.close();
						socket.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}).start();
			}
		} catch (IOException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
	}
	/**
	 * public boolean fallo (String palabra, String letra) { boolean fallo=true; if
	 * (palabra.indexOf(letra)) { fallo= false; } return fallo; }
	 */

}
