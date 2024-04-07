package cz.jeme.programu.hydrargyrum.persistence;

import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataHolder;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

record DataStorageImpl<P, C>(
        @NotNull NamespacedKey key,
        @NotNull PersistentDataType<P, C> type
) implements DataStorage<P, C> {
    @Override
    public boolean has(final @Nullable PersistentDataContainer container) {
        return container != null && container.has(key, type);
    }

    @Override
    public boolean has(final @Nullable PersistentDataHolder holder) {
        return holder != null && has(holder.getPersistentDataContainer());
    }

    @Override
    public boolean has(final @Nullable ItemStack item) {
        return item != null && has(item.getItemMeta());
    }

    @Override
    public @NotNull Optional<C> read(final @Nullable PersistentDataContainer container) {
        return container == null ? Optional.empty() : Optional.ofNullable(container.get(key, type));
    }

    @Override
    public @NotNull Optional<C> read(final @Nullable PersistentDataHolder holder) {
        return holder == null ? Optional.empty() : read(holder.getPersistentDataContainer());
    }

    @Override
    public @NotNull Optional<C> read(final @Nullable ItemStack item) {
        return item == null || !item.hasItemMeta() ? Optional.empty() : read(item.getItemMeta());
    }

    @Override
    public void write(final @NotNull PersistentDataContainer container, @NotNull final C value) {
        container.set(key, type, value);
    }

    @Override
    public void write(final @NotNull PersistentDataHolder holder, @NotNull final C value) {
        write(holder.getPersistentDataContainer(), value);
    }

    @Override
    public void write(final @NotNull ItemStack item, @NotNull final C value) {
        item.editMeta(meta -> write(meta, value));
    }

    @Override
    public @NotNull Optional<C> delete(final @NotNull PersistentDataContainer container) {
        final Optional<C> value = read(container);
        if (value.isPresent()) container.remove(key);
        return value;
    }

    @Override
    public @NotNull Optional<C> delete(final @NotNull PersistentDataHolder holder) {
        return delete(holder.getPersistentDataContainer());
    }

    @Override
    public @NotNull Optional<C> delete(final @NotNull ItemStack item) {
        final ItemMeta meta = item.getItemMeta();
        final Optional<C> value = delete(meta);
        if (value.isPresent()) item.setItemMeta(meta);
        return value;
    }
}