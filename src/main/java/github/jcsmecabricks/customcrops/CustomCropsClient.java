package github.jcsmecabricks.customcrops;

import github.jcsmecabricks.customcrops.screen.ModScreenHandlers;
import github.jcsmecabricks.customcrops.screen.custom.PastryScreen;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.screens.MenuScreens;

public class CustomCropsClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        MenuScreens.register(ModScreenHandlers.PASTRY_STATION_SCREEN_HANDLER, PastryScreen::new);
    }
}
