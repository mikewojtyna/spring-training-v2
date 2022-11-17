package pl.wojtyna.trainings.spring.crowdsorcery.investor.rest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import pl.wojtyna.trainings.spring.crowdsorcery.investor.profile.InvestorProfile;
import pl.wojtyna.trainings.spring.crowdsorcery.investor.repository.mongo.MongoSpringInvestorRepository;
import pl.wojtyna.trainings.spring.crowdsorcery.investor.service.Investor;
import pl.wojtyna.trainings.spring.crowdsorcery.testutils.CrowdSorceryTestBase;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Investor catalog REST API queries")
@SpringBootTest
@AutoConfigureMockMvc
class InvestorCatalogRestApiTest extends CrowdSorceryTestBase {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private MongoSpringInvestorRepository investorRepository;

    @BeforeEach
    void cleanState() {
        investorRepository.deleteAll();
    }

    // @formatter:off
    @DisplayName(
        """         
         investors can be found by exact name
        """
    )
    // @formatter:on
    @Test
    void test() throws Exception {
        // given
        registerInvestorWithName("George");
        registerInvestorWithName("Martin");
        registerInvestorWithName("Martin");

        // when
        List<InvestorInCatalogDto> foundInvestors = queryByName("George");

        // then
        assertThat(foundInvestors).hasSize(1)
                                  .allSatisfy(investorInCatalogDto -> assertThat(investorInCatalogDto.name()).isEqualTo(
                                      "George"));

        // when
        foundInvestors = queryByName("Martin");

        // then
        assertThat(foundInvestors).hasSize(2)
                                  .allSatisfy(investorInCatalogDto -> assertThat(investorInCatalogDto.name()).isEqualTo(
                                      "Martin"));
    }

    private List<InvestorInCatalogDto> queryByName(String name) throws Exception {
        return new ObjectMapper().readValue(mockMvc.perform(get("/investorModule/api/v0/investors").param("exactName",
                                                                                                          name)
                                                                                                   .header(
                                                                                                       "InvestorSecret",
                                                                                                       "aspecialsecret"))
                                                   .andExpect(status().isOk())
                                                   .andReturn()
                                                   .getResponse()
                                                   .getContentAsString(), new TypeReference<>() {});
    }

    private void registerInvestorWithName(String name) {
        investorRepository.save(new Investor(uniqueId(), name, defaultProfile()));
    }

    private InvestorProfile defaultProfile() {
        return new InvestorProfile(10, false, "wojtyna.pl?refLink=123");
    }

    private String uniqueId() {
        return UUID.randomUUID().toString();
    }
}
