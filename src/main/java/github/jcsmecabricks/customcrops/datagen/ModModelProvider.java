package github.jcsmecabricks.customcrops.datagen;

import github.jcsmecabricks.customcrops.block.ModBlocks;
import github.jcsmecabricks.customcrops.block.custom.BlackberryBushBlock;
import github.jcsmecabricks.customcrops.block.custom.BlueberryBushBlock;
import github.jcsmecabricks.customcrops.block.custom.StrawberryBushBlock;
import github.jcsmecabricks.customcrops.item.ModItems;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.BlockStateModelGenerator;
import net.minecraft.client.data.ItemModelGenerator;
import net.minecraft.client.data.Models;
import net.minecraft.client.data.TexturedModel;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerCooker(ModBlocks.PASTRY_STATION, TexturedModel.ORIENTABLE);
        blockStateModelGenerator.registerTintableCrossBlockStateWithStages(ModBlocks.BLUEBERRY_BUSH, BlockStateModelGenerator.CrossType.NOT_TINTED,
                BlueberryBushBlock.AGE, 0, 1, 2, 3);
        blockStateModelGenerator.registerTintableCrossBlockStateWithStages(ModBlocks.STRAWBERRY_BUSH, BlockStateModelGenerator.CrossType.NOT_TINTED,
                StrawberryBushBlock.AGE, 0, 1, 2, 3);
        blockStateModelGenerator.registerTintableCrossBlockStateWithStages(ModBlocks.BLACKBERRY_BUSH, BlockStateModelGenerator.CrossType.NOT_TINTED,
                BlackberryBushBlock.AGE, 0, 1, 2, 3);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.STRAWBERRY_LEMONADE, Models.GENERATED);
    }
}
