package github.jcsmecabricks.customcrops;

import github.jcsmecabricks.customcrops.block.ModBlocks;
import github.jcsmecabricks.customcrops.screen.ModScreenHandlers;
import github.jcsmecabricks.customcrops.screen.custom.PastryScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.BlockRenderLayer;

public class CustomCropsClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.putBlock(ModBlocks.BLUEBERRY_BUSH, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.STRAWBERRY_BUSH, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.BLACKBERRY_BUSH, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.TOMATO_CROP, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.CORN_CROP, BlockRenderLayer.CUTOUT);
        HandledScreens.register(ModScreenHandlers.PASTRY_STATION_SCREEN_HANDLER, PastryScreen::new);
    }
}
