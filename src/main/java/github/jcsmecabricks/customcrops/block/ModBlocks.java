package github.jcsmecabricks.customcrops.block;

import github.jcsmecabricks.customcrops.CustomCrops;
import github.jcsmecabricks.customcrops.block.custom.BlackberryBushBlock;
import github.jcsmecabricks.customcrops.block.custom.BlueberryBushBlock;
import github.jcsmecabricks.customcrops.block.custom.PastryBlock;
import github.jcsmecabricks.customcrops.block.custom.StrawberryBushBlock;
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

    public static final Block STRAWBERRY_BUSH = registerBlock("strawberry_bush",
            new StrawberryBushBlock(AbstractBlock.Settings.copy(Blocks.SWEET_BERRY_BUSH)
                    .registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(CustomCrops.MOD_ID, "strawberry_bush")))));

    public static final Block BLACKBERRY_BUSH = registerBlock("blackberry_bush",
            new BlackberryBushBlock(AbstractBlock.Settings.copy(Blocks.SWEET_BERRY_BUSH)
                    .registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(CustomCrops.MOD_ID, "blackberry_bush")))));

    public static final Block PASTRY_STATION = registerBlock("pastry_station",
            new PastryBlock(AbstractBlock.Settings.create().strength(2f).requiresTool()
                    .registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(CustomCrops.MOD_ID, "pastry_station")))));

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
