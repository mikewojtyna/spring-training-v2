package pl.wojtyna.trainings.spring.examples.beans.crowdsorcery.rest;

import java.util.Optional;

public interface RestClient {

    <T> Optional<T> get(String resourceId, Class<T> type);
}
