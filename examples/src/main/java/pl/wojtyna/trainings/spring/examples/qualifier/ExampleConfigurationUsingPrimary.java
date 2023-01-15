package pl.wojtyna.trainings.spring.examples.qualifier;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class ExampleConfigurationUsingPrimary {

    @Bean
    public DiscountPolicy loyaltyDiscount() {
        return new LoyaltyDiscount();
    }

    @Bean
    @Primary
    public DiscountPolicy locationDiscount() {
        return new LocationDiscount();
    }

    @Bean
    public DiscountService discountService(DiscountPolicy discountPolicy) {
        return new DiscountService(discountPolicy);
    }
}
