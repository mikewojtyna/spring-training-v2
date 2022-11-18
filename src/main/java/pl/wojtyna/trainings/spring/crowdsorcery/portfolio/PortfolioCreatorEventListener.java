package pl.wojtyna.trainings.spring.crowdsorcery.portfolio;

import org.springframework.context.event.EventListener;
import pl.wojtyna.trainings.spring.crowdsorcery.investor.service.Investor;
import pl.wojtyna.trainings.spring.crowdsorcery.investor.service.InvestorRegistered;

public class PortfolioCreatorEventListener {

    private final PortfolioRepository portfolioRepository;

    public PortfolioCreatorEventListener(PortfolioRepository portfolioRepository) {
        this.portfolioRepository = portfolioRepository;
    }

    @EventListener
    public void when(InvestorRegistered investorRegistered) {
        createPortfolio(investorRegistered.investor());
    }

    private void createPortfolio(Investor investor) {
        portfolioRepository.save(Portfolio.emptyPublicPortfolioOf(investor));
    }
}
