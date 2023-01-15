package pl.wojtyna.trainings.spring.examples.injection;

import io.restassured.RestAssured;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.ScopeNotActiveException;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.util.Set;
import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("Explanation what happens when you inject prototype or request bean into singleton")
// try to use webEnvironment = SpringBootTest.WebEnvironment.MOCK and try to explain why tests fail
@SpringBootTest(classes = InjectionExampleConfiguration.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class InjectionExplanationTest {

    @LocalServerPort
    private int port;
    @Autowired
    private OtherSingletonServiceUsingPrototype otherSingletonService;
    @Autowired
    private SingletonServiceUsingPrototype singletonService;
    @Autowired
    private SingletonServiceUsingRequest singletonServiceUsingRequest;

    // @formatter:off
    @DisplayName(
        """
         given prototype bean and singleton bean,
         when prototype bean is injected into singleton,
         then then singleton uses the same instance all the time
        """
    )
    // @formatter:on
    @Test
    void injectPrototypeTest0() {
        // when
        var idOfPrototypeBeanAfterFirstInvocation = singletonService.getIdOfPrototypeBean();
        var idOfPrototypeBeanAfterSecondInvocation = singletonService.getIdOfPrototypeBean();
        var idOfPrototypeBeanAfterThirdInvocation = singletonService.getIdOfPrototypeBean();

        // then
        assertThat(idOfPrototypeBeanAfterFirstInvocation).isEqualTo(idOfPrototypeBeanAfterSecondInvocation)
                                                         .isEqualTo(idOfPrototypeBeanAfterThirdInvocation);
    }

    // @formatter:off
    @DisplayName(
        """
         given prototype bean and two singleton beans,
         when prototype bean is injected into both singletons,
         then each singleton uses its own unique instance
        """
    )
    // @formatter:on
    @Test
    void injectPrototypeTest1() {
        // when
        var idOfPrototypeBeanUsedByFirstSingletonService = singletonService.getIdOfPrototypeBean();
        var idOfPrototypeBeanUsedByOtherSingletonService = otherSingletonService.getIdOfPrototypeBean();

        // then
        assertThat(idOfPrototypeBeanUsedByFirstSingletonService).isNotEqualTo(
            idOfPrototypeBeanUsedByOtherSingletonService);
    }

    // @formatter:off
    @DisplayName(
        """
         given proxied request bean and singleton,
         when request bean is injected into singleton,
         then singleton uses a proxy
        """
    )
    // @formatter:on
    @Test
    void injectRequestTest0() {
        // when
        var requestScopeService = singletonServiceUsingRequest.getRequestScopeService();

        // then
        assertThat(requestScopeService.getClass()).asString().contains("CGLIB");
    }

    // @formatter:off
    @DisplayName(
        """
         given proxied request bean and singleton and request bean injected into singleton,
         when singleton tries to use request bean out of web context,
         then an exception is thrown
        """
    )
    // @formatter:on
    @Test
    void injectRequestTest1() {
        assertThatThrownBy(() -> singletonServiceUsingRequest.getIdOfRequestBean()).isInstanceOf(ScopeNotActiveException.class);
    }

    // @formatter:off
    @DisplayName(
        """
         given proxied request bean and singleton and request bean injected into singleton,
         when singleton tries to use request bean in a web context three times in a row,
         then each time a different request bean is used
        """
    )
    // @formatter:on
    @Test
    void injectRequestTest2() {
        RestAssured.port = port;
        var firstId = UUID.fromString(given().when()
                                             .get("http://localhost/injection")
                                             .thenReturn()
                                             .getBody()
                                             .asString());
        var secondId = UUID.fromString(given().when()
                                              .get("http://localhost/injection")
                                              .thenReturn()
                                              .getBody()
                                              .asString());
        var thirdId = UUID.fromString(given().when()
                                             .get("http://localhost/injection")
                                             .thenReturn()
                                             .getBody()
                                             .asString());
        assertThat(Set.of(firstId, secondId, thirdId)).hasSize(3);
    }
}
