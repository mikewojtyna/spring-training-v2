package pl.wojtyna.trainings.spring.examples.beans.crowdsorcery.eventpublisher;

import java.util.function.Consumer;

public interface SubscriberRegistry {

    void register(Consumer<Event> subscriber);
}
