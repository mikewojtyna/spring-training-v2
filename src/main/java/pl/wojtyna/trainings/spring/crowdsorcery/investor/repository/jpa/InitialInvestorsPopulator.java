package pl.wojtyna.trainings.spring.crowdsorcery.investor.repository.jpa;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import pl.wojtyna.trainings.spring.crowdsorcery.investor.profile.InvestorProfile;
import pl.wojtyna.trainings.spring.crowdsorcery.investor.repository.InvestorRepository;
import pl.wojtyna.trainings.spring.crowdsorcery.investor.service.Investor;

public class InitialInvestorsPopulator implements ApplicationRunner {

    private final InvestorRepository investorRepository;

    public InitialInvestorsPopulator(InvestorRepository investorRepository) {
        this.investorRepository = investorRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        var investorProfile = new InvestorProfile(50, true, "wojtyna.pl?refLink=123");
        var defaultInvestor = new Investor("0f3ba620-533a-4047-a1d7-e4cc77c263c6", "Default User", investorProfile);
        investorRepository.save(defaultInvestor);
    }
}
