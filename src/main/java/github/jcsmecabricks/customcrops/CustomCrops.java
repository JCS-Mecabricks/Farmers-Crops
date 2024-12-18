package github.jcsmecabricks.customcrops;

import github.jcsmecabricks.customcrops.block.ModBlocks;
import github.jcsmecabricks.customcrops.block.entity.ModBlockEntities;
import github.jcsmecabricks.customcrops.group.ModGroups;
import github.jcsmecabricks.customcrops.item.ModItems;
import github.jcsmecabricks.customcrops.screen.ModScreenHandlers;
import github.jcsmecabricks.customcrops.world.gen.ModWorldGeneration;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
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
		ModBlockEntities.registerBlockEntities();
		ModScreenHandlers.registerScreenHandlers();
		ModWorldGeneration.generateModWorldGen();
		LOGGER.info("Loading...");
		CompostingChanceRegistry.INSTANCE.add(ModItems.BLUEBERRIES, 0.30f);
		CompostingChanceRegistry.INSTANCE.add(ModItems.STRAWBERRIES, 0.30f);
		CompostingChanceRegistry.INSTANCE.add(ModItems.BLACKBERRIES, 0.30f);

		ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(entries -> {
			entries.addAfter(Items.BEETROOT, ModItems.STRAWBERRIES);
			entries.addAfter(ModItems.STRAWBERRIES, ModItems.BLUEBERRIES);
			entries.addAfter(ModItems.BLUEBERRIES, ModItems.BLACKBERRIES);
		});
	}
	public static Identifier id(String path) {
		return Identifier.of(MOD_ID, path);
	}
}