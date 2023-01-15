package pl.wojtyna.trainings.spring.examples.qualifier;

public class DiscountService {

    private final DiscountPolicy discountPolicy;

    public DiscountService(DiscountPolicy discountPolicy) {
        this.discountPolicy = discountPolicy;
    }

    public DiscountPolicy getDiscountPolicy() {
        return discountPolicy;
    }
}
