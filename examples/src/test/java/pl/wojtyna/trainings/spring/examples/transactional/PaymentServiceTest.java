package pl.wojtyna.trainings.spring.examples.transactional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Transactional explanation")
@SpringBootTest(classes = TransactionalExampleConfiguration.class)
public class PaymentServiceTest {

    @Autowired
    private UnproxiedPaymentService unproxiedPaymentService;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProxiedPaymentService proxiedPaymentService;

    @DisplayName("Using payment service without proxy")
    @Test
    void withoutProxyTest() {
        // given
        MyOrder order = new MyOrder();
        order.setId("123");
        orderRepository.save(order);

        // when
        try {
            unproxiedPaymentService.pay("123");
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        // then
        assertThat(orderRepository.findById("123"))
            .describedAs("Order is still not paid because internal method calls are not proxied with @Transactional")
            .hasValueSatisfying(myOrder -> assertThat(myOrder.isPaid()).isFalse());
    }

    @DisplayName("Using proxied payment service")
    @Test
    void withProxyTest() {
        // given
        MyOrder order = new MyOrder();
        order.setId("123");
        orderRepository.save(order);

        // when
        try {
            proxiedPaymentService.pay("123");
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        // then
        assertThat(orderRepository.findById("123"))
            .describedAs(
                "Order is paid because internal method calls are now using proxy and therefore REQUIRES_NEW transaction propagation is applied")
            .hasValueSatisfying(myOrder -> assertThat(myOrder.isPaid()).isTrue());
    }
}
