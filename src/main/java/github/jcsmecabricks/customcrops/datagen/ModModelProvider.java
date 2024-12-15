package github.jcsmecabricks.customcrops.datagen;

import github.jcsmecabricks.customcrops.block.ModBlocks;
import github.jcsmecabricks.customcrops.block.custom.GrapeBushBlock;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.BlockStateModelGenerator;
import net.minecraft.client.data.ItemModelGenerator;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.GRAPE_BUSH);
        blockStateModelGenerator.registerTintableCrossBlockStateWithStages(ModBlocks.GRAPE_BUSH, BlockStateModelGenerator.CrossType.NOT_TINTED,
                GrapeBushBlock.AGE, 0, 1, 2, 3);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {

    }
}
