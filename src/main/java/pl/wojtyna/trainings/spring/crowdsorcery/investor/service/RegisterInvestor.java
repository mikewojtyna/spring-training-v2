package pl.wojtyna.trainings.spring.crowdsorcery.investor.service;

import pl.wojtyna.trainings.spring.crowdsorcery.audit.Command;

public record RegisterInvestor(String id, String name) implements Command {
}
