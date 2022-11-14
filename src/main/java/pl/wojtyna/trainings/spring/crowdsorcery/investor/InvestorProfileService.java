package pl.wojtyna.trainings.spring.crowdsorcery.investor;

import java.util.Optional;

public interface InvestorProfileService {

    Optional<InvestorProfile> fetchById(String id);
}
