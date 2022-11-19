package pl.wojtyna.trainings.spring.crowdsorcery.investor.service;

import org.springframework.data.annotation.Version;
import pl.wojtyna.trainings.spring.crowdsorcery.common.CrowdSorceryUser;
import pl.wojtyna.trainings.spring.crowdsorcery.investor.profile.InvestorProfile;

public record Investor(String id, String name, InvestorProfile investorProfile,
                       @Version int version) implements CrowdSorceryUser {
}
