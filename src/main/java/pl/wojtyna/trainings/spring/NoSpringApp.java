package pl.wojtyna.trainings.spring;

public class NoSpringApp {

    public static void main(String[] args) {
        var container = NoSpringDependencyInjectionContainer.create();

        container.register(HardcodedRestResources.class, RestResources.class);
        container.register(RestClientInvestorProfileService.class, InvestorProfileService.class);
        container.register(UnirestClient.class, RestClient.class);
        container.register(SurnameAwareCliCommandsMapper.class, CliCommandsMapper.class);
        container.register(InvestorService.class, InvestorService.class);
        container.register(InMemoryInvestorRepository.class, InvestorRepository.class);
        container.register(CliAdapter.class, CliAdapter.class);

        var cliAdapter = container.getComponent(CliAdapter.class).orElseThrow();
        cliAdapter.run(args);
    }
}
