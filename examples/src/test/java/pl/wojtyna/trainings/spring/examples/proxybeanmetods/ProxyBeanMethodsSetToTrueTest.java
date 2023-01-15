package pl.wojtyna.trainings.spring.examples.proxybeanmetods;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.wojtyna.trainings.spring.examples.proxybeanmethods.Baz;
import pl.wojtyna.trainings.spring.examples.proxybeanmethods.Foo;
import pl.wojtyna.trainings.spring.examples.proxybeanmethods.ProxyBeanMethodsSetToTrueConfiguration;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Proxy bean methods set to true explanation")
@SpringBootTest(classes = ProxyBeanMethodsSetToTrueConfiguration.class)
class ProxyBeanMethodsSetToTrueTest {

    @Autowired
    private Foo foo;
    @Autowired
    private Baz baz;

    // @formatter:off
    @DisplayName(
        """
         given proxy bean methods is set to true,
         then Foo and Baz use the same instance of Bar
        """
    )
    // @formatter:on
    @Test
    void test() {
        var barUsedByFoo = foo.getBar();
        var barUsedByBaz = baz.getBar();
        assertThat(barUsedByFoo).isSameAs(barUsedByBaz);
    }
}
