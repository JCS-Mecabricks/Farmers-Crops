package github.jcsmecabricks.customcrops.block.entity.custom;

import github.jcsmecabricks.customcrops.block.custom.PastryStationBlock;
import github.jcsmecabricks.customcrops.block.entity.ImplementedInventory;
import github.jcsmecabricks.customcrops.block.entity.ModBlockEntities;
import github.jcsmecabricks.customcrops.item.ModItems;
import github.jcsmecabricks.customcrops.recipe.ModRecipes;
import github.jcsmecabricks.customcrops.recipe.PastryStationRecipe;
import github.jcsmecabricks.customcrops.recipe.PastryStationRecipeInput;
import github.jcsmecabricks.customcrops.screen.custom.PastryScreenHandler;
import net.fabricmc.fabric.api.menu.v1.ExtendedMenuProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.Containers;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class PastryStationBlockEntity extends BlockEntity implements ExtendedMenuProvider<BlockPos>, ImplementedInventory {
    private final NonNullList<ItemStack> inventory = NonNullList.withSize(2, ItemStack.EMPTY);

    private static final int INPUT_SLOT = 0;
    private static final int OUTPUT_SLOT = 1;

    protected final ContainerData propertyDelegate;
    private int progress = 0;
    private int maxProgress = 72;

    public PastryStationBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.PASTRY_BE, pos, state);
        this.propertyDelegate = new ContainerData() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> PastryStationBlockEntity.this.progress;
                    case 1 -> PastryStationBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0: PastryStationBlockEntity.this.progress = value;
                    case 1: PastryStationBlockEntity.this.maxProgress = value;
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        };
    }

    @Override
    public BlockPos getScreenOpeningData(ServerPlayer player) {
        return this.worldPosition;
    }

    @Override
    public NonNullList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("gui.custom-crops.pastry_station");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int syncId, Inventory playerInventory, Player player) {
        return new PastryScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
    }

    @Override
    public void preRemoveSideEffects(BlockPos pos, BlockState oldState) {
        Containers.dropContents(level, pos, (this));
        super.preRemoveSideEffects(pos, oldState);
    }

    @Override
    protected void loadAdditional(ValueInput view) {
        super.loadAdditional(view);
        ContainerHelper.loadAllItems(view, inventory);
        progress = view.getIntOr("pastry_station.progress", 0);
        maxProgress = view.getIntOr("pastry_station.max_progress", 0);
    }

    @Override
    protected void saveAdditional(ValueOutput view) {
        super.saveAdditional(view);
        ContainerHelper.saveAllItems(view, inventory);
        view.putInt("pastry_station.progress", progress);
        view.putInt("pastry_station.max_progress", maxProgress);
    }

    public void tick(Level world, BlockPos pos, BlockState state) {
        if (world.isClientSide()) return; // run only on server

        boolean hasRecipe = hasRecipe();
        boolean isLit = state.getValue(PastryStationBlock.LIT);

        if (hasRecipe && !isLit) {
            world.setBlock(pos, state.setValue(PastryStationBlock.LIT, true), 3);
        } else if (!hasRecipe && isLit) {
            world.setBlock(pos, state.setValue(PastryStationBlock.LIT, false), 3);
        }

        if (hasRecipe) {
            increaseCraftingProgress();
            setChanged(world, pos, state);

            if (hasCraftingFinished()) {
                craftItem();
                resetProgress();
            }
        } else {
            resetProgress();
        }
    }


    private void resetProgress() {
        this.progress = 0;
        this.maxProgress = 72;
    }

    private void craftItem() {
        Optional<RecipeHolder<PastryStationRecipe>> recipe = getCurrentRecipe();

        if (recipe.isEmpty()) return;

        PastryStationRecipe r = recipe.get().value();

        ItemStack output = new ItemStack(r.resultItem());

        this.removeItem(INPUT_SLOT, 1);

        this.setItem(OUTPUT_SLOT, new ItemStack(
                output.getItem(),
                this.getItem(OUTPUT_SLOT).getCount() + output.getCount()
        ));
    }

    private boolean hasCraftingFinished() {
        return this.progress >= this.maxProgress;
    }

    private void increaseCraftingProgress() {
        this.progress++;
    }

    private boolean hasRecipe() {
        Optional<RecipeHolder<PastryStationRecipe>> recipe = getCurrentRecipe();
        if(recipe.isEmpty()) {
            return false;
        }

        PastryStationRecipe r = recipe.get().value();

        ItemStack output = new ItemStack(r.resultItem());
        return canInsertAmountIntoOutputSlot(output.getCount()) && canInsertItemIntoOutputSlot(output);
    }

    private Optional<RecipeHolder<PastryStationRecipe>> getCurrentRecipe() {
        return ((ServerLevel) this.getLevel()).recipeAccess()
                .getRecipeFor(ModRecipes.PASTRY_STATION_TYPE, new PastryStationRecipeInput(inventory.get(INPUT_SLOT)), this.getLevel());
    }

    private boolean canInsertItemIntoOutputSlot(ItemStack output) {
        return this.getItem(OUTPUT_SLOT).isEmpty() || this.getItem(OUTPUT_SLOT).getItem() == output.getItem();
    }

    private boolean canInsertAmountIntoOutputSlot(int count) {
        int maxCount = this.getItem(OUTPUT_SLOT).isEmpty() ? 64 : this.getItem(OUTPUT_SLOT).getMaxStackSize();
        int currentCount = this.getItem(OUTPUT_SLOT).getCount();

        return maxCount >= currentCount + count;
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registryLookup) {
        return saveWithoutMetadata(registryLookup);
    }
}