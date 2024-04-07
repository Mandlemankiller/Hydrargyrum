package cz.jeme.programu.hydrargyrum.element;

import net.kyori.adventure.key.Key;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public interface ElementManager {
    static @NotNull ElementManager elementManager() {
        return ElementManagerImpl.INSTANCE;
    }

    <T extends HydraElement> void registerElement(final @NotNull Class<T> clazz);

    void registerElements(final @NotNull ClassLoader classLoader, final @NotNull String pkg);

    default void registerElements(final @NotNull Class<? extends Plugin> pluginClass, final @NotNull String pkg) {
        registerElements(pluginClass.getClassLoader(), pkg);
    }

    default void registerElements(final @NotNull Class<? extends Plugin> pluginClass) {
        registerElements(pluginClass, pluginClass.getPackageName());
    }

    <T extends HydraElement> @NotNull Optional<T> elementByClass(final @NotNull Class<T> clazz);

    @NotNull
    Optional<HydraElement> elementByKey(final @NotNull Key key);

    @NotNull
    Optional<HydraElement> elementByKey(final @NotNull String key);
}