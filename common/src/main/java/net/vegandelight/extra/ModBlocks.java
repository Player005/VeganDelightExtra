package net.vegandelight.extra;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public abstract class ModBlocks {
    static Holder<Block> olive_leaves = register("olive_leaves", true, () -> new Block(
            BlockBehaviour.Properties.ofFullCopy(Blocks.BIRCH_LEAVES)
    ));

    static Holder<Block> olive_log = register("olive_log", true, () -> new RotatedPillarBlock(
            BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG)
    ));

    static Holder<Block> olive_wood = register("olive_wood", true, () -> new RotatedPillarBlock(
            BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD)
    ));

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
