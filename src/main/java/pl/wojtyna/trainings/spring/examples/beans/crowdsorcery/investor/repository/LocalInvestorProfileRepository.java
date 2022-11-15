package pl.wojtyna.trainings.spring.examples.beans.crowdsorcery.investor.repository;

import pl.wojtyna.trainings.spring.examples.beans.crowdsorcery.investor.profile.InvestorProfile;

import java.util.Optional;

public interface LocalInvestorProfileRepository {

    Optional<InvestorProfile> loadBy(String id);
}
