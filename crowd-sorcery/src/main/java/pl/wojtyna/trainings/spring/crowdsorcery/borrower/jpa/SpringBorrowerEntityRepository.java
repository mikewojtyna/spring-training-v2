package pl.wojtyna.trainings.spring.crowdsorcery.borrower.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringBorrowerEntityRepository extends JpaRepository<BorrowerEntity, String> {
}
