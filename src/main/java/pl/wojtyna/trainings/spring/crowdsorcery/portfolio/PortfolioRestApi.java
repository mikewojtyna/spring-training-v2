package pl.wojtyna.trainings.spring.crowdsorcery.portfolio;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/portfolio-module/api/v0/portfolios")
public class PortfolioRestApi {

    private final PortfolioService portfolioService;

    public PortfolioRestApi(PortfolioService portfolioService) {
        this.portfolioService = portfolioService;
    }

    @GetMapping
    public List<Portfolio> getAllPublicPortfolios() {
        return portfolioService.getAllPublicPortfolios();
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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addInvestment(@RequestBody Investment investment, Principal principal) {
        portfolioService.addInvestment(principal.getName(), investment);
    }
}
