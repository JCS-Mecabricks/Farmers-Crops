package github.jcsmecabricks.customcrops.datagen;

import github.jcsmecabricks.customcrops.block.ModBlocks;
import github.jcsmecabricks.customcrops.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.recipe.CookingRecipeJsonBuilder;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.TagKey;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeGenerator getRecipeGenerator(RegistryWrapper.WrapperLookup wrapperLookup, RecipeExporter recipeExporter) {
        return new RecipeGenerator(wrapperLookup, recipeExporter) {
            @Override
            public void generate() {
                List<ItemConvertible> custom_crops = List.of(ModItems.CORN);

                createShapeless(RecipeCategory.FOOD, ModItems.TOMATO_SEEDS)
                        .input(Items.WHEAT_SEEDS)
                        .input(Items.RED_DYE)
                        .criterion(hasItem(Items.WHEAT_SEEDS), conditionsFromItem(Items.WHEAT_SEEDS))
                        .criterion(hasItem(Items.RED_DYE), conditionsFromItem(Items.RED_DYE))
                        .offerTo(exporter);

                createShaped(RecipeCategory.FOOD, ModItems.BLACKBERRY_PIE)
                        .input('E', Items.BREAD)
                        .input('P', Items.PAPER)
                        .input('A', ModItems.BLACKBERRIES)
                        .pattern(" E ")
                        .pattern("EAE")
                        .pattern("PPP")
                        .criterion(hasItem(ModItems.BLACKBERRIES), conditionsFromItem(ModItems.BLACKBERRIES))
                        .criterion(hasItem(Items.BREAD), conditionsFromItem(Items.BREAD))
                        .criterion(hasItem(Items.PAPER), conditionsFromItem(Items.PAPER))
                        .offerTo(exporter);

                createShaped(RecipeCategory.FOOD, ModBlocks.PASTRY_STATION)
                        .input('E', Items.BREAD)
                        .input('P', Items.FURNACE)
                        .pattern(" E ")
                        .pattern("EPE")
                        .pattern(" E ")
                        .criterion(hasItem(Items.BREAD), conditionsFromItem(Items.BREAD))
                        .criterion(hasItem(Items.FURNACE), conditionsFromItem(Items.FURNACE))
                        .offerTo(exporter);

                createShapeless(RecipeCategory.FOOD, ModItems.CORN_KERNEL)
                        .input(Items.WHEAT_SEEDS)
                        .input(Items.YELLOW_DYE)
                        .criterion(hasItem(Items.WHEAT_SEEDS), conditionsFromItem(Items.WHEAT_SEEDS))
                        .criterion(hasItem(Items.YELLOW_DYE), conditionsFromItem(Items.YELLOW_DYE))
                        .offerTo(exporter);

                createShapeless(RecipeCategory.FOOD, ModItems.STRAWBERRY_LEMONADE, 3)
                        .input(ModItems.STRAWBERRIES)
                        .input(Items.GLASS_BOTTLE)
                        .criterion(hasItem(ModItems.STRAWBERRIES), conditionsFromItem(ModItems.STRAWBERRIES))
                        .criterion(hasItem(Items.GLASS_BOTTLE), conditionsFromItem(Items.GLASS_BOTTLE))
                        .offerTo(exporter);

                CookingRecipeJsonBuilder.createSmoking(Ingredient.ofItem(ModItems.CORN),
                        RecipeCategory.FOOD, ModItems.CORN_ON_THE_COB,
                        0.40F, 200).criterion("has_corn",
                        this.conditionsFromItem(ModItems.CORN)).offerTo(this.exporter);

                offerSmelting(custom_crops,
                        RecipeCategory.FOOD,
                        ModItems.CORN_ON_THE_COB,
                        0.3f,
                        200,
                        "custom-crops");

            }

            private static String hasTag(TagKey<Item> tag) {
                return "has_" + tag.id().toString();
            }
        };
    }

    @Override
    public String getName() {
        return "Custom Crops Recipes";
    }
}