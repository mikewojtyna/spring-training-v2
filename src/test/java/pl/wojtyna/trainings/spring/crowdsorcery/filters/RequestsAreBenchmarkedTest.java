package pl.wojtyna.trainings.spring.crowdsorcery.filters;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import pl.wojtyna.trainings.spring.crowdsorcery.testutils.CrowdSorceryTestBase;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Requests are benchmarked")
@SpringBootTest
@AutoConfigureMockMvc
class RequestsAreBenchmarkedTest extends CrowdSorceryTestBase {

    @Autowired
    private MockMvc mockMvc;

    // @formatter:off
    @DisplayName(
        """
         requests are benchmarked
        """
    )
    // @formatter:on
    @Test
    void test() throws Exception {
        // this should be benchmarked
        mockMvc.perform(get("/borrower-module/api/v0/borrowers"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$", hasSize(3)));
    }
}
