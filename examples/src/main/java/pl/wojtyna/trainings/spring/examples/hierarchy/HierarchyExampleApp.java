package pl.wojtyna.trainings.spring.examples.hierarchy;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import pl.wojtyna.trainings.spring.examples.hierarchy.root.RootContext;
import pl.wojtyna.trainings.spring.examples.hierarchy.root.child1.RootChild1Context;
import pl.wojtyna.trainings.spring.examples.hierarchy.root.child2.RootChild2Context;
import pl.wojtyna.trainings.spring.examples.hierarchy.root.child2.child1.RootChild2Child1Context;

public class HierarchyExampleApp {

    public static void main(String[] args) {
        new SpringApplicationBuilder()
            .parent(RootContext.class)
            .web(WebApplicationType.NONE)
            .child(RootChild1Context.class)
            .web(WebApplicationType.NONE)
            .sibling(RootChild2Context.class)
            .web(WebApplicationType.NONE)
            .child(RootChild2Child1Context.class)
            .web(WebApplicationType.NONE)
            .run(args);
    }
}
