package github.jcsmecabricks.customcrops.block.entity;

import github.jcsmecabricks.customcrops.CustomCrops;
import github.jcsmecabricks.customcrops.block.ModBlocks;
import github.jcsmecabricks.customcrops.block.entity.custom.PastryStationBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class ModBlockEntities {

    public static final BlockEntityType<PastryStationBlockEntity> PASTRY_BE = Registry.register(
            BuiltInRegistries.BLOCK_ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(CustomCrops.MOD_ID, "pastry_be"),
            FabricBlockEntityTypeBuilder.create(PastryStationBlockEntity::new, ModBlocks.PASTRY_STATION).build(null)
    );

    public static void registerBlockEntities() {
        CustomCrops.LOGGER.info("Registering Block Entities for " + CustomCrops.MOD_ID);
    }
}