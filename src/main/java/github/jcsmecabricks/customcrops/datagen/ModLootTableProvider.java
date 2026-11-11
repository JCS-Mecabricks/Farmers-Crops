package github.jcsmecabricks.customcrops.datagen;

import github.jcsmecabricks.customcrops.block.ModBlocks;
import github.jcsmecabricks.customcrops.block.custom.*;
import github.jcsmecabricks.customcrops.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootSubProvider;
import net.minecraft.advancements.predicates.StatePropertiesPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.MatchBlock;
import net.minecraft.world.level.storage.loot.providers.number.ints.ContextIntProviders;

import java.util.concurrent.CompletableFuture;

public class ModLootTableProvider extends FabricBlockLootSubProvider {
    public ModLootTableProvider(FabricPackOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        dropSelf(ModBlocks.PASTRY_STATION);

        this.add(ModBlocks.TOMATO_CROP, this.createCropDrops(ModBlocks.TOMATO_CROP, ModItems.TOMATO, ModItems.TOMATO_SEEDS,
                MatchBlock.blockMatches(blocks, ModBlocks.TOMATO_CROP,
                        StatePropertiesPredicate.Builder.properties().hasProperty(TomatoCropBlock.AGE, TomatoCropBlock.MAX_AGE))));

        this.add(ModBlocks.CORN_CROP, this.createCropDrops(ModBlocks.CORN_CROP, ModItems.CORN, ModItems.CORN_KERNEL,
                MatchBlock.blockMatches(blocks, ModBlocks.CORN_CROP,
                        StatePropertiesPredicate.Builder.properties().hasProperty(CornCropBlock.AGE, CornCropBlock.MAX_AGE))));

        this.add(ModBlocks.BLUEBERRY_BUSH,
                block -> this.applyExplosionDecay(
                        block, LootTable.lootTable().withPool(LootPool.lootPool().when(
                                                MatchBlock.blockMatches(blocks, ModBlocks.BLUEBERRY_BUSH,
                                                        StatePropertiesPredicate.Builder.properties()
                                                        .hasProperty(BlueberryBushBlock.AGE, 3)))
                                        .add(LootItem.lootTableItem(ModItems.BLUEBERRIES))
                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(2, 3)))
                                        .apply(ApplyBonusCount.addUniformBonusCount(enchantments.getOrThrow(Enchantments.FORTUNE)))
                        ).withPool(LootPool.lootPool().when(
                                        MatchBlock.blockMatches(blocks, ModBlocks.BLUEBERRY_BUSH,
                                                StatePropertiesPredicate.Builder.properties()
                                                .hasProperty(BlueberryBushBlock.AGE, 2))).add(LootItem.lootTableItem(ModItems.BLUEBERRIES))
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 2)))
                                .apply(ApplyBonusCount.addUniformBonusCount(enchantments.getOrThrow(Enchantments.FORTUNE))))));

        this.add(ModBlocks.STRAWBERRY_BUSH, block -> this.applyExplosionDecay(block,
                LootTable.lootTable().withPool(LootPool.lootPool()
                        .when(MatchBlock.blockMatches(blocks, ModBlocks.STRAWBERRY_BUSH,
                                StatePropertiesPredicate.Builder.properties().hasProperty(StrawberryBushBlock.AGE, 3)))
                        .add(LootItem.lootTableItem(ModItems.STRAWBERRIES))
                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(2, 3)))
                        .apply(ApplyBonusCount.addUniformBonusCount(enchantments.getOrThrow(Enchantments.FORTUNE)))
                ).withPool(LootPool.lootPool().when(MatchBlock.blockMatches(blocks, ModBlocks.STRAWBERRY_BUSH,
                                StatePropertiesPredicate.Builder.properties().hasProperty(StrawberryBushBlock.AGE, 2)))
                        .add(LootItem.lootTableItem(ModItems.STRAWBERRIES))
                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 2)))
                        .apply(ApplyBonusCount.addUniformBonusCount(enchantments.getOrThrow(Enchantments.FORTUNE)))))
        );

        this.add(ModBlocks.BLACKBERRY_BUSH,
                block -> this.applyExplosionDecay(
                        block, LootTable.lootTable().withPool(LootPool.lootPool().when(
                                                MatchBlock.blockMatches(blocks, ModBlocks.BLACKBERRY_BUSH,
                                                        StatePropertiesPredicate.Builder.properties().hasProperty(BlackberryBushBlock.AGE, 3)))
                                        .add(LootItem.lootTableItem(ModItems.BLACKBERRIES))
                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(2, 3)))
                                        .apply(ApplyBonusCount.addUniformBonusCount(enchantments.getOrThrow(Enchantments.FORTUNE)))
                        ).withPool(LootPool.lootPool().when(
                                        MatchBlock.blockMatches(blocks, ModBlocks.BLACKBERRY_BUSH,
                                                StatePropertiesPredicate.Builder.properties().hasProperty(BlackberryBushBlock.AGE, 2))
                                ).add(LootItem.lootTableItem(ModItems.BLACKBERRIES))
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 2)))
                                .apply(ApplyBonusCount.addUniformBonusCount(enchantments.getOrThrow(Enchantments.FORTUNE))))));

    }
}
