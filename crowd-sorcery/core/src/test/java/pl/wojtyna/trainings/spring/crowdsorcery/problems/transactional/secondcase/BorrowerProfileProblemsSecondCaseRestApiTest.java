package pl.wojtyna.trainings.spring.crowdsorcery.problems.transactional.secondcase;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BorrowerProfileProblemsSecondCaseRestApiTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void restApiTest() throws Exception {
        var createJson = """
                         {
                           "id": "123",
                           "name": "George"
                         }
                         """;
        mockMvc.perform(post("/problems/borrowers").contentType(MediaType.APPLICATION_JSON).content(createJson))
               .andExpect(
                   status().isOk());
        mockMvc.perform(get("/problems/borrowers/123")).andExpect(status().isOk()).andExpect(jsonPath("$.name",
                                                                                                      is("George")));

        var updateJson = """
                         {
                             "id": "123",
                             "name": "Martin"
                         }
                         """;
        mockMvc.perform(put("/problems/borrowers/123").contentType(MediaType.APPLICATION_JSON).content(updateJson))
               .andExpect(status().isOk());
        mockMvc.perform(get("/problems/borrowers/123")).andExpect(status().isOk()).andExpect(jsonPath("$.name",
                                                                                                      is("Martin")));
    }
}
