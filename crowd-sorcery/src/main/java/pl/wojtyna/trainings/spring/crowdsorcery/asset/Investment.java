package pl.wojtyna.trainings.spring.crowdsorcery.asset;

import lombok.Data;
import pl.wojtyna.trainings.spring.crowdsorcery.investor.service.Investor;

@Data
public class Investment {

    private Investor investor;
    private Money amountInvested;
}
