package pl.wojtyna.trainings.spring.crowdsorcery.portfolio;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.wojtyna.trainings.spring.crowdsorcery.investor.service.InvestorService;
import pl.wojtyna.trainings.spring.crowdsorcery.investor.service.RegisterInvestor;
import pl.wojtyna.trainings.spring.crowdsorcery.testutils.CrowdSorceryTestBase;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Investor Portfolio management examples")
@SpringBootTest
class PortfolioTest extends CrowdSorceryTestBase {

    @Autowired
    private InvestorService investorService;
    @Autowired
    private PortfolioService portfolioService;

    // @formatter:off
    @DisplayName(
        """
         when investor is registered,
         then empty public portfolio is created
        """
    )
    // @formatter:on
    @Test
    void test() {
        // given
        var investorId = UUID.randomUUID().toString();

        // when
        investorService.register(new RegisterInvestor(investorId, "George", 0));

        // then
        assertThat(portfolioService.getPublicPortfolio(investorId)).isPresent().hasValueSatisfying(portfolio -> {
            assertThat(portfolio.investorId()).isEqualTo(investorId);
            assertThat(portfolio.investments()).isEmpty();
            assertThat(portfolio.isPublic()).isTrue();
        });
    }

    // @formatter:off
    @DisplayName(
        """
         given investor is registered,
         then investor can hide her portfolio
        """
    )
    // @formatter:on
    @Test
    void test2() {
        // given
        var investorId = registerInvestor();

        // when
        portfolioService.hidePortfolio(investorId);

        // then
        assertThat(portfolioService.getPortfolio(investorId)).hasValueSatisfying(portfolio -> assertThat(portfolio.isPublic()).isFalse());
        assertThat(portfolioService.getPublicPortfolio(investorId)).isEmpty();
    }

    // @formatter:off
    @DisplayName(
        """
         given investor is registered
         and hide her portfolio,
         when investor decides to publish her portfolio,
         then portfolio is published
        """
    )
    // @formatter:on
    @Test
    void test3() {
        // given
        var investorId = registerInvestor();
        portfolioService.hidePortfolio(investorId);

        // when
        portfolioService.publishPortfolio(investorId);

        // then
        assertThat(portfolioService.getPortfolio(investorId)).hasValueSatisfying(portfolio -> assertThat(portfolio.isPublic()).isTrue());
        assertThat(portfolioService.getPublicPortfolio(investorId)).isPresent();
    }

    // @formatter:off
    @DisplayName(
        """
         given investor is registered,
         then her portfolio is included in publicly available list of portfolios
        """
    )
    // @formatter:on
    @Test
    void test4() {
        // given
        var investorId = registerInvestor();

        // when
        var allPublicPortfolios = portfolioService.getAllPublicPortfolios();

        // then
        assertThat(allPublicPortfolios).hasSize(1)
                                       .anySatisfy(portfolio -> assertThat(portfolio.investorId()).isEqualTo(investorId));
    }

    // @formatter:off
    @DisplayName(
        """
         given investor is registered,
         then investor can add new investment to her portfolio
        """
    )
    // @formatter:on
    @Test
    void test5() {
        // given
        var investorId = registerInvestor();
        var investment = new Investment(UUID.randomUUID().toString(),
                                        "My First Investment",
                                        "This is my very first investment. I'm extremely proud of it.");

        // when
        portfolioService.addInvestment(investorId, investment);

        // then
        assertThat(portfolioService.getPublicPortfolio(investorId)).hasValueSatisfying(portfolio -> {
            assertThat(portfolio.investorId()).isEqualTo(investorId);
            assertThat(portfolio.investments()).hasSize(1).containsExactly(investment);
        });
    }

    // @formatter:off
    @DisplayName(
        """
         given investor is registered
         and added an investment,
         then investor can edit the investment
        """
    )
    // @formatter:on
    @Test
    void test6() {
        // given
        var investorId = registerInvestor();
        var investment = new Investment(UUID.randomUUID().toString(),
                                        "My First Investment",
                                        "This is my very first investment. I'm extremely proud of it.");
        portfolioService.addInvestment(investorId, investment);

        // when
        portfolioService.editInvestment(investorId,
                                        new Investment(investment.id(), "A new title", investment.description()));

        // then
        assertThat(portfolioService.getPublicPortfolio(investorId)).hasValueSatisfying(portfolio -> {
            assertThat(portfolio.investorId()).isEqualTo(investorId);
            assertThat(portfolio.investments()).hasSize(1)
                                               .anySatisfy(it -> assertThat(it.title()).isEqualTo("A new title"));
        });
    }

    // @formatter:off
    @DisplayName(
        """
         given investor is registered
         and added an investment,
         then investor can delete the investment
        """
    )
    // @formatter:on
    @Test
    void test7() {
        // given
        var investorId = registerInvestor();
        var investment = new Investment(UUID.randomUUID().toString(),
                                        "My First Investment",
                                        "This is my very first investment. I'm extremely proud of it.");
        portfolioService.addInvestment(investorId, investment);

        // when
        portfolioService.deleteInvestment(investorId, investment.id());

        // then
        assertThat(portfolioService.getPublicPortfolio(investorId)).hasValueSatisfying(portfolio -> {
            assertThat(portfolio.investorId()).isEqualTo(investorId);
            assertThat(portfolio.investments()).isEmpty();
        });
    }

    // @formatter:off
    @DisplayName(
        """
         given investor is registered
         and added two investments,
         then investor can delete any of them
        """
    )
    // @formatter:on
    @Test
    void test8() {
        // given
        var investorId = registerInvestor();
        var firstInvestment = new Investment(UUID.randomUUID().toString(),
                                             "My First Investment",
                                             "This is my very first investment. I'm extremely proud of it.");
        var secondInvestment = new Investment(UUID.randomUUID().toString(),
                                              "My Second Investment",
                                              "This is my second investment.");
        portfolioService.addInvestment(investorId, firstInvestment);
        portfolioService.addInvestment(investorId, secondInvestment);

        // when
        portfolioService.deleteInvestment(investorId, firstInvestment.id());

        // then
        assertThat(portfolioService.getPublicPortfolio(investorId)).hasValueSatisfying(portfolio -> {
            assertThat(portfolio.investorId()).isEqualTo(investorId);
            assertThat(portfolio.investments()).hasSize(1).anySatisfy(investment -> {
                assertThat(investment.id()).isEqualTo(secondInvestment.id());
                assertThat(investment.title()).isEqualTo(secondInvestment.title());
                assertThat(investment.description()).isEqualTo(secondInvestment.description());
            });
        });
    }

    private String registerInvestor() {
        var investorId = UUID.randomUUID().toString();
        investorService.register(new RegisterInvestor(investorId, "George", 0));
        return investorId;
    }
}
