package pl.wojtyna.trainings.spring.crowdsorcery.investor.service;

import pl.wojtyna.trainings.spring.crowdsorcery.investor.profile.InvestorProfile;

public record Investor(String id, String name, InvestorProfile investorProfile) {
}
