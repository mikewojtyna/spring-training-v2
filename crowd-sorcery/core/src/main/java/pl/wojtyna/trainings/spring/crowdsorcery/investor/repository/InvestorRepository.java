package pl.wojtyna.trainings.spring.crowdsorcery.investor.repository;

import pl.wojtyna.trainings.spring.crowdsorcery.investor.service.Investor;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public interface InvestorRepository {

    void save(Investor investor);

    List<Investor> findAll();

    default Optional<Investor> findById(String id) {
        return findAll().stream().filter(investor -> Objects.equals(investor.id(), id)).findAny();
    }
}
