package pl.wojtyna.trainings.spring.crowdsorcery.investor.service;

import pl.wojtyna.trainings.spring.crowdsorcery.audit.AuditLog;
import pl.wojtyna.trainings.spring.crowdsorcery.eventpublisher.Event;
import pl.wojtyna.trainings.spring.crowdsorcery.eventpublisher.EventPublisher;
import pl.wojtyna.trainings.spring.crowdsorcery.investor.repository.InvestorRepository;

import java.util.List;

public class InvestorService {

    private final InvestorRepository investorRepository;
    private final InvestorProfileService investorProfileService;
    private final EventPublisher eventPublisher;
    private final AuditLog auditLog;

    public InvestorService(InvestorRepository investorRepository, InvestorProfileService investorProfileService,
                           EventPublisher eventPublisher) {
        this.investorRepository = investorRepository;
        this.investorProfileService = investorProfileService;
        this.eventPublisher = eventPublisher;
        auditLog = null;
    }

    public InvestorService(InvestorRepository investorRepository, InvestorProfileService investorProfileService,
                           EventPublisher eventPublisher, AuditLog auditLog) {
        this.investorRepository = investorRepository;
        this.investorProfileService = investorProfileService;
        this.eventPublisher = eventPublisher;
        this.auditLog = auditLog;
    }

    public void register(RegisterInvestor command) {
        var investor = new Investor(command.id(),
                                    command.name(),
                                    investorProfileService.fetchById(command.id()).orElseThrow());
        investorRepository.save(investor);
        // we should use outbox pattern here, but let's leave it like this for the sake of the simplicity
        Event event = new InvestorRegistered(investor);
        eventPublisher.publish(event);
        tryToRecordInAuditLog(command, event);
    }

    public List<Investor> findAll() {
        return investorRepository.findAll();
    }

    private void tryToRecordInAuditLog(RegisterInvestor command, Event event) {
        if (auditLog != null) {
            auditLog.record(command, event, getClass());
        }
    }
}
