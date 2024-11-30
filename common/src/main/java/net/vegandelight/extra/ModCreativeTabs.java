package net.vegandelight.extra;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import static net.vegandelight.extra.VDExtraMod.platform;

public abstract class ModCreativeTabs {

    public static final Holder<CreativeModeTab> vd_extra_tab = VDExtraMod.register(
            "vd_extra_tab", BuiltInRegistries.CREATIVE_MODE_TAB,
            () -> platform.creativeTabBuilder()
                    .icon(() -> new ItemStack(ModBlocks.olive_sapling.value()))
                    .title(Component.literal("Vegan Delight Extra"))
                    .displayItems((params, output) -> {
                        for (ItemLike item : get_all_vd_extra_items()) output.accept(item);
                    }).build()
    );

    @Contract(" -> new")
    public static ItemLike @NotNull [] get_all_vd_extra_items() {
        return new Block[]{
                ModBlocks.olive_wood.value(), ModBlocks.stripped_olive_wood.value(), ModBlocks.olive_log.value(),
                ModBlocks.stripped_olive_log.value(), ModBlocks.olive_planks.value(), ModBlocks.olive_leaves.value(),
                ModBlocks.olive_sapling.value()
        };
    }

    static void init() {}
}
