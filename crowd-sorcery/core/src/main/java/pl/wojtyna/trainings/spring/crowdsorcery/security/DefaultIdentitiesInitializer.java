package pl.wojtyna.trainings.spring.crowdsorcery.security;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

public class DefaultIdentitiesInitializer implements ApplicationRunner {

    private final IdentityRepository identityRepository;
    private final PasswordEncoder passwordEncoder;

    public DefaultIdentitiesInitializer(IdentityRepository identityRepository, PasswordEncoder passwordEncoder) {
        this.identityRepository = identityRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        identityRepository.save(new CrowdSorceryIdentity("MODERATOR_0",
                                                         "ROLE_MODERATOR",
                                                         passwordEncoder.encode("qwerty")));
    }
}
