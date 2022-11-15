# Testing support in Spring

Try to replace

```
@SpringBootTest
@AutoConfigureMockMvc
```

annotations at `RegisterAndFetchInvestorRestApiIT` with `@WebMvcTest`.

Did tests pass? Why?

This task is simple: make this test pass by using `@WebMvcTest`.

Now, again replace

```
@SpringBootTest
@AutoConfigureMockMvc
```

with `@WebMvcTest`, but this time at
`InvestorSecretFilterIsAppliedOnlyToInvestorModuleTest`. Try to make it pass.
Hint: just try. Your task is to answer why tests are not passing.