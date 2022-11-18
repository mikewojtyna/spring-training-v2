package pl.wojtyna.trainings.spring.crowdsorcery.portfolio;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import pl.wojtyna.trainings.spring.crowdsorcery.investor.service.InvestorService;
import pl.wojtyna.trainings.spring.crowdsorcery.investor.service.RegisterInvestor;
import pl.wojtyna.trainings.spring.crowdsorcery.testutils.CrowdSorceryTestBase;

import java.util.UUID;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Portfolio REST API is secured")
@SpringBootTest
@AutoConfigureMockMvc
class PortfolioSecurityRestApiTest extends CrowdSorceryTestBase {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private InvestorService investorService;

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
                                                                                     .content("false").with(
                       httpBasic(investorId, "dummypassword")))

               // then
               .andExpect(status().isOk());
    }
}
