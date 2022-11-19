package pl.wojtyna.trainings.spring.examples.beans.xml;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:examples/beans/configuration.xml")
public class InstantiateBeansUsingXml {

    public static void main(String[] args) {
        new SpringApplicationBuilder(InstantiateBeansUsingXml.class).web(WebApplicationType.NONE)
                                                                    .run(args);
    }
}
