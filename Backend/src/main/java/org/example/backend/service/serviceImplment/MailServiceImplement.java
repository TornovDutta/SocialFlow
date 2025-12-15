package org.example.backend.service.serviceImplment;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailServiceImplement {
    private final JavaMailSender mailSender;
    public void mail(String message,String email) {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(email);
        mail.setSubject("Post Notification");
        mail.setText(message);

        mailSender.send(mail);
    }
}
