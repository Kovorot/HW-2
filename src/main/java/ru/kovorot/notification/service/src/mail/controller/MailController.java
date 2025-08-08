package mail.controller;

import mail.dto.EmailRequest;
import mail.service.EmailService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notifications")
public class MailController {

    private final EmailService emailService;

    public MailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/send")
    public void sendEmail(@RequestBody EmailRequest request) {
        if ("registration".equals(request.getType())) {
            emailService.sendRegistrationEmail(request.getEmail());
        } else if ("deletion".equals(request.getType())) {
            emailService.sendDeletionEmail(request.getEmail());
        }
    }
}