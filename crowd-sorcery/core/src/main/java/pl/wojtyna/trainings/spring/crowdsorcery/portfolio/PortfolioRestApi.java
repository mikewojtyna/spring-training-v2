package pl.wojtyna.trainings.spring.crowdsorcery.portfolio;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/portfolio-module/api/v0/portfolios")
public class PortfolioRestApi {

    private final PortfolioService portfolioService;

    public PortfolioRestApi(PortfolioService portfolioService) {
        this.portfolioService = portfolioService;
    }

    @GetMapping("/public")
    public List<Portfolio> getAllPublicPortfolios() {
        return portfolioService.getAllPublicPortfolios();
    }

    @GetMapping("/public/{id}")
    public Optional<Portfolio> getPublicPortfolio(@PathVariable("id") String id) {
        return portfolioService.getPublicPortfolio(id);
    }

    @PutMapping(value = "/myPortfolio/public", consumes = "text/plain")
    public void changeVisibility(@RequestBody String publicFlag, Principal principal) {
        var isPublic = Boolean.parseBoolean(publicFlag);
        if (isPublic) {
            portfolioService.publishPortfolio(principal.getName());
        }
        else {
            portfolioService.hidePortfolio(principal.getName());
        }
    }

    @PostMapping("/myPortfolio/investments")
    @ResponseStatus(HttpStatus.CREATED)
    public void addInvestment(@RequestBody Investment investment, Principal principal) {
        portfolioService.addInvestment(principal.getName(), investment);
    }

    @PutMapping("/myPortfolio/investments/{id}")
    public void editInvestment(@PathVariable("id") String investmentId,
                               @RequestBody Investment investment,
                               Principal principal) {
        portfolioService.editInvestment(principal.getName(), investment);
    }

    @DeleteMapping("/myPortfolio/investments/{id}")
    public void deleteInvestment(@PathVariable("id") String investmentId, Principal principal) {
        portfolioService.deleteInvestment(principal.getName(), investmentId);
    }
}
