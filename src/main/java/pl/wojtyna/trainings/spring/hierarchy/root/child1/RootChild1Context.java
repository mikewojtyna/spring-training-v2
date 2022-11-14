package pl.wojtyna.trainings.spring.hierarchy.root.child1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.wojtyna.trainings.spring.hierarchy.root.CoreBeanOne;
import pl.wojtyna.trainings.spring.hierarchy.root.CoreBeanTwo;

@Configuration
public class RootChild1Context {

    private final CoreBeanOne coreBeanOne;
    private final CoreBeanTwo coreBeanTwo;

    public RootChild1Context(CoreBeanOne coreBeanOne, CoreBeanTwo coreBeanTwo) {
        this.coreBeanOne = coreBeanOne;
        this.coreBeanTwo = coreBeanTwo;
    }

    @Bean
    public RootChild1BeanOne rootChild1BeanOne() {
        return new RootChild1BeanOne();
    }

    @Bean
    public RootChild1BeanTwo rootChild1BeanTwo() {
        return new RootChild1BeanTwo();
    }
}
