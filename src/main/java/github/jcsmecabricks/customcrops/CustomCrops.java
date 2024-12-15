package github.jcsmecabricks.customcrops;

import github.jcsmecabricks.customcrops.block.ModBlocks;
import github.jcsmecabricks.customcrops.group.ModGroups;
import github.jcsmecabricks.customcrops.item.ModItems;
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
		LOGGER.info("Loading...");
		CompostingChanceRegistry.INSTANCE.add(ModItems.BLUEBERRIES, 0.15f);
	}
	public static Identifier id(String path) {
		return Identifier.of(MOD_ID, path);
	}
}