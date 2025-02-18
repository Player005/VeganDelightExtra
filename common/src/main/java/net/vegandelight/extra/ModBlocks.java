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
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
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

    public static final BlockSetType OLIVE_BLOCK_SET_TYPE = new BlockSetType("olive");

    public static final WoodType OLIVE_WOOD_TYPE = new WoodType("olive", OLIVE_BLOCK_SET_TYPE);

    public static final BlockColor LEAVE_BLOCK_COLOR =
            (blockState, blockAndTintGetter, blockPos, i) -> blockAndTintGetter != null && blockPos != null
                    ? BiomeColors.getAverageFoliageColor(blockAndTintGetter, blockPos)
                    : FoliageColor.getDefaultColor();

    public static final ItemColor LEAVE_ITEM_COLOR = (stack, i) -> FoliageColor.getDefaultColor();

    public static Holder<Block> olive_sapling = register("olive_sapling", true, RenderType.cutout(),
            () -> new SaplingBlock(OLIVE_TREE_GROWER, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)) {
            }
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

    public static final Holder<Block> olive_slab = register("olive_slab", true, () -> new SlabBlock(
            BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SLAB)
    ));

    public static final Holder<Block> olive_stairs = register("olive_stairs", true, () -> new StairBlock(
            olive_planks.value().defaultBlockState(),
            BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS)
    ) {});

    public static final Holder<Block> olive_fence = register("olive_fence", true, () -> new FenceBlock(
            BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE)
    ));

    public static final Holder<Block> olive_fence_gate = register("olive_fence_gate", true, () -> new FenceGateBlock(
            OLIVE_WOOD_TYPE,
            BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE)
    ));

    public static final Holder<Block> olive_door = register("olive_door", true, () -> new DoorBlock(
            OLIVE_BLOCK_SET_TYPE,
            BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR)
    ) {});

    public static final Holder<Block> olive_pressure_plate = register("olive_pressure_plate", true, () -> new PressurePlateBlock(
            OLIVE_BLOCK_SET_TYPE,
            BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PRESSURE_PLATE)
    ) {});

    public static final Holder<Block> olive_trapdoor = register("olive_trapdoor", true, () -> new TrapDoorBlock(
            OLIVE_BLOCK_SET_TYPE,
            BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR)
    ) {});

    public static final Holder<Block> olive_button = register("olive_button", true, () -> new ButtonBlock(
            OLIVE_BLOCK_SET_TYPE, 30,
            BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_BUTTON)
    ) {});

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

    static void init() {
    }
}
