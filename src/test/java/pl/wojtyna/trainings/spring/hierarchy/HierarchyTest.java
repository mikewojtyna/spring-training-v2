package pl.wojtyna.trainings.spring.hierarchy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.boot.builder.SpringApplicationBuilder;
import pl.wojtyna.trainings.spring.hierarchy.root.CoreBeanOne;
import pl.wojtyna.trainings.spring.hierarchy.root.CoreBeanTwo;
import pl.wojtyna.trainings.spring.hierarchy.root.RootContext;
import pl.wojtyna.trainings.spring.hierarchy.root.child1.RootChild1BeanOne;
import pl.wojtyna.trainings.spring.hierarchy.root.child1.RootChild1BeanTwo;
import pl.wojtyna.trainings.spring.hierarchy.root.child1.RootChild1Context;
import pl.wojtyna.trainings.spring.hierarchy.root.child2.RootChild2Bean;
import pl.wojtyna.trainings.spring.hierarchy.root.child2.RootChild2Context;
import pl.wojtyna.trainings.spring.hierarchy.root.child2.child1.RootChild2Child1BeanOne;
import pl.wojtyna.trainings.spring.hierarchy.root.child2.child1.RootChild2Child1BeanTwo;
import pl.wojtyna.trainings.spring.hierarchy.root.child2.child1.RootChild2Child1Context;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("Show how hierarchical contexts work")
public class HierarchyTest {

    @DisplayName("RootChild2Child1Context can access all beans except beans defined by RootChild1Context")
    @Test
    void test0() {
        var context = new SpringApplicationBuilder()
            .parent(RootContext.class)
            .child(RootChild1Context.class)
            .sibling(RootChild2Context.class)
            .child(RootChild2Child1Context.class)
            .run();

        assertThat(context.getBean(CoreBeanOne.class)).isNotNull();
        assertThat(context.getBean(CoreBeanTwo.class)).isNotNull();
        assertThat(context.getBean(RootChild2Bean.class)).isNotNull();
        assertThat(context.getBean(RootChild2Child1BeanOne.class)).isNotNull();
        assertThat(context.getBean(RootChild2Child1BeanTwo.class)).isNotNull();

        assertThatThrownBy(() -> context.getBean(RootChild1BeanOne.class)).isInstanceOf(NoSuchBeanDefinitionException.class);
        assertThatThrownBy(() -> context.getBean(RootChild1BeanTwo.class)).isInstanceOf(NoSuchBeanDefinitionException.class);
    }

    @DisplayName("RootChild2Context cannot access beans defined by RootChild1Context, but can access parent beans")
    @Test
    void test1() {
        var context = new SpringApplicationBuilder()
            .parent(RootContext.class)
            .child(RootChild1Context.class)
            .sibling(RootChild2Context.class)
            .run();

        assertThatThrownBy(() -> context.getBean(RootChild1BeanOne.class)).isInstanceOf(NoSuchBeanDefinitionException.class);
        assertThatThrownBy(() -> context.getBean(RootChild1BeanTwo.class)).isInstanceOf(NoSuchBeanDefinitionException.class);

        assertThat(context.getBean(CoreBeanOne.class)).isNotNull();
        assertThat(context.getBean(CoreBeanTwo.class)).isNotNull();
        assertThat(context.getBean(RootChild2Bean.class)).isNotNull();
    }
}
