package pl.wojtyna.trainings.spring.crowdsorcery.investor.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.wojtyna.trainings.spring.crowdsorcery.testutils.CrowdSorceryTestBase;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Investor registration examples")
@SpringBootTest
class InvestorRegistrationTest extends CrowdSorceryTestBase {

    @Autowired
    private InvestorService investorService;

    @DisplayName(
        """
         investor can be registered
        """
    )
    // @formatter:on
    @Test
    void test() {
        // given
        var command = new RegisterInvestor("123", "George");

        // when
        try {
            investorService.register(command);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        // then
        var foundInvestors = investorService.findAll();
        assertThat(foundInvestors).anySatisfy(investor -> {
            assertThat(investor.id()).isEqualTo("123");
            assertThat(investor.name()).isEqualTo("George");
            assertThat(investor.investorProfile()).isNotNull();
        });
    }
}
