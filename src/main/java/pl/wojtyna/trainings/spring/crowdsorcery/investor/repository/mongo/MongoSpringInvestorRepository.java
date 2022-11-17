package pl.wojtyna.trainings.spring.crowdsorcery.investor.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.wojtyna.trainings.spring.crowdsorcery.investor.service.Investor;

public interface MongoSpringInvestorRepository extends MongoRepository<Investor, String> {
}
