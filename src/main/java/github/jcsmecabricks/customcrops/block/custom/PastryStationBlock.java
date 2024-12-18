package github.jcsmecabricks.customcrops.block.custom;

import com.mojang.serialization.MapCodec;
import github.jcsmecabricks.customcrops.block.entity.ModBlockEntities;
import github.jcsmecabricks.customcrops.block.entity.custom.PastryStationBlockEntity;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class PastryStationBlock extends BlockWithEntity {
    public static final MapCodec<PastryStationBlock> CODEC = createCodec(PastryStationBlock::new);

    public PastryStationBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return CODEC;
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new PastryStationBlockEntity(pos, state);
    }

    @Override
    protected BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.getBlock() != newState.getBlock()) {
            if (world.getBlockEntity(pos) instanceof PastryStationBlockEntity pastryStationBlockEntity) {
                ItemScatterer.spawn(world, pos, pastryStationBlockEntity);
                world.updateComparators(pos,this);
            }
            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }

    @Override
    protected ActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient) {
            NamedScreenHandlerFactory screenHandlerFactory = ((PastryStationBlockEntity) world.getBlockEntity(pos));

            if (screenHandlerFactory != null) {
                player.openHandledScreen(screenHandlerFactory);
            }
        }

        return ActionResult.SUCCESS;
    }

    @Override
        public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
            return validateTicker(type, ModBlockEntities.PASTRY_BE, (world1, pos, state1, blockEntity) -> blockEntity.tick(world1, pos, state1));
        }
}
