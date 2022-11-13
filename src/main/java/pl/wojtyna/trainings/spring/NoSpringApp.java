package pl.wojtyna.trainings.spring;

public class NoSpringApp {

    public static void main(String[] args) {
        var container = NoSpringDependencyInjectionContainer.create();
        var cliAdapter = container.configureCliAdapter();
        cliAdapter.run(args);
    }
}
