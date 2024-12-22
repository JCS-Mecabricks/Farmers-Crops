package github.jcsmecabricks.customcrops.item;

import net.minecraft.component.type.ConsumableComponent;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.consume.ApplyEffectsConsumeEffect;
import net.minecraft.sound.SoundEvents;

import static net.minecraft.component.type.ConsumableComponents.drink;

public class ModFoodComponents {
    public static final FoodComponent BLUEBERRY = new FoodComponent.Builder()
            .nutrition(4)
            .saturationModifier(0.2f)
            .build();

    public static final FoodComponent STRAWBERRY = new FoodComponent.Builder()
            .nutrition(5)
            .saturationModifier(0.25f)
            .build();

    public static final FoodComponent BLACKBERRY = new FoodComponent.Builder()
            .nutrition(6)
            .saturationModifier(0.3f)
            .build();

    public static final FoodComponent BLACKBERRY_PIE = new FoodComponent.Builder()
            .nutrition(8)
            .saturationModifier(0.5f)
            .build();

    public static final FoodComponent TOMATO = new FoodComponent.Builder()
            .nutrition(7)
            .saturationModifier(0.25f)
            .build();

    public static final FoodComponent CORN = new FoodComponent.Builder()
            .nutrition(7)
            .saturationModifier(0.3f)
            .build();

    public static final FoodComponent MUFFIN = new FoodComponent.Builder()
            .nutrition(8)
            .saturationModifier(0.5f)
            .build();

    public static final FoodComponent CORN_COB = new FoodComponent.Builder()
            .nutrition(8)
            .saturationModifier(0.5f)
            .build();

    public static final ConsumableComponent STRAWBERRY_LEMONADE = drink()
            .consumeSeconds(2.0F)
            .sound(SoundEvents.ENTITY_GENERIC_DRINK)
            .build();

    public static final FoodComponent STRAWBERRY_LEMONADE_FOOD = new FoodComponent.Builder()
            .nutrition(7)
            .alwaysEdible()
            .saturationModifier(0.50f)
            .build();
}
