package github.jcsmecabricks.customcrops.util;

import github.jcsmecabricks.customcrops.item.ModItems;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

public class ModLootTableModifiers {
    private static final Identifier GRASS_BLOCK_ID
            = Identifier.fromNamespaceAndPath("minecraft", "blocks/short_grass");

    public static void modifyLootTables() {
        LootTableEvents.MODIFY.register((key, tableBuilder, source, registry) -> {
            if(GRASS_BLOCK_ID.equals(key.identifier())) {
                LootPool.Builder poolBuilder = LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .when(LootItemRandomChanceCondition.randomChance(0.15f))
                        .add(LootItem.lootTableItem(ModItems.TOMATO_SEEDS))
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 2.0f)).build());

                tableBuilder.pool(poolBuilder.build());
            }

            if(GRASS_BLOCK_ID.equals(key.identifier())) {
                LootPool.Builder poolBuilder = LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .when(LootItemRandomChanceCondition.randomChance(0.16f))
                        .add(LootItem.lootTableItem(ModItems.CORN_KERNEL))
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 2.0f)).build());

                tableBuilder.pool(poolBuilder.build());
            }
        });
    }
}