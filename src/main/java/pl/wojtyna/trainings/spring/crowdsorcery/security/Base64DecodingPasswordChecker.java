package pl.wojtyna.trainings.spring.crowdsorcery.security;

import java.util.Base64;
import java.util.Objects;

public class Base64DecodingPasswordChecker implements PasswordChecker {

    @Override
    public boolean isCorrect(String password) {
        return Objects.equals(new String(Base64.getDecoder().decode(password)), "password");
    }
}
