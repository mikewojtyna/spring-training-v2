package pl.wojtyna.trainings.spring.crowdsorcery.audit;

import pl.wojtyna.trainings.spring.crowdsorcery.eventpublisher.Event;

public record AuditLogEntry<C extends Command, E extends Event>(C command, E event, Class<?> receiver) {
}
