package net.vegandelight.extra;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;

import java.util.function.Supplier;

public class VDExtraMod {

    static final String modID = "vegan_delight_extra";
    public static VDExtraPlatform platform;

    static void init(VDExtraPlatform registrar) {
        VDExtraMod.platform = registrar;

        ModBlocks.init();
        ModItems.init();
        ModCreativeTabs.init();
    }

    static <T> Holder<T> register(String name, Registry<T> registry, Supplier<T> obj) {
        return platform.register(registry, ResourceLocation.fromNamespaceAndPath(modID, name), obj);
    }
}
