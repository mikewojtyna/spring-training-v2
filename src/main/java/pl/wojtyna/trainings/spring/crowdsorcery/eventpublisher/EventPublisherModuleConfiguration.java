package pl.wojtyna.trainings.spring.crowdsorcery.eventpublisher;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootConfiguration
public class EventPublisherModuleConfiguration {

    @Bean
    public EventPublisher eventPublisher() {
        return new InMemoryEventPublisher();
    }
}
