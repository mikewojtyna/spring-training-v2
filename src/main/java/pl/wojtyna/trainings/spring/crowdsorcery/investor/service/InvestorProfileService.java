package pl.wojtyna.trainings.spring.crowdsorcery.investor.service;

import pl.wojtyna.trainings.spring.crowdsorcery.investor.profile.InvestorProfile;

import java.util.Optional;

public interface InvestorProfileService {

    Optional<InvestorProfile> fetchById(String id);
}
