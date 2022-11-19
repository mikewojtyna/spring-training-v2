# Profiles and conditionals

Remember the problem we had with notification service using mail sender?
It's time to finally tackle this.

Your task is to use different implementations of `NotificationService`.

1. Use stubbed notification service (which does nothing) when running tests
   or when we want to run a local demo for our clients
2. Use mail-backed notification service when running on production
    - Try to pass mail properties at runtime, so you don't expose credentials
      in your files, e.g. this way `mvn spring-boot:run -Dspring-boot.run.
      arguments=--server.port=8085` or via your IDE

### If that was too easy...

Imagine that we need an audit log for `InvestorService`. However, audit log
service is not supported on every environment. Make sure that
`InvestorService` uses new `AuditLog` service only when audit log is
supported at the given **runtime** environment. It's up to you to define
what are the conditions whether audit log is supported.