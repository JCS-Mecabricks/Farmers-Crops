package github.jcsmecabricks.customcrops.recipe;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.NonNullList;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;

public record PastryStationRecipe(Ingredient inputItem, Item resultItem)
        implements Recipe<PastryStationRecipeInput> {

    public static final MapCodec<PastryStationRecipe> CODEC =
            RecordCodecBuilder.mapCodec(inst -> inst.group(
                    Ingredient.CODEC.fieldOf("ingredient")
                            .forGetter(PastryStationRecipe::inputItem),

                    BuiltInRegistries.ITEM.byNameCodec().fieldOf("result")
                            .forGetter(PastryStationRecipe::resultItem)

            ).apply(inst, PastryStationRecipe::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, PastryStationRecipe> STREAM_CODEC =
            new StreamCodec<>() {

                @Override
                public PastryStationRecipe decode(RegistryFriendlyByteBuf buf) {
                    Ingredient ingredient = Ingredient.CONTENTS_STREAM_CODEC.decode(buf);

                    int id = buf.readVarInt();
                    Item item = BuiltInRegistries.ITEM.byId(id);

                    return new PastryStationRecipe(ingredient, item);
                }

                @Override
                public void encode(RegistryFriendlyByteBuf buf, PastryStationRecipe recipe) {
                    Ingredient.CONTENTS_STREAM_CODEC.encode(buf, recipe.inputItem());

                    int id = BuiltInRegistries.ITEM.getId(recipe.resultItem());
                    buf.writeVarInt(id);
                }
            };

    // ===================== RECIPE LOGIC =====================

    @Override
    public boolean matches(PastryStationRecipeInput input, Level level) {
        if (level.isClientSide()) return false;
        return inputItem.test(input.getItem(0));
    }

    @Override
    public ItemStack assemble(PastryStationRecipeInput input) {
        return new ItemStack(resultItem);
    }

    @Override
    public boolean showNotification() {
        return false;
    }

    @Override
    public String group() {
        return "";
    }

    public NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> list = NonNullList.create();
        list.add(inputItem);
        return list;
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
    public PlacementInfo placementInfo() {
        return PlacementInfo.create(inputItem);
    }

    @Override
    public RecipeBookCategory recipeBookCategory() {
        return RecipeBookCategories.CRAFTING_MISC;
    }
}