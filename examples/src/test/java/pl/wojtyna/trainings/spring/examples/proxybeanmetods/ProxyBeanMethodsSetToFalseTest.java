package pl.wojtyna.trainings.spring.examples.proxybeanmetods;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.wojtyna.trainings.spring.examples.proxybeanmethods.Baz;
import pl.wojtyna.trainings.spring.examples.proxybeanmethods.Foo;
import pl.wojtyna.trainings.spring.examples.proxybeanmethods.ProxyBeanMethodsSetToFalseConfiguration;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Proxy bean methods set to false explanation")
@SpringBootTest(classes = ProxyBeanMethodsSetToFalseConfiguration.class)
class ProxyBeanMethodsSetToFalseTest {

    @Autowired
    private Foo foo;
    @Autowired
    private Baz baz;

    // @formatter:off
    @DisplayName(
        """
         given proxy bean methods is set to false,
         then Foo and Baz use different instances of Bar
        """
    )
    // @formatter:on
    @Test
    void test() {
        var barUsedByFoo = foo.getBar();
        var barUsedByBaz = baz.getBar();
        assertThat(barUsedByFoo).isNotSameAs(barUsedByBaz);
    }
}
