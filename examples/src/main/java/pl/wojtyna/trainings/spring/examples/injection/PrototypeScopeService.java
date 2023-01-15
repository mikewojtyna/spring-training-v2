package pl.wojtyna.trainings.spring.examples.injection;

import java.util.UUID;

public class PrototypeScopeService {

    private final String id;

    public PrototypeScopeService() {
        id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }
}
