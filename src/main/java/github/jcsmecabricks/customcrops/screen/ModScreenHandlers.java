package github.jcsmecabricks.customcrops.screen;

import github.jcsmecabricks.customcrops.CustomCrops;
import github.jcsmecabricks.customcrops.screen.custom.PastryScreenHandler;
import net.fabricmc.fabric.api.menu.v1.ExtendedMenuType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.inventory.MenuType;

public class ModScreenHandlers {
    public static final MenuType<PastryScreenHandler> PASTRY_STATION_SCREEN_HANDLER =
            Registry.register(BuiltInRegistries.MENU, Identifier.fromNamespaceAndPath(CustomCrops.MOD_ID, "pastry_station_screen_handler"),
                    new ExtendedMenuType<>(PastryScreenHandler::new, BlockPos.STREAM_CODEC));

    public static void registerScreenHandlers() {
        CustomCrops.LOGGER.info("Registering Screen Handlers for " + CustomCrops.MOD_ID);
    }
}