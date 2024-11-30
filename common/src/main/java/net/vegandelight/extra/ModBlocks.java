package net.vegandelight.extra;

import net.minecraft.client.color.block.BlockColor;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.FoliageColor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.function.Supplier;

import static net.minecraft.core.registries.Registries.CONFIGURED_FEATURE;
import static net.vegandelight.extra.VDExtraMod.platform;

public abstract class ModBlocks {

    public static final TreeGrower OLIVE_TREE_GROWER = new TreeGrower("olive",
            Optional.empty(),
            Optional.of(ResourceKey.create(
                    CONFIGURED_FEATURE,
                    ResourceLocation.fromNamespaceAndPath(VDExtraMod.modID, "olive_tree"))
            ),
            Optional.empty()
    );

    public static final BlockColor LEAVE_BLOCK_COLOR =
            (blockState, blockAndTintGetter, blockPos, i) -> blockAndTintGetter != null && blockPos != null
                    ? BiomeColors.getAverageFoliageColor(blockAndTintGetter, blockPos)
                    : FoliageColor.getDefaultColor();

    public static final ItemColor LEAVE_ITEM_COLOR = (stack, i) -> FoliageColor.getDefaultColor();

    public static Holder<Block> olive_sapling = register("olive_sapling", true, RenderType.cutout(),
            () -> new SaplingBlock(OLIVE_TREE_GROWER, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)) {}
    );

    public static Holder<Block> olive_leaves = register("olive_leaves", true, RenderType.cutoutMipped(),
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.BIRCH_LEAVES)), LEAVE_BLOCK_COLOR, LEAVE_ITEM_COLOR
    );

    public static Holder<Block> olive_log = register("olive_log", true, () -> new RotatedPillarBlock(
            BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG)
    ));

    public static final Holder<Block> stripped_olive_log = register("stripped_olive_log", true,
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG))
    );

    public static Holder<Block> olive_wood = register("olive_wood", true, () -> new RotatedPillarBlock(
            BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD)
    ));

    public static final Holder<Block> stripped_olive_wood = register("stripped_olive_wood", true,
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD))
    );

    public static final Holder<Block> olive_planks = register("olive_planks", true, () -> new Block(
            BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)
    ));

    private static @NotNull Holder<Block> register(String name, boolean registerItem, RenderType renderType,
                                                   Supplier<Block> block) {
        var holder = register(name, registerItem, block);
        platform.setRenderLayer(holder::value, renderType);

        return holder;
    }

    private static @NotNull Holder<Block> register(String name, boolean registerItem, RenderType renderType,
                                                   Supplier<Block> block, BlockColor color, ItemColor itemColor) {
        var holder = register(name, registerItem, block);

        platform.setRenderLayer(holder::value, renderType);
        platform.setBlockColor(holder::value, color);
        if (registerItem) platform.setItemColor(holder::value, itemColor);

        return holder;
    }

    private static @NotNull Holder<Block> register(String name, boolean registerItem, Supplier<Block> block) {
        var blockHolder = VDExtraMod.register(name, BuiltInRegistries.BLOCK, block);
        if (registerItem)
            VDExtraMod.register(
                    name, BuiltInRegistries.ITEM,
                    () -> new BlockItem(blockHolder.value(), new Item.Properties())
            );
        return blockHolder;
    }

    static void init() {}
}
