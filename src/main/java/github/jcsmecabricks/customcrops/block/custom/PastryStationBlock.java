package github.jcsmecabricks.customcrops.block.custom;

import com.mojang.serialization.MapCodec;
import github.jcsmecabricks.customcrops.block.entity.ModBlockEntities;
import github.jcsmecabricks.customcrops.block.entity.custom.PastryStationBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.*;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

public class PastryStationBlock extends BaseEntityBlock {
    public static final EnumProperty<Direction> FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty LIT = BlockStateProperties.LIT;
    public static final MapCodec<PastryStationBlock> CODEC = simpleCodec(PastryStationBlock::new);

    public PastryStationBlock(Properties settings) {
        super(settings);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    protected BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    @Override
    protected BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        return this.defaultBlockState().setValue(FACING, ctx.getHorizontalDirection().getOpposite());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, LIT);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new PastryStationBlockEntity(pos, state);
    }

    @Override
    protected RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    protected void affectNeighborsAfterRemoval(BlockState state, ServerLevel world, BlockPos pos, boolean moved) {
        if (state.getBlock() != state.getBlock()) {
            if (world.getBlockEntity(pos) instanceof PastryStationBlockEntity pastryStationBlockEntity) {
                Containers.dropContents(world, pos, pastryStationBlockEntity);
                world.updateNeighbourForOutputSignal(pos,this);
            }
            super.affectNeighborsAfterRemoval(state, world, pos, moved);
        }
    }


    @Override
    protected InteractionResult useItemOn(ItemStack stack, BlockState state, Level world, BlockPos pos,
                                         Player player, InteractionHand hand, BlockHitResult hit) {
        if (!world.isClientSide()) {
            MenuProvider screenHandlerFactory = ((PastryStationBlockEntity) world.getBlockEntity(pos));
            if (screenHandlerFactory != null) {
                player.openMenu(screenHandlerFactory);
            }
        }
        return InteractionResult.SUCCESS;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level world, BlockState state, BlockEntityType<T> type) {
        if(world.isClientSide()) {
            return null;
        }

        return createTickerHelper(type, ModBlockEntities.PASTRY_BE,
                (world1, pos, state1, blockEntity) -> blockEntity.tick(world1, pos, state1));
    }

    @Override
    public void animateTick(BlockState state, Level world, BlockPos pos, RandomSource random) {
        if (!state.getValue(LIT)) {
            return;
        }

        double xPos = (double)pos.getX() + 0.5;
        double yPos = pos.getY();
        double zPos = (double)pos.getZ() + 0.5;
        if (random.nextDouble() < 0.15) {
            world.playSound(null, xPos, yPos, zPos, SoundEvents.BLASTFURNACE_FIRE_CRACKLE, SoundSource.BLOCKS);
        }

        Direction direction = state.getValue(FACING);
        Direction.Axis axis = direction.getAxis();

        double defaultOffset = random.nextDouble() * 0.6 - 0.3;
        double xOffsets = axis == Direction.Axis.X ? (double)direction.getStepX() * 0.52 : defaultOffset;
        double yOffset = random.nextDouble() * 6.0 / 8.0;
        double zOffset = axis == Direction.Axis.Z ? (double)direction.getStepZ() * 0.52 : defaultOffset;

        world.addParticle(ParticleTypes.SMOKE, xPos + xOffsets, yPos + yOffset, zPos + zOffset, 0.0, 0.0, 0.0);

        if(world.getBlockEntity(pos) instanceof PastryStationBlockEntity pastrystationBlockEntity && !pastrystationBlockEntity.getItem(1).isEmpty()) {
            world.addParticle(new ItemParticleOption(ParticleTypes.ITEM, pastrystationBlockEntity.getItem(1).getItem()),
                    xPos + xOffsets, yPos + yOffset, zPos + zOffset, 0.0, 0.0, 0.0);
        }
    }
}