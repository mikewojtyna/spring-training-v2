package pl.wojtyna.trainings.spring.examples.beans.crowdsorcery.investor.cli;

import pl.wojtyna.trainings.spring.examples.beans.crowdsorcery.investor.service.RegisterInvestor;

import java.util.Optional;

public interface CliCommandsMapper {

    Optional<RegisterInvestor> map(String[] args);
}
