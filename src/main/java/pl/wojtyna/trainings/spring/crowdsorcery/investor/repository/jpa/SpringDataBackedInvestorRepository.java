package pl.wojtyna.trainings.spring.crowdsorcery.investor.repository.jpa;

import pl.wojtyna.trainings.spring.crowdsorcery.investor.profile.InvestorProfile;
import pl.wojtyna.trainings.spring.crowdsorcery.investor.repository.InvestorRepository;
import pl.wojtyna.trainings.spring.crowdsorcery.investor.service.Investor;

import java.util.List;
import java.util.UUID;

public class SpringDataBackedInvestorRepository implements InvestorRepository {

    private final SpringInvestorEntityRepository springInvestorEntityRepository;

    public SpringDataBackedInvestorRepository(SpringInvestorEntityRepository springInvestorEntityRepository) {
        this.springInvestorEntityRepository = springInvestorEntityRepository;
    }

    @Override
    public void save(Investor investor) {
        var investorEntity = new InvestorEntity();
        investorEntity.setId(investor.id());
        investorEntity.setName(investor.name());
        var investorProfileEntity = new InvestorProfileEntity();
        var investorProfile = investor.investorProfile();
        investorProfileEntity.setId(UUID.randomUUID().toString());
        investorProfileEntity.setVip(investorProfile.isVip());
        investorProfileEntity.setScore(investorProfile.score());
        investorProfileEntity.setReferralLink(investorProfile.referralLink());
        investorProfileEntity.setInvestor(investorEntity);
        investorEntity.setInvestorProfile(investorProfileEntity);
        springInvestorEntityRepository.save(investorEntity);
    }

    @Override
    public List<Investor> findAll() {
        return springInvestorEntityRepository.findAll().stream().map(investorEntity -> {
            var investorProfileEntity = investorEntity.getInvestorProfile();
            var investorProfile = new InvestorProfile(investorProfileEntity.getScore(),
                                                      investorProfileEntity.isVip(),
                                                      investorProfileEntity.getReferralLink());
            return new Investor(investorEntity.getId(),
                                investorEntity.getName(),
                                investorProfile,
                                investorEntity.getVersion());
        }).toList();
    }
}
