package pl.wojtyna.trainings.spring;

import java.util.Optional;

public interface RestClient {

    <T> Optional<T> get(String resourceId, Class<T> type);
}
