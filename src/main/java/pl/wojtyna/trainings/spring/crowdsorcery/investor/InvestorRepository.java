package pl.wojtyna.trainings.spring.crowdsorcery.investor;

import java.util.List;

public interface InvestorRepository {

    void save(Investor investor);

    List<Investor> findAll();
}
