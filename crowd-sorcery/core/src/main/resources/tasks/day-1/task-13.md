# Async time

We want to introduce investment simulations. These are long-running tasks,
which can effectively lead to quickly consuming all available threads of the web
server thread pool.

Your task is to create the investment simulation runner and make sure it's
executed on a separate background threads pool, so web requests are not
blocking anymore.

1. Create asynchronous investment simulation runner
2. Configure investment simulation runner to use a separate thread pool
3. Expose investment simulation runner as non-blocking REST API
4. Handle exceptions thrown by async components

### If that was too easy...

How do you return the result of a long-running non-blocking operation to the
REST client? Find a way to allow clients of our REST APIs to receive the
results of non-blocking investment simulation operations.