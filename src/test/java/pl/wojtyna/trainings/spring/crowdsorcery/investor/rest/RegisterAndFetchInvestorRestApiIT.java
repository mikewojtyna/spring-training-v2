package pl.wojtyna.trainings.spring.crowdsorcery.investor.rest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import pl.wojtyna.trainings.spring.crowdsorcery.investor.profile.InvestorProfile;
import pl.wojtyna.trainings.spring.crowdsorcery.investor.service.Investor;
import pl.wojtyna.trainings.spring.crowdsorcery.investor.service.InvestorService;
import pl.wojtyna.trainings.spring.crowdsorcery.investor.service.RegisterInvestor;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Register and fetch investor REST API")
@WebMvcTest(controllers = InvestorRestApi.class)
class RegisterAndFetchInvestorRestApiIT {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private InvestorService investorService;

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
        when(investorService.findAll()).thenReturn(List.of(new Investor("10",
                                                                        "George",
                                                                        new InvestorProfile(100, false, "reflink"),
                                                                        0)));

        // when
        var registeredInvestorLocation = mockMvc.perform(post("/investorModule/api/v0/investors").contentType(MediaType.APPLICATION_JSON)
                                                                                                 .content(requestBody)
                                                                                                 .header(
                                                                                                     "InvestorSecret",
                                                                                                     "aspecialsecret"))
                                                .andExpect(status().isCreated())
                                                .andReturn()
                                                .getResponse()
                                                .getHeader("Location");

        // then
        assertThat(registeredInvestorLocation).isNotNull();
        mockMvc.perform(get(registeredInvestorLocation).header("InvestorSecret", "aspecialsecret"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.id", is("10")))
               .andExpect(jsonPath("$.name", is("George")));
    }

    // @formatter:off
    @DisplayName(
        """
         given investor service fails when registering new investor,
         when POST on /investorModule/api/v0/investors,
         then status is 500 and error JSON response is produced
        """
    )
    // @formatter:on
    @Test
    void test2() throws Exception {
        // given
        var requestBody = """
                          {
                             "id": 10,
                             "name": "George"
                          }
                          """;
        doThrow(new RuntimeException("The reason why investor service failed")).when(investorService)
                                                                               .register(any(RegisterInvestor.class));

        // when
        mockMvc.perform(post("/investorModule/api/v0/investors").contentType(MediaType.APPLICATION_JSON)
                                                                .content(requestBody))
               .andExpect(status().isInternalServerError())
               .andExpect(jsonPath("$.reason", is("The reason why investor service failed")))
               .andDo(print());
    }

    // @formatter:off
    @DisplayName(
        """
         given investor service fails when fetching any investor,
         when GET on /investorModule/api/v0/investors/any-id,
         then status is 500 and error JSON response is produced
        """
    )
    // @formatter:on
    @Test
    void test3() throws Exception {
        // given
        doThrow(new RuntimeException("The reason why investor service failed")).when(investorService)
                                                                               .findAll();

        // when
        mockMvc.perform(get("/investorModule/api/v0/investors/any-id"))
               .andExpect(status().isInternalServerError())
               .andExpect(jsonPath("$.reason", is("The reason why investor service failed")))
               .andDo(print());
    }

    // @formatter:off
    @DisplayName(
        """
         given command with empty name and null id
         when POST on /investorModule/api/v0/investors,
         then status is 400 and json response with all field errors is returned
        """
    )
    // @formatter:on
    @Test
    void test4() throws Exception {
        // given
        var requestBody = """
                          {
                             "name": ""
                          }
                          """;

        // when
        mockMvc.perform(post("/investorModule/api/v0/investors").contentType(MediaType.APPLICATION_JSON)
                                                                .content(requestBody))
               .andExpect(status().isBadRequest())
               .andExpect(jsonPath("$.errors", is(not(emptyOrNullString()))))
               .andExpect(jsonPath("$.errors.name", is(not(empty()))))
               .andExpect(jsonPath("$.errors.id", is(not(empty()))))
               .andDo(print());
    }
}
