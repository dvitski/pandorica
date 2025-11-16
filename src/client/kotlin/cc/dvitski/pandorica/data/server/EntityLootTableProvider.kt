package cc.dvitski.pandorica.data.server

import cc.dvitski.pandorica.entity.PandoricaEntityTypes
import cc.dvitski.pandorica.item.PandoricaItems
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricEntityLootTableProvider
import net.minecraft.core.HolderLookup
import net.minecraft.world.level.storage.loot.LootPool
import net.minecraft.world.level.storage.loot.LootTable
import net.minecraft.world.level.storage.loot.entries.LootItem
import net.minecraft.world.level.storage.loot.functions.EnchantedCountIncreaseFunction
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator
import java.util.concurrent.CompletableFuture

class EntityLootTableProvider(output: FabricDataOutput, lookup: CompletableFuture<HolderLookup.Provider>) : FabricEntityLootTableProvider(output, lookup) {
    override fun generate() {
        add(PandoricaEntityTypes.LIQUFIED_SKELETON,
            LootTable.lootTable()
                .withPool(
                    LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1.0f))
                        .add(
                            LootItem.lootTableItem(PandoricaItems.CRUSTED_MAGMA)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(-1.0f, 1.0f)))
                                .apply(
                                    EnchantedCountIncreaseFunction.lootingMultiplier(
                                        registries,
                                        UniformGenerator.between(0.0f, 1.0f)
                                    )
                                )
                        )
                )
                .withPool(
                    LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1.0f))
                        .add(
                            LootItem.lootTableItem(PandoricaItems.LIQUEFIED_BONE)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0f, 2.0f)))
                                .apply(
                                    EnchantedCountIncreaseFunction.lootingMultiplier(
                                        registries,
                                        UniformGenerator.between(0.0f, 1.0f)
                                    )
                                )
                        )
                )
        )
    }
}
