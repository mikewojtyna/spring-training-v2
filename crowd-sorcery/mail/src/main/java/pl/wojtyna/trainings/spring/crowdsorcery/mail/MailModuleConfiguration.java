package pl.wojtyna.trainings.spring.crowdsorcery.mail;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MailModuleConfiguration {

    @Bean
    public EmailAddressResolver stubbedEmailAddressResolver() {
        return user -> "%s@wojtyna.pl".formatted(user.name());
    }
}
