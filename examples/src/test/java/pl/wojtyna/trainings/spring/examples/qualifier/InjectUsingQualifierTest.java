package pl.wojtyna.trainings.spring.examples.qualifier;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Injection examples using @Qualifier")
@SpringBootTest(classes = ExampleConfigurationUsingQualifier.class)
class InjectUsingQualifierTest {

    @Autowired
    private DiscountService discountService;

    @DisplayName(
        """
         Multiple beans implementing the same interface can be distinguished using @Qualifier
        """
    )
    // @formatter:on
    @Test
    void test() {
        assertThat(discountService.getDiscountPolicy()).isInstanceOf(LoyaltyDiscount.class);
    }
}
