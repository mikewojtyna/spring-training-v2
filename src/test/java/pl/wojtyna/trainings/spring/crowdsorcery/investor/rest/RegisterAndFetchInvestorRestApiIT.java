package pl.wojtyna.trainings.spring.crowdsorcery.investor.rest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import pl.wojtyna.trainings.spring.crowdsorcery.external.InvestorProfileRestApiServer;

import java.util.concurrent.CountDownLatch;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Register and fetch investor REST API")
@SpringBootTest
@AutoConfigureMockMvc
class RegisterAndFetchInvestorRestApiIT {

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setup() throws Exception {
        var countDownLatch = new CountDownLatch(1);
        InvestorProfileRestApiServer.start(server -> countDownLatch.countDown());
        countDownLatch.await();
    }

    // @formatter:off
    @DisplayName(
        """
         given JSON request body
            {
                "id": 10,
                "name": "George"
            },
         when POST on /investorModule/api/v0/investors,
         then investor is registered and her profile can be fetched from /investorModule/api/v0/investors/{id}
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

        // when
        var registeredInvestorLocation = mockMvc.perform(post("/investorModule/api/v0/investors").contentType(MediaType.APPLICATION_JSON)
                                                                                                 .content(requestBody))
                                                .andExpect(status().isCreated())
                                                .andReturn()
                                                .getResponse()
                                                .getHeader("Location");

        // then
        mockMvc.perform(get(registeredInvestorLocation))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.id", is("10")))
               .andExpect(jsonPath("$.name", is("George")));
    }
}
