package ru.kovorot.notification;

import com.icegreen.greenmail.junit5.GreenMailExtension;
import com.icegreen.greenmail.util.ServerSetup;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.kovorot.notification.service.EmailService;
import javax.mail.internet.MimeMessage;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class NotificationServiceIntegrationTest {

    @RegisterExtension
    static GreenMailExtension greenMail = new GreenMailExtension(ServerSetup.SMTP);

    @Autowired
    private EmailService emailService;

    @Test
    void testSendRegistrationEmail() throws Exception {
        emailService.sendRegistrationEmail("test@example.com");

        MimeMessage[] messages = greenMail.getReceivedMessages();
        assertEquals(1, messages.length);
        MimeMessage message = messages[0];

        assertEquals("Аккаунт создан", message.getSubject());
        String content = (String) message.getContent();
        assertTrue(content.contains("успешно создан"));
        assertEquals("test@example.com", message.getAllRecipients()[0].toString());
    }

    @Test
    void testSendDeletionEmail() throws Exception {
        emailService.sendDeletionEmail("delete@example.com");

        MimeMessage[] messages = greenMail.getReceivedMessages();
        assertEquals(1, messages.length);
        MimeMessage message = messages[0];

        assertEquals("Аккаунт удалён", message.getSubject());
        String content = (String) message.getContent();
        assertTrue(content.contains("был удалён"));
    }
}
