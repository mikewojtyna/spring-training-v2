package pl.wojtyna.trainings.spring.crowdsorcery.investor;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Scope;
import pl.wojtyna.trainings.spring.crowdsorcery.eventpublisher.EventPublisher;
import pl.wojtyna.trainings.spring.crowdsorcery.rest.RestClient;
import pl.wojtyna.trainings.spring.crowdsorcery.rest.RestModuleConfiguration;

@SpringBootConfiguration
@Import(RestModuleConfiguration.class)
public class InvestorModuleConfiguration {

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
    public InvestorProfileService investorProfileService(RestClient restClient) {
        return new RestClientInvestorProfileService(restClient);
    }

    @Bean
    public InvestorRepository investorRepository() {
        return new InMemoryInvestorRepository();
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
