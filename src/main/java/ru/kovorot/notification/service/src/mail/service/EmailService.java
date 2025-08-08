package mail.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

public class EmailService {

    private final JavaMailSender mailSender;

    private final String FROM_EMAIL = "noreply@example.com";

    public void sendRegistrationEmail(String toEmail) {
        sendEmail(toEmail, "Аккаунт создан", "Здравствуйте! Ваш аккаунт на сайте ваш сайт был успешно создан.");
    }

    public void sendDeletionEmail(String toEmail) {
        sendEmail(toEmail, "Аккаунт удалён", "Здравствуйте! Ваш аккаунт был удалён.");
    }

    private void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(FROM_EMAIL);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
    }
}
