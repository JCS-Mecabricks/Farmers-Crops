package github.jcsmecabricks.customcrops.block.custom;

import github.jcsmecabricks.customcrops.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.SweetBerryBushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;

public class BlueberryBushBlock extends SweetBerryBushBlock {
    public BlueberryBushBlock(Properties settings) {
        super(settings);
    }

    @Override
    protected ItemStack getCloneItemStack(LevelReader world, BlockPos pos, BlockState state, boolean includeData) {
        return new ItemStack(ModItems.BLUEBERRIES);
    }

    protected InteractionResult useWithoutItem(BlockState state, Level world, BlockPos pos, Player player, BlockHitResult hit) {
        int i = (Integer)state.getValue(AGE);
        boolean bl = i == 3;
        if (i > 1) {
            int j = 1 + world.getRandom().nextInt(2);
            popResource(world, pos, new ItemStack(ModItems.BLUEBERRIES, j + (bl ? 1 : 0)));
            world.playSound((Player)null, pos, SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, SoundSource.BLOCKS, 1.0F, 0.8F + world.getRandom().nextFloat() * 0.4F);
            BlockState blockState = (BlockState)state.setValue(AGE, 1);
            world.setBlock(pos, blockState, 2);
            world.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(player, blockState));
            return InteractionResult.SUCCESS;
        } else {
            return super.useWithoutItem(state, world, pos, player, hit);
        }
    }
}
