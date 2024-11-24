package net.vegandelight.extra;

import net.minecraft.core.Holder;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;

import java.util.function.Supplier;

public abstract class ModBlocks {
    static Holder<Block> olive_leaves = register("olive_leaves", true, () -> new Block(
            BlockBehaviour.Properties.of()
//            BlockBehaviour.Properties.ofFullCopy(Blocks.BIRCH_LEAVES)
    ));

    static Holder<Block> olive_log = register("olive_logs", true, () -> new Block(
            BlockBehaviour.Properties.of()
//                    .mapColor(blockState -> blockState.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ?
//                    MapColor.WOOD : MapColor.PODZOL)
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F)
                    .sound(SoundType.WOOD)
                    .ignitedByLava()
    ));

    static CauldronInteraction.InteractionMap cauldronInteractionMap = CauldronInteraction.newInteractionMap("");

    private static Holder<Block> register(String name, boolean registerItem, Block block) {
        return register(name, registerItem, () -> block);
    }

    private static Holder<Block> register(String name, boolean registerItem, Supplier<Block> block) {
        if (registerItem)
            VDExtraMod.register(
                    name, BuiltInRegistries.ITEM,
                    () -> new BlockItem(block.get(), new Item.Properties())
            );
        return VDExtraMod.register(name, BuiltInRegistries.BLOCK, block);
    }

    static void init() {
    }
}
