# Filters

Our application is growing. We want to introduce some diagnostics and
minimal security.

Let's implement following requirements using filters:

1. We want to log any web request coming to our application
2. We want to reject some requests if they are considered
   dangerous. It's up to you to define what "dangerous" means.
3. The rejected requests need to be logged, too
4. Measure the execution time of any request
5. Investor REST API should be accessible only by privileged users. For the
   needs of this task, let's assume that privileged user is someone who
   knows a special secret.

Add some new REST endpoints (without any real logic) to demonstrate that our
new features work as intended.

### If that was too easy...

Implement [Basic Authentication](https://www.rfc-editor.org/rfc/rfc7617).
You don't need to cover all requirements. Just enough for the user to be
able to pass base64 encoded passwords. 