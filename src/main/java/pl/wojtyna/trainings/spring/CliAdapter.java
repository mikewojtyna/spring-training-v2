package pl.wojtyna.trainings.spring;

public class CliAdapter {

    private final CliCommandsMapper cliCommandsMapper;

    public CliAdapter(CliCommandsMapper cliCommandsMapper) {
        this.cliCommandsMapper = cliCommandsMapper;
    }

    void run(String[] args) {
        var investorRepository = new InMemoryInvestorRepository();
        var investorService = new InvestorService(investorRepository);
        var registerInvestorCommand = cliCommandsMapper.map(args).orElseThrow();
        System.out.println("Registering new investor");
        investorService.register(registerInvestorCommand);
        System.out.println("New investor registered");
        System.out.println("All available investors");
        investorService.findAll().forEach(System.out::println);
    }
}
