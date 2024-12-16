package github.jcsmecabricks.customcrops.item;

import net.minecraft.component.type.FoodComponent;

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
}
