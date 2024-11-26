package net.vegandelight.extra;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public interface VDExtraPlatform {
    default <T> Holder<T> register(@NotNull Registry<T> registry, ResourceLocation rl, @NotNull Supplier<T> value) {
        var obj = value.get();
        var holder = Holder.direct(obj);
        Registry.register(registry, rl, obj);
        return holder;
    }

    CreativeModeTab.Builder creativeTabBuilder();
}
