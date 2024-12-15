package github.jcsmecabricks.customcrops;

import github.jcsmecabricks.customcrops.datagen.ModLootTableProvider;
import github.jcsmecabricks.customcrops.datagen.ModModelProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class CustomCropsDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
		pack.addProvider(ModModelProvider::new);
		pack.addProvider(ModLootTableProvider::new);
	}
}
