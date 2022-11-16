package pl.wojtyna.trainings.spring.crowdsorcery.investor.rest;

import javax.validation.constraints.NotEmpty;

public record RegisterInvestorRestDto(@NotEmpty String id, @NotEmpty String name) {
}
