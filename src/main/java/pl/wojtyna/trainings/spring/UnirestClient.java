package pl.wojtyna.trainings.spring;

import kong.unirest.Unirest;
import kong.unirest.UnirestException;

import java.util.Optional;

public class UnirestClient implements RestClient {

    private final String resourceUrl;

    public UnirestClient(String resourceUrl) {this.resourceUrl = resourceUrl;}

    @Override
    public <T> Optional<T> get(String resourceId, Class<T> type) {
        try {
            var response = Unirest.get(resourceUrl).asObject(type);
            return Optional.ofNullable(response.getBody());
        }
        catch (UnirestException e) {
            return Optional.empty();
        }
    }
}
