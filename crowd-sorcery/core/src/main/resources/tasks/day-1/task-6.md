# Modules in CrowdSorcery

We want to modularize our application a bit. Let's try to use Spring
hierarchical contexts to achieve that.

Your task is to create three modules and define relationships between them.

1. Investor registration module (you can extract it from the existing codebase)
2. Notifications module that will send emails to users
3. Generic REST client module

Now, make sure that beans registered in Notifications module cannot be
accessed by other modules.

Finally, change the investor registration module to publish an event when
investor is registered. Then, the Notification module should email the user who
registered the investor account.

Some questions

1. Have you encountered any issues?
2. Can you explain the design of your modules?
3. Are all modules separate contexts?
4. Are Spring hierarchical contexts good candidates for modules?