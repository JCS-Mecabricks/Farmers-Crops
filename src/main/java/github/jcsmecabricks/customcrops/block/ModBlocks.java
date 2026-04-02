package github.jcsmecabricks.customcrops.block;

import github.jcsmecabricks.customcrops.CustomCrops;
import github.jcsmecabricks.customcrops.block.custom.*;
import java.util.function.Function;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.PushReaction;

public class ModBlocks {
    public static final Block BLUEBERRY_BUSH = registerBlockWithoutBlockItem("blueberry_bush",
            new BlueberryBushBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SWEET_BERRY_BUSH)
                    .setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(CustomCrops.MOD_ID, "blueberry_bush")))));

    public static final Block STRAWBERRY_BUSH = registerBlockWithoutBlockItem("strawberry_bush",
            new StrawberryBushBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SWEET_BERRY_BUSH)
                    .setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(CustomCrops.MOD_ID, "strawberry_bush")))));

    public static final Block BLACKBERRY_BUSH = registerBlockWithoutBlockItem("blackberry_bush",
            new BlackberryBushBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SWEET_BERRY_BUSH)
                    .setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(CustomCrops.MOD_ID, "blackberry_bush")))));

    public static final Block TOMATO_CROP = registerBlockWithoutBlockItem("tomato_crop",
            new TomatoCropBlock(BlockBehaviour.Properties.of().noCollision()
                    .randomTicks().instabreak().sound(SoundType.CROP).pushReaction(PushReaction.DESTROY)
                    .setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(CustomCrops.MOD_ID, "tomato_crop")))));

    public static final Block CORN_CROP = registerBlockWithoutBlockItem("corn_crop",
            new CornCropBlock(BlockBehaviour.Properties.of().noCollision()
                    .randomTicks().instabreak().sound(SoundType.CROP).pushReaction(PushReaction.DESTROY)
                    .setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(CustomCrops.MOD_ID, "corn_crop")))));

    public static final Block PASTRY_STATION = registerBlock("pastry_station", properties ->
            new PastryStationBlock(properties.strength(2f).requiresCorrectToolForDrops()));

    private static Block registerBlock(String name, Function<BlockBehaviour.Properties, Block> function) {
        Block toRegister = function.apply(BlockBehaviour.Properties.of().setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(CustomCrops.MOD_ID, name))));
        registerBlockItem(name, toRegister);
        return Registry.register(BuiltInRegistries.BLOCK, Identifier.fromNamespaceAndPath(CustomCrops.MOD_ID, name), toRegister);
    }


    public static <T extends Block> T register(String name, T block) {
        CustomCrops.LOGGER.info("Registering block and item for: {}", name);

        Identifier blockId = Identifier.fromNamespaceAndPath(CustomCrops.MOD_ID, name);
        Registry.register(BuiltInRegistries.BLOCK, blockId, block);
        return block;
    }

    private static Block registerBlockWithoutBlockItem(String name, Block block) {
        return Registry.register(BuiltInRegistries.BLOCK, Identifier.fromNamespaceAndPath(CustomCrops.MOD_ID, name), block);
    }

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(BuiltInRegistries.BLOCK, Identifier.fromNamespaceAndPath(CustomCrops.MOD_ID, name), block);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(CustomCrops.MOD_ID, name),
                new BlockItem(block, new Item.Properties()
                        .setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(CustomCrops.MOD_ID, name))).useBlockDescriptionPrefix()));
    }
    public static void load() {}
}
