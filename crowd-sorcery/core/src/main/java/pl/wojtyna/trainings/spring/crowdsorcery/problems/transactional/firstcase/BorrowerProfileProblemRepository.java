package pl.wojtyna.trainings.spring.crowdsorcery.problems.transactional.firstcase;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface BorrowerProfileProblemRepository extends MongoRepository<BorrowerProfile, String> {
}
