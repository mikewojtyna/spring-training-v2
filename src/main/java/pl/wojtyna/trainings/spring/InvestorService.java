package pl.wojtyna.trainings.spring;

public class InvestorService {

    private final InvestorRepository investorRepository;

    public InvestorService(InvestorRepository investorRepository) {
        this.investorRepository = investorRepository;
    }

    public void register(Investor investor) {
        investorRepository.save(investor);
    }
}
