package net.vegandelight.extra;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.RegisterEvent;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

@Mod(VDExtraMod.modID)
public class VDExtraNeoforge {

    public static IEventBus modEventBus;

    public VDExtraNeoforge(IEventBus modEventBus) {
        VDExtraNeoforge.modEventBus = modEventBus;

        VDExtraMod.init(new VDNeoforgeRegistrar());
    }

    public static class VDNeoforgeRegistrar implements SimpleRegistrar {
        @Override
        public <T> Holder<T> register(@NotNull Registry<T> registry, ResourceLocation rl, @NotNull Supplier<T> value) {
            modEventBus.<RegisterEvent>addListener(event -> event.register(registry.key(), rl, value));
            return DeferredHolder.create(registry.key(), rl);
        }

        @Override
        public CreativeModeTab.Builder creativeTabBuilder() {
            return CreativeModeTab.builder();
        }
    }
}
