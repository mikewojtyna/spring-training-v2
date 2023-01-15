package pl.wojtyna.trainings.spring.examples.transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Component
public class PersistableRepoImpl implements PersistableRepo {

    @Autowired
    private EntityManager entityManager;

    @Override
    @Transactional
    public MyOrder persist(MyOrder order) {
        entityManager.persist(order);
        return order;
    }
}
