package pl.wojtyna.trainings.spring;

public class NoSpringApp {

    public static void main(String[] args) {
        var cliAdapter = new CliAdapter();
        cliAdapter.run(args);
    }
}
