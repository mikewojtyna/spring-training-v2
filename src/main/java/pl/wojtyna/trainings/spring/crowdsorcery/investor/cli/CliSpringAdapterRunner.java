package pl.wojtyna.trainings.spring.crowdsorcery.investor.cli;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

public class CliSpringAdapterRunner implements ApplicationRunner {

    private final CliAdapter cliAdapter;

    public CliSpringAdapterRunner(CliAdapter cliAdapter) {
        this.cliAdapter = cliAdapter;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        cliAdapter.run(args.getSourceArgs());
    }
}
