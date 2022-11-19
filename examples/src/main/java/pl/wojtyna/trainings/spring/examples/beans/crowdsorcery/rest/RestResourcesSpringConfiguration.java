package pl.wojtyna.trainings.spring.examples.beans.crowdsorcery.rest;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@EnableConfigurationProperties(RestResourcesConfiguration.class)
@PropertySource("classpath:examples/beans/rest-client.properties")
public class RestResourcesSpringConfiguration {
}
