package pl.wojtyna.trainings.spring;

import java.util.Optional;

public interface CliCommandsMapper {

    Optional<RegisterInvestor> map(String[] args);
}
