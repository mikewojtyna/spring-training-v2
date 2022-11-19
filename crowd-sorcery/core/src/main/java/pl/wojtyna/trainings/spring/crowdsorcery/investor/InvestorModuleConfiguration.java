package pl.wojtyna.trainings.spring.crowdsorcery.investor;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.wojtyna.trainings.spring.crowdsorcery.audit.AuditLog;
import pl.wojtyna.trainings.spring.crowdsorcery.eventpublisher.EventPublisher;
import pl.wojtyna.trainings.spring.crowdsorcery.investor.cli.CliAdapter;
import pl.wojtyna.trainings.spring.crowdsorcery.investor.cli.CliCommandsMapper;
import pl.wojtyna.trainings.spring.crowdsorcery.investor.cli.SurnameAwareCliCommandsMapper;
import pl.wojtyna.trainings.spring.crowdsorcery.investor.profile.InvestorProfileService;
import pl.wojtyna.trainings.spring.crowdsorcery.investor.profile.RepositoryInvestorProfileService;
import pl.wojtyna.trainings.spring.crowdsorcery.investor.repository.FakeInvestorProfileRepository;
import pl.wojtyna.trainings.spring.crowdsorcery.investor.repository.InitialInvestorsPopulator;
import pl.wojtyna.trainings.spring.crowdsorcery.investor.repository.InvestorRepository;
import pl.wojtyna.trainings.spring.crowdsorcery.investor.repository.LocalInvestorProfileRepository;
import pl.wojtyna.trainings.spring.crowdsorcery.investor.repository.mongo.MongoSpringDataBackedInvestorRepository;
import pl.wojtyna.trainings.spring.crowdsorcery.investor.repository.mongo.MongoSpringInvestorRepository;
import pl.wojtyna.trainings.spring.crowdsorcery.investor.service.InvestorService;

@Configuration
public class InvestorModuleConfiguration {

    @Bean
    public InitialInvestorsPopulator initialInvestorsPopulator(InvestorRepository investorRepository) {
        return new InitialInvestorsPopulator(investorRepository);
    }

    @Bean
    public CliAdapter cliAdapter(CliCommandsMapper cliMapper, InvestorService investorService) {
        return new CliAdapter(cliMapper, investorService);
    }

    @Bean
    public LocalInvestorProfileRepository localInvestorProfileRepository() {
        return new FakeInvestorProfileRepository();
    }

    @Bean
    public InvestorProfileService investorProfileService(LocalInvestorProfileRepository localInvestorProfileRepository) {
        return new RepositoryInvestorProfileService(localInvestorProfileRepository);
    }

    @Bean
    public InvestorRepository investorRepository(MongoSpringInvestorRepository springRepository) {
        return new MongoSpringDataBackedInvestorRepository(springRepository);
    }

    @Bean
    public InvestorService investorService(InvestorRepository investorRepository,
                                           InvestorProfileService investorProfileService,
                                           EventPublisher eventPublisher, ObjectProvider<AuditLog> auditLogProvider) {
        return auditLogProvider.stream()
                               .findAny()
                               .map(auditLog -> new InvestorService(investorRepository,
                                                                    investorProfileService,
                                                                    eventPublisher,
                                                                    auditLog))
                               .orElse(new InvestorService(investorRepository, investorProfileService, eventPublisher));
    }

    @Bean
    public CliCommandsMapper cliCommandsMapper() {
        return new SurnameAwareCliCommandsMapper();
    }
}
