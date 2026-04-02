package github.jcsmecabricks.customcrops;

import github.jcsmecabricks.customcrops.datagen.*;
import github.jcsmecabricks.customcrops.world.ModConfiguredFeatures;
import github.jcsmecabricks.customcrops.world.ModPlacedFeatures;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;

public class CustomCropsDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
		pack.addProvider(ModModelProvider::new);
		pack.addProvider(ModBlockTags::new);
		pack.addProvider(ModWorldGenerator::new);
		pack.addProvider(ModLootTableProvider::new);
		pack.addProvider(ModRecipeProvider::new);
	}

	@Override
	public void buildRegistry(RegistrySetBuilder registryBuilder) {
		DataGeneratorEntrypoint.super.buildRegistry(registryBuilder);
		registryBuilder.add(Registries.CONFIGURED_FEATURE, ModConfiguredFeatures::bootstrap);
		registryBuilder.add(Registries.PLACED_FEATURE, ModPlacedFeatures::bootstrap);
	}
}