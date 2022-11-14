package pl.wojtyna.trainings.spring.crowdsorcery;

import org.springframework.boot.builder.SpringApplicationBuilder;
import pl.wojtyna.trainings.spring.crowdsorcery.notification.NotificationModuleConfiguration;

public class CrowdSorceryApp {

    public static void main(String[] args) throws InterruptedException {
        configureContextBuilder().run(args);
    }

    private static SpringApplicationBuilder configureContextBuilder() {
        return new SpringApplicationBuilder()
            .parent(CrowdSorceryRootContextConfiguration.class)
            .child(NotificationModuleConfiguration.class);
    }
}
