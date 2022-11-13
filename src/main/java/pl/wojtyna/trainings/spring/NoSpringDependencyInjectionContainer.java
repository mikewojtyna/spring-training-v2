package pl.wojtyna.trainings.spring;

import org.reflections.Reflections;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

import static org.reflections.scanners.Scanners.SubTypes;

public class NoSpringDependencyInjectionContainer {

    private final ConcurrentMap<Class<?>, ImplementationDetails> allDependencies = new ConcurrentHashMap<>();
    private final Reflections reflections = new Reflections("pl.wojtyna");
    private final ConcurrentMap<Class<?>, Object> cache = new ConcurrentHashMap<>();

    public static NoSpringDependencyInjectionContainer create() {
        return new NoSpringDependencyInjectionContainer();
    }

    public <C> Optional<C> getComponent(Class<C> typeOfComponent) {
        var maybeImplementationDetails = Optional.ofNullable(allDependencies.get(typeOfComponent));
        List<?> resolvedDependencies = maybeImplementationDetails.stream()
                                                                 .map(ImplementationDetails::dependencies)
                                                                 .flatMap(dependencies -> dependencies.stream()
                                                                                                      .flatMap(aClass -> getComponent(
                                                                                                          aClass).stream()))
                                                                 .toList();
        return maybeImplementationDetails.map(ImplementationDetails::concreteType)
                                         .map(concreteType -> cache.computeIfAbsent(concreteType, key -> {
                                             try {
                                                 return key.getConstructors()[0].newInstance(resolvedDependencies.toArray());
                                             }
                                             catch (InstantiationException | IllegalAccessException |
                                                    InvocationTargetException e) {
                                                 throw new RuntimeException(e);
                                             }
                                         }))
                                         .map(typeOfComponent::cast);
    }

    public <C> void register(Class<C> typeOfComponent, Class<? super C> asType) {
        var constructors = typeOfComponent.getConstructors();
        if (constructors.length != 1) {
            throw new RuntimeException("There must be only one constructor of given component.");
        }
        putDependency(typeOfComponent, asType, constructors);
    }

    private void putDependency(Class<?> typeOfComponent, Class<?> asType, Constructor<?>[] constructors) {
        List<Class<?>> constructorDependencies = Arrays.stream(constructors[0].getParameters())
                                                       .map(Parameter::getType)
                                                       .collect(Collectors.toCollection(ArrayList::new));
        tryToAutomaticallyRegisterDependencies(constructorDependencies);
        allDependencies.put(asType, new ImplementationDetails(typeOfComponent, constructorDependencies));
    }

    private void tryToAutomaticallyRegisterDependencies(List<Class<?>> dependencies) {
        for (Class<?> dependency : dependencies) {
            Set<Class<?>> subTypes = reflections.get(SubTypes.of(dependency).asClass());
            subTypes.stream()
                    .findAny()
                    .ifPresentOrElse(anySubType -> putDependency(anySubType, dependency, anySubType.getConstructors()),
                                     () -> putDependency(dependency, dependency, dependency.getConstructors()));
        }
    }

    private record ImplementationDetails(Class<?> concreteType, List<Class<?>> dependencies) {}

}
