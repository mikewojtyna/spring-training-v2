package pl.wojtyna.trainings.spring.crowdsorcery.investor.repository;

import pl.wojtyna.trainings.spring.crowdsorcery.investor.service.Investor;

import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

public class InMemoryInvestorRepository implements InvestorRepository {

    private final ConcurrentLinkedQueue<Investor> investors = new ConcurrentLinkedQueue<>();

    @Override
    public void save(Investor investor) {
        investors.add(investor);
    }

    @Override
    public List<Investor> findAll() {
        return List.copyOf(investors);
    }
}
