package pl.wojtyna.trainings.spring.examples.injection;

import java.util.UUID;

public class RequestScopeService {

    private final String id;

    public RequestScopeService() {
        id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }
}
