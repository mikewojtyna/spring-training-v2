package pl.wojtyna.trainings.spring.examples.beans.crowdsorcery.notification;

import pl.wojtyna.trainings.spring.examples.beans.crowdsorcery.investor.service.Investor;

public interface EmailAddressResolver {

    String resolveAddressOf(Investor investor);
}
