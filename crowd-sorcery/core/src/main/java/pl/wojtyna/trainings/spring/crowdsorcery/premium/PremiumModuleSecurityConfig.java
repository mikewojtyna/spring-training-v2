package pl.wojtyna.trainings.spring.crowdsorcery.premium;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@Profile("premium")
public class PremiumModuleSecurityConfig {

    @Bean
    @Order(0)
    public SecurityFilterChain premiumChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.antMatcher("/premium/**")
                           .authorizeRequests()
                           .anyRequest()
                           .authenticated()
                           .and()
                           .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt).build();
    }
}
