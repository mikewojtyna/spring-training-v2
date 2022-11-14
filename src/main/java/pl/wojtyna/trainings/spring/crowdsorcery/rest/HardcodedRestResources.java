package pl.wojtyna.trainings.spring.crowdsorcery.rest;

import org.springframework.stereotype.Component;

@Component
public class HardcodedRestResources implements RestResources {

    private final String profiles;

    public HardcodedRestResources(RestResourcesConfiguration restResourcesConfiguration) {
        this.profiles = restResourcesConfiguration.host() + restResourcesConfiguration.investor().resource();
    }

    @Override
    public String profiles() {
        return profiles;
    }
}
