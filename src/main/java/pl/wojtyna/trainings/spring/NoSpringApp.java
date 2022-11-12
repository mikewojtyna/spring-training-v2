package pl.wojtyna.trainings.spring;

public class NoSpringApp {

    public static void main(String[] args) {
        CliCommandsMapper cliCommandsMapper = new SurnameAwareCliCommandsMapper();
        InvestorRepository investorRepository = new InMemoryInvestorRepository();
        RestClient restClient = new UnirestClient(
            "http://localhost:8080/investor/api/v0/profiles");
        InvestorProfileService investorProfileService = new RestClientInvestorProfileService(
            restClient);
        var investorService = new InvestorService(investorRepository, investorProfileService);
        var cliAdapter = new CliAdapter(cliCommandsMapper, investorService);
        cliAdapter.run(args);
    }
}
