package net.vegandelight.extra;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;

public class VDExtraFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        VDExtraMod.init(FabricItemGroup::builder);
    }
}
