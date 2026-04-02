package github.jcsmecabricks.customcrops.datagen;

import github.jcsmecabricks.customcrops.block.ModBlocks;
import github.jcsmecabricks.customcrops.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CookingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeBookCategories;
import net.minecraft.world.level.ItemLike;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeProvider createRecipeProvider(HolderLookup.Provider wrapperLookup, RecipeOutput recipeExporter) {
        return new RecipeProvider(wrapperLookup, recipeExporter) {
            @Override
            public void buildRecipes() {
                List<ItemLike> custom_crops = List.of(ModItems.CORN);

                shaped(RecipeCategory.FOOD, ModItems.BLACKBERRY_PIE)
                        .define('E', Items.BREAD)
                        .define('P', Items.PAPER)
                        .define('A', ModItems.BLACKBERRIES)
                        .pattern(" E ")
                        .pattern("EAE")
                        .pattern("PPP")
                        .unlockedBy(getHasName(ModItems.BLACKBERRIES), has(ModItems.BLACKBERRIES))
                        .unlockedBy(getHasName(Items.BREAD), has(Items.BREAD))
                        .unlockedBy(getHasName(Items.PAPER), has(Items.PAPER))
                        .save(output);

                shaped(RecipeCategory.FOOD, ModBlocks.PASTRY_STATION)
                        .define('E', Items.BREAD)
                        .define('P', Items.FURNACE)
                        .pattern(" E ")
                        .pattern("EPE")
                        .pattern(" E ")
                        .unlockedBy(getHasName(Items.BREAD), has(Items.BREAD))
                        .unlockedBy(getHasName(Items.FURNACE), has(Items.FURNACE))
                        .save(output);

                shapeless(RecipeCategory.FOOD, ModItems.STRAWBERRY_LEMONADE, 3)
                        .requires(ModItems.STRAWBERRIES)
                        .requires(Items.GLASS_BOTTLE)
                        .unlockedBy(getHasName(ModItems.STRAWBERRIES), has(ModItems.STRAWBERRIES))
                        .unlockedBy(getHasName(Items.GLASS_BOTTLE), has(Items.GLASS_BOTTLE))
                        .save(output);

                SimpleCookingRecipeBuilder.smoking(Ingredient.of(ModItems.CORN),
                        RecipeCategory.FOOD, ModItems.CORN_ON_THE_COB,
                        0.40F, 200).unlockedBy("has_corn",
                        this.has(ModItems.CORN)).save(this.output);

                oreSmelting(custom_crops,
                        RecipeCategory.FOOD,
                        CookingBookCategory.FOOD,
                        ModItems.CORN_ON_THE_COB,
                        0.3f,
                        200,
                        "custom-crops");

            }

            private static String hasTag(TagKey<Item> tag) {
                return "has_" + tag.location().toString();
            }
        };
    }

    @Override
    public String getName() {
        return "Custom Crops Recipes";
    }
}