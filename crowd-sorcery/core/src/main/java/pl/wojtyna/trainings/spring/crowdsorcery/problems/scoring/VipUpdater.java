package pl.wojtyna.trainings.spring.crowdsorcery.problems.scoring;

import java.util.concurrent.TimeUnit;

public class VipUpdater extends BaseBehavior {

    private final InvestorDataRepository repository;
    private final TransactionExecutor transactionExecutor;

    public VipUpdater(InvestorDataRepository repository, TransactionExecutor transactionExecutor) {
        this.repository = repository;
        this.transactionExecutor = transactionExecutor;
    }

    @Override
    public void upgradeAllToVipWhenNeeded() {
        transactionExecutor.executeInTransaction(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(200);
            }
            catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            repository.findAll().forEach(investor -> {
                if (investor.getScore() > 100) {
                    investor.setVip(true);
                }
            });
        });
    }
}
