package pl.wojtyna.trainings.spring.crowdsorcery.filters;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Investor secret filter is applied only to investor module")
@SpringBootTest
@AutoConfigureMockMvc
class InvestorSecretFilterIsAppliedOnlyToInvestorModuleTest {

    @Autowired
    private MockMvc mockMvc;

    // @formatter:off
    @DisplayName(
        """
         investor requests "protected" with correct InvestorHeader are allowed
        """
    )
    // @formatter:on
    @Test
    void test() throws Exception {
        var requestBody = """
                          {
                             "id": 10,
                             "name": "George"
                          }
                          """;
        // this should be logged by logging filter
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
        // this should be logged by logging filter
        mockMvc.perform(get(registeredInvestorLocation).header("InvestorSecret", "aspecialsecret"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.id", is("10")))
               .andExpect(jsonPath("$.name", is("George")));
    }

    // @formatter:off
    @DisplayName(
        """
         investor requests without InvestorHeader are rejected
        """
    )
    // @formatter:on
    @Test
    void test3() throws Exception {
        var requestBody = """
                          {
                             "id": 10,
                             "name": "George"
                          }
                          """;
        // this should be logged by logging filter
        mockMvc.perform(post("/investorModule/api/v0/investors").contentType(MediaType.APPLICATION_JSON)
                                                                .content(requestBody))
               .andExpect(status().isForbidden())
               .andExpect(jsonPath("$.reason", is("REJECTED_BAD_SECRET")));

        // this should be logged by logging filter
        mockMvc.perform(get("/investorModule/api/v0/investors/any-id"))
               .andExpect(status().isForbidden())
               .andExpect(jsonPath("$.reason", is("REJECTED_BAD_SECRET")));
    }

    // @formatter:off
    @DisplayName(
        """
         investor requests with incorrect InvestorHeader value are rejected
        """
    )
    // @formatter:on
    @Test
    void test4() throws Exception {
        var requestBody = """
                          {
                             "id": 10,
                             "name": "George"
                          }
                          """;
        // this should be logged by logging filter
        mockMvc.perform(post("/investorModule/api/v0/investors").contentType(MediaType.APPLICATION_JSON)
                                                                .content(requestBody)
                                                                .header("InvestorSecret", "badsecret"))
               .andExpect(status().isForbidden())
               .andExpect(jsonPath("$.reason", is("REJECTED_BAD_SECRET")));

        // this should be logged by logging filter
        mockMvc.perform(get("/investorModule/api/v0/investors/any-id").header("InvestorSecret", "badsecret"))
               .andExpect(status().isForbidden())
               .andExpect(jsonPath("$.reason", is("REJECTED_BAD_SECRET")));
    }

    // @formatter:off
    @DisplayName(
        """
         borrower requests don't need to be "protected" with secret header
        """
    )
    // @formatter:on
    @Test
    void test2() throws Exception {
        // this should be logged by logging filter
        mockMvc.perform(get("/borrower-module/api/v0/borrowers"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$", hasSize(3)));
    }
}
