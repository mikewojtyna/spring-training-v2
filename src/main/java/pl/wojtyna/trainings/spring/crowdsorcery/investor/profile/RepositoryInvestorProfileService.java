package pl.wojtyna.trainings.spring.crowdsorcery.investor.profile;

import pl.wojtyna.trainings.spring.crowdsorcery.investor.repository.LocalInvestorProfileRepository;
import pl.wojtyna.trainings.spring.crowdsorcery.investor.service.InvestorProfileService;

import java.util.Optional;

public class RepositoryInvestorProfileService implements InvestorProfileService {

    private final LocalInvestorProfileRepository localInvestorProfileRepository;

    public RepositoryInvestorProfileService(LocalInvestorProfileRepository localInvestorProfileRepository) {this.localInvestorProfileRepository = localInvestorProfileRepository;}

    @Override
    public Optional<InvestorProfile> fetchById(String id) {
        return localInvestorProfileRepository.loadBy(id);
    }
}
