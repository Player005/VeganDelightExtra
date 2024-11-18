package net.vegandelight.extra;

import net.fabricmc.api.ModInitializer;

public class VDExtraFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        VDExtraMod.init();
        // Your fabric initialisation code here
    }
}
