# AOP

We'd like to know what commands are processed by CrowdSorcery and
what events are produced. We'd also like to know when CrowdSorcery
communicates with the outside world.

Your task is to implement the logging aspect, that:

1. Logs every request made to the `InvestorService`, `BorrowerService`,
   `PortfolioService` and `InvestmentSimulationRunner`.
2. Logs every published event
3. Logs requests made to the external services: `InvestorProfileService` and
   `MailService`