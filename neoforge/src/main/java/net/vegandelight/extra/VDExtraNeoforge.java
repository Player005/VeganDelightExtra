package net.vegandelight.extra;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(VDExtraMod.modID)
public class VDExtraNeoforge {

    public static IEventBus modEventBus;

    @SuppressWarnings("removal")
    public VDExtraNeoforge() {
        VDExtraNeoforge.modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        VDExtraMod.init();
        // Your neoforge initialisation code here
    }
}
