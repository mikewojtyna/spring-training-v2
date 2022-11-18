package pl.wojtyna.trainings.spring.crowdsorcery.mail;

import pl.wojtyna.trainings.spring.crowdsorcery.common.CrowdSorceryUser;

public interface EmailAddressResolver {

    String resolveAddressOf(CrowdSorceryUser user);
}
