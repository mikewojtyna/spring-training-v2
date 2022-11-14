# Custom dependency injection container

`git checkout task-3-start`

So, we've configured the application directly in the main method previously.
It'd be nice, if we could have a solution which automatically resolves all
dependencies, instantiates the application and can be reused, right?

So... Let's do this!

1. Extract the configuration from main method to some external container,
   let's name it e.g. `NoSpringDependencyInjectionContainer`.
2. Now, allow registering types of components (e.g. `UnirestClient.class`)
   in the container
3. Allow to retrieve any component from the container, e.g. `T getComponent
   (Class<T> type)`
4. Finally, retrieve the `CliAdapter` in the main method and invoke it. So,
   the main method should now look something like this:

```java
import pl.wojtyna.trainings.spring.crowdsorcery.CliAdapter;

public class NoSpringApp {

    public static void main(String[] args) {
        NoSpringDependencyInjectionContainer container =
            NoSpringDependencyInjectionContainer.create();
        container.register(CliAdapter.class);
        container.register(UnirestClient.class);
        // and so on for all other components...
        CliAdapter cliAdapter = container.getComponent(CliAdapter.class);
        cliAdapter.run();
    }
}
```

### Let's complicate it a bit...

Make the container a bit more interesting

1. Let's imagine we want to support plug-ins in our system. Plug-in exposes
   its public API interface, but there can be multiple plug-in
   implementations available at runtime. So, add a method that automatically
   scans all components from the classpath, so you don't need to manually
   call `register` multiple times. **Note: you still need to manually register
   the root of the dependency graph**.
2. Ensure, that each component is instantiated only once. It's a kind of
   container-scoped singleton.
3. Ensure, that if component `A` depends on component `B` and component `C`
   also depends on component `B`, then **exactly the same** instance of `B` is
   used by both `A` and `C`.
4. Try to cover the scenario in which some component wants to receive new
   instances of its dependencies. How could you do that? Don't need to write
   code this time, just share your thoughts with me.