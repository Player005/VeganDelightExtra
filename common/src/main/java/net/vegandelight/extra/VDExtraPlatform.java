package net.vegandelight.extra;

import net.minecraft.client.Minecraft;
import net.minecraft.client.color.block.BlockColor;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;
import java.util.function.Supplier;

public interface VDExtraPlatform {
    default <T> Holder<T> register(@NotNull Registry<T> registry, ResourceLocation rl, @NotNull Supplier<T> value) {
        var obj = value.get();
        var holder = Holder.direct(obj);
        Registry.register(registry, rl, obj);
        return holder;
    }

    CreativeModeTab.Builder creativeTabBuilder();

    default void setRenderLayer(Block block, RenderType renderType) {
        if (isClient()) setRenderLayerUnsafe(block, renderType);
    }

    void onServerStart(Consumer<MinecraftServer> consumer);

    void onClientStart(Consumer<Minecraft> consumer);

    void setBlockColor(BlockColor color, Block... blocks);

    void setRenderLayerUnsafe(Block block, RenderType renderType);

    default boolean isClient() {
        try {
            Class.forName("net.minecraft.client.Minecraft");
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }
}
