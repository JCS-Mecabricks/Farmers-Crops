package github.jcsmecabricks.customcrops.recipe;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.recipe.*;
import net.minecraft.recipe.book.RecipeBookCategories;
import net.minecraft.recipe.book.RecipeBookCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;

public record PastryStationRecipe(Ingredient inputItem, ItemStack output) implements Recipe<PastryStationRecipeInput> {
    public DefaultedList<Ingredient> getIngredients() {
        DefaultedList<Ingredient> list = DefaultedList.of();
        list.add(this.inputItem);
        return list;
    }

    // read Recipe JSON files --> new GrowthChamberRecipe

    @Override
    public boolean matches(PastryStationRecipeInput input, World world) {
        if(world.isClient()) {
            return false;
        }

        return inputItem.test(input.getStackInSlot(0));
    }

    @Override
    public ItemStack craft(PastryStationRecipeInput input, RegistryWrapper.WrapperLookup lookup) {
        return output.copy();
    }

    @Override
    public RecipeSerializer<? extends Recipe<PastryStationRecipeInput>> getSerializer() {
        return ModRecipes.PASTRY_STATION_SERIALIZER;
    }

    @Override
    public RecipeType<? extends Recipe<PastryStationRecipeInput>> getType() {
        return ModRecipes.PASTRY_STATION_TYPE;
    }

    @Override
    public IngredientPlacement getIngredientPlacement() {
        return IngredientPlacement.forSingleSlot(inputItem);
    }

    @Override
    public RecipeBookCategory getRecipeBookCategory() {
        return RecipeBookCategories.CRAFTING_MISC;
    }

    public static class Serializer implements RecipeSerializer<PastryStationRecipe> {
        public static final MapCodec<PastryStationRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
                Ingredient.CODEC.fieldOf("ingredient").forGetter(PastryStationRecipe::inputItem),
                ItemStack.CODEC.fieldOf("result").forGetter(PastryStationRecipe::output)
        ).apply(inst, PastryStationRecipe::new));

        public static final PacketCodec<RegistryByteBuf, PastryStationRecipe> STREAM_CODEC =
                PacketCodec.tuple(
                        Ingredient.PACKET_CODEC, PastryStationRecipe::inputItem,
                        ItemStack.PACKET_CODEC, PastryStationRecipe::output,
                        PastryStationRecipe::new);

        @Override
        public MapCodec<PastryStationRecipe> codec() {
            return CODEC;
        }

        @Override
        public PacketCodec<RegistryByteBuf, PastryStationRecipe> packetCodec() {
            return STREAM_CODEC;
        }
    }
}