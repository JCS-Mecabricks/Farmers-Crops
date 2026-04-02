package github.jcsmecabricks.customcrops.recipe;

import github.jcsmecabricks.customcrops.CustomCrops;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;

public class ModRecipes {

    public static final RecipeType<PastryStationRecipe> PASTRY_STATION_TYPE =
            Registry.register(
                    BuiltInRegistries.RECIPE_TYPE,
                    Identifier.fromNamespaceAndPath(CustomCrops.MOD_ID, "baking"),
                    new RecipeType<>() {}
            );

    public static final RecipeSerializer<PastryStationRecipe> PASTRY_STATION_SERIALIZER =
            new RecipeSerializer<>(
                    PastryStationRecipe.CODEC,
                    PastryStationRecipe.STREAM_CODEC
            );

    public static void registerRecipes() {
        Registry.register(
                BuiltInRegistries.RECIPE_SERIALIZER,
                Identifier.fromNamespaceAndPath(CustomCrops.MOD_ID, "baking"),
                PASTRY_STATION_SERIALIZER
        );
    }
}