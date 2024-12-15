package github.jcsmecabricks.customcrops.item;

import github.jcsmecabricks.customcrops.CustomCrops;
import github.jcsmecabricks.customcrops.block.ModBlocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ModItems {

    public static final Item BLUEBERRIES = register("blueberries",
            new BlockItem(ModBlocks.BLUEBERRY_BUSH, new Item.Settings()
                    .food(ModFoodComponents.BLUEBERRY)
        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(CustomCrops.MOD_ID, "blueberries")))));


    public static final Item STRAWBERRIES = register("strawberries",
            new BlockItem(ModBlocks.STRAWBERRY_BUSH, new Item.Settings()
                    .food(ModFoodComponents.STRAWBERRY)
                    .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(CustomCrops.MOD_ID, "strawberries")))));


    public static <T extends Item> T register(String name, T item) {
        return Registry.register(Registries.ITEM, CustomCrops.id(name), item);
    }

    public static void load() {}
}
