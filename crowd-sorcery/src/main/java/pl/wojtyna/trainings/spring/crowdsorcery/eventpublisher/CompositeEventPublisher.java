package pl.wojtyna.trainings.spring.crowdsorcery.eventpublisher;

public abstract class CompositeEventPublisher implements EventPublisher {

    private final EventPublisher delegate;

    public CompositeEventPublisher(EventPublisher delegate) {
        this.delegate = delegate;
    }

    @Override
    public void publish(Event event) {
        action(event);
        delegate.publish(event);
    }

    protected abstract void action(Event event);
}
