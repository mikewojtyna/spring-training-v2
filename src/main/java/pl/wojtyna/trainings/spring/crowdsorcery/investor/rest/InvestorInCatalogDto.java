package pl.wojtyna.trainings.spring.crowdsorcery.investor.rest;

public record InvestorInCatalogDto(String id, String name, int score, boolean isVip, String refLink) {
}
