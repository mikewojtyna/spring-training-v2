package pl.wojtyna.trainings.spring.crowdsorcery.portfolio;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PortfolioModuleConfiguration {

    @Bean
    public PortfolioCreatorEventListener portfolioCreatorEventListener(PortfolioRepository portfolioRepository) {
        return new PortfolioCreatorEventListener(portfolioRepository);
    }

    @Bean
    public PortfolioService portfolioService(PortfolioRepository portfolioRepository) {
        return new PortfolioService(portfolioRepository);
    }
}
