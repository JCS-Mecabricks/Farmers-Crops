package github.jcsmecabricks.customcrops.block.custom;

import github.jcsmecabricks.customcrops.item.ModItems;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class TomatoCropBlock extends CropBlock {
    public static final int MAX_AGE = 5;
    public static final IntegerProperty AGE = IntegerProperty.create("age", 0, 5);

    public TomatoCropBlock(Properties settings) {
        super(settings);
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return ModItems.TOMATO_SEEDS;
    }

    @Override
    public IntegerProperty getAgeProperty() {
        return AGE;
    }

    @Override
    public int getMaxAge() {
        return MAX_AGE;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }
}