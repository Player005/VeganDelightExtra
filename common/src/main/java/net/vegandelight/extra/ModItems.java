package net.vegandelight.extra;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;

import java.util.function.Supplier;

public abstract class ModItems {

    static void init() {}

    private static Holder<Item> register(String name, Supplier<Item> block) {
        return VDExtraMod.register(name, BuiltInRegistries.ITEM, block);
    }
}
