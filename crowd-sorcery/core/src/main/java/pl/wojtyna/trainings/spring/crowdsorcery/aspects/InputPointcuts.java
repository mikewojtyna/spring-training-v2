package pl.wojtyna.trainings.spring.crowdsorcery.aspects;

import org.aspectj.lang.annotation.Pointcut;

public interface InputPointcuts {

    @Pointcut("execution(* pl.wojtyna.trainings.spring.crowdsorcery.investor.service.InvestorService.*(..))")
    static void investorService() {}

    @Pointcut("execution(* pl.wojtyna.trainings.spring.crowdsorcery.borrower.BorrowerService.*(..))")
    static void borrowerService() {}

    @Pointcut("execution(* pl.wojtyna.trainings.spring.crowdsorcery.portfolio.PortfolioService.*(..))")
    static void portfolioService() {}

    @Pointcut("investorService() || borrowerService() || portfolioService()")
    static void allInputs() {}
}
