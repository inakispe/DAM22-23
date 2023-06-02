package TCP;

	import java.io.BufferedReader;
	import java.io.Console;
	import java.io.IOException;
	import java.io.InputStream;
	import java.io.InputStreamReader;
	import java.io.OutputStream;
	import java.io.PrintWriter;
	import java.net.ServerSocket;
	import java.net.Socket;
	import java.net.UnknownHostException;

	public class ReversoPalabra {
		
		static String MENSAJE_FINAL="bye";

		public static void main(String[] args) {
			// TODO Esbozo de método generado automáticamente
	        if (args.length < 1) return;
	        
	        int port = Integer.parseInt(args[0]);
	 
	        try (ServerSocket serverSocket = new ServerSocket(port)) {
	 
	            System.out.println("Server esta conectado en el puerto " + port);
	 
	            while (true) {
	                Socket socket = serverSocket.accept();
	                System.out.println("Un cliente se ha conectado");
	 
	                InputStream input = socket.getInputStream();
	                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
	 
	                OutputStream output = socket.getOutputStream();
	                PrintWriter writer = new PrintWriter(output, true);
	 
	 
	                String text;
	                	
	                do {
	                    text = reader.readLine();
	                    //reverse() es un método 
	                    String reverseText = new StringBuilder(text).reverse().toString();
	                    writer.println("Server escribe: " + reverseText);
	 
	                } while (!text.equals("bye"));
	 
	                socket.close();
	            }
	 
	        } catch (IOException ex) {
	            System.out.println("Server exception: " + ex.getMessage());
	            ex.printStackTrace();
	        }
	    }
		}


