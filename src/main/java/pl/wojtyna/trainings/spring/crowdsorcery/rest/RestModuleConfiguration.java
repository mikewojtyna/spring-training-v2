package pl.wojtyna.trainings.spring.crowdsorcery.rest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RestModuleConfiguration {

    @Bean
    public RestClient restClient() {
        return new UnirestClient(() -> "http://localhost:8080/investor/api/v0/profiles");
    }
}
