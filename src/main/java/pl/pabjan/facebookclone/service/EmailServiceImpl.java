package pl.pabjan.facebookclone.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pl.pabjan.facebookclone.exceptions.FacebookCloneException;


import javax.mail.*;
import javax.mail.internet.*;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

@Component
@AllArgsConstructor
public class EmailServiceImpl {

    private final JavaMailSender mailSender;

    @Async
    public void sendmail(String email, String subject, String content) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("noreply@facebookclone.com");
            message.setTo(email);
            message.setSubject(subject);
            message.setText(content);
            mailSender.send(message);
        }
        catch(Exception e) {
            throw new FacebookCloneException("Error during sending email to " + email);
        }
    }
}
