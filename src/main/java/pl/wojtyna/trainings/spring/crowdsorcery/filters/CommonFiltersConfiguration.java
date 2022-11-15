package pl.wojtyna.trainings.spring.crowdsorcery.filters;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.wojtyna.trainings.spring.crowdsorcery.security.PasswordChecker;

import javax.servlet.Filter;


@Configuration
public class CommonFiltersConfiguration {

    @Bean
    public FilterRegistrationBean<CustomBasicAuthFilter> customBasicAuthFilterFilter(PasswordChecker passwordChecker) {
        var filter = new CustomBasicAuthFilter(passwordChecker);
        var registrationBean = new FilterRegistrationBean<>(filter);
        registrationBean.addUrlPatterns("/borrower-module/api/v0/borrowers/confidential/*");
        registrationBean.setOrder(4);
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<InvestorSecretFilter> investorSecretFilter() {
        var filter = new InvestorSecretFilter();
        var registrationBean = new FilterRegistrationBean<>(filter);
        registrationBean.addUrlPatterns("/investorModule/*");
        registrationBean.setOrder(3);
        return registrationBean;
    }

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
