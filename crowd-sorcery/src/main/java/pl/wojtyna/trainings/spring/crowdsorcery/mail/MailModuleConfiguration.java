package pl.wojtyna.trainings.spring.crowdsorcery.mail;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MailModuleConfiguration {

    @Bean
    public EmailAddressResolver stubbedEmailAddressResolver() {
        return investor -> "%s@wojtyna.pl".formatted(investor.name());
    }
}
