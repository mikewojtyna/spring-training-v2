package pl.wojtyna.trainings.spring.examples.beans.crowdsorcery.investor.service;

import pl.wojtyna.trainings.spring.examples.beans.crowdsorcery.investor.profile.InvestorProfile;

public record Investor(String id, String name, InvestorProfile investorProfile) {
}
