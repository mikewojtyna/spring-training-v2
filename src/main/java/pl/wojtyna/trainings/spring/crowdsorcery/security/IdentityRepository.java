package pl.wojtyna.trainings.spring.crowdsorcery.security;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface IdentityRepository extends MongoRepository<CrowdSorceryIdentity, String> {
}
