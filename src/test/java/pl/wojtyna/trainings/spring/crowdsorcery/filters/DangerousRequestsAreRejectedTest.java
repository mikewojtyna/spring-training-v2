package pl.wojtyna.trainings.spring.crowdsorcery.filters;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import pl.wojtyna.trainings.spring.crowdsorcery.testutils.CrowdSorceryTestBase;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Dangerous requests are rejected")
@SpringBootTest
@AutoConfigureMockMvc
class DangerousRequestsAreRejectedTest extends CrowdSorceryTestBase {

    @Autowired
    private MockMvc mockMvc;

    // @formatter:off
    @DisplayName(
        """
         requests with redirectTo=http://dangerous.com param are rejected
        """
    )
    // @formatter:on
    @Test
    void test() throws Exception {
        // this should be logged by logging filter
        mockMvc.perform(get("/borrower-module/api/v0/borrowers").param("redirectTo", "http://dangerous.com"))
               .andExpect(status().isBadRequest())
               .andExpect(jsonPath("$.reason", is("REJECTED_DANGEROUS_REQUEST")));
    }
}
