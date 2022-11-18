package pl.wojtyna.trainings.spring.crowdsorcery.security;

import org.springframework.security.crypto.password.PasswordEncoder;

public class OnlyForDemoHardcodedDummyDontUseOnProductionPasswordGenerator implements PasswordGenerator {

    private final PasswordEncoder passwordEncoder;
    private final String dummyPassword;

    public OnlyForDemoHardcodedDummyDontUseOnProductionPasswordGenerator(PasswordEncoder passwordEncoder,
                                                                         String dummyPassword) {
        this.passwordEncoder = passwordEncoder;
        this.dummyPassword = dummyPassword;
    }

    @Override
    public String password() {
        return passwordEncoder.encode(dummyPassword);
    }
}
