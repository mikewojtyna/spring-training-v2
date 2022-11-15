package pl.wojtyna.trainings.spring.crowdsorcery.filters;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;


@Configuration
public class CommonFiltersConfiguration {

    @Bean
    public Filter benchmarkingFilter() {
        return new RequestBenchmarkingFilter();
    }

    @Bean
    public Filter loggingFilter() {
        return new LoggingFilter();
    }

    @Bean
    public Filter dangerousFilter() {
        return new DangerousFilter();
    }
}
