package github.jcsmecabricks.customcrops.block.entity.custom;

import github.jcsmecabricks.customcrops.block.custom.PastryStationBlock;
import github.jcsmecabricks.customcrops.block.entity.ImplementedInventory;
import github.jcsmecabricks.customcrops.block.entity.ModBlockEntities;
import github.jcsmecabricks.customcrops.item.ModItems;
import github.jcsmecabricks.customcrops.recipe.ModRecipes;
import github.jcsmecabricks.customcrops.recipe.PastryStationRecipe;
import github.jcsmecabricks.customcrops.recipe.PastryStationRecipeInput;
import github.jcsmecabricks.customcrops.screen.custom.PastryScreenHandler;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.text.Text;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class PastryStationBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory<BlockPos>, ImplementedInventory {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(2, ItemStack.EMPTY);

    private static final int INPUT_SLOT = 0;
    private static final int OUTPUT_SLOT = 1;

    protected final PropertyDelegate propertyDelegate;
    private int progress = 0;
    private int maxProgress = 72;

    public PastryStationBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.PASTRY_BE, pos, state);
        this.propertyDelegate = new PropertyDelegate() {
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
            public int size() {
                return 2;
            }
        };
    }

    @Override
    public BlockPos getScreenOpeningData(ServerPlayerEntity player) {
        return this.pos;
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable("gui.custom-crops.pastry_station");
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new PastryScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
    }

    @Override
    public void onBlockReplaced(BlockPos pos, BlockState oldState) {
        ItemScatterer.spawn(world, pos, (this));
        super.onBlockReplaced(pos, oldState);
    }

    @Override
    protected void readData(ReadView view) {
        super.readData(view);
        Inventories.readData(view, inventory);
        progress = view.getInt("pastry_station.progress", 0);
        maxProgress = view.getInt("pastry_station.max_progress", 0);
    }

    @Override
    protected void writeData(WriteView view) {
        super.writeData(view);
        Inventories.writeData(view, inventory);
        view.putInt("pastry_station.progress", progress);
        view.putInt("pastry_station.max_progress", maxProgress);
    }

    public void tick(World world, BlockPos pos, BlockState state) {
        if (world.isClient) return; // run only on server

        boolean hasRecipe = hasRecipe();
        boolean isLit = state.get(PastryStationBlock.LIT);

        if (hasRecipe && !isLit) {
            world.setBlockState(pos, state.with(PastryStationBlock.LIT, true), 3);
        } else if (!hasRecipe && isLit) {
            world.setBlockState(pos, state.with(PastryStationBlock.LIT, false), 3);
        }

        if (hasRecipe) {
            increaseCraftingProgress();
            markDirty(world, pos, state);

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
        Optional<RecipeEntry<PastryStationRecipe>> recipe = getCurrentRecipe();

        ItemStack output = recipe.get().value().output();
        this.removeStack(INPUT_SLOT, 1);
        this.setStack(OUTPUT_SLOT, new ItemStack(output.getItem(),
                this.getStack(OUTPUT_SLOT).getCount() + output.getCount()));
    }

    private boolean hasCraftingFinished() {
        return this.progress >= this.maxProgress;
    }

    private void increaseCraftingProgress() {
        this.progress++;
    }

    private boolean hasRecipe() {
        Optional<RecipeEntry<PastryStationRecipe>> recipe = getCurrentRecipe();
        if(recipe.isEmpty()) {
            return false;
        }

        ItemStack output = recipe.get().value().output();
        return canInsertAmountIntoOutputSlot(output.getCount()) && canInsertItemIntoOutputSlot(output);
    }

    private Optional<RecipeEntry<PastryStationRecipe>> getCurrentRecipe() {
        return ((ServerWorld) this.getWorld()).getRecipeManager()
                .getFirstMatch(ModRecipes.PASTRY_STATION_TYPE, new PastryStationRecipeInput(inventory.get(INPUT_SLOT)), this.getWorld());
    }

    private boolean canInsertItemIntoOutputSlot(ItemStack output) {
        return this.getStack(OUTPUT_SLOT).isEmpty() || this.getStack(OUTPUT_SLOT).getItem() == output.getItem();
    }

    private boolean canInsertAmountIntoOutputSlot(int count) {
        int maxCount = this.getStack(OUTPUT_SLOT).isEmpty() ? 64 : this.getStack(OUTPUT_SLOT).getMaxCount();
        int currentCount = this.getStack(OUTPUT_SLOT).getCount();

        return maxCount >= currentCount + count;
    }

    @Nullable
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt(RegistryWrapper.WrapperLookup registryLookup) {
        return createNbt(registryLookup);
    }
}