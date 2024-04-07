package cz.jeme.programu.hydrargyrum.element;

import net.kyori.adventure.key.Key;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public abstract class HydraItem extends HydraElement {
    protected final @NotNull ItemStack item;

    protected HydraItem(final @NotNull Key key,
                        final @NotNull Material material,
                        final @NotNull Rarity rarity,
                        final int customModelData) {
        super(key);
        item = new ItemStack(material);
        item.editMeta(meta -> {
            meta.setCustomModelData(customModelData);
            HydraElement.KEY.write(meta, key.asString());
        });
    }

    public final @NotNull ItemStack item() {
        return item;
    }
}