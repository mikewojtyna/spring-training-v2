package pl.wojtyna.trainings.spring;

public class NoSpringApp {

    public static void main(String[] args) {
        var container = NoSpringDependencyInjectionContainer.create();
        container.register(CliAdapter.class, CliAdapter.class);
        var cliAdapter = container.getComponent(CliAdapter.class).orElseThrow();
        cliAdapter.run(args);
    }
}
