package net.vegandelight.extra;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Items;

import java.util.function.Supplier;

import static net.vegandelight.extra.VDExtraMod.registrar;

public abstract class ModCreativeTabs {

    public static final Holder<CreativeModeTab> vd_extra_tab = register("vd_extra_tab",
            () -> registrar.creativeTabBuilder()
                    .icon(Items.ACACIA_BOAT::getDefaultInstance)
                    .title(Component.literal("Hi!"))
                    .displayItems((params, output) -> {
                        output.accept(ModBlocks.olive_log.value());
                        output.accept(ModBlocks.olive_leaves.value());
                    }).build()
    );

    static void init() {

    }

    private static Holder<CreativeModeTab> register(String name, Supplier<CreativeModeTab> tab) {
        return VDExtraMod.register(name, BuiltInRegistries.CREATIVE_MODE_TAB, tab);
    }
}
