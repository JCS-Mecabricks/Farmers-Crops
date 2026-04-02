package github.jcsmecabricks.customcrops.world;

import github.jcsmecabricks.customcrops.CustomCrops;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.placement.RarityFilter;
import java.util.List;

public class ModPlacedFeatures {
    public static final ResourceKey<PlacedFeature> BLUEBERRY_BUSH_PLACED_KEY = registerKey("blueberry_bush_placed");
    public static final ResourceKey<PlacedFeature> STRAWBERRY_BUSH_PLACED_KEY = registerKey("strawberry_bush_placed");
    public static final ResourceKey<PlacedFeature> BLACKBERRY_BUSH_PLACED_KEY = registerKey("blackberry_bush_placed");

    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        var configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);
        register(context, BLUEBERRY_BUSH_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.BLUEBERRY_BUSH_KEY),
                RarityFilter.onAverageOnceEvery(30), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());

        register(context, STRAWBERRY_BUSH_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.STRAWBERRY_BUSH_KEY),
                RarityFilter.onAverageOnceEvery(30), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());

        register(context, BLACKBERRY_BUSH_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.BLACKBERRY_BUSH_KEY),
                RarityFilter.onAverageOnceEvery(30), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());
    }

    public static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, Identifier.fromNamespaceAndPath(CustomCrops.MOD_ID, name));
    }

    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key,
                                                                                   Holder<ConfiguredFeature<?, ?>> configuration,
                                                                                   PlacementModifier... modifiers) {
        register(context, key, configuration, List.of(modifiers));
    }
}