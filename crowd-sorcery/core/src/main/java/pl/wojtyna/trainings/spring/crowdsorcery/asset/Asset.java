package pl.wojtyna.trainings.spring.crowdsorcery.asset;

import lombok.Data;
import pl.wojtyna.trainings.spring.crowdsorcery.borrower.Borrower;

import java.util.Set;

@Data
public class Asset {

    private String id;
    private String name;
    private FundingGoal fundingGoal;
    private Money raised;
    private Borrower author;
    private boolean funded;
    private Set<Investment> investments;
    private ReturnRate returnRate;
}
