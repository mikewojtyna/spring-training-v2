package pl.wojtyna.trainings.spring.crowdsorcery.investor.rest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/investorModule/api/v0/investors")
public class InvestorRestApi {

    @PostMapping
    public void register() {
        throw new UnsupportedOperationException("Implement this method");
    }
}
