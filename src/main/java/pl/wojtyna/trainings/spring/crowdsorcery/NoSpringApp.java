package pl.wojtyna.trainings.spring.crowdsorcery;

public class NoSpringApp {

    public static void main(String[] args) {
        var container = NoSpringDependencyInjectionContainer.create();
        container.register(CliAdapter.class, CliAdapter.class);
        var cliAdapter = container.getComponent(CliAdapter.class).orElseThrow();
        cliAdapter.run(args);

        // Answer
        // Try to cover the scenario in which some component wants to receive new instances of its dependencies. How could you do that? Don't need to write code this time, just share your thoughts with me.
        container.register(ComponentA.class, ComponentA.class);
        var componentA = container.getComponent(ComponentA.class).orElseThrow();
        // Uses new instance each time
        componentA.doSomethingUsingNewInstanceOfBEachTime();
        componentA.doSomethingUsingNewInstanceOfBEachTime();
        componentA.doSomethingUsingNewInstanceOfBEachTime();
    }
}
