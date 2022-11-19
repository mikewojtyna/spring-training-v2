package pl.wojtyna.trainings.spring.crowdsorcery.borrower.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.wojtyna.trainings.spring.crowdsorcery.borrower.Borrower;

public interface MongoSpringBorrowerRepository extends MongoRepository<Borrower, String> {
}
