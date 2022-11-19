package pl.wojtyna.trainings.spring.crowdsorcery.notification;

import pl.wojtyna.trainings.spring.crowdsorcery.eventpublisher.Event;

public interface NotificationService {

    void notifyAbout(Event event);
}
