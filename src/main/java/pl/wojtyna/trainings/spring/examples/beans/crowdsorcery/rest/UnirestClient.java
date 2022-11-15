package pl.wojtyna.trainings.spring.examples.beans.crowdsorcery.rest;

import kong.unirest.Unirest;
import kong.unirest.UnirestException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UnirestClient implements RestClient {

    private final String resourceUrl;

    public UnirestClient(RestResources restResources) {this.resourceUrl = restResources.profiles();}

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
