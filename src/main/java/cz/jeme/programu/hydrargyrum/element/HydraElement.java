package cz.jeme.programu.hydrargyrum.element;

import cz.jeme.programu.hydrargyrum.Hydrargyrum;
import cz.jeme.programu.hydrargyrum.persistence.DataStorage;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.key.Keyed;
import org.bukkit.NamespacedKey;
import org.jetbrains.annotations.NotNull;

public abstract class HydraElement implements Keyed {
    public static final @NotNull DataStorage<String, String> KEY = DataStorage.stringStorage(
            new NamespacedKey(Hydrargyrum.instance(), "data.element_key")
    );

    protected final @NotNull Key key;

    protected HydraElement(final @NotNull Key key) {
        this.key = key;
    }

    @Override
    public final @NotNull Key key() {
        return key;
    }
}