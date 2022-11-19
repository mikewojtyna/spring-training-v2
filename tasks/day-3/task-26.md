# Custom Spring Boot starters

Create Spring Boot starter to automatically register common Crowd Sorcery
libraries. Currently, `mail` and `rest` modules are good candidates.

Your task is to:

1. Create a `crowd-sorcery-libs` starter.
2. The starter should register default beans only, if there are no beans of
   the same type already defined.
3. Remove spring configuration from `mail` and `rest` modules.