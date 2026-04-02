package github.jcsmecabricks.customcrops.world;

import github.jcsmecabricks.customcrops.CustomCrops;
import github.jcsmecabricks.customcrops.block.ModBlocks;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.SweetBerryBushBlock;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.VegetationPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.placement.CaveSurface;
import net.minecraft.util.valueproviders.IntProviders;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class ModConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> BLUEBERRY_BUSH_KEY = registerKey("blueberry_bush");
    public static final ResourceKey<ConfiguredFeature<?, ?>> STRAWBERRY_BUSH_KEY = registerKey("strawberry_bush");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BLACKBERRY_BUSH_KEY = registerKey("blackberry_bush");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        Holder<PlacedFeature> blueberryPlaced = context.lookup(Registries.PLACED_FEATURE).getOrThrow(ModPlacedFeatures.BLUEBERRY_BUSH_PLACED_KEY);
        Holder<PlacedFeature> strawberryPlaced = context.lookup(Registries.PLACED_FEATURE).getOrThrow(ModPlacedFeatures.STRAWBERRY_BUSH_PLACED_KEY);
        Holder<PlacedFeature> blackberryPlaced = context.lookup(Registries.PLACED_FEATURE).getOrThrow(ModPlacedFeatures.BLUEBERRY_BUSH_PLACED_KEY);
        // Blueberry Bush
        register(context, BLUEBERRY_BUSH_KEY, Feature.VEGETATION_PATCH,
                new VegetationPatchConfiguration(
                        BlockTags.LEAVES, // replaceable
                        BlockStateProvider.simple(ModBlocks.BLUEBERRY_BUSH.defaultBlockState()
                                .setValue(SweetBerryBushBlock.AGE, 3)),
                        blueberryPlaced,
                        CaveSurface.FLOOR,
                        ConstantInt.of(1),  // depth
                        0.0f,                      // extra bottom block chance
                        256,                       // vertical range
                        1.0f,                      // vegetation chance
                        ConstantInt.of(1),  // xz radius
                        0.0f                       // extra edge column chance
                )
        );

        // Strawberry Bush
        register(context, STRAWBERRY_BUSH_KEY, Feature.VEGETATION_PATCH,
                new VegetationPatchConfiguration(
                        BlockTags.LEAVES,
                        BlockStateProvider.simple(ModBlocks.STRAWBERRY_BUSH.defaultBlockState()
                                .setValue(SweetBerryBushBlock.AGE, 3)),
                        strawberryPlaced,
                        CaveSurface.FLOOR,
                        ConstantInt.of(1),
                        0.0f,
                        256,
                        1.0f,
                        ConstantInt.of(1),
                        0.0f
                )
        );

        // Blackberry Bush
        register(context, BLACKBERRY_BUSH_KEY, Feature.VEGETATION_PATCH,
                new VegetationPatchConfiguration(
                        BlockTags.LEAVES,
                        BlockStateProvider.simple(ModBlocks.BLACKBERRY_BUSH.defaultBlockState()
                                .setValue(SweetBerryBushBlock.AGE, 3)),
                        blackberryPlaced,
                        CaveSurface.FLOOR,
                        ConstantInt.of(1),
                        0.0f,
                        256,
                        1.0f,
                        ConstantInt.of(1),
                        0.0f
                )
        );
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, Identifier.fromNamespaceAndPath(CustomCrops.MOD_ID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>>
    void register(BootstrapContext<ConfiguredFeature<?, ?>> context,
                  ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}