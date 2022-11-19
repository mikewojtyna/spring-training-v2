package pl.wojtyna.trainings.spring.examples.beans.crowdsorcery.notification;

import org.springframework.stereotype.Component;
import pl.wojtyna.trainings.spring.examples.beans.crowdsorcery.investor.service.Investor;

@Component
public class StubbedEmailAddressResolver implements EmailAddressResolver {

    @Override
    public String resolveAddressOf(Investor investor) {
        return "mike@wojtyna.pl";
    }
}
