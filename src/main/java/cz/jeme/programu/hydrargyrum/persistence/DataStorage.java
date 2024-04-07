package cz.jeme.programu.hydrargyrum.persistence;

import net.kyori.adventure.key.Key;
import net.kyori.adventure.key.Keyed;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataHolder;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public interface DataStorage<P, C> extends Keyed {
    static <P, C> @NotNull DataStorage<P, C> storage(final @NotNull Key key,
                                                     final @NotNull PersistentDataType<P, C> type) {
        final NamespacedKey namespacedKey = key instanceof NamespacedKey
                ? (NamespacedKey) key
                : new NamespacedKey(key.namespace(), key.value());
        return new DataStorageImpl<>(namespacedKey, type);
    }

    static @NotNull DataStorage<Byte, Byte> byteStorage(final @NotNull NamespacedKey key) {
        return DataStorage.storage(key, PersistentDataType.BYTE);
    }

    static @NotNull DataStorage<Short, Short> shortStorage(final @NotNull NamespacedKey key) {
        return DataStorage.storage(key, PersistentDataType.SHORT);
    }

    static @NotNull DataStorage<Integer, Integer> integerStorage(final @NotNull NamespacedKey key) {
        return DataStorage.storage(key, PersistentDataType.INTEGER);
    }

    static @NotNull DataStorage<Long, Long> longStorage(final @NotNull NamespacedKey key) {
        return DataStorage.storage(key, PersistentDataType.LONG);
    }

    static @NotNull DataStorage<Float, Float> floatStorage(final @NotNull NamespacedKey key) {
        return DataStorage.storage(key, PersistentDataType.FLOAT);
    }

    static @NotNull DataStorage<Double, Double> doubleStorage(final @NotNull NamespacedKey key) {
        return DataStorage.storage(key, PersistentDataType.DOUBLE);
    }

    static @NotNull DataStorage<Byte, Boolean> booleanStorage(final @NotNull NamespacedKey key) {
        return DataStorage.storage(key, PersistentDataType.BOOLEAN);
    }

    static @NotNull DataStorage<String, String> stringStorage(final @NotNull NamespacedKey key) {
        return DataStorage.storage(key, PersistentDataType.STRING);
    }

    static @NotNull DataStorage<byte[], byte[]> byteArrayStorage(final @NotNull NamespacedKey key) {
        return DataStorage.storage(key, PersistentDataType.BYTE_ARRAY);
    }

    static @NotNull DataStorage<int[], int[]> intArrayStorage(final @NotNull NamespacedKey key) {
        return DataStorage.storage(key, PersistentDataType.INTEGER_ARRAY);
    }

    static @NotNull DataStorage<long[], long[]> longArrayStorage(final @NotNull NamespacedKey key) {
        return DataStorage.storage(key, PersistentDataType.LONG_ARRAY);
    }

    static @NotNull DataStorage<PersistentDataContainer, PersistentDataContainer> tagContainerStorage(final @NotNull NamespacedKey key) {
        return DataStorage.storage(key, PersistentDataType.TAG_CONTAINER);
    }

    @NotNull
    PersistentDataType<P, C> type();

    boolean has(final @Nullable PersistentDataContainer container);

    boolean has(final @Nullable PersistentDataHolder holder);

    boolean has(final @Nullable ItemStack item);

    @NotNull
    Optional<C> read(final @Nullable PersistentDataContainer container);

    @NotNull
    Optional<C> read(final @Nullable PersistentDataHolder holder);

    @NotNull
    Optional<C> read(final @Nullable ItemStack item);

    void write(final @NotNull PersistentDataContainer container, final @NotNull C value);

    void write(final @NotNull PersistentDataHolder holder, final @NotNull C value);

    void write(final @NotNull ItemStack item, final @NotNull C value);

    @NotNull
    Optional<C> delete(final @NotNull PersistentDataContainer container);

    @NotNull
    Optional<C> delete(final @NotNull PersistentDataHolder holder);

    @NotNull
    Optional<C> delete(final @NotNull ItemStack item);
}