package pl.wojtyna.trainings.spring.crowdsorcery.problems.scoring;

import java.util.concurrent.TimeUnit;

public class ScoreUpdater extends BaseBehavior {

    private final InvestorDataRepository repository;
    private final TransactionExecutor transactionExecutor;

    public ScoreUpdater(InvestorDataRepository repository, TransactionExecutor transactionExecutor) {
        this.repository = repository;
        this.transactionExecutor = transactionExecutor;
    }

    @Override
    public void incrementInvestorScoreBy(String id, int value) {
        transactionExecutor.executeInTransaction(() -> repository.findById(id).ifPresent(investorData -> {
            investorData.setScore(investorData.getScore() + value);
            repository.flush();
            try {
                TimeUnit.SECONDS.sleep(1);
                throw new RuntimeException("something bad happened");
            }
            catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }));
    }
}
