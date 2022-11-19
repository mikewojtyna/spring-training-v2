package pl.wojtyna.trainings.spring.crowdsorcery.investmentsimulation;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Configuration
@EnableAsync
public class InvestmentSimulationModuleConfiguration {

    @Bean
    public Executor asyncExecutor() {
        return Executors.newFixedThreadPool(20);
    }

    @Bean(name = "investmentSimulationExecutor")
    public Executor investmentSimulationExecutor() {
        return Executors.newFixedThreadPool(10);
    }

    @Bean
    public InvestmentSimulationRunner investmentSimulationRunner(@Qualifier("investmentSimulationExecutor") Executor executor) {
        return new InvestmentSimulationRunner(executor);
    }
}
