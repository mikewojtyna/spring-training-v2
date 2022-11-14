package pl.wojtyna.trainings.spring.crowdsorcery;

import java.util.Optional;

public interface CliCommandsMapper {

    Optional<RegisterInvestor> map(String[] args);
}
