package github.jcsmecabricks.customcrops.screen;

import github.jcsmecabricks.customcrops.CustomCrops;
import github.jcsmecabricks.customcrops.screen.custom.PastryScreenHandler;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

public class ModScreenHandlers {
    public static final ScreenHandlerType<PastryScreenHandler> PASTRY_STATION_SCREEN_HANDLER =
            Registry.register(Registries.SCREEN_HANDLER, Identifier.of(CustomCrops.MOD_ID, "pastry_station_screen_handler"),
                    new ExtendedScreenHandlerType<>(PastryScreenHandler::new, BlockPos.PACKET_CODEC));

    public static void registerScreenHandlers() {
        CustomCrops.LOGGER.info("Registering Screen Handlers for " + CustomCrops.MOD_ID);
    }
}