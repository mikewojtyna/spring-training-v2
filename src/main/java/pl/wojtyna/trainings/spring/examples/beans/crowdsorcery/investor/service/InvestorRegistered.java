package pl.wojtyna.trainings.spring.examples.beans.crowdsorcery.investor.service;

import pl.wojtyna.trainings.spring.examples.beans.crowdsorcery.eventpublisher.Event;

public record InvestorRegistered(Investor investor) implements Event {
}
