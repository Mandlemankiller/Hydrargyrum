package cz.jeme.programu.hydrargyrum;

import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class Hydrargyrum extends JavaPlugin {
    private static @Nullable Hydrargyrum instance;

    public Hydrargyrum() {
        if (Hydrargyrum.instance != null)
            throw new IllegalStateException("Hydrargyrum can only be instantiated once!");
        Hydrargyrum.instance = this;
    }

    @Override
    public void onEnable() {
    }

    @Override
    public void onDisable() {
    }

    public static synchronized @NotNull Hydrargyrum instance() {
        if (Hydrargyrum.instance == null)
            throw new IllegalStateException("Hydrargyrum must be instantiated before trying to access instance!");
        return Hydrargyrum.instance;
    }
}