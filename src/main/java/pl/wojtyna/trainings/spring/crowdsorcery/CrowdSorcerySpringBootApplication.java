package pl.wojtyna.trainings.spring.crowdsorcery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@SpringBootApplication
@ComponentScan(basePackages = {"pl.wojtyna.trainings.spring.crowdsorcery"}, excludeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = {CrowdSorceryRootContextConfiguration.class})})
public class CrowdSorcerySpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrowdSorcerySpringBootApplication.class, args);
    }
}
