package pl.wojtyna.trainings.spring.examples.injection;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/injection")
public class ControllerDelegatingToSingletonServiceUsingRequest {

    private final SingletonServiceUsingRequest singletonServiceUsingRequest;

    public ControllerDelegatingToSingletonServiceUsingRequest(SingletonServiceUsingRequest singletonServiceUsingRequest) {
        this.singletonServiceUsingRequest = singletonServiceUsingRequest;
    }

    @GetMapping
    public String getId() {
        return singletonServiceUsingRequest.getIdOfRequestBean();
    }
}
