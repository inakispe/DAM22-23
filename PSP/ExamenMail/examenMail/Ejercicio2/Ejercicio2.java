package Ejercicio2;

import java.io.File;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class Ejercicio2
{

    private static final String SERVER = "smtp.educa.madrid.org";
    private static final int PORT = 587;
    private static final String SUCCESS = "Correo electrónico enviado exitosamente.";
    private static final String SUBJECT = "EJERCICIO PSP RANDOM";   
    private static final String REMITENTE= "ipereiroeusa@gmail.com";
    private static final int ROTACION=13;
    public static void main(String[] args) {
    	File archivo = new File("data/mensajes.txt");
        File archivo2 = new File("data/dirs.txt");
        
        String cifrado;	
        String user = "Jorge.Duenas";
        String pass = "abc1234";
        String mailfrom = "jorge.duenas@educa.org";
        cifrado=cifrarCesar(archivo,ROTACION);
        enviarEmail(cifrado, mailfrom, user, pass);
    }

    private static void enviarEmail (String cuadrado, String correo, String user, String password) {
        // Leemos el usuario y la contraseña de un archivo oculto.


        Email email = new SimpleEmail();

        // Host y puerto
        email.setHostName(SERVER);
        email.setSmtpPort(PORT);

        // Credenciales, pedirlas de un fichero oculto.
        email.setAuthentication(user, password);
        email.setSSLOnConnect(true);

        try {
            email.setFrom(REMITENTE);
            email.setSubject(SUBJECT);
            email.setMsg(cuadrado);
            email.addTo(correo);
            email.send();
            System.out.println(SUCCESS);
        } catch (EmailException e) {
            e.printStackTrace();
        }
    }
        
        public static String cifrarCesar (File mensaje, int rotacion) {
        StringBuilder resultado = new StringBuilder();
        for (int i = 0; i < mensaje.length(); i++) {
            char caracter = ((CharSequence) mensaje).charAt(i);

            // Cifrar solo los caracteres alfabéticos
            if (Character.isLetter(caracter)) {
                // Determinar la posición del caracter en el alfabeto
                int posicion = caracter - (Character.isUpperCase(caracter) ? 'A' : 'a');

                // Aplicar la rotación y ajustar la posición para que esté dentro del alfabeto
                posicion = (posicion + rotacion + 13) % 13;

                // Convertir la posición de vuelta a un caracter y agregarlo al resultado
                caracter = (char) (posicion + (Character.isUpperCase(caracter) ? 'A' : 'a'));

                // Incremento la clave en 1
                rotacion++;
            }

            resultado.append(caracter);
        }
        return resultado.toString();

}

}