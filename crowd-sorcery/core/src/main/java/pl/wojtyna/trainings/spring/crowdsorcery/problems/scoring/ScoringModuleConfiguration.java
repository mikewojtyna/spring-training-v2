package pl.wojtyna.trainings.spring.crowdsorcery.problems.scoring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ScoringModuleConfiguration {

    @Bean
    public TransactionExecutor transactionExecutor() {
        return new TransactionExecutor();
    }

    @Bean
    public VipUpdater vipUpdater(InvestorDataRepository repository, TransactionExecutor transactionExecutor) {
        return new VipUpdater(repository, transactionExecutor);
    }

    @Bean
    public ScoreUpdater scoreUpdater(InvestorDataRepository repository, TransactionExecutor transactionExecutor) {
        return new ScoreUpdater(repository, transactionExecutor);
    }
}
