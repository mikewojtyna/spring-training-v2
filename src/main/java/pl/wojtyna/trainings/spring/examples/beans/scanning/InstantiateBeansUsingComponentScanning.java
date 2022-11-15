package pl.wojtyna.trainings.spring.examples.beans.scanning;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication(scanBasePackages = "pl.wojtyna.trainings.spring.examples.beans.crowdsorcery")
public class InstantiateBeansUsingComponentScanning {

    public static void main(String[] args) {
        new SpringApplicationBuilder(InstantiateBeansUsingComponentScanning.class).web(WebApplicationType.NONE)
                                                                                  .run(args);
    }
}
