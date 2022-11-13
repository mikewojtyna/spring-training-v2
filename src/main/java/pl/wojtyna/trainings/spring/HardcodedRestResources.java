package pl.wojtyna.trainings.spring;

import org.springframework.stereotype.Component;

@Component
public class HardcodedRestResources implements RestResources {

    @Override
    public String profiles() {
        return "http://localhost:8080/investor/api/v0/profiles";
    }
}
