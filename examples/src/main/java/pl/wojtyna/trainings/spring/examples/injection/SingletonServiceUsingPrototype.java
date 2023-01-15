package pl.wojtyna.trainings.spring.examples.injection;

public class SingletonServiceUsingPrototype {

    private final PrototypeScopeService prototypeScopeService;

    public SingletonServiceUsingPrototype(PrototypeScopeService prototypeScopeService) {
        this.prototypeScopeService = prototypeScopeService;
    }

    public String getIdOfPrototypeBean() {
        return prototypeScopeService.getId();
    }
}
