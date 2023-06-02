package SMTP;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.security.auth.login.AccountException;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;



public class CuadradoPorCorreo {

    private static final String SERVER = "smtp.educa.madrid.org";
    private static final int PORT = 587;
    private static final String SUCCESS = "Correo electrónico enviado exitosamente.";
    private static final String SUBJECT = "EJERCICIO PSP RANDOM";   
    private static final int ROTACION=13;
    public static void main(String[] args) {
    	File archivo = new File("data/mensajes.txt");
        File archivo2 = new File("data/dirs.txt");
        String correo= "ipereiroeusa@gmail.com";
        String cifrado;	
        cifrado=cifrarCesar(archivo,ROTACION);
        enviarEmail(cifrado, correo);
    }

    private static void enviarEmail (String cuadrado, String correo) {
        // Leemos el usuario y la contraseña de un archivo oculto.
        String user = "Jorge.Duenas";
        String pass = "abc1234";
        String mailfrom = "jorge.duenas@educa.org";

        Email email = new SimpleEmail();

        // Host y puerto
        email.setHostName(SERVER);
        email.setSmtpPort(PORT);

        // Credenciales, pedirlas de un fichero oculto.
        email.setAuthentication(user, pass);
        email.setSSLOnConnect(true);

        try {
            email.setFrom(mailfrom);
            email.setSubject(SUBJECT);
            email.setMsg(cuadrado);
            email.addTo(correo);
            email.send();
            System.out.println(SUCCESS);
        } catch (AccountException e) {
            e.printStackTrace();
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