package pl.wojtyna.trainings.spring.crowdsorcery.security;

public interface PasswordChecker {

    boolean isCorrect(String password);
}
