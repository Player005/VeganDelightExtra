package net.vegandelight.extra;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class VDExtraMod {

    static final String modID = "vegan_delight_extra";
    public static VDExtraPlatform platform;

    static void init(@NotNull VDExtraPlatform platform) {
        VDExtraMod.platform = platform;

        ModBlocks.init();
        ModItems.init();
        ModCreativeTabs.init();

        platform.onServerStart(minecraftServer -> System.out.println("Server start"));
        platform.onClientStart(minecraft -> System.out.println("Client start"));
    }

    static <T> Holder<T> register(String name, Registry<T> registry, Supplier<T> obj) {
        return platform.register(registry, ResourceLocation.fromNamespaceAndPath(modID, name), obj);
    }
}
