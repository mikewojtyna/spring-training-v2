package pl.wojtyna.trainings.spring.crowdsorcery.investor.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.wojtyna.trainings.spring.crowdsorcery.investor.repository.mongo.MongoSpringInvestorRepository;
import pl.wojtyna.trainings.spring.crowdsorcery.investor.service.Investor;

import java.util.stream.Stream;

@RestController
@RequestMapping("/investorModule/api/v0/investors")
public class RegisteredInvestorsRestApiCatalog {

    private final MongoSpringInvestorRepository repository;

    public RegisteredInvestorsRestApiCatalog(MongoSpringInvestorRepository repository) {
        this.repository = repository;
    }

    @GetMapping(params = "exactName")
    public Stream<InvestorInCatalogDto> findByName(@RequestParam("exactName") String name) {
        return repository.findAllByName(name).map(this::toDto);
    }

    private InvestorInCatalogDto toDto(Investor investor) {
        var profile = investor.investorProfile();
        return new InvestorInCatalogDto(investor.id(), investor.name(), profile.score(), profile.isVip(),
                                        profile.referralLink());
    }
}
