package pl.wojtyna.trainings.spring.crowdsorcery.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SecurityModuleConfiguration {

    @Bean
    public PasswordChecker passwordChecker() {
        return new Base64DecodingPasswordChecker();
    }
}
