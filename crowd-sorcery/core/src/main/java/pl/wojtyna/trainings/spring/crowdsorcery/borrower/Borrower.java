package pl.wojtyna.trainings.spring.crowdsorcery.borrower;

import pl.wojtyna.trainings.spring.crowdsorcery.common.CrowdSorceryUser;

public record Borrower(String id, String name) implements CrowdSorceryUser {
}
