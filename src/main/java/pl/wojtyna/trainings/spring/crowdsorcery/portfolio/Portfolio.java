package pl.wojtyna.trainings.spring.crowdsorcery.portfolio;

import com.google.common.collect.Iterables;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import pl.wojtyna.trainings.spring.crowdsorcery.common.Versionable;
import pl.wojtyna.trainings.spring.crowdsorcery.investor.service.Investor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public record Portfolio(@Id String investorId, List<Investment> investments, boolean isPublic,
                        @Version int version) implements Versionable {

    public static Portfolio emptyPublicPortfolioOf(Investor investor) {
        return new Portfolio(investor.id(), List.of(), true, 0);
    }

    public Portfolio publish() {
        return new Portfolio(investorId, investments, true, version);
    }

    public Portfolio hide() {
        return new Portfolio(investorId, investments, false, version);
    }

    public Portfolio addInvestment(Investment investment) {
        var updatedInvestments = new ArrayList<>(investments);
        updatedInvestments.add(investment);
        return new Portfolio(investorId, updatedInvestments, isPublic, version);
    }

    public Portfolio upsertInvestment(Investment investment) {
        var indexOfMatchingInvestment = Iterables.indexOf(investments, it -> Objects.equals(it.id(), investment.id()));
        if (indexOfMatchingInvestment != -1) {
            var updatedInvestments = new ArrayList<>(investments);
            updatedInvestments.set(indexOfMatchingInvestment, investment);
            return new Portfolio(investorId, updatedInvestments, isPublic, version);
        }
        return this;
    }

    public Portfolio deleteInvestment(String investmentId) {
        var updatedInvestments = new ArrayList<>(investments);
        updatedInvestments.removeIf(investment -> Objects.equals(investment.id(), investmentId));
        return new Portfolio(investorId, updatedInvestments, isPublic, version);
    }
}
