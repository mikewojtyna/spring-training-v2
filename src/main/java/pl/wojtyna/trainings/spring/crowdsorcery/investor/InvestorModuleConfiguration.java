package pl.wojtyna.trainings.spring.crowdsorcery.investor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import pl.wojtyna.trainings.spring.crowdsorcery.eventpublisher.EventPublisher;
import pl.wojtyna.trainings.spring.crowdsorcery.investor.cli.CliAdapter;
import pl.wojtyna.trainings.spring.crowdsorcery.investor.cli.CliCommandsMapper;
import pl.wojtyna.trainings.spring.crowdsorcery.investor.cli.SurnameAwareCliCommandsMapper;
import pl.wojtyna.trainings.spring.crowdsorcery.investor.profile.RestClientInvestorProfileService;
import pl.wojtyna.trainings.spring.crowdsorcery.investor.repository.InMemoryInvestorRepository;
import pl.wojtyna.trainings.spring.crowdsorcery.investor.repository.InvestorRepository;
import pl.wojtyna.trainings.spring.crowdsorcery.investor.service.InvestorProfileService;
import pl.wojtyna.trainings.spring.crowdsorcery.investor.service.InvestorService;
import pl.wojtyna.trainings.spring.crowdsorcery.rest.RestClient;
import pl.wojtyna.trainings.spring.crowdsorcery.rest.RestModuleConfiguration;

@Configuration
@Import(RestModuleConfiguration.class)
public class InvestorModuleConfiguration {

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
