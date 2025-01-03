package github.jcsmecabricks.customcrops.block.entity;

import github.jcsmecabricks.customcrops.CustomCrops;
import github.jcsmecabricks.customcrops.block.ModBlocks;
import github.jcsmecabricks.customcrops.block.entity.custom.PastryStationBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlockEntities {

    public static final BlockEntityType<PastryStationBlockEntity> PASTRY_BE = Registry.register(
            Registries.BLOCK_ENTITY_TYPE,
            Identifier.of(CustomCrops.MOD_ID, "pastry_be"),
            FabricBlockEntityTypeBuilder.create(PastryStationBlockEntity::new, ModBlocks.PASTRY_STATION).build(null)
    );

    public static void registerBlockEntities() {
        CustomCrops.LOGGER.info("Registering Block Entities for " + CustomCrops.MOD_ID);
    }
}