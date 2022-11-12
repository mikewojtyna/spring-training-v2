package pl.wojtyna.trainings.spring;

import java.util.List;

public class InvestorService {

    private final InvestorRepository investorRepository;

    public InvestorService(InvestorRepository investorRepository) {
        this.investorRepository = investorRepository;
    }

    public void register(Investor investor) {
        investorRepository.save(investor);
    }

    public List<Investor> findAll() {
        return investorRepository.findAll();
    }
}
