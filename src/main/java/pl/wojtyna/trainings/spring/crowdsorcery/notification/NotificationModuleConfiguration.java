package pl.wojtyna.trainings.spring.crowdsorcery.notification;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.MailSender;
import pl.wojtyna.trainings.spring.crowdsorcery.eventpublisher.SubscriberRegistry;

@SpringBootConfiguration
@EnableAutoConfiguration
public class NotificationModuleConfiguration {

    @Bean
    public EventSubscriberInitializer eventSubscriberInitializer(SubscriberRegistry subscriberRegistry,
                                                                 NotificationService notificationService) {
        return new EventSubscriberInitializer(subscriberRegistry, notificationService);
    }

    @Bean
    public EmailAddressResolver stubbedEmailAddressResolver() {
        return investor -> "mike@wojtyna.pl";
    }

    @Bean
    public NotificationService notificationService(MailSender mailSender, EmailAddressResolver emailAddressResolver) {
        return new SimpleMailNotificationService(mailSender, emailAddressResolver);
    }
}
