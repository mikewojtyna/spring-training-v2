package pl.wojtyna.trainings.spring.crowdsorcery.investmentsimulation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

@Slf4j
public class InvestmentSimulationRunner {

    private final Executor executor;

    public InvestmentSimulationRunner(Executor executor) {
        this.executor = executor;
    }

    public CompletableFuture<SimulationResult> runSimulation(String id) {
        log.info("Executing runSimulation");
        return CompletableFuture.supplyAsync(() -> produceSimulationResult(id), executor);
    }

    @Async
    public CompletableFuture<SimulationResult> runSimulationUsingAsync(String id) {
        log.info("Executing runSimulationUsingAsync");
        return CompletableFuture.completedFuture(produceSimulationResult(id));
    }

    private SimulationResult produceSimulationResult(String id) {
        try {
            log.info("Producing simulation result");
            // simulate long running task
            Thread.sleep(5_000);
            return new SimulationResult(id, "some random data: " + UUID.randomUUID());
        }
        catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
