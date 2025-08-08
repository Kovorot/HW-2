package mail.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class KafkaConsumerService {
    private final EmailService emailService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public KafkaConsumerService(EmailService emailService) {
        this.emailService = emailService;
    }

    @KafkaListener(topics = "user_events", groupId = "notification-group")
    public void listen(String message) {
        try {
            JsonNode node = objectMapper.readTree(message);
            String event = node.get("event").asText();
            String email = node.get("email").asText();

            if ("user_created".equals(event)) {
                emailService.sendRegistrationEmail(email);
            } else if ("user_deleted".equals(event)) {
                emailService.sendDeletionEmail(email);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}