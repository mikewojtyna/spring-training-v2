package pl.wojtyna.trainings.spring.examples.injection;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.*;
import org.springframework.web.context.WebApplicationContext;

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackageClasses = InjectionExampleConfiguration.class)
public class InjectionExampleConfiguration {

    @Bean
    public OtherSingletonServiceUsingPrototype otherSingletonServiceUsingPrototype(PrototypeScopeService prototypeScopeService) {
        return new OtherSingletonServiceUsingPrototype(prototypeScopeService);
    }

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public SingletonServiceUsingRequest otherSingletonService(RequestScopeService requestScopeService) {
        return new SingletonServiceUsingRequest(requestScopeService);
    }

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public SingletonServiceUsingPrototype singletonService(PrototypeScopeService prototypeScopeService) {
        return new SingletonServiceUsingPrototype(prototypeScopeService);
    }

    @Bean
    // proxy is required, so it can be injected into a singleton
    @Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public RequestScopeService requestScopeService() {
        return new RequestScopeService();
    }

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public PrototypeScopeService prototypeScopeService() {
        return new PrototypeScopeService();
    }
}
