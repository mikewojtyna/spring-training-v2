package pl.wojtyna.trainings.spring.crowdsorcery.eventpublisher;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EventPublisherModuleConfiguration {

    @Bean
    public EventPublisher eventPublisher() {
        return new InMemoryEventPublisher();
    }
}
