package pl.wojtyna.trainings.spring.examples.beans.xml;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:examples/beans/configuration.xml")
public class InstantiateBeansUsingXml {

    public static void main(String[] args) {
        SpringApplication.run(InstantiateBeansUsingXml.class, args);
    }
}
