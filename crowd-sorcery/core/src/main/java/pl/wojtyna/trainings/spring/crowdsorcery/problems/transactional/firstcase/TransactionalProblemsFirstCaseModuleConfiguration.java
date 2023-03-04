package pl.wojtyna.trainings.spring.crowdsorcery.problems.transactional.firstcase;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TransactionalProblemsFirstCaseModuleConfiguration {

    @Bean
    public BorrowerProfileManager profileManager(BorrowerProfileProblemRepository repo) {
        return new BorrowerProfileManager(repo);
    }
}
