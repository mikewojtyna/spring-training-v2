package pl.wojtyna.trainings.spring.hierarchy.root.child2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.wojtyna.trainings.spring.hierarchy.root.CoreBeanOne;
import pl.wojtyna.trainings.spring.hierarchy.root.CoreBeanTwo;

@Configuration
public class RootChild2Context {

    private final CoreBeanOne coreBeanOne;
    private final CoreBeanTwo coreBeanTwo;

    public RootChild2Context(CoreBeanOne coreBeanOne, CoreBeanTwo coreBeanTwo) {
        this.coreBeanOne = coreBeanOne;
        this.coreBeanTwo = coreBeanTwo;
    }

    @Bean
    public RootChild2Bean rootChild2Bean() {
        return new RootChild2Bean();
    }
}
