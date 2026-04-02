package github.jcsmecabricks.customcrops.item;

import github.jcsmecabricks.customcrops.CustomCrops;
import github.jcsmecabricks.customcrops.block.ModBlocks;
import github.jcsmecabricks.customcrops.item.custom.StrawberryLemonadeItem;
import java.util.function.Function;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;

public class ModItems {

    public static final Item BLUEBERRIES = registerItem("blueberries", settings ->
            new BlockItem(ModBlocks.BLUEBERRY_BUSH, settings
                    .food(ModFoodComponents.BLUEBERRY)));


    public static final Item STRAWBERRIES = registerItem("strawberries", settings ->
            new BlockItem(ModBlocks.STRAWBERRY_BUSH, settings
                    .food(ModFoodComponents.STRAWBERRY)));

    public static final Item BLACKBERRIES = registerItem("blackberries", settings ->
            new BlockItem(ModBlocks.BLACKBERRY_BUSH, settings
                    .food(ModFoodComponents.BLACKBERRY)));

    public static final Item BLACKBERRY_PIE = registerItem("blackberry_pie",settings ->
            new Item(settings
                    .food(ModFoodComponents.BLACKBERRY_PIE)));


    public static final Item TOMATO_SEEDS = registerItem("tomato_seeds", settings ->
            new BlockItem(ModBlocks.TOMATO_CROP, settings));

    public static final Item CORN_KERNEL = registerItem("corn_kernel", settings ->
            new BlockItem(ModBlocks.CORN_CROP, settings));


    public static final Item TOMATO = registerItem("tomato", settings ->
            new Item(settings
                    .food(ModFoodComponents.TOMATO)));

    public static final Item CORN = registerItem("corn", settings ->
            new Item(settings
                    .food(ModFoodComponents.CORN)));

    public static final Item CORN_ON_THE_COB = registerItem("corn_on_the_cob", settings ->
            new Item(settings
                    .food(ModFoodComponents.CORN_COB)));

    public static final Item MUFFIN = registerItem("muffin", settings ->
            new Item(settings
                    .food(ModFoodComponents.MUFFIN)));


    public static final Item STRAWBERRY_LEMONADE = registerItem("strawberry_lemonade", settings ->
            new StrawberryLemonadeItem(settings
                    .stacksTo(16)
                    .food(ModFoodComponents.STRAWBERRY_LEMONADE_FOOD, ModFoodComponents.STRAWBERRY_LEMONADE)));



    public static <T extends Item> T register(String name, T item) {
        return Registry.register(BuiltInRegistries.ITEM, CustomCrops.id(name), item);
    }

    private static Item registerItem(String name, Function<Item.Properties, Item> function) {
        return Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(CustomCrops.MOD_ID, name),
                function.apply(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(CustomCrops.MOD_ID, name)))));
    }

    private static Item registerItem(String name, Item item) {
        return Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(CustomCrops.MOD_ID, name), item);
    }

    public static void load() {}
}
