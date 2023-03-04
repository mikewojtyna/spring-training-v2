package pl.wojtyna.trainings.spring.crowdsorcery.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.wojtyna.trainings.spring.crowdsorcery.relationships.RelationshipsManager;

@Configuration
public class SecurityModuleConfiguration {

    @Bean
    public CrowdSorceryPermissionEvaluator crowdSorceryPermissionEvaluator(RelationshipsManager relationshipsManager) {
        return new CrowdSorceryPermissionEvaluator(relationshipsManager);
    }

    @Bean
    public DefaultIdentitiesInitializer defaultIdentitiesInitializer(IdentityRepository identityRepository,
                                                                     PasswordEncoder passwordEncoder) {
        return new DefaultIdentitiesInitializer(identityRepository, passwordEncoder);
    }

    @Bean
    public IdentityCreatorEventListener identityCreatorEventListener(IdentityRepository identityRepository,
                                                                     PasswordGenerator passwordGenerator) {
        return new IdentityCreatorEventListener(identityRepository, passwordGenerator);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public PasswordGenerator passwordGenerator(PasswordEncoder passwordEncoder,
                                               @Value("${crowd-sorcery.security.dummy-password}") String dummyPassword) {
        return new OnlyForDemoHardcodedDummyDontUseOnProductionPasswordGenerator(passwordEncoder, dummyPassword);
    }

    @Bean
    public PasswordChecker passwordChecker() {
        return new Base64DecodingPasswordChecker();
    }
}
