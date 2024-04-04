package cz.jeme.programu.hydrargyrum.element;

import cz.jeme.programu.hydrargyrum.Hydrargyrum;
import cz.jeme.programu.hydrargyrum.persistence.DataStorage;
import org.bukkit.NamespacedKey;
import org.jetbrains.annotations.NotNull;

public abstract class HydraElement {
    public static final @NotNull DataStorage<String, String> KEY = DataStorage.stringStorage(
            new NamespacedKey(Hydrargyrum.instance(), "data.element_key")
    );
    protected final @NotNull NamespacedKey key;

    protected HydraElement(final @NotNull NamespacedKey key) {
        this.key = key;
    }

    public final @NotNull NamespacedKey key() {
        return key;
    }
}