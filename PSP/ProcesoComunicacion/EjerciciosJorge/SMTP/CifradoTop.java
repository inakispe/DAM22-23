package SMTP;

import java.io.PrintWriter;
import java.net.Socket;

public class CifradoTop {
    public static void main(String[] args) {
        // Verificar que se han proporcionado los par�metros necesarios
        if (args.length < 4) {
            System.out.println("Uso: java CifradoCesar <direcci�n> <puerto> <rotaci�n> <mensaje>");
            return;
        }

        String direccion = args[0];
        int puerto = Integer.parseInt(args[1]);
        int rotacion = Integer.parseInt(args[2]);
        String mensaje = args[3];

        // Cifrar el mensaje utilizando el cifrado C�sar
        String mensajeCifrado = cifrarCesar(mensaje, rotacion);

        try {
            // Establecer la conexi�n con el servidor
            Socket socket = new Socket(direccion, puerto);

            // Enviar el mensaje cifrado al servidor
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println(mensajeCifrado);

            // Cerrar la conexi�n
            socket.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static String cifrarCesar(String mensaje, int rotacion) {
        StringBuilder resultado = new StringBuilder();

        for (int i = 0; i < mensaje.length(); i++) {
            char caracter = mensaje.charAt(i);

            // Cifrar solo los caracteres alfab�ticos
            if (Character.isLetter(caracter)) {
                // Determinar la posici�n del caracter en el alfabeto
                int posicion = caracter - (Character.isUpperCase(caracter) ? 'A' : 'a');

                // Aplicar la rotaci�n y ajustar la posici�n para que est� dentro del alfabeto
                posicion = (posicion + rotacion + 26) % 26;

                // Convertir la posici�n de vuelta a un caracter y agregarlo al resultado
                caracter = (char) (posicion + (Character.isUpperCase(caracter) ? 'A' : 'a'));

                // Incremento la clave en 1
                rotacion++;
            }

            resultado.append(caracter);
        }

        return resultado.toString();
    }
}