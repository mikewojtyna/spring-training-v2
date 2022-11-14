package pl.wojtyna.trainings.spring.hierarchy.root.child2.child1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.wojtyna.trainings.spring.hierarchy.root.CoreBeanOne;
import pl.wojtyna.trainings.spring.hierarchy.root.CoreBeanTwo;
import pl.wojtyna.trainings.spring.hierarchy.root.child2.RootChild2Bean;

@Configuration
public class RootChild2Child1Context {

    private final CoreBeanOne coreBeanOne;
    private final CoreBeanTwo coreBeanTwo;
    private final RootChild2Bean rootChild2Bean;

    public RootChild2Child1Context(RootChild2Bean rootChild2Bean, CoreBeanOne coreBeanOne, CoreBeanTwo coreBeanTwo) {
        this.rootChild2Bean = rootChild2Bean;
        this.coreBeanOne = coreBeanOne;
        this.coreBeanTwo = coreBeanTwo;
    }

    @Bean
    public RootChild2Child1BeanOne rootChild2Child1BeanOne() {
        return new RootChild2Child1BeanOne();
    }

    @Bean
    public RootChild2Child1BeanTwo rootChild2Child1BeanTwo() {
        return new RootChild2Child1BeanTwo();
    }
}
