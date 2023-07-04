package pl.wojtyna.trainings.spring.crowdsorcery.validation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FormValidationConfig {

    @Bean
    public FormValidatingService formValidatingService() {
        return new FormValidatingService();
    }
}
