package pl.wojtyna.trainings.spring.crowdsorcery.investmentsimulation;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/investment-simulations-module/api/v0/investments")
public class InvestmentSimulationRestApi {

    private final InvestmentSimulationRunner investmentSimulationRunner;

    public InvestmentSimulationRestApi(InvestmentSimulationRunner investmentSimulationRunner) {
        this.investmentSimulationRunner = investmentSimulationRunner;
    }

    @PostMapping
    public CompletableFuture<SimulationResult> runSimulationUsingCompletableFuture(@RequestBody RunSimulationRestCommand command) {
        return investmentSimulationRunner.runSimulation(command.id());
    }

    @PostMapping(params = "usingAsync")
    public CompletableFuture<SimulationResult> runSimulationUsingAsync(@RequestBody RunSimulationRestCommand command) {
        return investmentSimulationRunner.runSimulationUsingAsync(command.id());
    }
}
