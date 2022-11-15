package pl.wojtyna.trainings.spring.crowdsorcery.audit;

import pl.wojtyna.trainings.spring.crowdsorcery.eventpublisher.Event;

import java.util.List;

public interface AuditLog {

    <C extends Command, E extends Event> void record(C command, E event, Class<?> receiver);

    List<AuditLogEntry<Command, Event>> entries();
}
