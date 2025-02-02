package github.jcsmecabricks.customcrops.item.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.World;

public class StrawberryLemonadeItem extends Item {

    public StrawberryLemonadeItem(Settings settings) {
        super(settings);
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if (user instanceof PlayerEntity player) {
            if (!player.getInventory().insertStack(new ItemStack(Items.GLASS_BOTTLE))) {
                // Drop the coffee cup if inventory is full
                player.dropItem(new ItemStack(Items.GLASS_BOTTLE), false);
            }
        }
        // Reduce the consumed item by 1
        return super.finishUsing(stack, world, user);
    }
}
