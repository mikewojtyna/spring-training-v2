package rest.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "crowdsorcery.rest-client")
public record RestResourcesConfigurationProperties(String host, InvestorProfileResourcesConfiguration investor) {

    public record InvestorProfileResourcesConfiguration(String resource) {

    }
}
