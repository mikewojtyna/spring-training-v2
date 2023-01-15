package pl.wojtyna.trainings.spring.examples.transactional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<MyOrder, String>, PersistableRepo {

}
