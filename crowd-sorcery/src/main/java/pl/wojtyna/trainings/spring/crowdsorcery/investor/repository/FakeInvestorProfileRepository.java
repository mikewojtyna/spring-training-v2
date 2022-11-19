package pl.wojtyna.trainings.spring.crowdsorcery.investor.repository;

import pl.wojtyna.trainings.spring.crowdsorcery.investor.profile.InvestorProfile;

import java.util.Optional;

public class FakeInvestorProfileRepository implements LocalInvestorProfileRepository {

    @Override
    public Optional<InvestorProfile> loadBy(String id) {
        return Optional.of(someFakeProfile());
    }

    private InvestorProfile someFakeProfile() {
        return new InvestorProfile(100, false, "https://howtobe.pro?refId=1234");
    }
}
