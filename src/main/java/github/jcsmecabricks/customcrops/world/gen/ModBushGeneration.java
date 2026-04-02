package github.jcsmecabricks.customcrops.world.gen;

import github.jcsmecabricks.customcrops.world.ModPlacedFeatures;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep;

public class ModBushGeneration {
    public static void generateBushes() {
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.FOREST),
                GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.BLUEBERRY_BUSH_PLACED_KEY);

        BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.TAIGA),
                GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.STRAWBERRY_BUSH_PLACED_KEY);

        BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.TAIGA, Biomes.OLD_GROWTH_SPRUCE_TAIGA,
                        Biomes.OLD_GROWTH_PINE_TAIGA, Biomes.FOREST),
                GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.BLACKBERRY_BUSH_PLACED_KEY);
    }
}