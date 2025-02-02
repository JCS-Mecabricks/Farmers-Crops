package github.jcsmecabricks.customcrops.item;

import github.jcsmecabricks.customcrops.CustomCrops;
import github.jcsmecabricks.customcrops.block.ModBlocks;
import github.jcsmecabricks.customcrops.item.custom.StrawberryLemonadeItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ModItems {

    public static final Item BLUEBERRIES = registerItem("blueberries",
            new BlockItem(ModBlocks.BLUEBERRY_BUSH, new Item.Settings()
                    .food(ModFoodComponents.BLUEBERRY)
        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(CustomCrops.MOD_ID, "blueberries")))));


    public static final Item STRAWBERRIES = registerItem("strawberries",
            new BlockItem(ModBlocks.STRAWBERRY_BUSH, new Item.Settings()
                    .food(ModFoodComponents.STRAWBERRY)
                    .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(CustomCrops.MOD_ID, "strawberries")))));

    public static final Item BLACKBERRIES = registerItem("blackberries",
            new BlockItem(ModBlocks.BLACKBERRY_BUSH, new Item.Settings()
                    .food(ModFoodComponents.BLACKBERRY)
                    .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(CustomCrops.MOD_ID, "blackberries")))));

    public static final Item BLACKBERRY_PIE = registerItem("blackberry_pie",
            new Item(new Item.Settings()
                    .food(ModFoodComponents.BLACKBERRY_PIE)
                    .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(CustomCrops.MOD_ID, "blackberry_pie")))));


    public static final Item TOMATO_SEEDS = registerItem("tomato_seeds",
            new BlockItem(ModBlocks.TOMATO_CROP, new Item.Settings()
                    .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(CustomCrops.MOD_ID, "tomato_seeds")))));

    public static final Item CORN_KERNEL = registerItem("corn_kernel",
            new BlockItem(ModBlocks.CORN_CROP, new Item.Settings()
                    .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(CustomCrops.MOD_ID, "corn_kernel")))));


    public static final Item TOMATO = registerItem("tomato",
            new Item(new Item.Settings()
                    .food(ModFoodComponents.TOMATO)
                    .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(CustomCrops.MOD_ID, "tomato")))));

    public static final Item CORN = registerItem("corn",
            new Item(new Item.Settings()
                    .food(ModFoodComponents.CORN)
                    .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(CustomCrops.MOD_ID, "corn")))));

    public static final Item CORN_ON_THE_COB = registerItem("corn_on_the_cob",
            new Item(new Item.Settings()
                    .food(ModFoodComponents.CORN_COB)
                    .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(CustomCrops.MOD_ID, "corn_on_the_cob")))));

    public static final Item MUFFIN = registerItem("muffin",
            new Item(new Item.Settings()
                    .food(ModFoodComponents.MUFFIN)
                    .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(CustomCrops.MOD_ID, "muffin")))));


    public static final Item STRAWBERRY_LEMONADE = registerItem("strawberry_lemonade",
            new StrawberryLemonadeItem(new Item.Settings()
                    .maxCount(16)
                    .food(ModFoodComponents.STRAWBERRY_LEMONADE_FOOD, ModFoodComponents.STRAWBERRY_LEMONADE)
                    .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(CustomCrops.MOD_ID, "strawberry_lemonade")))));



    public static <T extends Item> T register(String name, T item) {
        return Registry.register(Registries.ITEM, CustomCrops.id(name), item);
    }

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(CustomCrops.MOD_ID, name), item);
    }

    public static void load() {}
}
