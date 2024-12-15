package github.jcsmecabricks.customcrops.item;

import net.minecraft.component.type.FoodComponent;

public class ModFoodComponents {
    public static final FoodComponent GRAPE = new FoodComponent.Builder()
            .nutrition(4)
            .saturationModifier(0.2f)
            .build();
}
