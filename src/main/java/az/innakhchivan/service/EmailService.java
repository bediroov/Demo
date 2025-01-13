package az.innakhchivan.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    @Value("${spring.mail.username}")
    private String fromAddress;

    private final JavaMailSender javaMailSender;

    /**
     * Email göndərmə metodu
     *
     * @param to      Göndərilən email ünvanı
     * @param subject Email mövzusu
     * @param text    Email məzmunu
     * @return Email gonderme statusu
     */
    @Retryable(value = MailException.class, maxAttempts = 3, backoff = @Backoff(delay = 3000))
    public String sendEmail(String to, String subject, String text) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setFrom(fromAddress);
            message.setSubject(subject);
            message.setText(text);

            javaMailSender.send(message);
            return "Success send email";
        } catch (MailException e) {
            System.err.println("Error sending email: " + e.getMessage());
            throw e;
        }
    }
}
