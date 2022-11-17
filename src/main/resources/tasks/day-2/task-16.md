# Simple JDBC persistence

We want investors and borrowers to be persisted in the database.

Your task is to populate some SQL database (you can use embedded H2) with
initial data (one investor and one borrower) and persist new registered
investors and borrowers.

**Note**: you need to write the registration code for borrowers, too.

Let's start with simple approach using simple Spring JDBC

1. Create the db schema
2. Populate the db with some initial data (one investor and one borrower is
   enough)
3. Make sure initial data is loaded
4. Persist all new investors and borrowers
5. Allow to load investors and borrowers from the db using investor and
   borrower services