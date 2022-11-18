package pl.wojtyna.trainings.spring.crowdsorcery.eventpublisher;

import org.springframework.context.ApplicationEventPublisher;

public class CompositeSpringEventPublisher extends CompositeEventPublisher {

    private final ApplicationEventPublisher springPublisher;

    public CompositeSpringEventPublisher(EventPublisher delegate, ApplicationEventPublisher springPublisher) {
        super(delegate);
        this.springPublisher = springPublisher;
    }

    @Override
    protected void action(Event event) {
        springPublisher.publishEvent(event);
    }
}
