package pl.wojtyna.trainings.spring.crowdsorcery.investor.repository.jpa;

import org.springframework.transaction.annotation.Transactional;
import pl.wojtyna.trainings.spring.crowdsorcery.investor.profile.InvestorProfile;
import pl.wojtyna.trainings.spring.crowdsorcery.investor.repository.InvestorRepository;
import pl.wojtyna.trainings.spring.crowdsorcery.investor.service.Investor;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.UUID;

public class EntityManagerInvestorRepository implements InvestorRepository {

    private final EntityManager entityManager;

    public EntityManagerInvestorRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
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
        entityManager.merge(investorEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Investor> findAll() {
        return entityManager.createQuery("SELECT investor FROM InvestorEntity investor", InvestorEntity.class)
                            .getResultStream()
                            .map(investorEntity -> {
                                var investorProfileEntity = investorEntity.getInvestorProfile();
                                var investorProfile = new InvestorProfile(investorProfileEntity.getScore(),
                                                                          investorProfileEntity.isVip(),
                                                                          investorProfileEntity.getReferralLink());
                                return new Investor(investorEntity.getId(), investorEntity.getName(), investorProfile);
                            })
                            .toList();
    }
}
