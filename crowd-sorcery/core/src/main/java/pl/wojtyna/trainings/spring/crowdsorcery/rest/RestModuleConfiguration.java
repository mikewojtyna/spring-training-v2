package pl.wojtyna.trainings.spring.crowdsorcery.rest;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@EnableConfigurationProperties(RestResourcesConfiguration.class)
@PropertySource("classpath:rest-client.properties")
public class RestModuleConfiguration {

    @Bean
    public RestResources restResources(RestResourcesConfiguration configuration) {
        return new HardcodedRestResources(configuration);
    }

    @Bean
    public RestClient restClient(RestResources restResources) {
        return new UnirestClient(restResources);
    }
}
