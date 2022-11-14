package pl.wojtyna.trainings.spring.crowdsorcery;

import java.util.Optional;

public interface LocalInvestorProfileRepository {

    Optional<InvestorProfile> loadBy(String id);
}
