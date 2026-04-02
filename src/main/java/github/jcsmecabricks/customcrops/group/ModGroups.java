package github.jcsmecabricks.customcrops.group;

import github.jcsmecabricks.customcrops.CustomCrops;
import github.jcsmecabricks.customcrops.block.ModBlocks;
import github.jcsmecabricks.customcrops.item.ModItems;
import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;

public class ModGroups {
    public static final Component FARMERS_CROPS_TITLE = Component.translatable("itemGroup." + CustomCrops.MOD_ID + ".farmers_crops_group");
    public static final CreativeModeTab FARMERS_CROPS_GROUP = register("farmers_crops_group", FabricCreativeModeTab.builder()
            .title(FARMERS_CROPS_TITLE)
            .icon(ModItems.BLUEBERRIES::getDefaultInstance)
            .displayItems((displayContext, entries) -> {
                entries.accept(ModItems.BLUEBERRIES);
                entries.accept(ModItems.STRAWBERRIES);
                entries.accept(ModItems.STRAWBERRY_LEMONADE);
                entries.accept(ModItems.BLACKBERRIES);
                entries.accept(ModItems.TOMATO);
                entries.accept(ModItems.CORN_ON_THE_COB);
                entries.accept(ModItems.BLACKBERRY_PIE);
                entries.accept(ModItems.TOMATO_SEEDS);
                entries.accept(ModItems.CORN);
                entries.accept(ModItems.CORN_KERNEL);
                entries.accept(ModBlocks.PASTRY_STATION);
}).build());
    public static <T extends CreativeModeTab> T register(String name, T itemGroup) {
        return Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, CustomCrops.id(name), itemGroup);
    }
    public static void load() {
    }
}