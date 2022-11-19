package pl.wojtyna.trainings.spring.crowdsorcery.investor.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.wojtyna.trainings.spring.crowdsorcery.investor.service.InvestorService;
import pl.wojtyna.trainings.spring.crowdsorcery.investor.service.RegisterInvestor;
import pl.wojtyna.trainings.spring.crowdsorcery.investor.service.UpdateInvestor;

import javax.validation.Valid;
import java.net.URI;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/investorModule/api/v0/investors")
public class InvestorRestApi {

    private final InvestorService investorService;

    public InvestorRestApi(InvestorService investorService) {
        this.investorService = investorService;
    }

    @PostMapping
    public ResponseEntity<RegisterInvestorErrorResponse> register(@Valid @RequestBody RegisterInvestorRestDto registerInvestorRestDto) {
        investorService.register(new RegisterInvestor(registerInvestorRestDto.id(),
                                                      registerInvestorRestDto.name(),
                                                      registerInvestorRestDto.version()));
        return ResponseEntity.created(URI.create("/investorModule/api/v0/investors/%s".formatted(
                                 registerInvestorRestDto.id())))
                             .build();
    }

    @GetMapping("/{id}")
    public Optional<InvestorFetchResultRestDto> fetch(@PathVariable("id") String id) {
        return investorService.findAll()
                              .stream()
                              .filter(investor -> Objects.equals(investor.id(), id))
                              .map(investor -> new InvestorFetchResultRestDto(investor.id(),
                                                                              investor.name(),
                                                                              investor.version()))
                              .findAny();
    }

    @PatchMapping("/{id}")
    public void update(@PathVariable("id") String id,
                       @RequestBody UpdateInvestorRestDto command) {
        investorService.update(id, new UpdateInvestor(command.name(), command.version()));
    }

    private record RegisterInvestorErrorResponse(RegisterInvestorRestDto command, String reason) {

    }
}
