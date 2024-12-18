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

    public static final Item BLACKBERRIES = register("blackberries",
            new BlockItem(ModBlocks.BLACKBERRY_BUSH, new Item.Settings()
                    .food(ModFoodComponents.BLACKBERRY)
                    .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(CustomCrops.MOD_ID, "blackberries")))));

    public static final Item STRAWBERRY_LEMONADE = register("strawberry_lemonade",
            new Item(new Item.Settings()
                    .maxCount(16)
                    .food(ModFoodComponents.STRAWBERRY_LEMONADE_FOOD, ModFoodComponents.STRAWBERRY_LEMONADE)
                    .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(CustomCrops.MOD_ID, "strawberry_lemonade")))));



    public static <T extends Item> T register(String name, T item) {
        return Registry.register(Registries.ITEM, CustomCrops.id(name), item);
    }

    public static void load() {}
}
