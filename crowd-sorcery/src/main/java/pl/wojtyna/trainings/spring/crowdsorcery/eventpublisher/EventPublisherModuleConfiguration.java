package pl.wojtyna.trainings.spring.crowdsorcery.eventpublisher;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class EventPublisherModuleConfiguration {

    @Bean
    public SubscriberRegistry subscriberRegistry(InMemoryEventPublisher inMemoryEventPublisher) {
        return inMemoryEventPublisher;
    }

    @Bean
    public InMemoryEventPublisher inMemoryEventPublisher() {
        return new InMemoryEventPublisher();
    }

    @Bean(name = "compositeEventPublisher")
    @Primary
    public EventPublisher eventPublisher(InMemoryEventPublisher inMemoryEventPublisher,
                                         ApplicationEventPublisher applicationEventPublisher) {
        return new CompositeSpringEventPublisher(inMemoryEventPublisher, applicationEventPublisher);
    }
}
