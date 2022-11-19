package pl.wojtyna.trainings.spring.crowdsorcery.investor.profile;

import java.util.Optional;

public interface InvestorProfileService {

    Optional<InvestorProfile> fetchById(String id);
}
