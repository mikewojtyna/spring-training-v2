package pl.wojtyna.trainings.spring.examples.beans.scanning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "pl.wojtyna.trainings.spring.examples.beans.crowdsorcery")
public class InstantiateBeansUsingComponentScanning {

    public static void main(String[] args) {
        SpringApplication.run(InstantiateBeansUsingComponentScanning.class, args);
    }
}
