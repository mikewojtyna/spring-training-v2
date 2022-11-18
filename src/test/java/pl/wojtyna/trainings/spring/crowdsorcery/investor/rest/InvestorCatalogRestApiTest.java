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
        registerNonVipInvestorWithName("George");
        registerNonVipInvestorWithName("Martin");
        registerNonVipInvestorWithName("Martin");

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
        registerNonVipInvestorWithName("George");
        registerNonVipInvestorWithName("Porgie");
        registerNonVipInvestorWithName("Martin");

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
        registerNonVipInvestorWithName("George");
        registerNonVipInvestorWithName("Tom");
        registerNonVipInvestorWithName("Martin");

        // when
        var foundInvestors = queryByNameHavingLength(6);

        // then
        assertThat(foundInvestors).hasSize(2)
                                  .anySatisfy(investorInCatalogDto -> assertThat(investorInCatalogDto.name()).isEqualTo(
                                      "George"))
                                  .anySatisfy(investorInCatalogDto -> assertThat(investorInCatalogDto.name()).isEqualTo(
                                      "Martin"));
    }

    // @formatter:off
    @DisplayName(
        """         
         investors can be found by name length range
        """
    )
    // @formatter:on
    @Test
    void test4() throws Exception {
        // given
        registerNonVipInvestorWithName("Eddy");
        registerNonVipInvestorWithName("Riker");
        registerNonVipInvestorWithName("Martin");
        registerNonVipInvestorWithName("Adelard");
        registerNonVipInvestorWithName("Tom");

        // when
        var foundInvestors = queryByNameHavingLengthBetween(4, 6);

        // then
        assertThat(foundInvestors).hasSize(3)
                                  .anySatisfy(investorInCatalogDto -> assertThat(investorInCatalogDto.name()).isEqualTo(
                                      "Eddy"))
                                  .anySatisfy(investorInCatalogDto -> assertThat(investorInCatalogDto.name()).isEqualTo(
                                      "Riker"))
                                  .anySatisfy(investorInCatalogDto -> assertThat(investorInCatalogDto.name()).isEqualTo(
                                      "Martin"));
    }

    // @formatter:off
    @DisplayName(
        """         
         only VIP investors can be found
        """
    )
    // @formatter:on
    @Test
    void test5() throws Exception {
        // given
        registerNonVipInvestorWithName("George");
        registerVipInvestorWithName("Tom");
        registerVipInvestorWithName("Andy");
        registerNonVipInvestorWithName("Martin");

        // when
        var foundInvestors = queryOnlyVips();

        // then
        assertThat(foundInvestors).hasSize(2)
                                  .anySatisfy(investorInCatalogDto -> assertThat(investorInCatalogDto.name()).isEqualTo(
                                      "Tom"))
                                  .anySatisfy(investorInCatalogDto -> assertThat(investorInCatalogDto.name()).isEqualTo(
                                      "Andy"));
    }

    // @formatter:off
    @DisplayName(
        """         
         only non-VIP investors can be found
        """
    )
    // @formatter:on
    @Test
    void test6() throws Exception {
        // given
        registerNonVipInvestorWithName("George");
        registerVipInvestorWithName("Tom");
        registerVipInvestorWithName("Andy");
        registerNonVipInvestorWithName("Martin");

        // when
        var foundInvestors = queryNoVips();

        // then
        assertThat(foundInvestors).hasSize(2)
                                  .anySatisfy(investorInCatalogDto -> assertThat(investorInCatalogDto.name()).isEqualTo(
                                      "George"))
                                  .anySatisfy(investorInCatalogDto -> assertThat(investorInCatalogDto.name()).isEqualTo(
                                      "Martin"));
    }

    // @formatter:off
    @DisplayName(
        """         
         investors can be found by exact score value
        """
    )
    // @formatter:on
    @Test
    void test7() throws Exception {
        // given
        registerNonVipInvestorWithNameAndScore("George", 15);
        registerNonVipInvestorWithNameAndScore("Martin", 20);
        registerNonVipInvestorWithNameAndScore("Henry", 30);

        // when
        var foundInvestors = queryByScoreExactly(20);

        // then
        assertThat(foundInvestors).hasSize(1)
                                  .anySatisfy(investorInCatalogDto -> assertThat(investorInCatalogDto.name()).isEqualTo(
                                      "Martin"));
    }

    // @formatter:off
    @DisplayName(
        """         
         investors can be found by score value greater than or equal to
        """
    )
    // @formatter:on
    @Test
    void test8() throws Exception {
        // given
        registerNonVipInvestorWithNameAndScore("George", 15);
        registerNonVipInvestorWithNameAndScore("Martin", 20);
        registerNonVipInvestorWithNameAndScore("Henry", 30);

        // when
        var foundInvestors = queryByScoreGreaterThanOrEqualTo(20);

        // then
        assertThat(foundInvestors).hasSize(2)
                                  .anySatisfy(investorInCatalogDto -> assertThat(investorInCatalogDto.name()).isEqualTo(
                                      "Martin"))
                                  .anySatisfy(investorInCatalogDto -> assertThat(investorInCatalogDto.name()).isEqualTo(
                                      "Henry"));
    }

    // @formatter:off
    @DisplayName(
        """         
         investors can be found by ref link
        """
    )
    // @formatter:on
    @Test
    void test9() throws Exception {
        // given
        registerNonVipInvestorWithNameAndRefLinkId("George", "123");
        registerNonVipInvestorWithNameAndRefLinkId("Martin", "456");
        registerNonVipInvestorWithNameAndRefLinkId("Henry", "456");

        // when
        var foundInvestors = queryByRefLinkId("456");

        // then
        assertThat(foundInvestors).hasSize(2)
                                  .anySatisfy(investorInCatalogDto -> assertThat(investorInCatalogDto.name()).isEqualTo(
                                      "Martin"))
                                  .anySatisfy(investorInCatalogDto -> assertThat(investorInCatalogDto.name()).isEqualTo(
                                      "Henry"));
    }

    private List<InvestorInCatalogDto> queryByRefLinkId(String refLinkId) throws Exception {
        return queryByParam("refLink", refLinkId);
    }

    private void registerNonVipInvestorWithNameAndRefLinkId(String name, String refLinkId) {
        investorRepository.save(new Investor(uniqueId(),
                                             name,
                                             new InvestorProfile(10, true, "wojtyna.pl?refLink=" + refLinkId), 0));
    }

    private List<InvestorInCatalogDto> queryByScoreGreaterThanOrEqualTo(int score) throws Exception {
        return queryByParam("scoreGtOrEq", String.valueOf(score));
    }

    private List<InvestorInCatalogDto> queryByScoreExactly(int score) throws Exception {
        return queryByParam("exactScore", String.valueOf(score));
    }

    private void registerNonVipInvestorWithNameAndScore(String name, int score) {
        investorRepository.save(new Investor(uniqueId(),
                                             name,
                                             new InvestorProfile(score, true, "wojtyna.pl?refLink=456"), 0));
    }

    private List<InvestorInCatalogDto> queryNoVips() throws Exception {
        return queryByParam("vipsOnly", "false");
    }

    private List<InvestorInCatalogDto> queryOnlyVips() throws Exception {
        return queryByParam("vipsOnly", "true");
    }

    private void registerVipInvestorWithName(String name) {
        investorRepository.save(new Investor(uniqueId(),
                                             name,
                                             new InvestorProfile(20, true, "wojtyna.pl?refLink=456"), 0));
    }

    private List<InvestorInCatalogDto> queryByNameHavingLengthBetween(int start, int end) throws Exception {
        return queryByParam("nameLengthRange", "%s-%s".formatted(start, end));
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

    private void registerNonVipInvestorWithName(String name) {
        investorRepository.save(new Investor(uniqueId(), name, defaultProfile(), 0));
    }

    private InvestorProfile defaultProfile() {
        return new InvestorProfile(10, false, "wojtyna.pl?refLink=123");
    }

    private String uniqueId() {
        return UUID.randomUUID().toString();
    }
}
