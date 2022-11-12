package pl.wojtyna.trainings.spring;

import java.util.stream.Stream;

public class NoSpringApp {

    public static void main(String[] args) {
        var investorRepository = new InMemoryInvestorRepository();
        var investorService = new InvestorService(investorRepository);
        var id = extractIdOrFail(args);
        var name = extractNameOrFail(args);
        var investor = new Investor(id, name);
        System.out.println("Registering new investor");
        investorService.register(investor);
        System.out.println("New investor registered");
        System.out.println("All available investors");
        investorService.findAll().forEach(System.out::println);
    }

    private static String extractNameOrFail(String[] args) {
        return extractArgOrFail("name", args);
    }

    private static String extractIdOrFail(String[] args) {
        return extractArgOrFail("id", args);
    }

    private static String extractArgOrFail(String argName, String[] args) {
        return Stream.of(args)
                     .filter(arg -> arg.startsWith("-%s=".formatted(argName)))
                     .findAny()
                     .map(arg -> arg.split("=")[1])
                     .orElseThrow();
    }
}
