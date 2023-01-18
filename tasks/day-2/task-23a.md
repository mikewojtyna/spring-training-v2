# Image authentication

Secure investor portfolio CRUD & hide operations with custom authentication based on photos of identity documents.

For the needs of this task, let's simply assume that `IdDocument` uses `String` as content instead of `byte[]` and looks like this:

```java
record IdDocument(String content) {

}
```

Your task is to:

1. Create your custom filter. For the needs of simplicity, let's assume that identity document photo is placed in a `Id-Document` header.
2. Create your custom `AuthenticationProvider` to handle identity document authentication. You can assume some very simple authentication rules, e.g. supporting only some hardcoded acceptable identity document.
3. Register this custom authentication provider
4. Write tests to confirm your authentication works