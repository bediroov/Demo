package az.innakhchivan.utility;

import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;

import java.util.Properties;

@Getter
public class EmailValidator {

    @Value("${spring.mail.host}")
    private static String host;

    @Value("${spring.mail.port}")
    private static String port;

    @Value("${spring.mail.username}")
    private static String username;
    @Value("${spring.mail.password}")
    private static String password;


    public static Boolean isEmailValid(String email) {
        try {
            Properties properties = new Properties();
            properties.put("mail.smtp.host", host);
            properties.put("mail.smtp.port", port);
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true");

            Session session = Session.getInstance(properties, null);
            Transport transport = session.getTransport("smtp");

            transport.connect(host, username, password);

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.addRecipient(MimeMessage.RecipientType.TO, new InternetAddress(email));
            message.setSubject("Email Validation Test");
            message.setText("Bu test emailidir. Email aktivdir.");

            transport.sendMessage(message, message.getAllRecipients());
            transport.close();

            return true;
        } catch (Exception e) {
            System.err.println("Email validation failed: " + e.getMessage());
            return false;
        }
    }
}
