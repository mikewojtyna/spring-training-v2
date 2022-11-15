package pl.wojtyna.trainings.spring.crowdsorcery.investor.service;

import pl.wojtyna.trainings.spring.crowdsorcery.eventpublisher.EventPublisher;
import pl.wojtyna.trainings.spring.crowdsorcery.investor.repository.InvestorRepository;

import java.util.List;

public class InvestorService {

    private final InvestorRepository investorRepository;
    private final InvestorProfileService investorProfileService;
    private final EventPublisher eventPublisher;

    public InvestorService(InvestorRepository investorRepository, InvestorProfileService investorProfileService,
                           EventPublisher eventPublisher) {
        this.investorRepository = investorRepository;
        this.investorProfileService = investorProfileService;
        this.eventPublisher = eventPublisher;
    }

    public void register(RegisterInvestor command) {
        var investor = new Investor(command.id(),
                                    command.name(),
                                    investorProfileService.fetchById(command.id()).orElseThrow());
        investorRepository.save(investor);
        // we should use outbox pattern here, but let's leave it like this for the sake of the simplicity
        eventPublisher.publish(new InvestorRegistered(investor));
    }

    public List<Investor> findAll() {
        return investorRepository.findAll();
    }
}
