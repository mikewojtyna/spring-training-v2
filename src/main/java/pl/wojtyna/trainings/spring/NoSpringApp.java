package pl.wojtyna.trainings.spring;

public class NoSpringApp {

    public static void main(String[] args) {
        CliCommandsMapper cliCommandsMapper = new SimpleNameCliCommandsMapper();
        var cliAdapter = new CliAdapter(cliCommandsMapper);
        cliAdapter.run(args);
    }
}
