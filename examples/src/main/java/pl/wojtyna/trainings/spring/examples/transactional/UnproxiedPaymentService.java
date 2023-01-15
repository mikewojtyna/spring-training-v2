package pl.wojtyna.trainings.spring.examples.transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UnproxiedPaymentService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private PaymentRepository paymentRepository;

    @Transactional(propagation = Propagation.REQUIRED)
    public void pay(String id) {
        markAsPaid(id);
        paymentRepository.save(new Payment());
        throw new RuntimeException("something was wrong");
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void markAsPaid(String id) {
        var order = orderRepository.findById(id).orElseThrow();
        order.paid();
    }

}
