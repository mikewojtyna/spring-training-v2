package pl.wojtyna.trainings.spring.crowdsorcery.problems.scoring;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

public class TransactionExecutor {

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void executeInTransaction(Runnable code) {
        code.run();
    }
}
