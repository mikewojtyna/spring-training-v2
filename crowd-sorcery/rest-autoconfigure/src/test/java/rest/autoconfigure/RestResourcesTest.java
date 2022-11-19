package rest.autoconfigure;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@SpringBootTest(classes = RestAutoConfiguration.class)
public class RestResourcesTest {

    @Autowired
    private RestResourcesConfigurationProperties restResourcesConfiguration;

    @DisplayName(
        """
         Rest client resources are configured
        """
    )
    // @formatter:on
    @Test
    void test() {
        assertThat(restResourcesConfiguration.host()).isEqualTo("http://localhost:8080");
        assertThat(restResourcesConfiguration.investor().resource()).isEqualTo("/investor/api/v0/profiles");
    }
}
