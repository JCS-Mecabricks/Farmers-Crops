package github.jcsmecabricks.customcrops.group;

import github.jcsmecabricks.customcrops.CustomCrops;
import github.jcsmecabricks.customcrops.item.ModItems;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;

import java.util.Optional;

public class ModGroups {
    public static final Text FARMERS_CROPS_TITLE = Text.translatable("itemGroup." + CustomCrops.MOD_ID + ".farmers_crops_group");
    public static final ItemGroup CUSTOMWEAPONS_GROUP = register("farmers_crops_group", FabricItemGroup.builder()
            .displayName(FARMERS_CROPS_TITLE)
            .icon(ModItems.GRAPES::getDefaultStack)
            .entries((displayContext, entries) -> Registries.ITEM.getIds()
                    .stream()
                    .filter(key -> key.getNamespace().equals(CustomCrops.MOD_ID))
                    .map(Registries.ITEM::getOptionalValue)
                    .map(Optional::orElseThrow)
                    .forEach(entries::add))
            .build());
    public static <T extends ItemGroup> T register(String name, T itemGroup) {
        return Registry.register(Registries.ITEM_GROUP, CustomCrops.id(name), itemGroup);
    }
    public static void load() {
    }
}