package mail.autoconfigure;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.wojtyna.trainings.spring.crowdsorcery.mail.EmailAddressResolver;
import pl.wojtyna.trainings.spring.crowdsorcery.mail.FakeEmailAddressResolver;

@Configuration
@EnableConfigurationProperties(MailConfigurationProperties.class)
public class MailAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(EmailAddressResolver.class)
    public EmailAddressResolver stubbedEmailAddressResolver(MailConfigurationProperties properties) {
        return new FakeEmailAddressResolver(properties.fakeDomain());
    }
}
