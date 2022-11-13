package pl.wojtyna.trainings.spring;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

@Component
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
