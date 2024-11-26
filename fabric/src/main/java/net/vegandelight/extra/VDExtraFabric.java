package net.vegandelight.extra;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;

import java.util.function.Consumer;

public class VDExtraFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        VDExtraMod.init(new VDExtraPlatform() {
            @Override
            public CreativeModeTab.Builder creativeTabBuilder() {
                return FabricItemGroup.builder();
            }

            @Override
            public void onServerStart(Consumer<MinecraftServer> consumer) {
                ServerLifecycleEvents.SERVER_STARTING.register(consumer::accept);
            }

            @Override
            public void onClientStart(Consumer<Minecraft> consumer) {
                ClientLifecycleEvents.CLIENT_STARTED.register(consumer::accept);
            }

            @Override
            public void setRenderLayerUnsafe(Block block, RenderType renderType) {
                BlockRenderLayerMap.INSTANCE.putBlock(block, renderType);
            }
        });
    }
}
