package pl.wojtyna.trainings.spring;

import java.util.Optional;

public class RepositoryInvestorProfileService implements InvestorProfileService {

    private final LocalInvestorProfileRepository localInvestorProfileRepository;

    public RepositoryInvestorProfileService(LocalInvestorProfileRepository localInvestorProfileRepository) {this.localInvestorProfileRepository = localInvestorProfileRepository;}

    @Override
    public Optional<InvestorProfile> fetchById(String id) {
        return localInvestorProfileRepository.loadBy(id);
    }
}
