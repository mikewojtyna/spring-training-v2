package pl.wojtyna.trainings.spring.crowdsorcery.aspects;

import org.aspectj.lang.annotation.Pointcut;

public interface OutputPointcuts {

    @Pointcut("execution(* pl.wojtyna.trainings.spring.crowdsorcery.investor.service.InvestorProfileService.*(..))")
    static void investorProfileService() {}

    @Pointcut("execution(* pl.wojtyna.trainings.spring.crowdsorcery.eventpublisher.EventPublisher.*(..))")
    static void eventPublisher() {}

    @Pointcut("investorProfileService() || eventPublisher()")
    static void allOutputs() {}
}
