package pl.wojtyna.trainings.spring.crowdsorcery.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.authorizeRequests()
                           .antMatchers("/portfolio-module/**")
                           .authenticated()
                           .and()
                           .httpBasic()
                           .and()
                           .authorizeRequests()
                           .anyRequest()
                           .permitAll()
                           .and()
                           .csrf()
                           .disable()
                           .build();
    }

    @Bean
    public UserDetailsService userDetailsService(IdentityRepository identityRepository) {
        return new IdentityRepositoryBackedUserDetailsService(identityRepository);
    }
}
