package pl.wojtyna.trainings.spring.crowdsorcery.investor.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import pl.wojtyna.trainings.spring.crowdsorcery.testutils.CrowdSorceryTestBase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Investors can be modified safely by multiple clients concurrently")
@SpringBootTest
@AutoConfigureMockMvc
class InvestorConcurrentModificationTest extends CrowdSorceryTestBase {

    @Autowired
    private MockMvc mockMvc;

    // @formatter:off
    @DisplayName(
        """
         given investor named George is registered
         and George is fetched by GET and resource version is included,
         when first client sends PATCH on /investorModule/api/v0/investors/{id} changing name to Martin using the same version
         and
         when second client sends PATCH on /investorModule/api/v0/investors/{id} changing name to Henry using the same version
         then second client modification is rejected and investor is still named Martin
        """
    )
    // @formatter:on
    @Test
    void test() throws Exception {
        // given
        var requestBody = """
                          {
                             "id": 10,
                             "name": "George"
                          }
                          """;
        var registeredInvestorLocation = mockMvc.perform(post("/investorModule/api/v0/investors").contentType(MediaType.APPLICATION_JSON)
                                                                                                 .content(requestBody)
                                                                                                 .header(
                                                                                                     "InvestorSecret",
                                                                                                     "aspecialsecret"))
                                                .andExpect(status().isCreated())
                                                .andReturn()
                                                .getResponse()
                                                .getHeader("Location");
        assertThat(registeredInvestorLocation).isNotNull();
        var originallyFetchedInvestor = new ObjectMapper().readValue(mockMvc.perform(get(registeredInvestorLocation).header(
                                                                                "InvestorSecret",
                                                                                "aspecialsecret"))
                                                                            .andExpect(status().isOk())
                                                                            .andExpect(jsonPath("$.id", is("10")))
                                                                            .andExpect(jsonPath("$.name", is("George")))
                                                                            .andReturn()
                                                                            .getResponse()
                                                                            .getContentAsString(),
                                                                     InvestorFetchResultRestDto.class);

        // when
        var changeToMartinRequestBody = """
                                        {
                                         "name": "Martin",
                                         "version": %s
                                        }
                                        """.formatted(originallyFetchedInvestor.version());
        mockMvc.perform(patch(registeredInvestorLocation).contentType(MediaType.APPLICATION_JSON)
                                                         .content(changeToMartinRequestBody)
                                                         .header("InvestorSecret", "aspecialsecret"))
               .andExpect(status().isOk());

        // then
        mockMvc.perform(get(registeredInvestorLocation).header("InvestorSecret", "aspecialsecret"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.id", is("10")))
               .andExpect(jsonPath("$.name", is("Martin")));

        // and
        // when
        var changeToHenryRequestBody = """
                                       {
                                        "name": "Henry",
                                        "version": %s
                                       }
                                       """.formatted(originallyFetchedInvestor.version());
        mockMvc.perform(patch(registeredInvestorLocation).contentType(MediaType.APPLICATION_JSON)
                                                         .content(changeToHenryRequestBody)
                                                         .header("InvestorSecret", "aspecialsecret"))
               .andExpect(status().isConflict());

        // then
        mockMvc.perform(get(registeredInvestorLocation).header("InvestorSecret", "aspecialsecret"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.id", is("10")))
               .andExpect(jsonPath("$.name", is("Martin")));
    }
}
