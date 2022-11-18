package pl.wojtyna.trainings.spring.crowdsorcery.portfolio;

import java.util.List;
import java.util.Optional;

public class PortfolioService {

    private final PortfolioRepository portfolioRepository;

    public PortfolioService(PortfolioRepository portfolioRepository) {
        this.portfolioRepository = portfolioRepository;
    }

    public void hidePortfolio(String investorId) {
        portfolioRepository.findById(investorId).ifPresent(portfolio -> portfolioRepository.save(portfolio.hide()));
    }

    public void publishPortfolio(String investorId) {
        portfolioRepository.findById(investorId).ifPresent(portfolio -> portfolioRepository.save(portfolio.publish()));
    }

    public List<Portfolio> getAllPublicPortfolios() {
        return portfolioRepository.findAll();
    }

    public Optional<Portfolio> getPortfolio(String investorId) {
        return portfolioRepository.findById(investorId);
    }

    public Optional<Portfolio> getPublicPortfolio(String investorId) {
        return portfolioRepository.findById(investorId).filter(Portfolio::isPublic);
    }

    public void addInvestment(String investorId, Investment investment) {
        portfolioRepository.findById(investorId)
                           .ifPresent(portfolio -> portfolioRepository.save(portfolio.addInvestment(investment)));
    }

    public void editInvestment(String investorId, Investment investment) {
        portfolioRepository.findById(investorId)
                           .ifPresent(portfolio -> portfolioRepository.save(portfolio.upsertInvestment(investment)));
    }

    public void deleteInvestment(String investorId, String investmentId) {
        portfolioRepository.findById(investorId)
                           .ifPresent(portfolio -> portfolioRepository.save(portfolio.deleteInvestment(investmentId)));
    }
}
