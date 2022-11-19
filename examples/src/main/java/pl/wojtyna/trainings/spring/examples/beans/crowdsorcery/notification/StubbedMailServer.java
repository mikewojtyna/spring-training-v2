package pl.wojtyna.trainings.spring.examples.beans.crowdsorcery.notification;

import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

@Component
public class StubbedMailServer implements MailSender {

    @Override
    public void send(SimpleMailMessage simpleMessage) throws MailException {
        // do nothing intentionally
    }

    @Override
    public void send(SimpleMailMessage... simpleMessages) throws MailException {
        // do nothing intentionally
    }
}
