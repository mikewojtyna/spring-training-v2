package pl.wojtyna.trainings.spring.crowdsorcery.rest;

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
