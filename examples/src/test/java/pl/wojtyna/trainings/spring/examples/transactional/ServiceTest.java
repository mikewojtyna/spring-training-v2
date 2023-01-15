package pl.wojtyna.trainings.spring.examples.transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(classes = TransactionalExampleConfiguration.class)
class ServiceTest {

    @Autowired
    private Service serviceTest;
    @Autowired
    private OrderRepository orderRepository;

    @Test
    void saveUsingSave1() {
        MyOrder order = new MyOrder();
        order.setId(UUID.randomUUID().toString());
        serviceTest.save1(order);
        assertThat(orderRepository.findById(order.getId())).isNotEmpty();
    }

    @Test
    void saveUsingSave() {
        MyOrder order = new MyOrder();
        order.setId(UUID.randomUUID().toString());
        serviceTest.save(order);
        assertThat(orderRepository.findById(order.getId())).isNotEmpty();
    }
}