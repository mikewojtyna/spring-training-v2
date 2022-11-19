package pl.wojtyna.trainings.spring.crowdsorcery.security;

import org.springframework.context.event.EventListener;
import pl.wojtyna.trainings.spring.crowdsorcery.investor.service.Investor;
import pl.wojtyna.trainings.spring.crowdsorcery.investor.service.InvestorRegistered;

public class IdentityCreatorEventListener {

    private final IdentityRepository identityRepository;
    private final PasswordGenerator passwordGenerator;

    public IdentityCreatorEventListener(IdentityRepository identityRepository, PasswordGenerator passwordGenerator) {
        this.identityRepository = identityRepository;
        this.passwordGenerator = passwordGenerator;
    }

    @EventListener
    public void when(InvestorRegistered investorRegistered) {
        createIdentityOf(investorRegistered.investor());
    }

    private void createIdentityOf(Investor investor) {
        identityRepository.save(new CrowdSorceryIdentity(investor.id(), passwordGenerator.password()));
    }
}
