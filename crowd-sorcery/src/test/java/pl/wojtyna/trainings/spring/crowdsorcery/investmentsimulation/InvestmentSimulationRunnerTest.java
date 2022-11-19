package pl.wojtyna.trainings.spring.crowdsorcery.investmentsimulation;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import pl.wojtyna.trainings.spring.crowdsorcery.testutils.CrowdSorceryTestBase;

import static org.hamcrest.Matchers.emptyOrNullString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

@SpringBootTest
@AutoConfigureMockMvc
class InvestmentSimulationRunnerTest extends CrowdSorceryTestBase {

    @Autowired
    private MockMvc mockMvc;

    // @formatter:off
    @DisplayName(
        """
         using async REST API with custom executor
        """
    )
    // @formatter:on
    @Test
    void test() throws Exception {
        String command = """
                         {
                            "id": 100
                         }
                         """;
        var mvcResult = mockMvc.perform(post("/investment-simulations-module/api/v0/investments").content(command)
                                                                                                 .contentType(MediaType.APPLICATION_JSON))

                               .andExpect(request().asyncStarted()).andReturn();

        mockMvc.perform(asyncDispatch(mvcResult))
               .andDo(print())
               .andExpect(jsonPath("$.id", Matchers.is(String.valueOf(100))))
               .andExpect(jsonPath("$.someData", Matchers.is(Matchers.not(emptyOrNullString()))));
    }

    // @formatter:off
    @DisplayName(
        """
         using async REST API with @Async annotation
        """
    )
    // @formatter:on
    @Test
    void test2() throws Exception {
        String command = """
                         {
                            "id": 100
                         }
                         """;
        var mvcResult = mockMvc.perform(post("/investment-simulations-module/api/v0/investments")
                                            .param("usingAsync", "true")
                                            .content(command)
                                            .contentType(MediaType.APPLICATION_JSON))

                               .andExpect(request().asyncStarted()).andReturn();

        mockMvc.perform(asyncDispatch(mvcResult))
               .andDo(print())
               .andExpect(jsonPath("$.id", Matchers.is(String.valueOf(100))))
               .andExpect(jsonPath("$.someData", Matchers.is(Matchers.not(emptyOrNullString()))));
    }
}
