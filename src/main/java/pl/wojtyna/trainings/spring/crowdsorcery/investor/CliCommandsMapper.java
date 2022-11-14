package pl.wojtyna.trainings.spring.crowdsorcery.investor;

import java.util.Optional;

public interface CliCommandsMapper {

    Optional<RegisterInvestor> map(String[] args);
}
