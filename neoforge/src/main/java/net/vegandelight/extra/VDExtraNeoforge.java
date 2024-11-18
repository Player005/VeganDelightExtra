package net.vegandelight.extra;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(VDExtraMod.modID)
public class VDExtraNeoforge {

    public static IEventBus modEventBus;

    public VDExtraNeoforge(IEventBus modEventBus) {
        VDExtraNeoforge.modEventBus = modEventBus;

        VDExtraMod.init();
        // Your neoforge initialisation code here
    }
}
