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
        var foundInvestors = queryByNameExactly("George");

        // then
        assertThat(foundInvestors).hasSize(1)
                                  .allSatisfy(investorInCatalogDto -> assertThat(investorInCatalogDto.name()).isEqualTo(
                                      "George"));

        // when
        foundInvestors = queryByNameExactly("Martin");

        // then
        assertThat(foundInvestors).hasSize(2)
                                  .allSatisfy(investorInCatalogDto -> assertThat(investorInCatalogDto.name()).isEqualTo(
                                      "Martin"));
    }

    // @formatter:off
    @DisplayName(
        """         
         investors can be found by name containing the given string
        """
    )
    // @formatter:on
    @Test
    void test2() throws Exception {
        // given
        registerInvestorWithName("George");
        registerInvestorWithName("Porgie");
        registerInvestorWithName("Martin");

        // when
        var foundInvestors = queryByNameContaining("or");

        // then
        assertThat(foundInvestors).hasSize(2)
                                  .anySatisfy(investorInCatalogDto -> assertThat(investorInCatalogDto.name()).isEqualTo(
                                      "George"))
                                  .anySatisfy(investorInCatalogDto -> assertThat(investorInCatalogDto.name()).isEqualTo(
                                      "Porgie"));
    }

    // @formatter:off
    @DisplayName(
        """         
         investors can be found by exact name length
        """
    )
    // @formatter:on
    @Test
    void test3() throws Exception {
        // given
        registerInvestorWithName("George");
        registerInvestorWithName("Tom");
        registerInvestorWithName("Martin");

        // when
        var foundInvestors = queryByNameHavingLength(6);

        // then
        assertThat(foundInvestors).hasSize(2)
                                  .anySatisfy(investorInCatalogDto -> assertThat(investorInCatalogDto.name()).isEqualTo(
                                      "George"))
                                  .anySatisfy(investorInCatalogDto -> assertThat(investorInCatalogDto.name()).isEqualTo(
                                      "Martin"));
    }

    private List<InvestorInCatalogDto> queryByNameHavingLength(int length) throws Exception {
        return queryByParam("exactNameLength", String.valueOf(length));
    }

    private List<InvestorInCatalogDto> queryByNameExactly(String name) throws Exception {
        return queryByParam("exactName", name);
    }

    private List<InvestorInCatalogDto> queryByNameContaining(String subString) throws Exception {
        return queryByParam("nameContaining", subString);
    }

    private List<InvestorInCatalogDto> queryByParam(String param, String value) throws Exception {
        return new ObjectMapper().readValue(mockMvc.perform(get("/investorModule/api/v0/investors").param(param, value)
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
