package github.jcsmecabricks.customcrops.datagen;

import github.jcsmecabricks.customcrops.block.ModBlocks;
import github.jcsmecabricks.customcrops.block.custom.*;
import github.jcsmecabricks.customcrops.item.ModItems;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TexturedModel;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricPackOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockStateModelGenerator) {
        blockStateModelGenerator.createCropBlock(ModBlocks.TOMATO_CROP, TomatoCropBlock.AGE, 0, 1, 2, 3, 4, 5);
        blockStateModelGenerator.createCropBlock(ModBlocks.CORN_CROP, CornCropBlock.AGE, 0, 1, 2, 3, 4, 5, 6);
        blockStateModelGenerator.createFurnace(ModBlocks.PASTRY_STATION, TexturedModel.ORIENTABLE_ONLY_TOP);
        blockStateModelGenerator.createCrossBlock(ModBlocks.BLUEBERRY_BUSH, BlockModelGenerators.PlantType.NOT_TINTED,
                BlueberryBushBlock.AGE, 0, 1, 2, 3);
        blockStateModelGenerator.createCrossBlock(ModBlocks.STRAWBERRY_BUSH, BlockModelGenerators.PlantType.NOT_TINTED,
                StrawberryBushBlock.AGE, 0, 1, 2, 3);
        blockStateModelGenerator.createCrossBlock(ModBlocks.BLACKBERRY_BUSH, BlockModelGenerators.PlantType.NOT_TINTED,
                BlackberryBushBlock.AGE, 0, 1, 2, 3);
    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerator) {
        itemModelGenerator.generateFlatItem(ModItems.STRAWBERRY_LEMONADE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.TOMATO, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.CORN, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.MUFFIN, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.CORN_ON_THE_COB, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.BLACKBERRY_PIE, ModelTemplates.FLAT_ITEM);
    }
}
