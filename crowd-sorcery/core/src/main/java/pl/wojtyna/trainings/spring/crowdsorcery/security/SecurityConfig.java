package pl.wojtyna.trainings.spring.crowdsorcery.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.authenticationProvider(new IdDocumentAuthenticationProvider())
                           .addFilterBefore(new IdDocumentAuthenticationFilter(), BasicAuthenticationFilter.class)
                           .authorizeRequests()
                           .antMatchers("/portfolio-module/api/v0/portfolios/public/**")
                           .permitAll()
                           .and()
                           .authorizeRequests()
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

    private static class IdDocumentAuthentication extends AbstractAuthenticationToken {

        private final IdDocument idDocument;

        public IdDocumentAuthentication(IdDocument idDocument,
                                        boolean authenticated,
                                        Collection<? extends GrantedAuthority> authorities) {
            super(authorities);
            this.idDocument = idDocument;
            setAuthenticated(authenticated);
        }

        @Override
        public Object getCredentials() {
            return idDocument;
        }

        @Override
        public Object getPrincipal() {
            return idDocument.content();
        }

        record IdDocument(String content) {

        }
    }

    private static class IdDocumentAuthenticationProvider implements AuthenticationProvider {

        // use some hardcoded value => only one id document is acceptable
        private final IdDocumentAuthentication.IdDocument acceptableIdDocument = new IdDocumentAuthentication.IdDocument(
            "45419b98-ea19-48e7-a39f-d990d948c64a");

        @Override
        public Authentication authenticate(Authentication authentication) throws AuthenticationException {
            if (authentication instanceof IdDocumentAuthentication idDocumentAuthentication) {
                if (acceptableIdDocument.equals(idDocumentAuthentication.idDocument)) {
                    return new IdDocumentAuthentication(idDocumentAuthentication.idDocument,
                                                        true,
                                                        List.of(new SimpleGrantedAuthority("USER")));
                }
            }
            throw new BadCredentialsException("Bad identity document");
        }

        @Override
        public boolean supports(Class<?> authentication) {
            return IdDocumentAuthentication.class.isAssignableFrom(authentication);
        }
    }

    private static class IdDocumentAuthenticationFilter extends HttpFilter {

        @Override
        protected void doFilter(HttpServletRequest request,
                                HttpServletResponse response,
                                FilterChain chain) throws IOException, ServletException {
            var idDocumentContent = request.getHeader("Id-Document");
            if (idDocumentContent != null) {
                SecurityContextHolder.getContext()
                                     .setAuthentication(new IdDocumentAuthentication(new IdDocumentAuthentication.IdDocument(
                                         idDocumentContent), false, List.of()));
            }
            chain.doFilter(request, response);
        }
    }
}
