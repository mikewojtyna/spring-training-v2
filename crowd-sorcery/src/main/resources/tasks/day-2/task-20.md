# Investor profile updates

There's a new requirement: investors want to be able to update their
problems. Seems simple, right? There's one caveat, though. Not only
investors can modify their profile at the same time. Also, other superusers
can do that. Plus, investors can use multiple devices at the same time
(phones, browsers, native GUI clients...). To sum it up: there can be
multiple concurrent modifications happening at the same time. How do you
solve the lost update problem?

Your task is to implement a protection against lost updates when modifying
investor data.