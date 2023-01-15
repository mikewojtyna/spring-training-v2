package pl.wojtyna.trainings.spring.examples.injection;

public class OtherSingletonServiceUsingPrototype {

    private final PrototypeScopeService prototypeScopeService;

    public OtherSingletonServiceUsingPrototype(PrototypeScopeService prototypeScopeService) {
        this.prototypeScopeService = prototypeScopeService;
    }

    public String getIdOfPrototypeBean() {
        return prototypeScopeService.getId();
    }
}
