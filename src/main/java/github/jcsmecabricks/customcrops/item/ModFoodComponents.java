package github.jcsmecabricks.customcrops.item;

import static net.minecraft.world.item.component.Consumables.defaultDrink;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.component.Consumable;

public class ModFoodComponents {
    public static final FoodProperties BLUEBERRY = new FoodProperties.Builder()
            .nutrition(4)
            .saturationModifier(0.2f)
            .build();

    public static final FoodProperties STRAWBERRY = new FoodProperties.Builder()
            .nutrition(5)
            .saturationModifier(0.25f)
            .build();

    public static final FoodProperties BLACKBERRY = new FoodProperties.Builder()
            .nutrition(6)
            .saturationModifier(0.3f)
            .build();

    public static final FoodProperties BLACKBERRY_PIE = new FoodProperties.Builder()
            .nutrition(8)
            .saturationModifier(0.5f)
            .build();

    public static final FoodProperties TOMATO = new FoodProperties.Builder()
            .nutrition(7)
            .saturationModifier(0.25f)
            .build();

    public static final FoodProperties CORN = new FoodProperties.Builder()
            .nutrition(7)
            .saturationModifier(0.3f)
            .build();

    public static final FoodProperties MUFFIN = new FoodProperties.Builder()
            .nutrition(8)
            .saturationModifier(0.5f)
            .build();

    public static final FoodProperties CORN_COB = new FoodProperties.Builder()
            .nutrition(8)
            .saturationModifier(0.5f)
            .build();

    public static final Consumable STRAWBERRY_LEMONADE = defaultDrink()
            .consumeSeconds(2.0F)
            .sound(SoundEvents.GENERIC_DRINK)
            .build();

    public static final FoodProperties STRAWBERRY_LEMONADE_FOOD = new FoodProperties.Builder()
            .nutrition(7)
            .alwaysEdible()
            .saturationModifier(0.50f)
            .build();
}
