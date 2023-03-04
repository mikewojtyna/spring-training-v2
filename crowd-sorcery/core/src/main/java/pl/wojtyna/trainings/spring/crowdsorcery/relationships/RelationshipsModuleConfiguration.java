package pl.wojtyna.trainings.spring.crowdsorcery.relationships;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RelationshipsModuleConfiguration {

    @Bean
    public RelationshipsManager relationshipsManager() {
        return new RelationshipsManager();
    }
}
