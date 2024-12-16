package github.jcsmecabricks.customcrops;

import github.jcsmecabricks.customcrops.block.ModBlocks;
import github.jcsmecabricks.customcrops.group.ModGroups;
import github.jcsmecabricks.customcrops.item.ModItems;
import github.jcsmecabricks.customcrops.world.gen.ModWorldGeneration;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomCrops implements ModInitializer {
	public static final String MOD_ID = "custom-crops";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModBlocks.load();
		ModItems.load();
		ModGroups.load();
		ModWorldGeneration.generateModWorldGen();
		LOGGER.info("Loading...");
		CompostingChanceRegistry.INSTANCE.add(ModItems.BLUEBERRIES, 0.30f);
		CompostingChanceRegistry.INSTANCE.add(ModItems.STRAWBERRIES, 0.30f);
		CompostingChanceRegistry.INSTANCE.add(ModItems.BLACKBERRIES, 0.30f);
	}
	public static Identifier id(String path) {
		return Identifier.of(MOD_ID, path);
	}
}