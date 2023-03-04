package pl.wojtyna.trainings.spring.crowdsorcery.premium;

import dasniko.testcontainers.keycloak.KeycloakContainer;
import kong.unirest.Unirest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import pl.wojtyna.trainings.spring.crowdsorcery.testutils.CrowdSorceryTestBase;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
@ActiveProfiles("premium")
class JwtPremiumContentAccessRestApiTest extends CrowdSorceryTestBase {

    @Container
    private static final KeycloakContainer keycloakContainer = new KeycloakContainer().withAdminUsername("admin")
                                                                                      .withAdminPassword("admin")
                                                                                      .withRealmImportFile(
                                                                                          "keycloak-realm.json");
    @Autowired
    private MockMvc mockMvc;

    @DynamicPropertySource
    static void registerKeycloakProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.security.oauth2.resourceserver.jwt.issuer-uri",
                     () -> keycloakContainer.getAuthServerUrl() + "realms/spring-training-v2");
    }

    // @formatter:off
    @DisplayName(
        """
         Access premium content using JWT
        """
    )
    // @formatter:on
    @Test
    void successTest() throws Exception {
        // given
        var username = "george";
        var token = obtainToken(username);

        // when
        mockMvc.perform(get("/premium").header("Authorization", "Bearer " + token))

               // then
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.firstName", is("George")))
               .andExpect(jsonPath("$.lastName", is("Martin")));
    }

    // @formatter:off
    @DisplayName(
        """
         Cannot access premium content when not using JWT
        """
    )
    // @formatter:on
    @Test
    void failWhenNoJwtTest() throws Exception {
        // when
        mockMvc.perform(get("/premium"))

               // then
               .andExpect(status().isUnauthorized());
    }

    // @formatter:off
    @DisplayName(
        """
         Cannot access premium content when using JWT issued by another provider
        """
    )
    // @formatter:on
    @Test
    void failWhenInvalidJwtTest() throws Exception {
        // given
        var token = tokenIssuedByAnotherProvider();

        // when
        mockMvc.perform(get("/premium").header("Authorization", "Bearer " + token))

               // then
               .andExpect(status().isUnauthorized());
    }

    private String tokenIssuedByAnotherProvider() {
        return "eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICI5UFdqUEt2aS1Gb2dmVWpvVlpXRG5UdE4yYnRVUm0yY0twMmE0QUxjT0dNIn0.eyJleHAiOjE2Nzc5NDM3NjgsImlhdCI6MTY3Nzk0MzQ2OCwianRpIjoiZGJjYTQ5ZTgtYzE1Mi00MzgxLTgwZDgtOGE0MzU5ODRlNDlkIiwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDo0OTE5OC9yZWFsbXMvc3ByaW5nLXRyYWluaW5nLXYyIiwic3ViIjoiMjJlNDFkZDUtZjg2Mi00YmQwLTkyZmYtNmExNWJkM2M0ZGE1IiwidHlwIjoiQmVhcmVyIiwiYXpwIjoiYWRtaW4tY2xpIiwic2Vzc2lvbl9zdGF0ZSI6IjExYTEyOTVjLTkwMjMtNDdjNS04ZGEyLWM1YzdhN2RjOWExNCIsImFjciI6IjEiLCJzY29wZSI6InByb2ZpbGUgZW1haWwiLCJzaWQiOiIxMWExMjk1Yy05MDIzLTQ3YzUtOGRhMi1jNWM3YTdkYzlhMTQiLCJlbWFpbF92ZXJpZmllZCI6ZmFsc2UsIm5hbWUiOiJHZW9yZ2UgTWFydGluIiwicHJlZmVycmVkX3VzZXJuYW1lIjoiZ2VvcmdlIiwiZ2l2ZW5fbmFtZSI6Ikdlb3JnZSIsImZhbWlseV9uYW1lIjoiTWFydGluIiwiZW1haWwiOiJnZW9yZ2VAdGVzdC5jb20ifQ.DG6MvRTWHUUq8rOQFSu7QBP9VBa1lhXJCMhU2Z83Qln8VayiMtO2_vQ1zKT6b5TTRbhhR8weFDFAzO9Jkt60dBEFPksoHLOWzktAcOC_4v3wgfAul8BjHpszzUOcMbKQ23GKq2AY5FUT5_vX_hOOHpsr2phZjqzmxUknXWcbTa_RPes8bKFnz6olpe3TUgKdt4sr1bRGTZe11Yhp96bS9-Vxym7um5dJ6ZOtEuc4cqqjB5RjGtXvHSUZGydrzGjjVo3KgFEHP8-zVtWBHkhcsydH8dE_2M-ICgtb3WfxpBdjeBUXo1ZQF2IJ0tT882m4QXbQ7WtWuK5x4cAjxhcu7w";
    }

    private String obtainToken(String username) {
        var tokenUri = "%s/realms/spring-training-v2/protocol/openid-connect/token".formatted(keycloakContainer.getAuthServerUrl());
        return Unirest.post(tokenUri)
                      .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                      .body("client_id=admin-cli&username=%s&password=password&grant_type=password".formatted(username))
                      .asJson()
                      .getBody()
                      .getObject()
                      .getString("access_token");
    }
}
