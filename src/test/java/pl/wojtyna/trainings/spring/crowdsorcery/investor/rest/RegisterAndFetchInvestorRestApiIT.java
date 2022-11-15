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
import pl.wojtyna.trainings.spring.crowdsorcery.notification.NotificationService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Register and fetch investor REST API")
@WebMvcTest
@MockBean(NotificationService.class)
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
                                                                        new InvestorProfile(100, false, "reflink"))));

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
}
