package pl.wojtyna.trainings.spring.examples.beans.crowdsorcery.rest;

import org.springframework.stereotype.Component;

@Component
public class HardcodedRestResources implements RestResources {

    private final String profiles;

    public HardcodedRestResources(RestResourcesConfiguration restResourcesConfiguration) {
        this.profiles = restResourcesConfiguration.host() + restResourcesConfiguration.investor().resource();
    }

    public HardcodedRestResources() {
        // added because of xml configuration limitations
        this.profiles = "http://localhost:8080/investor/api/v0/profiles";
    }

    @Override
    public String profiles() {
        return profiles;
    }
}
