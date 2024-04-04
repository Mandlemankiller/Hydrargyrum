package cz.jeme.programu.hydrargyrum.element;

import io.github.classgraph.ClassGraph;
import io.github.classgraph.ScanResult;
import org.bukkit.NamespacedKey;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

enum ElementManagerImpl implements ElementManager {
    INSTANCE;

    private final @NotNull Map<String, HydraElement> keyedElements = new HashMap<>();
    private final @NotNull Map<Class<? extends HydraElement>, HydraElement> typedElements = new HashMap<>();

    @Override
    public <T extends HydraElement> void registerElement(final @NotNull Class<T> clazz) {
        try {
            final Constructor<T> constructor = clazz.getConstructor();
            constructor.setAccessible(true);
            final T element = constructor.newInstance();
            final String key = element.key().asString();
            if (keyedElements.containsKey(key))
                throw new IllegalArgumentException("An element with this key already exists: " + key);
            keyedElements.put(key, element);
            typedElements.put(clazz, element);
        } catch (InstantiationException e) {
            throw new RuntimeException("Could not initialize element: " + clazz.getName(), e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Could not access constructor in element: " + clazz.getName(), e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException("The constructor threw an exception in element: " + clazz.getName(), e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("Could not find a parameterless constructor in element: " + clazz.getName(), e);
        }
    }

    @Override
    public void registerElements(final @NotNull ClassLoader classLoader, final @NotNull String pkg) {
        try (final ScanResult result = new ClassGraph()
                .acceptPackages(pkg)
                .overrideClassLoaders(classLoader)
                .ignoreParentClassLoaders()
                .scan()) {
            final List<Class<?>> classes = result.getSubclasses(HydraElement.class)
                    .filter(clazz -> !clazz.isAbstract())
                    .loadClasses();

            for (final Class<?> clazz : classes) {
                final @SuppressWarnings("unchecked") Class<HydraElement> elementClass = (Class<HydraElement>) clazz;
                registerElement(elementClass);
            }
        }
    }

    @Override
    public <T extends HydraElement> @NotNull Optional<T> elementByClass(final @NotNull Class<T> clazz) {
        if (!typedElements.containsKey(clazz)) return Optional.empty();
        final @SuppressWarnings("unchecked") T typedElement = (T) typedElements.get(clazz);
        return Optional.of(typedElement);
    }

    @Override
    public @NotNull Optional<HydraElement> elementByKey(final @NotNull NamespacedKey key) {
        return elementByKey(key.asString());
    }

    @Override
    public @NotNull Optional<HydraElement> elementByKey(final @NotNull String key) {
        return Optional.ofNullable(keyedElements.get(key));
    }
}
