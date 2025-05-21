package github.jcsmecabricks.customcrops.block;

import github.jcsmecabricks.customcrops.CustomCrops;
import github.jcsmecabricks.customcrops.block.custom.*;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public class ModBlocks {
    public static final Block BLUEBERRY_BUSH = registerBlockWithoutBlockItem("blueberry_bush",
            new BlueberryBushBlock(AbstractBlock.Settings.copy(Blocks.SWEET_BERRY_BUSH)
                    .registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(CustomCrops.MOD_ID, "blueberry_bush")))));

    public static final Block STRAWBERRY_BUSH = registerBlockWithoutBlockItem("strawberry_bush",
            new StrawberryBushBlock(AbstractBlock.Settings.copy(Blocks.SWEET_BERRY_BUSH)
                    .registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(CustomCrops.MOD_ID, "strawberry_bush")))));

    public static final Block BLACKBERRY_BUSH = registerBlockWithoutBlockItem("blackberry_bush",
            new BlackberryBushBlock(AbstractBlock.Settings.copy(Blocks.SWEET_BERRY_BUSH)
                    .registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(CustomCrops.MOD_ID, "blackberry_bush")))));

    public static final Block TOMATO_CROP = registerBlockWithoutBlockItem("tomato_crop",
            new TomatoCropBlock(AbstractBlock.Settings.create().noCollision()
                    .ticksRandomly().breakInstantly().sounds(BlockSoundGroup.CROP).pistonBehavior(PistonBehavior.DESTROY)
                    .registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(CustomCrops.MOD_ID, "tomato_crop")))));

    public static final Block CORN_CROP = registerBlockWithoutBlockItem("corn_crop",
            new CornCropBlock(AbstractBlock.Settings.create().noCollision()
                    .ticksRandomly().breakInstantly().sounds(BlockSoundGroup.CROP).pistonBehavior(PistonBehavior.DESTROY)
                    .registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(CustomCrops.MOD_ID, "corn_crop")))));

    public static final Block PASTRY_STATION = registerBlock("pastry_station", properties ->
            new PastryStationBlock(properties.strength(2f).requiresTool()));



    private static Block registerBlock(String name, Function<AbstractBlock.Settings, Block> function) {
        Block toRegister = function.apply(AbstractBlock.Settings.create().registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(CustomCrops.MOD_ID, name))));
        registerBlockItem(name, toRegister);
        return Registry.register(Registries.BLOCK, Identifier.of(CustomCrops.MOD_ID, name), toRegister);
    }

    public static <T extends Block> T register(String name, T block) {
        CustomCrops.LOGGER.info("Registering block and item for: {}", name);

        Identifier blockId = Identifier.of(CustomCrops.MOD_ID, name);
        Registry.register(Registries.BLOCK, blockId, block);
        return block;
    }

    private static Block registerBlockWithoutBlockItem(String name, Block block) {
        return Registry.register(Registries.BLOCK, Identifier.of(CustomCrops.MOD_ID, name), block);
    }

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(CustomCrops.MOD_ID, name), block);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(CustomCrops.MOD_ID, name),
                new BlockItem(block, new Item.Settings()
                        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(CustomCrops.MOD_ID, name))).useBlockPrefixedTranslationKey()));
    }
    public static void load() {}
}
