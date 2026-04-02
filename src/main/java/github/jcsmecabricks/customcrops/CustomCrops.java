package github.jcsmecabricks.customcrops;

import github.jcsmecabricks.customcrops.block.ModBlocks;
import github.jcsmecabricks.customcrops.block.entity.ModBlockEntities;
import github.jcsmecabricks.customcrops.group.ModGroups;
import github.jcsmecabricks.customcrops.item.ModItems;
import github.jcsmecabricks.customcrops.recipe.ModRecipes;
import github.jcsmecabricks.customcrops.screen.ModScreenHandlers;
import github.jcsmecabricks.customcrops.util.ModLootTableModifiers;
import github.jcsmecabricks.customcrops.world.gen.ModWorldGeneration;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.fabricmc.fabric.api.registry.CompostableRegistry;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Items;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomCrops implements ModInitializer {
	public static final String MOD_ID = "custom-crops";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
        ModItems.load();
		ModBlocks.load();
        ModRecipes.registerRecipes();
        ModLootTableModifiers.modifyLootTables();
		ModGroups.load();
		ModBlockEntities.registerBlockEntities();
		ModScreenHandlers.registerScreenHandlers();
		ModWorldGeneration.generateModWorldGen();
		LOGGER.info("Loading...");
        CompostableRegistry.INSTANCE.add(ModItems.BLUEBERRIES, 0.30f);
		CompostableRegistry.INSTANCE.add(ModItems.TOMATO, 0.50f);
        CompostableRegistry.INSTANCE.add(ModItems.CORN, 0.50f);
        CompostableRegistry.INSTANCE.add(ModItems.CORN_KERNEL, 0.40f);
        CompostableRegistry.INSTANCE.add(ModItems.TOMATO_SEEDS, 0.40f);
        CompostableRegistry.INSTANCE.add(ModItems.STRAWBERRIES, 0.30f);
        CompostableRegistry.INSTANCE.add(ModItems.BLACKBERRIES, 0.30f);

		CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.FOOD_AND_DRINKS).register(entries -> {
			entries.insertAfter(Items.BEETROOT, ModItems.STRAWBERRIES);
			entries.insertAfter(ModItems.STRAWBERRIES, ModItems.BLUEBERRIES);
			entries.insertAfter(ModItems.BLUEBERRIES, ModItems.BLACKBERRIES);
		});
	}
	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
}