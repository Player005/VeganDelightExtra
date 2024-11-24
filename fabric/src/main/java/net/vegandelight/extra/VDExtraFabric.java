package net.vegandelight.extra;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.world.item.CreativeModeTab;

public class VDExtraFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        VDExtraMod.init(FabricItemGroup::builder);
    }
}
