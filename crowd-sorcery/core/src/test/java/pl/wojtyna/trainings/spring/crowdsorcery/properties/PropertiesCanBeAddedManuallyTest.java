package pl.wojtyna.trainings.spring.crowdsorcery.properties;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.core.env.Environment;
import pl.wojtyna.trainings.spring.crowdsorcery.CrowdSorceryApp;

import static org.assertj.core.api.Assertions.assertThat;

public class PropertiesCanBeAddedManuallyTest {


    // @formatter:off
    @DisplayName(
        """
         properties can be added manually
        """
    )
    // @formatter:on
    @Test
    void test() {
        var context = new SpringApplicationBuilder().sources(CrowdSorceryApp.class)
                                                    .web(WebApplicationType.NONE)
                                                    .properties("someProperty=A", "otherProperty=B")
                                                    .run();

        var environment = context.getBean(Environment.class);

        assertThat(environment.getProperty("someProperty")).isEqualTo("A");
        assertThat(environment.getProperty("otherProperty")).isEqualTo("B");
    }
}
