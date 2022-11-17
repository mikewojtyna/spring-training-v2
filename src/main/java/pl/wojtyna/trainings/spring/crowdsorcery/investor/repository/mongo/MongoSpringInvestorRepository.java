package pl.wojtyna.trainings.spring.crowdsorcery.investor.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.wojtyna.trainings.spring.crowdsorcery.investor.service.Investor;

import java.util.stream.Stream;

public interface MongoSpringInvestorRepository extends MongoRepository<Investor, String>, AdvancedNameFinderRepository {

    Stream<Investor> findAllByName(String name);

    Stream<Investor> findAllByNameContaining(String substring);

    Stream<Investor> findAllByInvestorProfileIsVip(boolean isVip);

    Stream<Investor> findAllByInvestorProfileScore(int score);
}
