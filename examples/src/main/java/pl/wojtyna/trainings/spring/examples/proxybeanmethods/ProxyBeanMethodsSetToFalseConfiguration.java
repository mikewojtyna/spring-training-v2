package pl.wojtyna.trainings.spring.examples.proxybeanmethods;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class ProxyBeanMethodsSetToFalseConfiguration {

    @Bean
    public Foo foo() {
        return new Foo(bar());
    }

    @Bean
    public Bar bar() {
        System.out.println("Calling method bar()");
        return new Bar();
    }

    @Bean
    public Baz baz(Bar bar) {
        return new Baz(bar);
    }
}
