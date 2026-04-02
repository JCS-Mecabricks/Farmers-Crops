package github.jcsmecabricks.customcrops.item.custom;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

public class StrawberryLemonadeItem extends Item {

    public StrawberryLemonadeItem(Properties settings) {
        super(settings);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity user) {
        if (user instanceof Player player) {
            if (!player.getInventory().add(new ItemStack(Items.GLASS_BOTTLE))) {
                // Drop the coffee cup if inventory is full
                player.drop(new ItemStack(Items.GLASS_BOTTLE), false);
            }
        }
        // Reduce the consumed item by 1
        return super.finishUsingItem(stack, world, user);
    }
}
