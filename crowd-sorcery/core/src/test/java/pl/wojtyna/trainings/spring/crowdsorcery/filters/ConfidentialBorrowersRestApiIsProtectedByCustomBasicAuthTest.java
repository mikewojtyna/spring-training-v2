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

@DisplayName("Confidential borrower rest api is protected by custom basic auth")
@SpringBootTest
@AutoConfigureMockMvc
class ConfidentialBorrowersRestApiIsProtectedByCustomBasicAuthTest extends CrowdSorceryTestBase {

    @Autowired
    private MockMvc mockMvc;

    // @formatter:off
    @DisplayName(
        """
         confidential borrowers can be fetched only after providing correct basic auth credentials
        """
    )
    // @formatter:on
    @Test
    void test() throws Exception {
        // no password
        mockMvc.perform(get("/borrower-module/api/v0/borrowers/confidential")).andExpect(status().isUnauthorized());

        // incorrect password
        mockMvc.perform(get("/borrower-module/api/v0/borrowers/confidential").header("Authorization", "c29tZXRoaW5n"))
               .andExpect(status().isUnauthorized());

        // correct password
        mockMvc.perform(get("/borrower-module/api/v0/borrowers/confidential").header("Authorization", "cGFzc3dvcmQ="))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$", hasSize(2)));
    }
}
