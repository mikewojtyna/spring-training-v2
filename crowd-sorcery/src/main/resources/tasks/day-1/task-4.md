# Use Spring to inject all dependencies

`git checkout task-4-start`

Create a single context using `@SpringBootApplication` and instantiate
`CliAdapter` using Spring. Basically, do the same stuff as in previous task,
but using a Spring container.

1. Use `AnnotationConfigApplicationContext` and manually register all beans
2. Use `AnnotationConfigApplicationContext` and scan for components by using
   `@Component` annotations
3. Use `@SpringBootApplication` to bootstrap the context using Spring Boot