package pl.wojtyna.trainings.spring.crowdsorcery;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InvestorService {

    private final InvestorRepository investorRepository;
    private final InvestorProfileService investorProfileService;

    public InvestorService(InvestorRepository investorRepository, InvestorProfileService investorProfileService) {
        this.investorRepository = investorRepository;
        this.investorProfileService = investorProfileService;
    }

    public void register(RegisterInvestor command) {
        investorRepository.save(new Investor(command.id(),
                                             command.name(),
                                             investorProfileService.fetchById(command.id()).orElseThrow()));
    }

    public List<Investor> findAll() {
        return investorRepository.findAll();
    }
}
