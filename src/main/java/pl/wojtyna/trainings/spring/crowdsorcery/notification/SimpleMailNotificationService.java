package pl.wojtyna.trainings.spring.crowdsorcery.notification;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import pl.wojtyna.trainings.spring.crowdsorcery.eventpublisher.Event;
import pl.wojtyna.trainings.spring.crowdsorcery.investor.InvestorRegistered;

public class SimpleMailNotificationService implements NotificationService {

    private final MailSender mailSender;
    private final EmailAddressResolver emailAddressResolver;

    public SimpleMailNotificationService(MailSender mailSender, EmailAddressResolver emailAddressResolver) {
        this.mailSender = mailSender;
        this.emailAddressResolver = emailAddressResolver;
    }

    @Override
    public void notifyAbout(Event event) {
        if (event instanceof InvestorRegistered investorRegistered) {
            var message = new SimpleMailMessage();
            message.setSubject("Investor profile is registered!");
            message.setText("Your investor profile is registered");
            message.setTo(emailAddressResolver.resolveAddressOf(investorRegistered.investor()));
            mailSender.send(message);
        }
    }
}
