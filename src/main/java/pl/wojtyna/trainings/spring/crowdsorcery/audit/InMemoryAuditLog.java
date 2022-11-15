package pl.wojtyna.trainings.spring.crowdsorcery.audit;

import lombok.extern.slf4j.Slf4j;
import pl.wojtyna.trainings.spring.crowdsorcery.eventpublisher.Event;

import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

@Slf4j
public class InMemoryAuditLog implements AuditLog {

    private final ConcurrentLinkedQueue<AuditLogEntry<Command, Event>> entries = new ConcurrentLinkedQueue<>();

    @Override
    public <C extends Command, E extends Event> void record(C command, E event, Class<?> receiver) {
        log.info(
            """            
            Command: {}
            Event: {}
            Receiver: {}
            """, command, event, receiver
        );
        entries.add(new AuditLogEntry<>(command, event, receiver));
    }

    @Override
    public List<AuditLogEntry<Command, Event>> entries() {
        return entries.stream().toList();
    }
}
