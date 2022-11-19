package pl.wojtyna.trainings.spring.examples.beans.crowdsorcery.notification;

import pl.wojtyna.trainings.spring.examples.beans.crowdsorcery.eventpublisher.Event;

public interface NotificationService {

    void notifyAbout(Event event);
}
