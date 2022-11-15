package pl.wojtyna.trainings.spring.crowdsorcery.investor.repository;

import pl.wojtyna.trainings.spring.crowdsorcery.investor.service.Investor;

import java.util.List;

public interface InvestorRepository {

    void save(Investor investor);

    List<Investor> findAll();
}
