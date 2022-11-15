package pl.wojtyna.trainings.spring.crowdsorcery.rest;

import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

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
        var responseBody = mockMvc.perform(post("/investorModule/api/v0/investors").contentType(MediaType.APPLICATION_JSON)
                                                                                   .content(requestBody))
                                  .andExpect(status().isOk())
                                  .andReturn().getResponse().getContentAsString();

        // then
        String registeredInvestorId = JsonPath.parse(responseBody).read("$.id");
        mockMvc.perform(get("/investorModule/api/v0/investors/{id}", registeredInvestorId))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.id", is(10)))
               .andExpect(jsonPath("$.name", is("George")));
    }
}
