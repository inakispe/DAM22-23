package MailRobert;

import java.io.IOException;
import java.util.Base64;

//import org.simplejavamail.api.email.CalendarMethod;
import org.simplejavamail.api.email.Email;
import org.simplejavamail.api.mailer.Mailer;
import org.simplejavamail.api.mailer.config.TransportStrategy;
import org.simplejavamail.config.ConfigLoader;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.MailerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.slf4j.impl.StaticLoggerBinder;

public class EjercicioMail {

        public static void main(String[] args) {
        	
        		
        	

                String mensaje = "hola, esto es el cuerpo del mensaje enviado con java";



                Email email = EmailBuilder.startingBlank()
                			
                          .to("Conseguidooo", "ignacio.pereiro@educa.madrid.org")
                          //la terminacion tiene que ser s o si con educa.madrid en eclipse
                          .from("robert", "robertConNuevoMail@educa.madrid.org")
                          .withSubject("prueba mensaje desde java")
                          // .withHTMLText("<b>We should meet up!</b>")
                          .withPlainText(mensaje)
                          .buildEmail();


                Mailer mailer = MailerBuilder
                          .withSMTPServer("smtp.educa.madrid.org", 587, "ignacio.pereiro", "ReinoUnido1993")
                          .withTransportStrategy(TransportStrategy.SMTP_TLS)
                          .clearEmailValidator() // turns off email validatio
                          .buildMailer();

                mailer.sendMail(email);

                System.out.println("Mensaje enviado");


        }

}

