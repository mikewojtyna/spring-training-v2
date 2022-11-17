package pl.wojtyna.trainings.spring.crowdsorcery.investor.service;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.wojtyna.trainings.spring.crowdsorcery.testutils.CrowdSorceryTestBase;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class InitialInvestorsArePopulatedTest extends CrowdSorceryTestBase {

    @Autowired
    private InvestorService investorService;

    // @formatter:off
    @DisplayName(
        """
         investors db is initialized with one investor named Default User
        """
    )
    // @formatter:on
    @Test
    void test() {
        assertThat(investorService.findAll()).hasSize(1).satisfies(investor -> {
            assertThat(investor.name()).isEqualTo("Default User");
            assertThat(investor.id()).isEqualTo("0f3ba620-533a-4047-a1d7-e4cc77c263c6");
            assertThat(investor.investorProfile().score()).isEqualTo(50);
            assertThat(investor.investorProfile().referralLink()).isEqualTo("wojtyna.pl?refLink=123");
        }, Index.atIndex(0));
    }
}
