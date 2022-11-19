package pl.wojtyna.trainings.spring.crowdsorcery.investor.repository.mongo;

import pl.wojtyna.trainings.spring.crowdsorcery.investor.service.Investor;

import java.util.stream.Stream;

public interface AdvancedNameFinderRepository {

    Stream<Investor> findAllByNameLength(int length);

    Stream<Investor> findAllByNameLengthRange(int start, int end);
}
