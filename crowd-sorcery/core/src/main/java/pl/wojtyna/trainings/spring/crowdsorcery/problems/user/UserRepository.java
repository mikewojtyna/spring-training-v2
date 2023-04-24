package pl.wojtyna.trainings.spring.crowdsorcery.problems.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
