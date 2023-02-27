package pl.wojtyna.trainings.spring.crowdsorcery.problems.scoring;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.wojtyna.trainings.spring.crowdsorcery.testutils.CrowdSorceryTestBase;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class UpgradeToVipProblemTest extends CrowdSorceryTestBase {

    @Autowired
    private InvestorDataRepository investorDataRepository;
    @Autowired
    private ScoreUpdater scoreUpdater;
    @Autowired
    private VipUpdater vipUpdater;

    @Test
    void problemTest() throws InterruptedException {
        var george = new InvestorData(UUID.randomUUID().toString(), 0, false);
        investorDataRepository.save(george);
        assertThat(investorDataRepository.findById(george.getId())).hasValueSatisfying(investorData -> assertThat(
            investorData.isVip()).isFalse());

        new Thread(() -> scoreUpdater.incrementInvestorScoreBy(george.getId(), 101)).start();
        new Thread(() -> vipUpdater.upgradeAllToVipWhenNeeded()).start();

        TimeUnit.SECONDS.sleep(2);

        assertThat(investorDataRepository.findById(george.getId())).hasValueSatisfying(investorData -> assertThat(
            investorData.isVip()).isTrue());
    }
}
