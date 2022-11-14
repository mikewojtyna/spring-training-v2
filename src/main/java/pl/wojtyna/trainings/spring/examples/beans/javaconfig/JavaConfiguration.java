package pl.wojtyna.trainings.spring.examples.beans.javaconfig;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import pl.wojtyna.trainings.spring.crowdsorcery.*;

@Configuration
public class JavaConfiguration {

    @Bean
    @Scope(BeanDefinition.SCOPE_SINGLETON) // singleton is the default scope
    public CliSpringAdapterRunner cliSpringAdapterRunner(CliAdapter cliAdapter) {
        return new CliSpringAdapterRunner(cliAdapter);
    }

    @Bean
    public CliAdapter cliAdapter(CliCommandsMapper cliMapper, InvestorService investorService) {
        return new CliAdapter(cliMapper, investorService);
    }

    @Bean
    public RestClient restClient() {
        return new UnirestClient(() -> "http://localhost:8080/investor/api/v0/profiles");
    }

    @Bean
    public InvestorProfileService investorProfileService(RestClient restClient) {
        return new RestClientInvestorProfileService(restClient);
    }

    @Bean
    public InvestorRepository investorRepository() {
        return new InMemoryInvestorRepository();
    }

    @Bean
    public InvestorService investorService(InvestorRepository investorRepository,
                                           InvestorProfileService investorProfileService) {
        return new InvestorService(investorRepository, investorProfileService);
    }

    @Bean
    public CliCommandsMapper cliCommandsMapper() {
        return new SurnameAwareCliCommandsMapper();
    }
}
