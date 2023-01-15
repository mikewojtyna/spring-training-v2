package pl.wojtyna.trainings.spring.examples.proxybeanmethods;

public class Baz {

    private final Bar bar;

    public Baz(Bar bar) {

        this.bar = bar;
    }

    public Bar getBar() {
        return bar;
    }
}
