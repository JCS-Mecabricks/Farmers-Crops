package github.jcsmecabricks.customcrops;

import github.jcsmecabricks.customcrops.block.ModBlocks;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;

public class CustomCropsClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.GRAPE_BUSH, RenderLayer.getCutout());
    }
}
