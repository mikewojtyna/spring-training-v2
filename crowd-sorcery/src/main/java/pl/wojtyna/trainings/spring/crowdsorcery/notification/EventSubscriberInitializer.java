package pl.wojtyna.trainings.spring.crowdsorcery.notification;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import pl.wojtyna.trainings.spring.crowdsorcery.eventpublisher.SubscriberRegistry;

public class EventSubscriberInitializer implements ApplicationRunner {

    private final SubscriberRegistry subscriberRegistry;
    private final NotificationService notificationService;

    public EventSubscriberInitializer(SubscriberRegistry subscriberRegistry, NotificationService notificationService) {
        this.subscriberRegistry = subscriberRegistry;
        this.notificationService = notificationService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        subscriberRegistry.register(notificationService::notifyAbout);
    }
}
