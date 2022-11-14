package pl.wojtyna.trainings.spring.crowdsorcery;

import java.util.Optional;

public interface InvestorProfileService {

    Optional<InvestorProfile> fetchById(String id);
}
