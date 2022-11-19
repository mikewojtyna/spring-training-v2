# MongoDb persistence

We decided we want to switch to a document database. When working with
documents, there's
no [Object–relational impedance mismatch](https://en.wikipedia.org/wiki/Object%E2%80%93relational_impedance_mismatch)
. Therefore,
mapping is much easier and MongoDb is a good choice for building prototypes.

Your task is to create a MongoDb implementation of your repositories. You
might use any approach: implement your repositories directly using low-level
MongoDb Java client, you can use `MongoTemplate` or Spring Data repositories.
The choice is yours.