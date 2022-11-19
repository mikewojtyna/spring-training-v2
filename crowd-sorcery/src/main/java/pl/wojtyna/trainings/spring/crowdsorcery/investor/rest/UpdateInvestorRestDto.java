package pl.wojtyna.trainings.spring.crowdsorcery.investor.rest;

import javax.validation.constraints.NotEmpty;

public record UpdateInvestorRestDto(@NotEmpty String name, int version) {
}
