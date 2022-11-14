package pl.wojtyna.trainings.spring.hierarchy.root;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RootContext {

    @Bean
    public CoreBeanOne method() {
        return new CoreBeanOne();
    }

    @Bean
    public CoreBeanTwo coreBeanTwo() {
        return new CoreBeanTwo();
    }
}
