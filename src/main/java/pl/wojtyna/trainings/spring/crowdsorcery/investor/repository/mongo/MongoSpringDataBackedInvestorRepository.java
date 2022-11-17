package pl.wojtyna.trainings.spring.crowdsorcery.investor.repository.mongo;

import pl.wojtyna.trainings.spring.crowdsorcery.investor.repository.InvestorRepository;
import pl.wojtyna.trainings.spring.crowdsorcery.investor.service.Investor;

import java.util.List;

public class MongoSpringDataBackedInvestorRepository implements InvestorRepository {

    private final MongoSpringInvestorRepository springRepository;

    public MongoSpringDataBackedInvestorRepository(MongoSpringInvestorRepository springRepository) {this.springRepository = springRepository;}

    @Override
    public void save(Investor investor) {
        springRepository.save(investor);
    }

    @Override
    public List<Investor> findAll() {
        return springRepository.findAll();
    }
}
