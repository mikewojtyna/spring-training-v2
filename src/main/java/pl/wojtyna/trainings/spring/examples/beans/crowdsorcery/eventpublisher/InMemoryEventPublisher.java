package pl.wojtyna.trainings.spring.examples.beans.crowdsorcery.eventpublisher;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.function.Consumer;

public class InMemoryEventPublisher implements EventPublisher, SubscriberRegistry {

    private final ConcurrentLinkedQueue<Consumer<Event>> subscribers = new ConcurrentLinkedQueue<>();

    @Override
    public void publish(Event event) {
        subscribers.forEach(eventConsumer -> eventConsumer.accept(event));
    }

    @Override
    public void register(Consumer<Event> subscriber) {
        subscribers.add(subscriber);
    }
}
