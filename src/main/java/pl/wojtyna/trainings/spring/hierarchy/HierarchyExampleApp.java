package pl.wojtyna.trainings.spring.hierarchy;

import org.springframework.boot.builder.SpringApplicationBuilder;
import pl.wojtyna.trainings.spring.hierarchy.root.RootContext;
import pl.wojtyna.trainings.spring.hierarchy.root.child1.RootChild1Context;
import pl.wojtyna.trainings.spring.hierarchy.root.child2.RootChild2Context;
import pl.wojtyna.trainings.spring.hierarchy.root.child2.child1.RootChild2Child1Context;

public class HierarchyExampleApp {

    public static void main(String[] args) {
        new SpringApplicationBuilder()
            .parent(RootContext.class)
            .child(RootChild1Context.class)
            .sibling(RootChild2Context.class)
            .child(RootChild2Child1Context.class)
            .run(args);
    }
}
