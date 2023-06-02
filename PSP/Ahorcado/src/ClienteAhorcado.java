import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClienteAhorcado {
	public static void main(String[] args) {
		String ip = "192.168.56.1";
		int port = 2222;
		String msg;
		Socket conn;
	
		try {
			conn = new Socket(ip,port);
		
			DataOutputStream outTCP = new DataOutputStream(conn.getOutputStream());
			DataInputStream inTCP = new DataInputStream(conn.getInputStream());
			
			System.out.println("Servidor dice:" + inTCP.readUTF());
			String cadenaLetras;
			String letra;
			Scanner sc = new Scanner(System.in);
			
			do {
				
				letra = sc.nextLine();
				// Aquí pensar
				
				outTCP.writeUTF("Mi letra es "+letra);
				
				msg=inTCP.readUTF();
			
				
				System.out.println(msg);
				
			}while(!msg.equals(ServidorAhorcado.MENSAJE_FINAL));
			
			inTCP.close();
			outTCP.close();
			conn.close();
			sc.close();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
