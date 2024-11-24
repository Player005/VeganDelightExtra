package net.vegandelight.extra;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public interface SimpleRegistrar {
    default <T> Holder<T> register(Registry<T> registry, ResourceLocation rl, @NotNull Supplier<T> value) {
        return Registry.registerForHolder(registry, rl, value.get());
    }

    CreativeModeTab.Builder creativeTabBuilder();
}
