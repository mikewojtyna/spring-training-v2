package pl.wojtyna.trainings.spring;

import java.util.List;

public interface InvestorRepository {

    void save(Investor investor);

    List<Investor> findAll();
}
