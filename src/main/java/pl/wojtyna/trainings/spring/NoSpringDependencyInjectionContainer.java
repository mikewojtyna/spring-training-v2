package pl.wojtyna.trainings.spring;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

public class NoSpringDependencyInjectionContainer {

    private final ConcurrentMap<Class<?>, ImplementationDetails> allDependencies = new ConcurrentHashMap<>();

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
        return maybeImplementationDetails.map(ImplementationDetails::concreteType).map(concreteType -> {
            try {
                return concreteType.getConstructors()[0].newInstance(resolvedDependencies.toArray());
            }
            catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }).map(typeOfComponent::cast);
    }

    public <C> void register(Class<C> typeOfComponent, Class<? super C> asType) {
        var constructors = typeOfComponent.getConstructors();
        if (constructors.length != 1) {
            throw new RuntimeException("There must be only one constructor of given component.");
        }
        List<Class<?>> constructorDependencies = Arrays.stream(constructors[0].getParameters())
                                                       .map(Parameter::getType)
                                                       .collect(Collectors.toCollection(ArrayList::new));
        allDependencies.put(asType, new ImplementationDetails(typeOfComponent, constructorDependencies));
    }

    private record ImplementationDetails(Class<?> concreteType, List<Class<?>> dependencies) {}

}
