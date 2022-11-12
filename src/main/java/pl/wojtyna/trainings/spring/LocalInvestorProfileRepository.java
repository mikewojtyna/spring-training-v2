package pl.wojtyna.trainings.spring;

import java.util.Optional;

public interface LocalInvestorProfileRepository {

    Optional<InvestorProfile> loadBy(String id);
}
