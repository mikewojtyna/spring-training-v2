package pl.wojtyna.trainings.spring.crowdsorcery.rest;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "crowdsorcery.rest-client")
public record RestResourcesConfiguration(String host, InvestorProfileResourcesConfiguration investor) {

    public record InvestorProfileResourcesConfiguration(String resource) {

    }
}
