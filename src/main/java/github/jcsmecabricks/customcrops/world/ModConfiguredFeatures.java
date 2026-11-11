package github.jcsmecabricks.customcrops.world;

import github.jcsmecabricks.customcrops.CustomCrops;
import github.jcsmecabricks.customcrops.block.ModBlocks;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.SweetBerryBushBlock;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.SimpleBlockFeature;
import net.minecraft.world.level.levelgen.feature.SimpleRandomSelectorFeature;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.placement.CaveSurface;
import net.minecraft.util.valueproviders.IntProviders;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class ModConfiguredFeatures {
    public static final ResourceKey<Feature> BLUEBERRY_BUSH_KEY = registerKey("blueberry_bush");
    public static final ResourceKey<Feature> STRAWBERRY_BUSH_KEY = registerKey("strawberry_bush");
    public static final ResourceKey<Feature> BLACKBERRY_BUSH_KEY = registerKey("blackberry_bush");

    public static void bootstrap(BootstrapContext<Feature> context) {
        var blocks = context.lookup(Registries.BLOCK);
        Holder<PlacedFeature> strawberryPlaced = context.lookup(Registries.PLACED_FEATURE).getOrThrow(ModPlacedFeatures.STRAWBERRY_BUSH_PLACED_KEY);
        Holder<PlacedFeature> blackberryPlaced = context.lookup(Registries.PLACED_FEATURE).getOrThrow(ModPlacedFeatures.BLUEBERRY_BUSH_PLACED_KEY);
        // Blueberry Bush
        context.register(BLUEBERRY_BUSH_KEY, new SimpleRandomSelectorFeature(
                HolderSet.direct(PlacementUtils.inlinePlaced(new SimpleBlockFeature(
                        BlockStateProvider.of(ModBlocks.BLUEBERRY_BUSH.defaultBlockState()
                                .setValue(SweetBerryBushBlock.AGE, 3)))))));

        // Strawberry Bush
        context.register(STRAWBERRY_BUSH_KEY, new SimpleRandomSelectorFeature(
                HolderSet.direct(PlacementUtils.inlinePlaced(new SimpleBlockFeature(
                        BlockStateProvider.holderOf(ModBlocks.STRAWBERRY_BUSH.defaultBlockState()
                                .setValue(SweetBerryBushBlock.AGE, 3))
                )))));

        // Blackberry Bush
        context.register(BLACKBERRY_BUSH_KEY, new SimpleRandomSelectorFeature(
                HolderSet.direct(PlacementUtils.inlinePlaced(new SimpleBlockFeature(
                        BlockStateProvider.holderOf(ModBlocks.BLACKBERRY_BUSH.defaultBlockState()
                                .setValue(SweetBerryBushBlock.AGE, 3)))))));
    }

    public static ResourceKey<Feature> registerKey(String name) {
        return ResourceKey.create(Registries.FEATURE, Identifier.fromNamespaceAndPath(CustomCrops.MOD_ID, name));
    }
}