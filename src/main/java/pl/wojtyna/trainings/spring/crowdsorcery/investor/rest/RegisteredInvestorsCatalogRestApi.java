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
public class RegisteredInvestorsCatalogRestApi {

    private final MongoSpringInvestorRepository repository;

    public RegisteredInvestorsCatalogRestApi(MongoSpringInvestorRepository repository) {
        this.repository = repository;
    }

    @GetMapping(params = "exactName")
    public Stream<InvestorInCatalogDto> findByName(@RequestParam("exactName") String name) {
        return repository.findAllByName(name).map(this::toDto);
    }

    @GetMapping(params = "nameContaining")
    public Stream<InvestorInCatalogDto> findByNameContaining(@RequestParam("nameContaining") String subString) {
        return repository.findAllByNameContaining(subString).map(this::toDto);
    }

    @GetMapping(params = "exactNameLength")
    public Stream<InvestorInCatalogDto> findByExactNameLength(@RequestParam("exactNameLength") int nameLength) {
        return repository.findAllByNameLength(nameLength).map(this::toDto);
    }

    @GetMapping(params = "nameLengthRange")
    public Stream<InvestorInCatalogDto> findByNameLengthRange(@RequestParam("nameLengthRange") String nameLengthRange) {
        var rangeArray = nameLengthRange.split("-");
        var start = Integer.parseInt(rangeArray[0]);
        var end = Integer.parseInt(rangeArray[1]);
        return repository.findAllByNameLengthRange(start, end).map(this::toDto);
    }

    @GetMapping(params = "vipsOnly")
    public Stream<InvestorInCatalogDto> findByVipStatus(@RequestParam("vipsOnly") boolean onlyVips) {
        return repository.findAllByInvestorProfileIsVip(onlyVips).map(this::toDto);
    }

    @GetMapping(params = "exactScore")
    public Stream<InvestorInCatalogDto> findByExactScore(@RequestParam("exactScore") int score) {
        return repository.findAllByInvestorProfileScore(score).map(this::toDto);
    }

    @GetMapping(params = "scoreGtOrEq")
    public Stream<InvestorInCatalogDto> findByScoreGreaterThanOrEqualTo(@RequestParam("scoreGtOrEq") int score) {
        return repository.findByInvestorProfileScoreGreaterThanEqual(score).map(this::toDto);
    }

    private InvestorInCatalogDto toDto(Investor investor) {
        var profile = investor.investorProfile();
        return new InvestorInCatalogDto(investor.id(), investor.name(), profile.score(), profile.isVip(),
                                        profile.referralLink());
    }
}
