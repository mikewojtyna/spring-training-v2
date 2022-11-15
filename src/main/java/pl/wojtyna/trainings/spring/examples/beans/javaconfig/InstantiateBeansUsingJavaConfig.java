package pl.wojtyna.trainings.spring.examples.beans.javaconfig;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Import;

@SpringBootConfiguration
@Import(JavaConfiguration.class)
public class InstantiateBeansUsingJavaConfig {

    public static void main(String[] args) {
        new SpringApplicationBuilder(InstantiateBeansUsingJavaConfig.class).web(WebApplicationType.NONE).run(args);
    }
}
