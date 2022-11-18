package pl.wojtyna.trainings.spring.crowdsorcery.portfolio;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface PortfolioRepository extends MongoRepository<Portfolio, String> {
}
