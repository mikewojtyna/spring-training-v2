package pl.wojtyna.trainings.spring.crowdsorcery.portfolio;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import pl.wojtyna.trainings.spring.crowdsorcery.investor.service.InvestorService;
import pl.wojtyna.trainings.spring.crowdsorcery.investor.service.RegisterInvestor;
import pl.wojtyna.trainings.spring.crowdsorcery.testutils.CrowdSorceryTestBase;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Portfolio REST API is secured")
@SpringBootTest
@AutoConfigureMockMvc
class PortfolioSecurityRestApiTest extends CrowdSorceryTestBase {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private InvestorService investorService;
    @MockBean
    private PortfolioService portfolioService;
    @Value("${crowd-sorcery.security.dummy-password}")
    private String dummyPassword;

    // @formatter:off
    @DisplayName(
        """
         investor can hide her portfolio
        """
    )
    // @formatter:on
    @Test
    void test() throws Exception {
        // given
        var investorId = UUID.randomUUID().toString();
        investorService.register(new RegisterInvestor(investorId, "George", 0));

        // when
        mockMvc.perform(put("/portfolio-module/api/v0/portfolios/myPortfolio/public").contentType(MediaType.TEXT_PLAIN)
                                                                                     .content("false")
                                                                                     .with(httpBasic(
                                                                                         resolveInvestorUsername(
                                                                                             investorId),
                                                                                         resolveInvestorPassword(
                                                                                             investorId))))

               // then
               .andExpect(status().isOk());

        verify(portfolioService).hidePortfolio(investorId);
    }

    // @formatter:off
    @DisplayName(
        """
         investor can add new investment to her portfolio
        """
    )
    // @formatter:on
    @Test
    void test2() throws Exception {
        // given
        var investorId = UUID.randomUUID().toString();
        investorService.register(new RegisterInvestor(investorId, "George", 0));
        var investmentRequestBody = """
                                    {
                                     "id": "123456",
                                     "title":  "My First Investment",
                                     "description": "I'm very proud of it!"
                                    }
                                    """;

        // when
        mockMvc.perform(post("/portfolio-module/api/v0/portfolios/myPortfolio/investments").contentType(MediaType.APPLICATION_JSON)
                                                                                           .content(
                                                                                               investmentRequestBody)
                                                                                           .with(httpBasic(
                                                                                               resolveInvestorUsername(
                                                                                                   investorId),
                                                                                               resolveInvestorPassword(
                                                                                                   investorId))))

               // then
               .andExpect(status().isCreated());
        // @formatter:off
        ArgumentMatcher<Investment> investmentRequirements =
            investment -> investment.id().equals("123456")
                && investment.title().equals("My First Investment")
                && investment.description().equals("I'm very proud of it!");
        // @formatter:on
        verify(portfolioService).addInvestment(eq(investorId), argThat(investmentRequirements));
    }

    // @formatter:off
    @DisplayName(
        """
         investor can edit the investment in her portfolio
        """
    )
    // @formatter:on
    @Test
    void test3() throws Exception {
        // given
        var investorId = UUID.randomUUID().toString();
        investorService.register(new RegisterInvestor(investorId, "George", 0));
        var investmentId = "123456";
        var investmentRequestBody = """
                                    {
                                     "id": "%s",
                                     "title":  "My Second Investment",
                                     "description": "I love it"
                                    }
                                    """.formatted(investmentId);

        // when
        mockMvc.perform(put("/portfolio-module/api/v0/portfolios/myPortfolio/investments/{id}",
                            investmentId).contentType(MediaType.APPLICATION_JSON)
                                         .content(investmentRequestBody)
                                         .with(httpBasic(resolveInvestorUsername(investorId),
                                                         resolveInvestorPassword(investorId))))

               // then
               .andExpect(status().isOk());
        // @formatter:off
        ArgumentMatcher<Investment> investmentRequirements =
            investment -> investment.id().equals("123456")
                && investment.title().equals("My Second Investment")
                && investment.description().equals("I love it");
        // @formatter:on
        verify(portfolioService).editInvestment(eq(investorId), argThat(investmentRequirements));
    }

    // @formatter:off
    @DisplayName(
        """
         investor can delete the investment from her portfolio
        """
    )
    // @formatter:on
    @Test
    void test4() throws Exception {
        // given
        var investorId = UUID.randomUUID().toString();
        investorService.register(new RegisterInvestor(investorId, "George", 0));
        var investmentId = "123456";

        // when
        mockMvc.perform(delete("/portfolio-module/api/v0/portfolios/myPortfolio/investments/{id}", investmentId).with(
                   httpBasic(resolveInvestorUsername(investorId), resolveInvestorPassword(investorId))))

               // then
               .andExpect(status().isOk());
        verify(portfolioService).deleteInvestment(investorId, investmentId);
    }

    // @formatter:off
    @DisplayName(
        """
         anyone is allowed to get a list of all public portfolios and see details of any public portfolio
        """
    )
    // @formatter:on
    @Test
    void test5() throws Exception {
        // given
        var georgeId = UUID.randomUUID().toString();
        investorService.register(new RegisterInvestor(georgeId, "George", 0));
        var martinId = UUID.randomUUID().toString();
        investorService.register(new RegisterInvestor(martinId, "Martin", 0));
        var henryId = UUID.randomUUID().toString();
        investorService.register(new RegisterInvestor(henryId, "Henry", 0));
        var georgeInvestment = new Investment(UUID.randomUUID().toString(),
                                              "My First Investment",
                                              "This is my very first investment. I'm extremely proud of it.");
        var georgePortfolio = new Portfolio(georgeId, List.of(georgeInvestment), true, 0);
        when(portfolioService.getAllPublicPortfolios()).thenReturn(List.of(georgePortfolio,
                                                                           Portfolio.emptyPublicPortfolioOf(martinId),
                                                                           Portfolio.emptyPublicPortfolioOf(henryId)));
        when(portfolioService.getPublicPortfolio(georgeId)).thenReturn(Optional.of(georgePortfolio));

        // when
        mockMvc.perform(get("/portfolio-module/api/v0/portfolios/public"))
               .andDo(print())

               // then
               .andExpect(status().isOk())
               .andExpect(jsonPath("$", hasSize(3)))
               .andExpect(jsonPath("$[0].investorId", is(georgeId)))
               .andExpect(jsonPath("$[1].investorId", is(martinId)))
               .andExpect(jsonPath("$[2].investorId", is(henryId)));
        verify(portfolioService).getAllPublicPortfolios();

        // when
        mockMvc.perform(get("/portfolio-module/api/v0/portfolios/public/{id}", georgeId))
               .andDo(print())

               // then
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.investorId", is(georgeId)))
               .andExpect(jsonPath("$.isPublic", is(true)))
               .andExpect(jsonPath("$.investments", hasSize(1)))
               .andExpect(jsonPath("$.investments[0].id", is(georgeInvestment.id())))
               .andExpect(jsonPath("$.investments[0].title", is(georgeInvestment.title())))
               .andExpect(jsonPath("$.investments[0].description", is(georgeInvestment.description())));
        verify(portfolioService).getPublicPortfolio(georgeId);
    }

    private String resolveInvestorUsername(String investorId) {
        // for now, let's assume it's always the investor id
        return investorId;
    }

    private String resolveInvestorPassword(String investorId) {
        return dummyPassword;
    }
}
