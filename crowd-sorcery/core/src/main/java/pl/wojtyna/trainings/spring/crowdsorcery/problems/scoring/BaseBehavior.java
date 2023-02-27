package pl.wojtyna.trainings.spring.crowdsorcery.problems.scoring;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(isolation = Isolation.READ_UNCOMMITTED)
public abstract class BaseBehavior {

    public void incrementInvestorScoreBy(String id, int value) {
        // ¯\_(ツ)_/¯
    }

    public void upgradeAllToVipWhenNeeded() {
        // ¯\_(ツ)_/¯
    }
}
