package github.jcsmecabricks.customcrops.datagen;

import github.jcsmecabricks.customcrops.block.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;
import java.util.concurrent.CompletableFuture;

public class ModBlockTags extends FabricTagsProvider.BlockTagsProvider {
    public ModBlockTags(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider wrapperLookup) {
        valueLookupBuilder(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.PASTRY_STATION);

        valueLookupBuilder(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.PASTRY_STATION);
    }
}
