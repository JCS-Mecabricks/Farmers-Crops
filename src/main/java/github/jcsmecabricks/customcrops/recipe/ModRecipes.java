package github.jcsmecabricks.customcrops.recipe;

import github.jcsmecabricks.customcrops.CustomCrops;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModRecipes {
    public static final RecipeSerializer<PastryStationRecipe> PASTRY_STATION_SERIALIZER = Registry.register(
            Registries.RECIPE_SERIALIZER, Identifier.of(CustomCrops.MOD_ID, "baking"),
            new PastryStationRecipe.Serializer());
    public static final RecipeType<PastryStationRecipe> PASTRY_STATION_TYPE = Registry.register(
            Registries.RECIPE_TYPE, Identifier.of(CustomCrops.MOD_ID, "baking"), new RecipeType<PastryStationRecipe>() {
                @Override
                public String toString() {
                    return "baking";
                }
            });

    public static void registerRecipes() {
    }
}
