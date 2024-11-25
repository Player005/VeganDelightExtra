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
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.UnknownNullability;

import java.util.function.Supplier;

public abstract class ModBlocks {
    static RegisteredBlock olive_leaves = register("olive_leaves", true, () -> {
        System.out.println("olive_leaves block lambda called");
        return new Block(
                BlockBehaviour.Properties.ofFullCopy(Blocks.BIRCH_LEAVES)
        );
    });

    static RegisteredBlock olive_log = register("olive_log", true, () -> {
        System.out.println("olive_logs block lambda called");
        return new RotatedPillarBlock(
                BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG)
        );
    });

    private static @NotNull RegisteredBlock register(String name, boolean registerItem, Supplier<Block> block) {
        var blockHolder = VDExtraMod.register(name, BuiltInRegistries.BLOCK, block);
        Holder<Item> itemHolder = null;
        if (registerItem)
            itemHolder = VDExtraMod.register(
                    name, BuiltInRegistries.ITEM,
                    () -> new BlockItem(blockHolder.value(), new Item.Properties())
            );
        return new RegisteredBlock(blockHolder, itemHolder);
    }

    static void init() {}

    public record RegisteredBlock(Holder<Block> blockHolder, @Nullable Holder<Item> itemHolder) {
        public @UnknownNullability Item getItem() {
            assert itemHolder != null;
            return itemHolder.value();
        }

        public @NotNull Block getBlock() {
            return blockHolder.value();
        }
    }
}
