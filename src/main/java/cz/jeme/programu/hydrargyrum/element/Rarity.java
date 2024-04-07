package cz.jeme.programu.hydrargyrum.element;

import net.kyori.adventure.key.Keyed;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;


public interface Rarity extends Keyed {
    @NotNull
    String name();

    @NotNull
    Component component();
}