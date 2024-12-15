package github.jcsmecabricks.customcrops.block;

import github.jcsmecabricks.customcrops.CustomCrops;
import github.jcsmecabricks.customcrops.block.custom.BlueberryBushBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ModBlocks {
    public static final Block BLUEBERRY_BUSH = registerBlock("blueberry_bush",
            new BlueberryBushBlock(AbstractBlock.Settings.copy(Blocks.SWEET_BERRY_BUSH)
                    .registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(CustomCrops.MOD_ID, "blueberry_bush")))));


    public static <T extends Block> T register(String name, T block) {
        CustomCrops.LOGGER.info("Registering block and item for: {}", name);

        Identifier blockId = Identifier.of(CustomCrops.MOD_ID, name); // Use the identifier for the block
        Registry.register(Registries.BLOCK, blockId, block);
        Registry.register(Registries.ITEM, blockId, new BlockItem(block, new Item.Settings()));

        return block;
    }
    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(CustomCrops.MOD_ID, name), block);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(CustomCrops.MOD_ID, name),
                new BlockItem(block, new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(CustomCrops.MOD_ID, name)))));
    }
    public static void load() {}
}
