package EjercicioEmailHTML;

	import java.io.BufferedReader;
	import java.io.InputStreamReader;
	import java.net.URL;
	import java.util.Properties;
	import javax.mail.*;
	import javax.mail.internet.*;

	public class EjercicioHTML {
	  public static void main(String[] args) throws Exception {
	    String webUrl = "https://www.thecocktaildb.com/api/json/v1/1/search.php?s=margarita";
	    String emailTo = "ipereiroeusa@gmail.com";
	    final String emailFrom = "ipereiroeusa@gmail.com";
	    final String emailPassword = "mourinho";

	    // Obtener el HTML de la página web
	    URL url = new URL(webUrl);
	    BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
	    StringBuilder html = new StringBuilder();
	    String line;
	    while ((line = in.readLine()) != null) {
	      html.append(line);
	    }
	    in.close();

	    // Extraer los textos de los elementos h1
	    String[] h1Texts = html.toString().split("<h1[^>]*>");
	    for (int i = 1; i < h1Texts.length; i++) {
	      h1Texts[i] = h1Texts[i].split("</h1>")[0];
	    }

	    // Enviar el correo electrónico
	    Properties props = new Properties();
	    props.put("mail.smtp.host", "smtp.gmail.com");
	    props.put("mail.smtp.port", "587");
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.mime.address.strict", "false");
	    props.put("mail.smtp.starttls.enable", "true");

	    Session session = Session.getInstance(props, new Authenticator() {
	     
	    protected PasswordAuthentication getPasswordAuthentication() {
	        return new PasswordAuthentication(emailFrom, emailPassword);
	      }
	    });
	
	    Message message = new MimeMessage(session);
	    message.setFrom(new InternetAddress(emailFrom));
	    message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailTo));
	    message.setSubject("Textos de h1 de " + webUrl);

	    StringBuilder text = new StringBuilder();
	    for (int i = 1; i < h1Texts.length; i++) {
	      text.append(h1Texts[i] + "\n");
	    }
	    message.setText(text.toString());

	    Transport.send(message);

	    System.out.println("Correo enviado satisfactoriamente.");
	  }
	}


