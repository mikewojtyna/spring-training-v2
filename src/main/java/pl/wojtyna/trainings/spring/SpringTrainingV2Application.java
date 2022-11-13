package pl.wojtyna.trainings.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringTrainingV2Application {

    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext();
        context.scan("pl.wojtyna");
        context.refresh();
        var cliAdapter = context.getBean(CliAdapter.class);
        cliAdapter.run(args);
    }

}
