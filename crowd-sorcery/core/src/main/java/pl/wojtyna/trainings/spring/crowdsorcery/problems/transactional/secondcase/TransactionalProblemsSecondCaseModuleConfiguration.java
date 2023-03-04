package pl.wojtyna.trainings.spring.crowdsorcery.problems.transactional.secondcase;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TransactionalProblemsSecondCaseModuleConfiguration {

    @Bean
    public BorrowerProfileManager profileManagerSecondCase(
        BorrowerProfileSecondCaseProblemRepository repo) {
        return new BorrowerProfileManager(repo);
    }
}
