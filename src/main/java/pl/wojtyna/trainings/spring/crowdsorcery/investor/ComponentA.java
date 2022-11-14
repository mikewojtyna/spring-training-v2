package pl.wojtyna.trainings.spring.crowdsorcery.investor;

public class ComponentA {

    private final ComponentBFactory componentBFactory;

    public ComponentA(ComponentBFactory componentBFactory) {this.componentBFactory = componentBFactory;}

    public void doSomethingUsingNewInstanceOfBEachTime() {
        componentBFactory.newInstance().doSomething();
    }
}
