package pl.wojtyna.trainings.spring.crowdsorcery.investor.profile;

import lombok.Data;
import pl.wojtyna.trainings.spring.crowdsorcery.investor.service.InvestorProfileService;
import pl.wojtyna.trainings.spring.crowdsorcery.rest.RestClient;

import java.util.Optional;

public class RestClientInvestorProfileService implements InvestorProfileService {

    private final RestClient restClient;

    public RestClientInvestorProfileService(RestClient restClient) {
        this.restClient = restClient;
    }

    @Override
    public Optional<InvestorProfile> fetchById(String id) {
        return restClient.get(id, InvestorProfileDto.class)
                         .map(investorProfileDto -> new InvestorProfile(investorProfileDto.getScore(),
                                                                        investorProfileDto.isVip(),
                                                                        investorProfileDto.getReferralLink()));
    }

    @Data
    private static class InvestorProfileDto {

        int score;
        boolean isVip;
        String referralLink;
    }
}
