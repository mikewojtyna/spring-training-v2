package rest.autoconfigure;

import pl.wojtyna.trainings.spring.crowdsorcery.rest.RestResources;

public class HardcodedRestResources implements RestResources {

    private final String profiles;

    public HardcodedRestResources(RestResourcesConfigurationProperties properties) {
        this.profiles = properties.host() + properties.investor().resource();
    }

    @Override
    public String profiles() {
        return profiles;
    }
}
