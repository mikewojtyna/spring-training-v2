package pl.wojtyna.trainings.spring.examples.transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class Service {

    @Autowired
    OrderRepository orderRepository;

    @Transactional
    public void save(MyOrder order) {
        orderRepository.persist(order);
    }

    public void save1(MyOrder order) {
        save(order);
    }

}
