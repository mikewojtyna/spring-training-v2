package pl.wojtyna.trainings.spring.examples.qualifier;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExampleConfigurationUsingQualifier {

    @Bean(name = "loyalty")
    public DiscountPolicy loyaltyDiscount() {
        return new LoyaltyDiscount();
    }

    @Bean
    public DiscountPolicy locationDiscount() {
        return new LocationDiscount();
    }

    @Bean
    public DiscountService discountService(@Qualifier("loyalty") DiscountPolicy discountPolicy) {
        return new DiscountService(discountPolicy);
    }
}
