package github.jcsmecabricks.customcrops.datagen;

import github.jcsmecabricks.customcrops.block.ModBlocks;
import github.jcsmecabricks.customcrops.block.custom.*;
import github.jcsmecabricks.customcrops.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootSubProvider;
import net.minecraft.advancements.criterion.StatePropertiesPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import java.util.concurrent.CompletableFuture;

public class ModLootTableProvider extends FabricBlockLootSubProvider {
    public ModLootTableProvider(FabricPackOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(dataOutput, registryLookup);
    }
    HolderLookup.RegistryLookup<Enchantment> impl = this.registries.lookupOrThrow(Registries.ENCHANTMENT);

    @Override
    public void generate() {
        dropSelf(ModBlocks.PASTRY_STATION);
        LootItemBlockStatePropertyCondition.Builder builder2 = LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.TOMATO_CROP)
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(TomatoCropBlock.AGE, TomatoCropBlock.MAX_AGE));
        this.add(ModBlocks.TOMATO_CROP, this.createCropDrops(ModBlocks.TOMATO_CROP, ModItems.TOMATO, ModItems.TOMATO_SEEDS, builder2));

        LootItemBlockStatePropertyCondition.Builder builder = LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.CORN_CROP)
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(CornCropBlock.AGE, CornCropBlock.MAX_AGE));
        this.add(ModBlocks.CORN_CROP, this.createCropDrops(ModBlocks.CORN_CROP, ModItems.CORN, ModItems.CORN_KERNEL, builder));

        this.add(ModBlocks.BLUEBERRY_BUSH,
                block -> this.applyExplosionDecay(
                        block, LootTable.lootTable().withPool(LootPool.lootPool().when(
                                                LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.BLUEBERRY_BUSH).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(BlueberryBushBlock.AGE, 3))
                                        )
                                        .add(LootItem.lootTableItem(ModItems.BLUEBERRIES))
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 3.0F)))
                                        .apply(ApplyBonusCount.addUniformBonusCount(impl.getOrThrow(Enchantments.FORTUNE)))
                        ).withPool(LootPool.lootPool().when(
                                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.BLUEBERRY_BUSH).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(BlueberryBushBlock.AGE, 2))
                                ).add(LootItem.lootTableItem(ModItems.BLUEBERRIES))
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                                .apply(ApplyBonusCount.addUniformBonusCount(impl.getOrThrow(Enchantments.FORTUNE))))));

        this.add(ModBlocks.STRAWBERRY_BUSH,
                block -> this.applyExplosionDecay(
                        block, LootTable.lootTable().withPool(LootPool.lootPool().when(
                                                LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.STRAWBERRY_BUSH).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(StrawberryBushBlock.AGE, 3))
                                        )
                                        .add(LootItem.lootTableItem(ModItems.STRAWBERRIES))
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 3.0F)))
                                        .apply(ApplyBonusCount.addUniformBonusCount(impl.getOrThrow(Enchantments.FORTUNE)))
                        ).withPool(LootPool.lootPool().when(
                                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.STRAWBERRY_BUSH).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(StrawberryBushBlock.AGE, 2))
                                ).add(LootItem.lootTableItem(ModItems.STRAWBERRIES))
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                                .apply(ApplyBonusCount.addUniformBonusCount(impl.getOrThrow(Enchantments.FORTUNE))))));

        this.add(ModBlocks.BLACKBERRY_BUSH,
                block -> this.applyExplosionDecay(
                        block, LootTable.lootTable().withPool(LootPool.lootPool().when(
                                                LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.BLACKBERRY_BUSH).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(BlackberryBushBlock.AGE, 3))
                                        )
                                        .add(LootItem.lootTableItem(ModItems.BLACKBERRIES))
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 3.0F)))
                                        .apply(ApplyBonusCount.addUniformBonusCount(impl.getOrThrow(Enchantments.FORTUNE)))
                        ).withPool(LootPool.lootPool().when(
                                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.BLACKBERRY_BUSH).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(BlackberryBushBlock.AGE, 2))
                                ).add(LootItem.lootTableItem(ModItems.BLACKBERRIES))
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                                .apply(ApplyBonusCount.addUniformBonusCount(impl.getOrThrow(Enchantments.FORTUNE))))));

    }
}
