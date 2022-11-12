package pl.wojtyna.trainings.spring;

public class NoSpringApp {

    public static void main(String[] args) {
        CliCommandsMapper cliCommandsMapper = new SimpleNameCliCommandsMapper();
        InvestorRepository investorRepository = new InMemoryInvestorRepository();
        LocalInvestorProfileRepository localInvestorProfileRepository = new FakeInvestorProfileRepository();
        InvestorProfileService investorProfileService = new RepositoryInvestorProfileService(
            localInvestorProfileRepository);
        var investorService = new InvestorService(investorRepository, investorProfileService);
        var cliAdapter = new CliAdapter(cliCommandsMapper, investorService);
        cliAdapter.run(args);
    }
}
