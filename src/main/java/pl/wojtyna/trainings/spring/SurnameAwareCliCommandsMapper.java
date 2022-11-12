package pl.wojtyna.trainings.spring;

import java.util.Optional;
import java.util.stream.Stream;

public class SurnameAwareCliCommandsMapper implements CliCommandsMapper {

    @Override
    public Optional<RegisterInvestor> map(String[] args) {
        var id = extractIdOrFail(args);
        var name = extractFirstNameOrFail(args) + tryToExtractSurname(args).map(surname -> " " + surname).orElse("");
        return Optional.of(new RegisterInvestor(id, name));
    }

    private Optional<String> tryToExtractSurname(String[] args) {
        return tryToExtractArg("surname", args);
    }

    private String extractFirstNameOrFail(String[] args) {
        return tryToExtractArg("name", args).orElseThrow();
    }

    private String extractIdOrFail(String[] args) {
        return tryToExtractArg("id", args).orElseThrow();
    }

    private Optional<String> tryToExtractArg(String argName, String[] args) {
        return Stream.of(args)
                     .filter(arg -> arg.startsWith("-%s=".formatted(argName)))
                     .findAny()
                     .map(arg -> arg.split("=")[1]);
    }
}
