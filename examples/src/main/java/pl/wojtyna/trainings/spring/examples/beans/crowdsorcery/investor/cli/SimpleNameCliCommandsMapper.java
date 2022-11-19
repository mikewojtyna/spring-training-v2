package pl.wojtyna.trainings.spring.examples.beans.crowdsorcery.investor.cli;

import pl.wojtyna.trainings.spring.examples.beans.crowdsorcery.investor.service.RegisterInvestor;

import java.util.Optional;
import java.util.stream.Stream;

public class SimpleNameCliCommandsMapper implements CliCommandsMapper {

    @Override
    public Optional<RegisterInvestor> map(String[] args) {
        var id = extractIdOrFail(args);
        var name = extractNameOrFail(args);
        return Optional.of(new RegisterInvestor(id, name));
    }

    private String extractNameOrFail(String[] args) {
        return extractArgOrFail("name", args);
    }

    private String extractIdOrFail(String[] args) {
        return extractArgOrFail("id", args);
    }

    private String extractArgOrFail(String argName, String[] args) {
        return Stream.of(args)
                     .filter(arg -> arg.startsWith("-%s=".formatted(argName)))
                     .findAny()
                     .map(arg -> arg.split("=")[1])
                     .orElseThrow();
    }
}
