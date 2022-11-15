package pl.wojtyna.trainings.spring.crowdsorcery.notification;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.MailSender;
import pl.wojtyna.trainings.spring.crowdsorcery.eventpublisher.SubscriberRegistry;

@Configuration
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
