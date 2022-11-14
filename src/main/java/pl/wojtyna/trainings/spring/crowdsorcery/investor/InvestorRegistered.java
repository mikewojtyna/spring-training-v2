package pl.wojtyna.trainings.spring.crowdsorcery.investor;

import pl.wojtyna.trainings.spring.crowdsorcery.eventpublisher.Event;

public record InvestorRegistered(Investor investor) implements Event {
}
