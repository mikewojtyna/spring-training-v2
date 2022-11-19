package pl.wojtyna.trainings.spring.crowdsorcery.notification;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.MailSender;
import pl.wojtyna.trainings.spring.crowdsorcery.eventpublisher.SubscriberRegistry;
import pl.wojtyna.trainings.spring.crowdsorcery.mail.EmailAddressResolver;

@Configuration
public class NotificationModuleConfiguration {

    @Bean
    public EventSubscriberInitializer eventSubscriberInitializer(SubscriberRegistry subscriberRegistry,
                                                                 NotificationService notificationService) {
        return new EventSubscriberInitializer(subscriberRegistry, notificationService);
    }

    @Bean
    @Profile({"prod", "default"})
    public NotificationService mailNotificationService(MailSender mailSender,
                                                       EmailAddressResolver emailAddressResolver) {
        return new SimpleMailNotificationService(mailSender, emailAddressResolver);
    }

    @Bean
    @Profile({"test", "demo"})
    public NotificationService stubbedNotificationService() {
        return event -> {};
    }
}
