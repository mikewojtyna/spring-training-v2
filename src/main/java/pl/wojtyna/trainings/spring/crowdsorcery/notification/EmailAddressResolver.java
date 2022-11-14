package pl.wojtyna.trainings.spring.crowdsorcery.notification;

import pl.wojtyna.trainings.spring.crowdsorcery.investor.Investor;

public interface EmailAddressResolver {

    String resolveAddressOf(Investor investor);
}
