package pl.wojtyna.trainings.spring.crowdsorcery.aspects;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AspectsConfiguration {

    @Bean
    public LoggingAspect loggingAspect() {
        return new LoggingAspect();
    }
}
