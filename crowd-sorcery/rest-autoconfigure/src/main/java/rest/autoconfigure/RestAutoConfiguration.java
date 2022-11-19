package rest.autoconfigure;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.wojtyna.trainings.spring.crowdsorcery.rest.RestClient;
import pl.wojtyna.trainings.spring.crowdsorcery.rest.RestResources;
import pl.wojtyna.trainings.spring.crowdsorcery.rest.UnirestClient;

@Configuration
@EnableConfigurationProperties(RestResourcesConfigurationProperties.class)
public class RestAutoConfiguration {

    @Bean
    public RestResources restResources(RestResourcesConfigurationProperties properties) {
        return new HardcodedRestResources(properties);
    }

    @Bean
    @ConditionalOnMissingBean(RestClient.class)
    public RestClient restClient(RestResources restResources) {
        return new UnirestClient(restResources);
    }
}
