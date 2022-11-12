package pl.wojtyna.trainings.spring;

import java.util.List;

public class InvestorService {

    private final InvestorRepository investorRepository;

    public InvestorService(InvestorRepository investorRepository) {
        this.investorRepository = investorRepository;
    }

    public void register(RegisterInvestor command) {
        investorRepository.save(new Investor(command.id(), command.name()));
    }

    public List<Investor> findAll() {
        return investorRepository.findAll();
    }
}
