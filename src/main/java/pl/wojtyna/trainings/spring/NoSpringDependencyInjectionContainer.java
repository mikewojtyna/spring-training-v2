package pl.wojtyna.trainings.spring;

public class NoSpringDependencyInjectionContainer {

    public static NoSpringDependencyInjectionContainer create() {
        return new NoSpringDependencyInjectionContainer();
    }

    CliAdapter configureCliAdapter() {
        CliCommandsMapper cliCommandsMapper = new SurnameAwareCliCommandsMapper();
        InvestorRepository investorRepository = new InMemoryInvestorRepository();
        RestClient restClient = new UnirestClient(
            "http://localhost:8080/investor/api/v0/profiles");
        InvestorProfileService investorProfileService = new RestClientInvestorProfileService(
            restClient);
        var investorService = new InvestorService(investorRepository, investorProfileService);
        return new CliAdapter(cliCommandsMapper, investorService);
    }
}
