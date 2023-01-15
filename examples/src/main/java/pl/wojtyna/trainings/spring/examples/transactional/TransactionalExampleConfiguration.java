package pl.wojtyna.trainings.spring.examples.transactional;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackageClasses = TransactionalExampleConfiguration.class)
public class TransactionalExampleConfiguration {
}
