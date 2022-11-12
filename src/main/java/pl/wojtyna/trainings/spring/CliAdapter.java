package pl.wojtyna.trainings.spring;

import java.util.stream.Stream;

public class CliAdapter {

    void run(String[] args) {
        var investorRepository = new InMemoryInvestorRepository();
        var investorService = new InvestorService(investorRepository);
        var id = extractIdOrFail(args);
        var name = extractNameOrFail(args);
        var registerInvestorCommand = new RegisterInvestor(id, name);
        System.out.println("Registering new investor");
        investorService.register(registerInvestorCommand);
        System.out.println("New investor registered");
        System.out.println("All available investors");
        investorService.findAll().forEach(System.out::println);
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
