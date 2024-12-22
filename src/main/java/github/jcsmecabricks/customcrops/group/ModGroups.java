package github.jcsmecabricks.customcrops.group;

import github.jcsmecabricks.customcrops.CustomCrops;
import github.jcsmecabricks.customcrops.block.ModBlocks;
import github.jcsmecabricks.customcrops.item.ModItems;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;

public class ModGroups {
    public static final Text FARMERS_CROPS_TITLE = Text.translatable("itemGroup." + CustomCrops.MOD_ID + ".farmers_crops_group");
    public static final ItemGroup FARMERS_CROPS_GROUP = register("farmers_crops_group", FabricItemGroup.builder()
            .displayName(FARMERS_CROPS_TITLE)
            .icon(ModItems.BLUEBERRIES::getDefaultStack)
            .entries((displayContext, entries) -> {
                entries.add(ModItems.BLUEBERRIES);
                entries.add(ModItems.STRAWBERRIES);
                entries.add(ModItems.STRAWBERRY_LEMONADE);
                entries.add(ModItems.BLACKBERRIES);
                entries.add(ModItems.TOMATO);
                entries.add(ModItems.CORN_ON_THE_COB);
                entries.add(ModItems.BLACKBERRY_PIE);
                entries.add(ModItems.TOMATO_SEEDS);
                entries.add(ModItems.CORN);
                entries.add(ModItems.CORN_KERNEL);
                entries.add(ModBlocks.PASTRY_STATION);
}).build());
    public static <T extends ItemGroup> T register(String name, T itemGroup) {
        return Registry.register(Registries.ITEM_GROUP, CustomCrops.id(name), itemGroup);
    }
    public static void load() {
    }
}