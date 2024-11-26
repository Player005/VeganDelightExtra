package net.vegandelight.extra;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.RegisterEvent;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;
import java.util.function.Supplier;

@Mod(VDExtraMod.modID)
public class VDExtraNeoforge {

    public static IEventBus modEventBus;

    public VDExtraNeoforge(IEventBus modEventBus) {
        VDExtraNeoforge.modEventBus = modEventBus;

        VDExtraMod.init(new VDNeoforgeRegistrar());
    }

    public static class VDNeoforgeRegistrar implements VDExtraPlatform {
        @Override
        public <T> Holder<T> register(@NotNull Registry<T> registry, ResourceLocation rl, @NotNull Supplier<T> value) {
            modEventBus.<RegisterEvent>addListener(event -> event.register(registry.key(), rl, value));
            return DeferredHolder.create(registry.key(), rl);
        }

        @Override
        public CreativeModeTab.Builder creativeTabBuilder() {
            return CreativeModeTab.builder();
        }

        @Override
        public void onServerStart(Consumer<MinecraftServer> consumer) {
            VDExtraNeoforge.modEventBus.addListener(
                    ServerStartingEvent.class,
                    event -> consumer.accept(event.getServer())
            );
        }

        @Override
        public void onClientStart(Consumer<Minecraft> consumer) {
            VDExtraNeoforge.modEventBus.addListener(FMLCommonSetupEvent.class, event -> {
                if (isClient()) consumer.accept(Minecraft.getInstance());
            });
        }

        @SuppressWarnings("deprecation")
        @Override
        public void setRenderLayerUnsafe(Block block, RenderType renderType) {
            ItemBlockRenderTypes.setRenderLayer(block, renderType);
        }
    }
}
