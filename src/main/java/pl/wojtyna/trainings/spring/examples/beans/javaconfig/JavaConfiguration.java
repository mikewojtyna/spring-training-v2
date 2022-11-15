package pl.wojtyna.trainings.spring.examples.beans.javaconfig;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import pl.wojtyna.trainings.spring.crowdsorcery.eventpublisher.EventPublisher;
import pl.wojtyna.trainings.spring.crowdsorcery.eventpublisher.InMemoryEventPublisher;
import pl.wojtyna.trainings.spring.crowdsorcery.investor.cli.CliAdapter;
import pl.wojtyna.trainings.spring.crowdsorcery.investor.cli.CliCommandsMapper;
import pl.wojtyna.trainings.spring.crowdsorcery.investor.cli.CliSpringAdapterRunner;
import pl.wojtyna.trainings.spring.crowdsorcery.investor.cli.SurnameAwareCliCommandsMapper;
import pl.wojtyna.trainings.spring.crowdsorcery.investor.profile.RestClientInvestorProfileService;
import pl.wojtyna.trainings.spring.crowdsorcery.investor.repository.InMemoryInvestorRepository;
import pl.wojtyna.trainings.spring.crowdsorcery.investor.repository.InvestorRepository;
import pl.wojtyna.trainings.spring.crowdsorcery.investor.service.InvestorProfileService;
import pl.wojtyna.trainings.spring.crowdsorcery.investor.service.InvestorService;
import pl.wojtyna.trainings.spring.crowdsorcery.rest.RestClient;
import pl.wojtyna.trainings.spring.crowdsorcery.rest.UnirestClient;

@Configuration
public class JavaConfiguration {

    @Bean
    @Scope(BeanDefinition.SCOPE_SINGLETON) // singleton is the default scope
    public CliSpringAdapterRunner cliSpringAdapterRunner(CliAdapter cliAdapter) {
        return new CliSpringAdapterRunner(cliAdapter);
    }

    @Bean
    public CliAdapter cliAdapter(CliCommandsMapper cliMapper, InvestorService investorService) {
        return new CliAdapter(cliMapper, investorService);
    }

    @Bean
    public RestClient restClient() {
        return new UnirestClient(() -> "http://localhost:8080/investor/api/v0/profiles");
    }

    @Bean
    public InvestorProfileService investorProfileService(RestClient restClient) {
        return new RestClientInvestorProfileService(restClient);
    }

    @Bean
    public InvestorRepository investorRepository() {
        return new InMemoryInvestorRepository();
    }

    @Bean
    public EventPublisher eventPublisher() {
        return new InMemoryEventPublisher();
    }

    @Bean
    public InvestorService investorService(InvestorRepository investorRepository,
                                           InvestorProfileService investorProfileService,
                                           EventPublisher eventPublisher) {
        return new InvestorService(investorRepository, investorProfileService, eventPublisher);
    }

    @Bean
    public CliCommandsMapper cliCommandsMapper() {
        return new SurnameAwareCliCommandsMapper();
    }
}
