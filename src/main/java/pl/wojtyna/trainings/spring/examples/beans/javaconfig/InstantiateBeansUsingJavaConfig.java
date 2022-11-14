package pl.wojtyna.trainings.spring.examples.beans.javaconfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Import;

@SpringBootConfiguration
@Import(JavaConfiguration.class)
public class InstantiateBeansUsingJavaConfig {

    public static void main(String[] args) {
        SpringApplication.run(InstantiateBeansUsingJavaConfig.class, args);
    }
}
